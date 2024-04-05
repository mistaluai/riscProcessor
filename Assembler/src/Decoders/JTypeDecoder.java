package Decoders;

import Exceptions.SyntaxException;
import Utils.BinaryOperations;
import Utils.SymbolTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.BinaryOperations.binaryString;

public class JTypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;
    SymbolTable st;

    public JTypeDecoder(SymbolTable st) {
        instructionsOperations = new InstructionsOperations();
        this.st = st;
    }
    /**
     * Decodes a special type of instruction to its binary representation.
     * This method takes a specific type of assembly instruction as input along
     * with the current address, and returns the binary representation of the instruction.
     *
     * @param instruction The assembly instruction to decode.
     * @param currentAddress The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws SyntaxException if the instruction is unsupported, missing parameters,
     *                   or if the immediate value or target register is not found.
     */
    public String decodeInstruction(String instruction, int currentAddress) throws Exception {
        // Get the name of the instruction from instructionsOperations
        String instructionName = instructionsOperations.getInstruction(instruction);

        // Decode instruction based on its name
        if (instructionName.equals("LUI")) {
            // If the instruction is LUI
            // Retrieve the opcode from instructionsOperations
            String opcode = instructionsOperations.getOpcode(instruction);

            // Extract the immediate value from the instruction
            Pattern immediatePattern = Pattern.compile("[ ][\\d]*");
            Matcher immediateMatcher = immediatePattern.matcher(instruction);
            int immediate = 0;
            if (immediateMatcher.find())
                immediate = Integer.parseInt(immediateMatcher.group().substring(1));
            else
                throw new SyntaxException("["+currentAddress+"] Immediate value not given in " + instruction);

            // Encode the immediate value as an 11-bit binary string
            String immediateString = binaryString(immediate, 11, 0);

            // Concatenate opcode and immediate value to form the binary instruction
            return opcode + immediateString;
        }

        if (instructionName.equals("J") || instructionName.equals("JAL")) {
            // If the instruction is J or JAL
            // Retrieve the opcode from instructionsOperations
            String opcode = instructionsOperations.getOpcode(instruction);

            // Extract the label from the instruction
            String label = "";
            if (instructionName.equals("J")) label = instruction.substring(2);
            else if (instructionName.equals("JAL")) label = instruction.substring(4);

            int labelAddress = st.getLabel(label);
            // Calculate the offset relative to the current address
            int offset = labelAddress - currentAddress;

            // Encode the offset value as an 11-bit binary string
            String offsetString = BinaryOperations.binaryString(offset, 11, 1);

            // Concatenate opcode and offset value to form the binary instruction
            return opcode + offsetString;
        }

        if (instructionName.equals("JR")) {
            // If the instruction is JR
            // Retrieve the opcode from instructionsOperations
            String opcode = instructionsOperations.getOpcode(instruction);

            // Extract the target register number from the instruction
            Pattern registersPattern = Pattern.compile("[$]+\\d");
            Matcher registersMatcher = registersPattern.matcher(instruction);

            int rs = 0;
            if (registersMatcher.find())
                rs = Integer.parseInt(registersMatcher.group().substring(1));
            else
                throw new SyntaxException("["+currentAddress+"] Target register not found in " + instruction);

            // Encode the target register number as a 3-bit binary string
            String rsString = BinaryOperations.binaryString(rs, 3, 0);

            // Concatenate opcode, zeros, rs, and zeros to form the binary instruction
            return opcode + "00" + "000" + rsString + "000";
        }

        // If the instruction is not supported or recognized, return an empty string
        return "";
    }




}
