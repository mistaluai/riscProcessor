package ProcessorCircuitSimulator.Processor;

import Assember.Utils.SymbolTable;
import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;
import ProcessorCircuitSimulator.Utils.SimulatedInstructionsFactory;

import java.util.ArrayList;
import java.util.List;

public class Processor {
    private RegisterFile registerFile;
    private ProgramCounter programCounter;
    private DataMemory memory;
    private SimulatedInstructionsFactory instructionsFactory;
    private List<Instruction> instructionMemory;

    public Processor(SymbolTable symbolTable) {
        registerFile = new RegisterFile();
        memory = new DataMemory();
        programCounter = new ProgramCounter();
        instructionsFactory = new SimulatedInstructionsFactory(registerFile, programCounter, memory, symbolTable);
        instructionMemory = new ArrayList<>();
    }

    public void addInstruction(String instruction, int instructionAddress) {
        instructionMemory.add(instructionsFactory.brew(instruction, instructionAddress));
    }
    public List<Instruction> getProgram()
    {
        return instructionMemory;
    }
    public void loadProgram() {
        programCounter.initializeData(instructionMemory);
    }
    public boolean performCycle() {
        Instruction next = programCounter.nextInstruction();
        if (next==null)
            return false;

            next.execute();
            return true;
    }
    public int getCurrentInstruction() {
        return programCounter.getCurrentValue();
    }
    public String[] getRegisters() {
        return registerFile.getRegisters();
    }
    public String[][] getMemory() {
        return memory.getMemory();
    }
    public void executeProgram() {
        while (performCycle())
            continue;
    }
}
