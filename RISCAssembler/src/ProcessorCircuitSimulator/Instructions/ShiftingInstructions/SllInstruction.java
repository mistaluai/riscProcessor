package ProcessorCircuitSimulator.Instructions.ShiftingInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.ShiftingOperations.sll;

public class SllInstruction extends Instruction {
    int[] parameters;

    public SllInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter);
        this.parameters = parameters;
    }

    public void execute() {
        int rt = parameters[0];
        int rs = parameters[1];

        String rsValue = registerFile.getRegister(rs);
        int shiftAmount = parameters[2];

        String result = sll(rsValue, shiftAmount);

        registerFile.setRegister(rt, result);
    }
}
