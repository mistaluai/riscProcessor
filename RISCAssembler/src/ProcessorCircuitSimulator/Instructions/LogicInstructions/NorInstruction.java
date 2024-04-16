package ProcessorCircuitSimulator.Instructions.LogicInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.LogicOperations.bitwiseNor;

/**
 * Nor (NOR) instruction class.
 */
public class NorInstruction extends Instruction {

    /**
     * Constructs a new NorInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public NorInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the Nor (NOR) instruction.
     */
    public void execute() {
        // Get the destination register (rd), source register (rs), and target register (rt)
        int rd = parameters[0];
        int rs = parameters[1];
        int rt = parameters[2];

        // Get the values from the source and target registers
        String rsValue = registerFile.getRegister(rs);
        String rtValue = registerFile.getRegister(rt);

        // Perform bitwise NOR operation between the values in rs and rt
        String result = bitwiseNor(rsValue, rtValue);

        // Set the result in the destination register (rd)
        registerFile.setRegister(rd, result);
    }
}
