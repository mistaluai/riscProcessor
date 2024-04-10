package ProcessorCircuitSimulator.Instructions.LogicInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static Assember.Utils.BinaryOperations.hexString;
import static ProcessorCircuitSimulator.ALU.LogicOperations.bitwiseAnd;

public class AndiInstruction extends Instruction {
    public AndiInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    public void execute() {
        int rt = parameters[0];
        int rs = parameters[1];

        String rsValue = registerFile.getRegister(rs);
        String immediate = hexString(parameters[2], 0);

        String result = bitwiseAnd(rsValue, immediate);

        registerFile.setRegister(rt, result);
    }
}
