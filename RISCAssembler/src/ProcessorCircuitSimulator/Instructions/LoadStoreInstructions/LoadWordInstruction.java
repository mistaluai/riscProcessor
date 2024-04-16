package ProcessorCircuitSimulator.Instructions.LoadStoreInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

/**
 * Load Word (LW) instruction class.
 */
public class LoadWordInstruction extends Instruction {

    /**
     * Constructs a new LoadWordInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public LoadWordInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the Load Word (LW) instruction.
     */
    public void execute() {
        // Extract parameters
        int rt = parameters[0];
        int rs = parameters[1];
        int offset = parameters[2];

        // Calculate memory address to load from
        String rsHex = registerFile.getRegister(rs);
        int rsValue = Integer.parseInt(rsHex, 16);
        int address = rsValue + offset;
        String addressValue = Integer.toHexString(address).toUpperCase();

        // Load data from memory
        String result = memory.loadFromMemory(addressValue);

        // Store result in register
        registerFile.setRegister(rt, result);
    }
}
