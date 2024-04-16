package ProcessorCircuitSimulator.Instructions.ShiftingInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.ShiftingOperations.ror;

/**
 * RorInstruction class represents the ROR (Rotate Right) instruction.
 */
public class RorInstruction extends Instruction {

    /**
     * Constructs a new RorInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public RorInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the ROR instruction.
     */
    public void execute() {
        // Get the destination register (rt), source register (rs), and shift amount
        int rt = parameters[0];
        int rs = parameters[1];
        int shiftAmount = parameters[2];

        // Get the value from the source register (rs)
        String rsValue = registerFile.getRegister(rs);

        // Perform rotate right (ROR) operation on the value in rs with the specified shift amount
        String result = ror(rsValue, shiftAmount);

        // Set the result in the destination register (rt)
        registerFile.setRegister(rt, result);
    }
}
