package Decoders;

import Exceptions.SyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.BinaryOperations.binaryString;

public class ITypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public ITypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    /**
     * Decodes an instruction with an immediate value to its binary representation.
     * This method takes an assembly instruction with an immediate value as input along
     * with the current address, and returns the binary representation of the instruction.
     *
     * @param instruction The assembly instruction with an immediate value to decode.
     * @param currentAddress The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws SyntaxException if the instruction is unsupported, missing parameters, or
     *                   if the immediate value is not given.
     */
    public String decodeInstruction(String instruction, int currentAddress) throws Exception {
        // Compile a regular expression pattern to match registers in the instruction
        Pattern registersPattern = Pattern.compile("[$]+\\d");
        // Create a matcher for the instruction
        Matcher registersMatcher = registersPattern.matcher(instruction);

        // Initialize an array to store register numbers
        int[] registers = new int[2];
        int i = 0;

        // Extract register numbers from the instruction using the matcher
        for (;registersMatcher.find(); i++) {
            registers[i] = Integer.parseInt(registersMatcher.group().substring(1));
        }

        // Check if there are less than 2 registers specified in the instruction
        if (i < 2)
            throw new SyntaxException("["+currentAddress+"] Unsupported or missing Parameters in " + instruction);

        // Compile a regular expression pattern to match the immediate value in the instruction
        Pattern immediatePattern = Pattern.compile("[^$][\\d]+");
        // Create a matcher for the immediate value
        Matcher immediateMatcher = immediatePattern.matcher(instruction);
        int immediate = 0;

        // Extract the immediate value from the instruction if present
        if (immediateMatcher.find())
            immediate = Integer.parseInt(immediateMatcher.group().substring(1));
        else
            throw new SyntaxException("Immediate value not given in " + instruction);

        // Retrieve opcode and register representations from instructionsOperations
        String opcodeString = instructionsOperations.getOpcode(instruction);
        int rd = registers[0];
        String rdString = instructionsOperations.getRegister(rd);
        int rs = registers[1];
        String rsString = instructionsOperations.getRegister(rs);

        // Retrieve the integer opcode from instructionsOperations
        int integerOpcode = instructionsOperations.getIntegerOpcode(instruction);
        String immediateString = "";

        // Determine how to encode the immediate value based on the opcode
        if (integerOpcode == 7)
            immediateString = binaryString(immediate, 5, 1);
        else
            immediateString = binaryString(immediate, 5, 0);

        // Concatenate opcode, immediate value, and register representations to form the binary instruction
        return opcodeString + immediateString + rsString + rdString;
    }

}
