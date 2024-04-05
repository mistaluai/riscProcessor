package Decoders;

import Exceptions.SyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RTypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public RTypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    /**
     * Decodes an instruction to its binary representation.
     * This method takes an assembly instruction as input along with the current address,
     * and returns the binary representation of the instruction.
     *
     * @param instruction The assembly instruction to decode.
     * @param currentAddress The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws SyntaxException if the instruction is unsupported or missing parameters.
     */
    public String decodeInstruction(String instruction, int currentAddress) throws Exception {
        // Compile a regular expression pattern to match registers in the instruction
        Pattern registersPattern = Pattern.compile("[$]+\\d");
        // Create a matcher for the instruction
        Matcher registersMatcher = registersPattern.matcher(instruction);

        // Initialize an array to store register numbers
        int[] registers = new int[3];
        int i = 0;
        // Extract register numbers from the instruction using the matcher
        for (;registersMatcher.find(); i++) {
            registers[i] = Integer.parseInt(registersMatcher.group().substring(1));
        }

        // Check if there are less than 3 registers specified in the instruction
        if (i < 3)
            throw new SyntaxException("["+currentAddress+"] Unsupported or missing parameters in " + instruction);

        // Retrieve opcode, function, and register representations from instructionsOperations
        String opcodeString = instructionsOperations.getOpcode(instruction);
        String funcString = instructionsOperations.getFunction(instruction);

        // Retrieve register names for rd, rs, and rt from instructionsOperations
        int rd = registers[0];
        String rdString = instructionsOperations.getRegister(rd);

        int rs = registers[1];
        String rsString = instructionsOperations.getRegister(rs);

        int rt = registers[2];
        String rtString = instructionsOperations.getRegister(rt);

        // Concatenate opcode, function, and register representations to form the binary instruction
        return opcodeString + funcString + rdString + rsString + rtString;
    }

}
