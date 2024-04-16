package ProcessorCircuitSimulator.Instructions.LogicInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static Assember.Utils.BinaryOperations.hexString;
import static ProcessorCircuitSimulator.ALU.LogicOperations.bitwiseOr;

/**
 * Ori (OR Immediate) instruction class.
 */
public class OriInstruction extends Instruction {

    /**
     * Constructs a new OriInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public OriInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the Ori (OR Immediate) instruction.
     */
    public void execute() {
        // Get the destination register (rt) and source register (rs)
        int rt = parameters[0];
        int rs = parameters[1];

        // Get the value from the source register (rs) and the immediate value
        String rsValue = registerFile.getRegister(rs);
        String immediate = hexString(parameters[2], 0);

        // Perform bitwise OR operation between the value in rs and the immediate value
        String result = bitwiseOr(rsValue, immediate);

        // Set the result in the destination register (rt)
        registerFile.setRegister(rt, result);
    }
}
