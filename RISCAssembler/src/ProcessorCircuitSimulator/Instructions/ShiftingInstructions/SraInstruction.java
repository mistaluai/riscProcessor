package ProcessorCircuitSimulator.Instructions.ShiftingInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.ShiftingOperations.sra;

/**
 * SraInstruction class represents the SRA (Arithmetic Right Shift) instruction.
 */
public class SraInstruction extends Instruction {

    /**
     * Constructs a new SraInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public SraInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the SRA instruction.
     */
    public void execute() {
        // Get the destination register (rt), source register (rs), and shift amount
        int rt = parameters[0];
        int rs = parameters[1];
        int shiftAmount = parameters[2];

        // Get the value from the source register (rs)
        String rsValue = registerFile.getRegister(rs);

        // Perform arithmetic right shift (SRA) operation on the value in rs with the specified shift amount
        String result = sra(rsValue, shiftAmount);

        // Set the result in the destination register (rt)
        registerFile.setRegister(rt, result);
    }
}
