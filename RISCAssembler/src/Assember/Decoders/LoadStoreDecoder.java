/**
 * The LoadStoreDecoder class decodes instructions with offset values to their binary representations.
 * It takes an assembly instruction with an offset value as input along with the current address,
 * and returns the binary representation of the instruction.
 */
package Assember.Decoders;

import Assember.Exceptions.RangeException;
import Assember.Exceptions.SyntaxException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Assember.Utils.BinaryOperations.binaryString;

public class LoadStoreDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    /**
     * Constructs a LoadStoreDecoder object.
     */
    public LoadStoreDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    /**
     * Decodes an instruction with an offset value to its binary representation.
     *
     * @param instruction     The assembly instruction with an offset value to decode.
     * @param currentAddress  The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws SyntaxException if the instruction is unsupported, missing parameters,
     *                        or if the offset value is not found.
     * @throws RangeException  if the offset value is out of range.
     */
    public String decodeInstruction(String instruction, int currentAddress) throws SyntaxException, RangeException {
        int[] parameters = extractParameters(instruction, currentAddress);
        int offset = parameters[2];

        String opcodeString = instructionsOperations.getOpcode(instruction);
        int rd = parameters[0];
        String rdString = instructionsOperations.getRegister(rd);
        int rs = parameters[1];
        String rsString = instructionsOperations.getRegister(rs);

        String offsetString = binaryString(offset, 5, 1);

        return opcodeString + offsetString + rsString + rdString;
    }

    /**
     * Extracts parameters from the instruction.
     *
     * @param instruction     The assembly instruction.
     * @param currentAddress  The current memory address of the instruction.
     * @return An array containing extracted parameters: rd, rs, and offset value.
     * @throws SyntaxException if the offset value is not found in the instruction.
     * @throws RangeException  if the offset value is out of range.
     */
    public int[] extractParameters(String instruction, int currentAddress) throws SyntaxException, RangeException {
        int[] output = new int[3];
        int[] registers = instructionsOperations.extractRegisters(instruction, 2, currentAddress);
        output[0] = registers[0];
        output[1] = registers[1];

        Pattern offsetPattern = Pattern.compile("[-| ]\\d*[(]");
        Matcher offsetMatcher = offsetPattern.matcher(instruction);

        String offsetString = "";
        if (offsetMatcher.find()) {
            String offsetDecimal = offsetMatcher.group();
            offsetString = offsetDecimal.substring(0, offsetDecimal.length() - 1);
            if (offsetString.charAt(0) == ' ')
                offsetString = offsetString.substring(1);
        } else
            throw new SyntaxException("["+currentAddress+"] Offset value not found in " + instruction);

        int offset = Integer.parseInt(offsetString);

        if (offset > 15 || offset < -16)
            throw new RangeException("["+currentAddress+"] Offset value out of range in " + instruction);

        output[2] = offset;

        return output;
    }
}
