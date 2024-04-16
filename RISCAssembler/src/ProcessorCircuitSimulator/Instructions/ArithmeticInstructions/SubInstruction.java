package ProcessorCircuitSimulator.Instructions.ArithmeticInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;
import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.subtractSignedHexStrings;

/**
 * Represents a subtraction (SUB) instruction.
 */
public class SubInstruction extends Instruction {

    /**
     * Constructs a new SubInstruction object with the specified register file, data memory, program counter, and parameters.
     *
     * @param registerFile   the register file to be used by the instruction.
     * @param memory         the data memory to be used by the instruction.
     * @param programCounter the program counter to be used by the instruction.
     * @param parameters     the parameters of the instruction.
     */
    public SubInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the subtraction (SUB) instruction.
     */
    @Override
    public void execute() {
        int rd = parameters[0];
        int rs = parameters[1];
        int rt = parameters[2];

        String rsValue = registerFile.getRegister(rs);
        String rtValue = registerFile.getRegister(rt);

        String result = subtractSignedHexStrings(rsValue, rtValue);

        registerFile.setRegister(rd, result);
    }
}
