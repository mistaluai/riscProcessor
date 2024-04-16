package ProcessorCircuitSimulator.DataPath;

import ProcessorCircuitSimulator.Instructions.Instruction;

import java.util.List;

/**
 * Represents the program counter component in a processor's data path.
 */
public class ProgramCounter {
    private int currentInstruction;
    private List<Instruction> instructionMemory;

    /**
     * Constructs a new ProgramCounter object with the initial instruction set to 0.
     */
    public ProgramCounter() {
        currentInstruction = 0;
    }

    /**
     * Offsets the program counter by the specified offset value.
     *
     * @param offset the offset value to adjust the program counter.
     */
    public void offsetCounter(int offset) {
        currentInstruction += offset - 1;
    }

    /**
     * Changes the value of the program counter to the specified new value.
     *
     * @param newValue the new value to set for the program counter.
     */
    public void changeCounter(int newValue) {
        currentInstruction = newValue;
    }

    /**
     * Retrieves the current value of the program counter.
     *
     * @return the current value of the program counter.
     */
    public int getCurrentValue() {
        return currentInstruction;
    }

    /**
     * Retrieves the next instruction from the instruction memory and advances the program counter.
     *
     * @return the next instruction, or null if there are no more instructions.
     */
    public Instruction nextInstruction() {
        Instruction next = null;
        if (currentInstruction < instructionMemory.size()) {
            next = instructionMemory.get(currentInstruction);
            currentInstruction++;
        }
        return next;
    }

    /**
     * Checks if there is a next instruction available in the instruction memory.
     *
     * @return true if there is a next instruction, false otherwise.
     */
    public boolean hasNext() {
        return (currentInstruction < instructionMemory.size() - 1);
    }

    /**
     * Initializes the instruction memory with the provided list of instructions.
     *
     * @param instructions the list of instructions to be stored in the instruction memory.
     */
    public void initializeData(List<Instruction> instructions) {
        instructionMemory = instructions;
    }
}
