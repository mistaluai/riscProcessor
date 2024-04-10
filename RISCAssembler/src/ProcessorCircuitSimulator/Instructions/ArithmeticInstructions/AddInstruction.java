package ProcessorCircuitSimulator.Instructions.ArithmeticInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.addSignedHexStrings;

public class AddInstruction extends Instruction {
    private int[] registers;

    public AddInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] registers) {
        super(registerFile, memory, programCounter);
        this.registers = registers;
    }

    public void execute() {

        int rd = registers[0];
        int rs = registers[1];
        int rt = registers[2];

        String rsValue = registerFile.getRegister(rs);
        String rtValue = registerFile.getRegister(rd);

        String result = addSignedHexStrings(rsValue, rtValue);

        registerFile.setRegister(rd, result);
    }
}
