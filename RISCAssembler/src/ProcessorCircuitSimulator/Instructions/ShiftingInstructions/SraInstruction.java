package ProcessorCircuitSimulator.Instructions.ShiftingInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.ShiftingOperations.sra;

public class SraInstruction extends Instruction {
    int[] parameters;

    public SraInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter);
        this.parameters = parameters;
    }

    public void execute() {
        int rt = parameters[0];
        int rs = parameters[1];

        String rsValue = registerFile.getRegister(rs);
        int shiftAmount = parameters[2];

        String result = sra(rsValue, shiftAmount);

        registerFile.setRegister(rt, result);
    }
}
