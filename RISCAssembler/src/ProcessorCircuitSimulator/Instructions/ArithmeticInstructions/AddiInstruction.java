package ProcessorCircuitSimulator.Instructions.ArithmeticInstructions;

import ProcessorCircuitSimulator.DataPath.DataMemory;
import ProcessorCircuitSimulator.DataPath.ProgramCounter;
import ProcessorCircuitSimulator.DataPath.RegisterFile;
import ProcessorCircuitSimulator.Instructions.Instruction;

import static Assember.Utils.BinaryOperations.hexString;
import static ProcessorCircuitSimulator.ALU.ArithmeticOperations.addSignedHexStrings;

public class AddiInstruction extends Instruction {

    public AddiInstruction(RegisterFile registerFile, DataMemory memory, ProgramCounter programCounter, int[] parameters) {
        super(registerFile, memory, programCounter, parameters);
    }

    public void execute() {
        int rt = parameters[0];
        int rs = parameters[1];

        String rsValue = registerFile.getRegister(rs);
        String immediate = hexString(parameters[2], 1);

        String result = addSignedHexStrings(rsValue, immediate);

        registerFile.setRegister(rt, result);
    }
}
