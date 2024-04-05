package Decoders;

import Exceptions.RangeException;
import Exceptions.SyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.BinaryOperations.binaryString;

public class LoadStoreDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public LoadStoreDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    /**
     * Decodes an instruction with an offset value to its binary representation.
     * This method takes an assembly instruction with an offset value as input along
     * with the current address, and returns the binary representation of the instruction.
     *
     * @param instruction The assembly instruction with an offset value to decode.
     * @param currentAddress The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws Exception if the instruction is unsupported, missing parameters,
     *                   the offset value is out of range, or if the offset value is not found.
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

        // Compile a regular expression pattern to match the offset value in the instruction
        Pattern offsetPattern = Pattern.compile("\\d*[(]");
        // Create a matcher for the offset value
        Matcher offsetMatcher = offsetPattern.matcher(instruction);

        int offset = 0;
        // Extract the offset value from the instruction if present
        if (offsetMatcher.find()) {
            String offsetDecimal = offsetMatcher.group();
            offset = Integer.parseInt(offsetDecimal.substring(0, offsetDecimal.length() - 1));
        } else
            throw new SyntaxException("["+currentAddress+"] Offset value not found in " + instruction);

        // Check if the offset value is within the range -16 to +15 because it is only 5 bit
        if (offset > 15 || offset < -16)
            throw new RangeException("Offset value out of range in " + instruction);

        // Retrieve opcode and register representations from instructionsOperations
        String opcodeString = instructionsOperations.getOpcode(instruction);
        int rd = registers[0];
        String rdString = instructionsOperations.getRegister(rd);
        int rs = registers[1];
        String rsString = instructionsOperations.getRegister(rs);

        // Encode the offset value as a signed 5-bit binary string
        String offsetString = binaryString(offset, 5, 1);

        // Concatenate opcode, offset value, and register representations to form the binary instruction
        return opcodeString + offsetString + rsString + rdString;
    }


}
