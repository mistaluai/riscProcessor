package ProcessorCircuitSimulator.Instructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;

/**
 * Represents an abstract instruction in a processor.
 */
public abstract class Instruction {
    protected DataMemory memory;
    protected RegisterFile registerFile;
    protected ProgramCounter programCounter;
    protected int[] parameters;

    /**
     * Constructs a new Instruction object with the specified register file, data memory, program counter, and parameters.
     *
     * @param registerFile   the register file to be used by the instruction.
     * @param memory         the data memory to be used by the instruction.
     * @param programCounter the program counter to be used by the instruction.
     * @param parameters     the parameters of the instruction.
     */
    public Instruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        this.memory = memory;
        this.registerFile = registerFile;
        this.programCounter = programCounter;
        this.parameters = parameters;
    }

    /**
     * Executes the instruction.
     */
    public abstract void execute();
}
