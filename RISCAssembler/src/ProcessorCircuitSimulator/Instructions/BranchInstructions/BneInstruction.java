package ProcessorCircuitSimulator.Instructions.BranchInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

public class BneInstruction extends Instruction {
    public BneInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    public void execute() {
        int rs = parameters[0];
        String rsHex = registerFile.getRegister(rs);
        int rsValue = Integer.parseInt(rsHex, 16);

        int rt = parameters[1];
        String rtHex = registerFile.getRegister(rt);
        int rtValue = Integer.parseInt(rtHex, 16);

        int offset = parameters[2];

        if (rsValue != rtValue) programCounter.offsetCounter(offset);
    }
}
