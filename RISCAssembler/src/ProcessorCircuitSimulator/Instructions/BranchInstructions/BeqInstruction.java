package ProcessorCircuitSimulator.Instructions.BranchInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

/**
 * Branch Equal (BEQ) instruction class.
 */
public class BeqInstruction extends Instruction {

    /**
     * Constructs a new BeqInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public BeqInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the BEQ instruction.
     */
    public void execute() {
        int rs = parameters[0];
        String rsHex = registerFile.getRegister(rs);
        int rsValue = Integer.parseInt(rsHex, 16);

        int rt = parameters[1];
        String rtHex = registerFile.getRegister(rt);
        int rtValue = Integer.parseInt(rtHex, 16);

        int offset = parameters[2];

        if (rsValue == rtValue) {
            programCounter.offsetCounter(offset);
        }
    }
}
