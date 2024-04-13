package ProcessorCircuitSimulator.DataPath;

import ProcessorCircuitSimulator.Instructions.Instruction;

import java.util.List;

public class ProgramCounter {
    private int currentInstruction;
    private List<Instruction> instructionMemory;

    public ProgramCounter() {
        currentInstruction = 0;
    }

    public void offsetCounter(int offset) {
        currentInstruction += offset - 1;
    }

    public void changeCounter(int newValue) {
        currentInstruction = newValue;
    }

    public int getCurrentValue() {
        return currentInstruction;
    }

    public Instruction nextInstruction() {
        Instruction next = null;
        if (currentInstruction < instructionMemory.size()) {
            next = instructionMemory.get(currentInstruction);
            currentInstruction++;
        }
        return next;
    }

    public boolean hasNext() {
        return (currentInstruction < instructionMemory.size() - 1);
    }

    public void initializeData(List<Instruction> instructions) {
        instructionMemory = instructions;
    }
}
