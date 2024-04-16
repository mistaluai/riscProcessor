/**
 * The BTypeDecoder class decodes branch instructions to their binary representations.
 * It takes a branch assembly instruction as input along with the current address,
 * and returns the binary representation of the instruction.
 */
package Assember.Decoders;

import Assember.Exceptions.RangeException;
import Assember.Exceptions.SyntaxException;
import Assember.Utils.BinaryOperations;
import Assember.Utils.SymbolTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BTypeDecoder implements Decoder {
    SymbolTable st;
    InstructionsOperations instructionsOperations;

    /**
     * Constructs a BTypeDecoder object with the given symbol table.
     *
     * @param st The symbol table object.
     */
    public BTypeDecoder(SymbolTable st) {
        this.st = st;
        instructionsOperations = new InstructionsOperations();
    }

    /**
     * Decodes a branch instruction to its binary representation.
     *
     * @param instruction     The branch assembly instruction to decode.
     * @param currentAddress  The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws SyntaxException if the label is not found in the instruction.
     * @throws RangeException  if the branch range exceeds the supported range.
     */
    public String decodeInstruction(String instruction, int currentAddress) throws SyntaxException, RangeException {
        int[] parameters = extractParameters(instruction, currentAddress);

        int offset = parameters[2];

        String opcode = instructionsOperations.getOpcode(instruction);
        String offsetString = BinaryOperations.binaryString(offset, 5, 1);

        int rs = parameters[0];
        int rt = parameters[1];
        String rtString = instructionsOperations.getRegister(rt);
        String rsString = instructionsOperations.getRegister(rs);

        return opcode + offsetString + rsString + rtString;
    }

    /**
     * Extracts parameters from the branch instruction.
     *
     * @param instruction     The branch assembly instruction.
     * @param currentAddress  The current memory address of the instruction.
     * @return An array containing extracted parameters: rs, rt, and offset.
     * @throws SyntaxException if the label is not found in the instruction.
     * @throws RangeException  if the branch range exceeds the supported range.
     */
    public int[] extractParameters(String instruction, int currentAddress) throws SyntaxException, RangeException {
        int[] output = new int[3];
        int[] registers = instructionsOperations.extractRegisters(instruction, 2, currentAddress);

        output[0] = registers[0];
        output[1] = registers[1];

        Pattern labelPattern = Pattern.compile("[ ][\\w]+");
        Matcher labelMatcher = labelPattern.matcher(instruction);
        String label = "";

        if (labelMatcher.find())
            label = labelMatcher.group().substring(1);
        else
            throw new SyntaxException("["+currentAddress+"] Label not found in " + instruction);

        int labelAddress = st.getLabel(label);
        int offset = labelAddress - currentAddress;

        if (offset < -16 || offset > 15)
            throw new RangeException("["+currentAddress+"] The branch range exceeded in " + instruction);

        output[2] = offset;

        return output;
    }
}
