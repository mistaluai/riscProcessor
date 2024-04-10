package ProcessorCircuitSimulator.DataPath;

import ProcessorCircuitSimulator.Instructions.Instruction;

import java.util.List;

public class ProgramCounter {
    private int currentInstruction;
    private List<Instruction> instructionMemory;

    public ProgramCounter() {
        currentInstruction = -1;
    }

    public void offsetCounter(int offset) {
        currentInstruction += offset;
    }

    public void changeCounter(int newValue) {
        currentInstruction = newValue;
    }

    public int getCurrentValue() {
        return currentInstruction;
    }

    public Instruction nextInstruction() {
        currentInstruction++;
        return (currentInstruction < instructionMemory.size())?instructionMemory.get(currentInstruction):null;
    }
}
