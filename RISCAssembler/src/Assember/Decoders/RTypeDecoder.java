/**
 * The RTypeDecoder class decodes instructions to their binary representations.
 * It takes an assembly instruction as input along with the current address,
 * and returns the binary representation of the instruction.
 */
package Assember.Decoders;

public class RTypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    /**
     * Constructs an RTypeDecoder object.
     */
    public RTypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    /**
     * Decodes an instruction to its binary representation.
     *
     * @param instruction     The assembly instruction to decode.
     * @param currentAddress  The current memory address of the instruction.
     * @return The binary representation of the instruction.
     */
    public String decodeInstruction(String instruction, int currentAddress) {
        int[] registers = extractParameters(instruction, currentAddress);

        String opcodeString = instructionsOperations.getOpcode(instruction);
        String funcString = instructionsOperations.getFunction(instruction);

        int rd = registers[0];
        String rdString = instructionsOperations.getRegister(rd);

        int rs = registers[1];
        String rsString = instructionsOperations.getRegister(rs);

        int rt = registers[2];
        String rtString = instructionsOperations.getRegister(rt);

        return opcodeString + funcString + rdString + rsString + rtString;
    }

    /**
     * Extracts registers from the instruction.
     *
     * @param instruction     The assembly instruction.
     * @param currentAddress  The current memory address of the instruction.
     * @return An array containing extracted registers: rd, rs, and rt.
     */
    public int[] extractParameters(String instruction, int currentAddress) {
        return instructionsOperations.extractRegisters(instruction, 3, currentAddress);
    }
}
