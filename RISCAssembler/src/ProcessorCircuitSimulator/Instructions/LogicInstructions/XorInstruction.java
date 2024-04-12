package ProcessorCircuitSimulator.Instructions.LogicInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.LogicOperations.bitwiseXor;

public class XorInstruction extends Instruction {
    public XorInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    public void execute() {

        int rd = parameters[0];
        int rs = parameters[1];
        int rt = parameters[2];

        String rsValue = registerFile.getRegister(rs);
        String rtValue = registerFile.getRegister(rt);

        String result = bitwiseXor(rsValue, rtValue);

        registerFile.setRegister(rd, result);

    }
}
