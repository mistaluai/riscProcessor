/**
 * The JTypeDecoder class decodes a special type of instructions to their binary representations.
 * It takes a specific type of assembly instruction as input along with the current address,
 * and returns the binary representation of the instruction.
 */
package Assember.Decoders;

import Assember.Exceptions.RangeException;
import Assember.Exceptions.SyntaxException;
import Assember.Utils.SymbolTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Assember.Utils.BinaryOperations.binaryString;

public class JTypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;
    SymbolTable st;

    /**
     * Constructs a JTypeDecoder object with the given symbol table.
     *
     * @param st The symbol table object.
     */
    public JTypeDecoder(SymbolTable st) {
        instructionsOperations = new InstructionsOperations();
        this.st = st;
    }

    /**
     * Decodes a special type of instruction to its binary representation.
     *
     * @param instruction     The assembly instruction to decode.
     * @param currentAddress  The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws SyntaxException if the instruction is unsupported, missing parameters,
     *                        or if the immediate value or target register is not found.
     * @throws RangeException  if the immediate value exceeds the supported range.
     */
    public String decodeInstruction(String instruction, int currentAddress) throws SyntaxException, RangeException {
        String instructionName = instructionsOperations.getInstruction(instruction);

        if (instructionName.equals("LUI")) {
            String opcode = instructionsOperations.getOpcode(instruction);
            int immediate = extractParameters(instruction, currentAddress)[0];
            String immediateString = binaryString(immediate, 11, 0);
            return opcode + immediateString;
        }

        if (instructionName.equals("J") || instructionName.equals("JAL")) {
            String opcode = instructionsOperations.getOpcode(instruction);
            int offset = extractParameters(instruction, currentAddress)[0];
            String offsetString = binaryString(offset, 11, 1);
            return opcode + offsetString;
        }

        if (instructionName.equals("JR")) {
            String opcode = instructionsOperations.getOpcode(instruction);
            int[] registers = extractParameters(instruction, currentAddress);
            String rsString = binaryString(registers[0], 3, 0);
            return opcode + "00" + "000" + rsString + "000";
        }

        return "";
    }

    /**
     * Extracts parameters from the instruction.
     *
     * @param instruction     The assembly instruction.
     * @param currentAddress  The current memory address of the instruction.
     * @return An array containing extracted parameters: immediate value or offset.
     * @throws SyntaxException if the immediate value is not given in the instruction.
     * @throws RangeException  if the immediate value exceeds the supported range.
     */
    public int[] extractParameters(String instruction, int currentAddress) throws SyntaxException, RangeException {
        String instructionName = instructionsOperations.getInstruction(instruction);

        if (instructionName.equals("LUI")) {
            Pattern immediatePattern = Pattern.compile("[ ][\\d]*");
            Matcher immediateMatcher = immediatePattern.matcher(instruction);
            int immediate = 0;
            if (immediateMatcher.find())
                immediate = Integer.parseInt(immediateMatcher.group().substring(1));
            else
                throw new SyntaxException("["+currentAddress+"] Immediate value not given in " + instruction);

            if (immediate >= 2048)
                throw new RangeException("["+currentAddress+"] The immediate range exceeded in " + instruction);

            return new int[]{immediate};
        }

        if (instructionName.equals("J") || instructionName.equals("JAL")) {
            String label = "";
            if (instructionName.equals("J")) label = instruction.substring(2);
            else if (instructionName.equals("JAL")) label = instruction.substring(4);

            label = label.trim();
            int labelAddress = st.getLabel(label);
            int offset = labelAddress - currentAddress;
            return new int[]{offset};
        }

        if (instructionName.equals("JR")) {
            return instructionsOperations.extractRegisters(instruction, 1, currentAddress);
        } else {
            return new int[0];
        }
    }
}
