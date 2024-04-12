package ProcessorCircuitSimulator.Utils;

import Assember.Decoders.*;
import Assember.Utils.SymbolTable;
import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.ArithmeticInstructions.*;
import ProcessorCircuitSimulator.Instructions.BranchInstructions.BeqInstruction;
import ProcessorCircuitSimulator.Instructions.BranchInstructions.BgeInstruction;
import ProcessorCircuitSimulator.Instructions.BranchInstructions.BltInstruction;
import ProcessorCircuitSimulator.Instructions.BranchInstructions.BneInstruction;
import ProcessorCircuitSimulator.Instructions.Instruction;
import ProcessorCircuitSimulator.Instructions.JumpInstructions.JumpInstruction;
import ProcessorCircuitSimulator.Instructions.JumpInstructions.JumpLinkInstruction;
import ProcessorCircuitSimulator.Instructions.JumpInstructions.JumpRegisterInstruction;
import ProcessorCircuitSimulator.Instructions.LoadStoreInstructions.LoadWordInstruction;
import ProcessorCircuitSimulator.Instructions.LoadStoreInstructions.LuiInstruction;
import ProcessorCircuitSimulator.Instructions.LoadStoreInstructions.StoreWordInstruction;
import ProcessorCircuitSimulator.Instructions.LogicInstructions.*;
import ProcessorCircuitSimulator.Instructions.ShiftingInstructions.RorInstruction;
import ProcessorCircuitSimulator.Instructions.ShiftingInstructions.SllInstruction;
import ProcessorCircuitSimulator.Instructions.ShiftingInstructions.SraInstruction;
import ProcessorCircuitSimulator.Instructions.ShiftingInstructions.SrlInstruction;

public class SimulatedInstructionsFactory {
    private RegisterFile registerFile;
    private ProgramCounter programCounter;
    private DataMemory memory;
    private SymbolTable symbolTable;
    private InstructionsOperations instructionsOperations;

    public SimulatedInstructionsFactory(RegisterFile registerFile, ProgramCounter programCounter, DataMemory memory, SymbolTable symbolTable) {
        this.registerFile = registerFile;
        this.programCounter = programCounter;
        this.memory = memory;
        this.symbolTable = symbolTable;
        instructionsOperations = new InstructionsOperations();
    }

    public Instruction brew(String instruction, int instructionAddress) {
        int[] parameters = prepareParameters(instruction, instructionAddress);
        instruction = instructionsOperations.getInstruction(instruction);

        switch (instruction) {
            case "ADDI":
                return new AddiInstruction(registerFile, memory, programCounter, parameters);
            case "SUB":
                return new SubInstruction(registerFile, memory, programCounter, parameters);
            case "ADD":
                return new AddInstruction(registerFile, memory, programCounter, parameters);
            case "ORI":
                return new OriInstruction(registerFile, memory, programCounter, parameters);
            case "XORI":
                return new XoriInstruction(registerFile, memory, programCounter, parameters);
            case "ANDI":
                return new AndiInstruction(registerFile, memory, programCounter, parameters);
            case "AND":
                return new AndInstruction(registerFile, memory, programCounter, parameters);
            case "NOR":
                return new NorInstruction(registerFile, memory, programCounter, parameters);
            case "XOR":
                return new XorInstruction(registerFile, memory, programCounter, parameters);
            case "OR":
                return new OrInstruction(registerFile, memory, programCounter, parameters);
            case "ROR":
                return new RorInstruction(registerFile, memory, programCounter, parameters);
            case "SRA":
                return new SraInstruction(registerFile, memory, programCounter, parameters);
            case "SRL":
                return new SrlInstruction(registerFile, memory, programCounter, parameters);
            case "SLL":
                return new SllInstruction(registerFile, memory, programCounter, parameters);
            case "LUI":
                return new LuiInstruction(registerFile, memory, programCounter, parameters);
            case "SW":
                return new StoreWordInstruction(registerFile, memory, programCounter, parameters);
            case "LW":
                return new LoadWordInstruction(registerFile, memory, programCounter, parameters);
            case "SLT":
                return new SltInstruction(registerFile, memory, programCounter, parameters);
            case "SLTU":
                return new SltuInstruction(registerFile, memory, programCounter, parameters);
            case "BEQ":
                return new BeqInstruction(registerFile, memory, programCounter, parameters);
            case "BNE":
                return new BneInstruction(registerFile, memory, programCounter, parameters);
            case "BLT":
                return new BltInstruction(registerFile, memory, programCounter, parameters);
            case "BGE":
                return new BgeInstruction(registerFile, memory, programCounter, parameters);
            case "J":
                return new JumpInstruction(registerFile, memory, programCounter, parameters);
            case "JAL":
                return new JumpLinkInstruction(registerFile, memory, programCounter, parameters);
            case "JR":
                return new JumpRegisterInstruction(registerFile, memory, programCounter, parameters);
        }
    return null;
    }


    public int[] prepareParameters(String instruction, int instructionAddress) {

        RTypeDecoder rTypeDecoder = new RTypeDecoder();
        ITypeDecoder iTypeDecoder = new ITypeDecoder();
        LoadStoreDecoder loadStoreDecoder = new LoadStoreDecoder();
        BTypeDecoder bTypeDecoder = new BTypeDecoder(symbolTable);
        JTypeDecoder jTypeDecoder = new JTypeDecoder(symbolTable);
        int[] parameters = new int[0];
        switch (instructionsOperations.getInstructionType(instruction)) {
            case RType:
                parameters = rTypeDecoder.extractParameters(instruction, instructionAddress);
                break;
            case IType:
                parameters = iTypeDecoder.extractParameters(instruction, instructionAddress);
                break;
            case JType:
                parameters = jTypeDecoder.extractParameters(instruction, instructionAddress);
                break;
            case LoadStoreType:
                parameters = loadStoreDecoder.extractParameters(instruction, instructionAddress);
                break;
            case BType:
                parameters = bTypeDecoder.extractParameters(instruction, instructionAddress);
                break;
        }
        return parameters;
    }
}
