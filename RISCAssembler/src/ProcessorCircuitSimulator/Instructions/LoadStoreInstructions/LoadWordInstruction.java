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

        int address = parameters[1] + parameters[2];
        String addressValue = hexString(address, 0);

        String result = memory.loadFromMemory(addressValue);

        registerFile.setRegister(rt, result);
    }
}
