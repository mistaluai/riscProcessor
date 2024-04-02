package Decoders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ITypeDecoder implements Decoder {
    InstructionsOperations instructionsOperations;

    public ITypeDecoder() {
        instructionsOperations = new InstructionsOperations();
    }

    public String decodeInstruction(String instruction) throws Exception {

            Pattern parametersPattern = Pattern.compile("[$]+\\d");
            Matcher parametersMatcher = parametersPattern.matcher(instruction);

            int[] parameters = new int[2];
            int i = 0;

            for (;parametersMatcher.find(); i++) {
                parameters[i] = Integer.parseInt(parametersMatcher.group().substring(1));
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

            int rd = parameters[0];
            String rdString = instructionsOperations.getRegister(rd);

            int rs = parameters[1];
            String rsString = instructionsOperations.getRegister(rs);


            String immediateString = Integer.toBinaryString(immediate);
            immediateString = binaryExtend(immediateString, 5);

            return opcodeString
                    + immediateString
                    + rsString
                    + rdString;

        }

    private String binaryExtend(String binary, int amount) {
        String extender = "";
        for (int c = 0; c < amount - binary.length(); c++) {
            extender += "0";
        }
        binary = extender + binary;
        return binary;
    }
}
