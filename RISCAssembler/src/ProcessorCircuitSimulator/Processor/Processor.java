package ProcessorCircuitSimulator.Processor;

import Assember.Utils.SymbolTable;
import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;
import ProcessorCircuitSimulator.Utils.SimulatedInstructionsFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the processor of the circuit simulator.
 */
public class Processor {
    private RegisterFile registerFile;
    private ProgramCounter programCounter;
    private DataMemory memory;
    private SimulatedInstructionsFactory instructionsFactory;
    private List<Instruction> instructionMemory;
    private int cycleCount;

    /**
     * Constructs a new Processor object with the specified symbol table.
     *
     * @param symbolTable the symbol table containing assembly code information.
     */
    public Processor(SymbolTable symbolTable) {
        registerFile = new RegisterFile();
        memory = new DataMemory();
        programCounter = new ProgramCounter();
        instructionsFactory = new SimulatedInstructionsFactory(registerFile, programCounter, memory, symbolTable);
        instructionMemory = new ArrayList<>();
        cycleCount = 0;
    }

    /**
     * Adds an instruction to the processor's instruction memory.
     *
     * @param instruction       the instruction to be added.
     * @param instructionAddress the address of the instruction.
     */
    public void addInstruction(String instruction, int instructionAddress) {
        instructionMemory.add(instructionsFactory.brew(instruction, instructionAddress));
    }

    /**
     * Retrieves the program's instructions.
     *
     * @return a list of instructions in the program.
     */
    public List<Instruction> getProgram() {
        return instructionMemory;
    }

    /**
     * Loads the program into the program counter.
     */
    public void loadProgram() {
        programCounter.initializeData(instructionMemory);
    }

    /**
     * Performs a single cycle of execution.
     *
     * @return true if the cycle was performed successfully, false otherwise.
     */
    public boolean performCycle() {
        if (cycleCount >= 5000)
            return false;
        Instruction next = programCounter.nextInstruction();
        if (next == null)
            return false;

        next.execute();
        cycleCount++;
        return true;
    }

    /**
     * Retrieves the current instruction address.
     *
     * @return the address of the current instruction.
     */
    public int getCurrentInstruction() {
        return programCounter.getCurrentValue();
    }

    /**
     * Retrieves the values of all registers.
     *
     * @return an array containing the values of all registers.
     */
    public String[] getRegisters() {
        return registerFile.getRegisters();
    }

    /**
     * Retrieves the contents of the memory.
     *
     * @return a 2D array containing the memory addresses and their values.
     */
    public String[][] getMemory() {
        return memory.getMemory();
    }

    /**
     * Executes the program by continuously performing cycles until completion.
     */
    public void executeProgram() {
        while (performCycle())
            continue;
    }
}
