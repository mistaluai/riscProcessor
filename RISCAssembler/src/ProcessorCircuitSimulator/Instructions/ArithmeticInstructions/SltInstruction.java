package ProcessorCircuitSimulator.Instructions.ArithmeticInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.setLessThan;

public class SltInstruction extends Instruction {
    public SltInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    public void execute() {

        int rd = parameters[0];
        int rs = parameters[1];
        int rt = parameters[2];

        String rsValue = registerFile.getRegister(rs);
        String rtValue = registerFile.getRegister(rt);

        String result = setLessThan(rsValue, rtValue);

        registerFile.setRegister(rd, result);
    }
}
