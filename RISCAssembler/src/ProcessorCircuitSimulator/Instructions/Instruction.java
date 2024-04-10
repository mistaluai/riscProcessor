package ProcessorCircuitSimulator.Instructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;

public abstract class Instruction {
    public DataMemory memory;
    public RegisterFile registerFile;
    public ProgramCounter programCounter;
    public int[] parameters;

    public Instruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        this.memory = memory;
        this.registerFile = registerFile;
        this.programCounter = programCounter;
        this.parameters = parameters;
    }
    public abstract void execute();
}
