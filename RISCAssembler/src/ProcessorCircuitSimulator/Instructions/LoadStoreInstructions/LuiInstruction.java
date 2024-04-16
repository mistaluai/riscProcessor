package ProcessorCircuitSimulator.Instructions.LoadStoreInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

/**
 * Load Upper Immediate (LUI) instruction class.
 */
public class LuiInstruction extends Instruction {

    /**
     * Constructs a new LuiInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public LuiInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the Load Upper Immediate (LUI) instruction.
     */
    public void execute() {
        // Extract immediate value from parameters and left shift by 16 bits
        int immediate = parameters[0] << 16;

        // Convert immediate value to hexadecimal string
        String result = Integer.toHexString(immediate).toUpperCase();

        // Set the result in register $1
        registerFile.setRegister(1, result);
    }
}
