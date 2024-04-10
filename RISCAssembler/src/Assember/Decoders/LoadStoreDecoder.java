package Assember.Decoders;


import Assember.Exceptions.RangeException;
import Assember.Exceptions.SyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Assember.Utils.BinaryOperations.binaryString;


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
    public String decodeInstruction(String instruction, int currentAddress) {

        int[] parameters = extractParameters(instruction, currentAddress);

        int offset = parameters[2];

        // Retrieve opcode and register representations from instructionsOperations
        String opcodeString = instructionsOperations.getOpcode(instruction);
        int rd = parameters[0];
        String rdString = instructionsOperations.getRegister(rd);
        int rs = parameters[1];
        String rsString = instructionsOperations.getRegister(rs);

        // Encode the offset value as a signed 5-bit binary string
        String offsetString = binaryString(offset, 5, 1);

        // Concatenate opcode, offset value, and register representations to form the binary instruction
        return opcodeString + offsetString + rsString + rdString;
    }

    public int[] extractParameters(String instruction, int currentAddress) {
        int[] output = new int[3];
        int[] registers = instructionsOperations.extractRegisters(instruction, 2, currentAddress);
        output[0] = registers[0];
        output[1] = registers[1];

        // Compile a regular expression pattern to match the offset value in the instruction
        Pattern offsetPattern = Pattern.compile("[-| ]\\d*[(]");
        // Create a matcher for the offset value
        Matcher offsetMatcher = offsetPattern.matcher(instruction);

        String offsetString = "";
        // Extract the offset value from the instruction if present
        if (offsetMatcher.find()) {
            String offsetDecimal = offsetMatcher.group();
            offsetString = offsetDecimal.substring(0, offsetDecimal.length() - 1);
            if (offsetString.charAt(0) == ' ')
                offsetString = offsetString.substring(1);
        } else
            throw new SyntaxException("["+currentAddress+"] Offset value not found in " + instruction);
        int offset = Integer.parseInt(offsetString);
        //System.out.println(offset);
        // Check if the offset value is within the range -16 to +15 because it is only 5 bit
        if (offset > 15 || offset < -16)
            throw new RangeException("Offset value out of range in " + instruction);

        output[2] = offset;

        return output;

    }


}
