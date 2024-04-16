package ProcessorCircuitSimulator.Instructions.ArithmeticInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;
import static Assember.Utils.BinaryOperations.hexString;
import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.addSignedHexStrings;

/**
 * Represents an add immediate (addi) instruction.
 */
public class AddiInstruction extends Instruction {

    /**
     * Constructs a new AddiInstruction object with the specified register file, data memory, program counter, and parameters.
     *
     * @param registerFile   the register file to be used by the instruction.
     * @param memory         the data memory to be used by the instruction.
     * @param programCounter the program counter to be used by the instruction.
     * @param parameters     the parameters of the instruction.
     */
    public AddiInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    /**
     * Executes the add immediate (addi) instruction.
     */
    @Override
    public void execute() {
        int rt = parameters[0];
        int rs = parameters[1];

        String rsValue = registerFile.getRegister(rs);
        String immediate = hexString(parameters[2], 1);

        String result = addSignedHexStrings(rsValue, immediate);

        registerFile.setRegister(rt, result);
    }
}
