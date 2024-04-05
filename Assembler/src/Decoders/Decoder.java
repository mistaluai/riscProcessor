package Decoders;

public interface Decoder {
    public String decodeInstruction(String instruction, int currentAddress) throws Exception;
}
