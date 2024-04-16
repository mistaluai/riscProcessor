package ProcessorCircuitSimulator.Instructions.ShiftingInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.ShiftingOperations.srl;

/**
 * SrlInstruction class represents the SRL (Logical Right Shift) instruction.
 */
public class SrlInstruction extends Instruction {

    /**
     * Constructs a new SrlInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public SrlInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the SRL instruction.
     */
    public void execute() {
        // Get the destination register (rt), source register (rs), and shift amount
        int rt = parameters[0];
        int rs = parameters[1];
        int shiftAmount = parameters[2];

        // Get the value from the source register (rs)
        String rsValue = registerFile.getRegister(rs);

        // Perform logical right shift (SRL) operation on the value in rs with the specified shift amount
        String result = srl(rsValue, shiftAmount);

        // Set the result in the destination register (rt)
        registerFile.setRegister(rt, result);
    }
}
