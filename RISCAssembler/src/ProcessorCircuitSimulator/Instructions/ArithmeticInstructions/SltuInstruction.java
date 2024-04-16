package ProcessorCircuitSimulator.Instructions.ArithmeticInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;
import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.setLessThanUnsigned;

/**
 * Represents a set less than unsigned (SLTU) instruction.
 */
public class SltuInstruction extends Instruction {

    /**
     * Constructs a new SltuInstruction object with the specified register file, data memory, program counter, and parameters.
     *
     * @param registerFile   the register file to be used by the instruction.
     * @param memory         the data memory to be used by the instruction.
     * @param programCounter the program counter to be used by the instruction.
     * @param parameters     the parameters of the instruction.
     */
    public SltuInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the set less than unsigned (SLTU) instruction.
     */
    @Override
    public void execute() {
        int rd = parameters[0];
        int rs = parameters[1];
        int rt = parameters[2];

        String rsValue = registerFile.getRegister(rs);
        String rtValue = registerFile.getRegister(rt);

        String result = setLessThanUnsigned(rsValue, rtValue);

        registerFile.setRegister(rd, result);
    }
}
