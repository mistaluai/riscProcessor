package ProcessorCircuitSimulator.Instructions.JumpInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

/**
 * Jump Register (JR) instruction class.
 */
public class JumpRegisterInstruction extends Instruction {

    /**
     * Constructs a new JumpRegisterInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public JumpRegisterInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the Jump Register (JR) instruction.
     */
    public void execute() {
        // Get the target address from the specified register
        int rs = parameters[0];
        String rsHex = registerFile.getRegister(rs);
        int rsValue = Integer.parseInt(rsHex, 16);

        // Change the program counter to the target address
        programCounter.changeCounter(rsValue);
    }
}
