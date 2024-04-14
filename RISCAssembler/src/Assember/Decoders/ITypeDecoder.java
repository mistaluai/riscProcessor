package Assember.Decoders;


import Assember.Exceptions.RangeException;
import Assember.Exceptions.SyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Assember.Utils.BinaryOperations.binaryString;


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
    public String decodeInstruction(String instruction, int currentAddress) {
        // Extracts two registers from the given assembly instruction 'instruction'
// at the current address 'currentAddress' using the instructionsOperations object.
// The extracted registers will be used for further processing.
        int[] parameters = extractParameters(instruction, currentAddress);

        int immediate = parameters[2];

        //System.out.println(immediate);
        // Retrieve opcode and register representations from instructionsOperations
        String opcodeString = instructionsOperations.getOpcode(instruction);
        int rd = parameters[0];
        String rdString = instructionsOperations.getRegister(rd);
        int rs = parameters[1];
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

    public int[] extractParameters(String instruction, int currentAddress) {

        int[] output = new int[3];
        int[] registers = instructionsOperations.extractRegisters(instruction, 2, currentAddress);
        output[0] = registers[0];
        output[1] = registers[1];

        Pattern immediatePattern = Pattern.compile("[ |-][\\d]+");
        // Create a matcher for the immediate value
        Matcher immediateMatcher = immediatePattern.matcher(instruction);
        String immediateString = "";

        // Extract the immediate value from the instruction if present
        if (immediateMatcher.find())
            immediateString = immediateMatcher.group();
        else
            throw new SyntaxException("Immediate value not given in " + instruction);
        if (immediateString.charAt(0) == ' ')
            immediateString = immediateString.substring(1);

        int immediate = Integer.parseInt(immediateString);

        if ((immediate < -16 || immediate > 15) && instructionsOperations.getIntegerOpcode(instruction) == 7)
            throw new RangeException("["+currentAddress+"] The immediate range exceeded in " + instruction);
        else if ((immediate < 0 || immediate > 31) && (instructionsOperations.getIntegerOpcode(instruction) >= 4 && instructionsOperations.getIntegerOpcode(instruction) <= 6))
            throw new RangeException("["+currentAddress+"] The immediate range exceeded in " + instruction);
        else if ((immediate < 0 || immediate > 15) && (instructionsOperations.getIntegerOpcode(instruction) >= 8 && instructionsOperations.getIntegerOpcode(instruction) <= 11))
            throw new RangeException("["+currentAddress+"] The immediate range exceeded in " + instruction);

        output[2] = immediate;

        return output;
    }

}
