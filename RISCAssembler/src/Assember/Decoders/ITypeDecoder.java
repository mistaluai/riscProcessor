/**
 * The ITypeDecoder class decodes instructions with immediate values to their binary representations.
 * It takes an assembly instruction with an immediate value as input along with the current address,
 * and returns the binary representation of the instruction.
 */
package Assember.Decoders;

import Assember.Exceptions.RangeException;
import Assember.Exceptions.SyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Assember.Utils.BinaryOperations.binaryString;

public class ITypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    /**
     * Constructs an ITypeDecoder object.
     */
    public ITypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    /**
     * Decodes an instruction with an immediate value to its binary representation.
     *
     * @param instruction     The assembly instruction with an immediate value to decode.
     * @param currentAddress  The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws SyntaxException if the instruction is unsupported, missing parameters, or
     *                        if the immediate value is not given.
     * @throws RangeException  if the immediate value exceeds the supported range.
     */
    public String decodeInstruction(String instruction, int currentAddress) throws SyntaxException, RangeException {
        String instructionName = instructionsOperations.getInstruction(instruction);
        if (instructionName.equals("DRAW"))
            return handleDrawInstruction(instruction, currentAddress);
        int[] parameters = extractParameters(instruction, currentAddress);

        int immediate = parameters[2];

        String opcodeString = instructionsOperations.getOpcode(instruction);
        int rd = parameters[0];
        String rdString = instructionsOperations.getRegister(rd);
        int rs = parameters[1];
        String rsString = instructionsOperations.getRegister(rs);

        int integerOpcode = instructionsOperations.getIntegerOpcode(instruction);
        String immediateString = "";

        if (integerOpcode == 7)
            immediateString = binaryString(immediate, 5, 1);
        else
            immediateString = binaryString(immediate, 5, 0);

        return opcodeString + immediateString + rsString + rdString;
    }

    /**
     * Extracts parameters from the instruction.
     *
     * @param instruction     The assembly instruction.
     * @param currentAddress  The current memory address of the instruction.
     * @return An array containing extracted parameters: rd, rs, and immediate value.
     * @throws SyntaxException if the immediate value is not given in the instruction.
     * @throws RangeException  if the immediate value exceeds the supported range.
     */
    public int[] extractParameters(String instruction, int currentAddress) throws SyntaxException, RangeException {
        int[] output = new int[3];
        int[] registers = instructionsOperations.extractRegisters(instruction, 2, currentAddress);
        output[0] = registers[0];
        output[1] = registers[1];

        Pattern immediatePattern = Pattern.compile("[ |-][\\d]+");
        Matcher immediateMatcher = immediatePattern.matcher(instruction);
        String immediateString = "";

        if (immediateMatcher.find())
            immediateString = immediateMatcher.group();
        else
            throw new SyntaxException("Immediate value not given in " + instruction);
        if (immediateString.charAt(0) == ' ')
            immediateString = immediateString.substring(1);

        int immediate = Integer.parseInt(immediateString);

        int integerOpcode = instructionsOperations.getIntegerOpcode(instruction);
        if ((immediate < -16 || immediate > 15) && integerOpcode == 7)
            throw new RangeException("["+currentAddress+"] The immediate range exceeded in " + instruction);
        else if ((immediate < 0 || immediate > 31) && (integerOpcode >= 4 && integerOpcode <= 6))
            throw new RangeException("["+currentAddress+"] The immediate range exceeded in " + instruction);
        else if ((immediate < 0 || immediate > 15) && (integerOpcode >= 8 && integerOpcode <= 11))
            throw new RangeException("["+currentAddress+"] The immediate range exceeded in " + instruction);

        output[2] = immediate;

        return output;
    }

    /**
     * Handles the DRAW instruction separately.
     *
     * @param instruction     The DRAW assembly instruction.
     * @param currentAddress  The current memory address of the instruction.
     * @return The binary representation of the DRAW instruction.
     * @throws SyntaxException if the column value is not within the supported range.
     */
    private String handleDrawInstruction(String instruction, int currentAddress) throws SyntaxException {
        int[] registers = instructionsOperations.extractRegisters(instruction, 1, currentAddress);

        int rs = registers[0];
        String rsString = instructionsOperations.getRegister(rs);

        Pattern immediatePattern = Pattern.compile("[ |-][\\d]+");
        Matcher immediateMatcher = immediatePattern.matcher(instruction);
        String immediateString = "";

        if (immediateMatcher.find())
            immediateString = immediateMatcher.group();
        else
            throw new SyntaxException("Immediate value not given in " + instruction);
        if (immediateString.charAt(0) == ' ')
            immediateString = immediateString.substring(1);

        int immediate = Integer.parseInt(immediateString);

        if (immediate > 15 || immediate < 0)
            throw new SyntaxException("Unknown column value in " + instruction);

        immediateString = binaryString(immediate, 5, 0);

        String opcode = instructionsOperations.getOpcode(instruction);
        return opcode + immediateString + rsString + "000";
    }
}
