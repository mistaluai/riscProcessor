package Decoders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Utils.BinaryOperations.binaryExtend;

public class ITypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public ITypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    public String decodeInstruction(String instruction) throws Exception {

            Pattern registersPattern = Pattern.compile("[$]+\\d");
            Matcher registersMatcher = registersPattern.matcher(instruction);

            int[] registers = new int[2];
            int i = 0;

            for (;registersMatcher.find(); i++) {
                registers[i] = Integer.parseInt(registersMatcher.group().substring(1));
            }

            if (i < 2)
                throw new Exception("Unsupported or missing Parameters in " + instruction);


            Pattern immediatePattern = Pattern.compile("[^$][\\d+]");
            Matcher immediateMatcher = immediatePattern.matcher(instruction);
            int immediate = 0;
            if (immediateMatcher.find())
                immediate = Integer.parseInt(immediateMatcher.group().substring(1));
            else
                throw new Exception("immediate value not given in " + instruction);

            String opcodeString = instructionsOperations.getOpcode(instruction);

            int rd = registers[0];
            String rdString = instructionsOperations.getRegister(rd);

            int rs = registers[1];
            String rsString = instructionsOperations.getRegister(rs);


            String immediateString = Integer.toBinaryString(immediate);
            immediateString = binaryExtend(immediateString, 5);

            return opcodeString
                    + immediateString
                    + rsString
                    + rdString;

        }

}
