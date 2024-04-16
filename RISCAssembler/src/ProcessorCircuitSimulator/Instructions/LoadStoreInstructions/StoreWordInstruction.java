package ProcessorCircuitSimulator.Instructions.LoadStoreInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

/**
 * Store Word (SW) instruction class.
 */
public class StoreWordInstruction extends Instruction {

    /**
     * Constructs a new StoreWordInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public StoreWordInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the Store Word (SW) instruction.
     */
    public void execute() {
        // Get the value from register rt
        int rt = parameters[0];
        String rtValue = registerFile.getRegister(rt);

        // Get the base address from register rs and calculate the memory address
        int rs = parameters[1];
        String rsHex = registerFile.getRegister(rs);
        int rsValue = Integer.parseInt(rsHex, 16);
        int address = rsValue + parameters[2];

        // Convert the memory address to hexadecimal string
        String addressValue = Integer.toHexString(address).toUpperCase();

        // Store the value in memory at the calculated address
        memory.storeInMemory(addressValue, rtValue);
    }
}
