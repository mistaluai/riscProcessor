package ProcessorCircuitSimulator.Instructions.ShiftingInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.ShiftingOperations.sll;

/**
 * SllInstruction class represents the SLL (Logical Left Shift) instruction.
 */
public class SllInstruction extends Instruction {

    /**
     * Constructs a new SllInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public SllInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the SLL instruction.
     */
    public void execute() {
        // Get the destination register (rt), source register (rs), and shift amount
        int rt = parameters[0];
        int rs = parameters[1];
        int shiftAmount = parameters[2];

        // Get the value from the source register (rs)
        String rsValue = registerFile.getRegister(rs);

        // Perform logical left shift (SLL) operation on the value in rs with the specified shift amount
        String result = sll(rsValue, shiftAmount);

        // Set the result in the destination register (rt)
        registerFile.setRegister(rt, result);
    }
}
