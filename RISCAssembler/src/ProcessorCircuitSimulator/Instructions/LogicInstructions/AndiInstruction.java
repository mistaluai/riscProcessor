package ProcessorCircuitSimulator.Instructions.LogicInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static ProcessorCircuitSimulator.ALU.LogicOperations.bitwiseAnd;

/**
 * And Immediate (ANDI) instruction class.
 */
public class AndiInstruction extends Instruction {

    /**
     * Constructs a new AndiInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public AndiInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the And Immediate (ANDI) instruction.
     */
    public void execute() {
        // Get the destination register (rt), source register (rs), and immediate value
        int rt = parameters[0];
        int rs = parameters[1];
        String rsValue = registerFile.getRegister(rs);
        String immediate = Integer.toHexString(parameters[2]).toUpperCase();

        // Perform bitwise AND operation between the value in rs and the immediate value
        String result = bitwiseAnd(rsValue, immediate);

        // Set the result in the destination register (rt)
        registerFile.setRegister(rt, result);
    }
}
