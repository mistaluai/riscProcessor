package ProcessorCircuitSimulator.Instructions.LogicInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.LogicOperations.bitwiseNor;

public class NorInstruction extends Instruction {
    private int[] registers;

    public NorInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] registers) {
        super(registerFile, memory, programCounter);
        this.registers = registers;
    }

    public void execute() {

        int rd = registers[0];
        int rs = registers[1];
        int rt = registers[2];

        String rsValue = registerFile.getRegister(rs);
        String rtValue = registerFile.getRegister(rt);

        String result = bitwiseNor(rsValue, rtValue);

        registerFile.setRegister(rd, result);

    }
}