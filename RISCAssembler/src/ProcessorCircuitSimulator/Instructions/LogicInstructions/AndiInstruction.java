package ProcessorCircuitSimulator.Instructions.LogicInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static Assember.Utils.BinaryOperations.hexString;
import static ProcessorCircuitSimulator.ALU.LogicOperations.bitwiseAnd;

public class AndiInstruction extends Instruction {
    int[] parameters;

    public AndiInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter);
        this.parameters = parameters;
    }

    public void execute() {
        int rd = parameters[0];
        int rt = parameters[1];

        String rtValue = registerFile.getRegister(rt);
        String immediate = hexString(parameters[2], 0);

        String result = bitwiseAnd(rtValue, immediate);

        registerFile.setRegister(rd, result);
    }
}
