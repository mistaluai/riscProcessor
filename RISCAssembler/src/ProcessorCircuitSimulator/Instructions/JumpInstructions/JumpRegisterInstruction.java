package ProcessorCircuitSimulator.Instructions.JumpInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

public class JumpRegisterInstruction extends Instruction {


    public JumpRegisterInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    public void execute() {
        int rs = parameters[0];
        String rsHex = registerFile.getRegister(rs);
        int rsValue = Integer.parseInt(rsHex, 16);
        programCounter.changeCounter(rsValue);
    }
}
