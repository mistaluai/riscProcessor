package Decoders;

import Exceptions.SyntaxException;

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
    public String decodeInstruction(String instruction, int currentAddress) {

        // Extracts three registers from the given assembly instruction 'instruction'
// at the current address 'currentAddress' using the instructionsOperations object.
// The extracted registers will be used for further processing.
        int[] registers = instructionsOperations.extractRegisters(instruction, 3, currentAddress);

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
