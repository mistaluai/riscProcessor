package ProcessorCircuitSimulator.Instructions.LoadStoreInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static Assember.Utils.BinaryOperations.hexString;

public class LoadWordInstruction extends Instruction {
    public LoadWordInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    public void execute() {
        int rt = parameters[0];

        int rs = parameters[1];
        String rsHex = registerFile.getRegister(rs);
        int rsValue = Integer.parseInt(rsHex, 16);
        int address =  rsValue + parameters[2];
        String addressValue = hexString(address, 0);

        String result = memory.loadFromMemory(addressValue);

        registerFile.setRegister(rt, result);
    }
}
