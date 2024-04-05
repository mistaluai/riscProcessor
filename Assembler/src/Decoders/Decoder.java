package Decoders;

/**
 * The Decoder interface represents an object capable of decoding assembly instructions
 * into their binary representations.
 */
public interface Decoder {

    /**
     * Decodes an assembly instruction to its binary representation.
     *
     * @param instruction The assembly instruction to decode.
     * @param currentAddress The current memory address of the instruction.
     * @return The binary representation of the instruction.
     * @throws Exception if an error occurs during decoding.
     */
    public String decodeInstruction(String instruction, int currentAddress) throws Exception;
}
