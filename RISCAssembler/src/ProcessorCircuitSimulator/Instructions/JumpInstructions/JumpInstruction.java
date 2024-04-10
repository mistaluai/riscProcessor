package ProcessorCircuitSimulator.Instructions.JumpInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

public class JumpInstruction extends Instruction {
    int[] parameters;

    public JumpInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter);
        this.parameters = parameters;
    }

    public void execute() {
        int offset = parameters[0];
        programCounter.offsetCounter(offset);
    }
}
