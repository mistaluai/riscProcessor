package ProcessorCircuitSimulator.Instructions.LoadStoreInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static Assember.Utils.BinaryOperations.hexString;

public class StoreWordInstruction extends Instruction {
    int[] parameters;

    public StoreWordInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter);
        this.parameters = parameters;
    }

    public void execute() {
        int rt = parameters[0];
        String rtValue = registerFile.getRegister(rt);

        int address = parameters[1] + parameters[2];
        String addressValue = hexString(address, 0);

        memory.storeInMemory(addressValue, rtValue);
    }
}
