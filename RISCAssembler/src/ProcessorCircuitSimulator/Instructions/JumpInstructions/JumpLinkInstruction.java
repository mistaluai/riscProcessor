package ProcessorCircuitSimulator.Instructions.JumpInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static Assember.Utils.BinaryOperations.hexString;

/**
 * Jump and Link (JAL) instruction class.
 */
public class JumpLinkInstruction extends Instruction {

    /**
     * Constructs a new JumpLinkInstruction with the specified components and parameters.
     *
     * @param registerFile   the register file of the processor.
     * @param memory         the data memory of the processor.
     * @param programCounter the program counter of the processor.
     * @param parameters     the parameters of the instruction.
     */
    public JumpLinkInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the Jump and Link (JAL) instruction.
     */
    public void execute() {
        // Save return address (address of the next instruction) in register $ra (register 7)
        int returnValue = programCounter.getCurrentValue();
        String returnHex = hexString(returnValue, 0);
        registerFile.setRegister(7, returnHex);

        // Offset the program counter by the specified amount
        int offset = parameters[0];
        programCounter.offsetCounter(offset);
    }
}
