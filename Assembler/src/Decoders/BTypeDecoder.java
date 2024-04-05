package Decoders;

import Utils.BinaryOperations;
import Utils.SymbolTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BTypeDecoder implements Decoder {
    SymbolTable st;
    InstructionsOperations instructionsOperations;

    public BTypeDecoder(SymbolTable st)
    {
        this.st = st;
        instructionsOperations = new InstructionsOperations();
    }
    public String decodeInstruction(String instruction, int currentAddress) throws Exception {
        Pattern registersPattern = Pattern.compile("[$]+\\d");
        Matcher registersMatcher = registersPattern.matcher(instruction);

        int[] registers = new int[2];
        int i = 0;

        for (;registersMatcher.find(); i++) {
            registers[i] = Integer.parseInt(registersMatcher.group().substring(1));
        }

        if (i < 2)
            throw new Exception("Unsupported or missing Parameters in " + instruction);

        Pattern labelPattern = Pattern.compile("[ ][\\w]+");
        Matcher labelMatcher = labelPattern.matcher(instruction);
        String label = "";
        if (labelMatcher.find())
            label = labelMatcher.group();
        else throw new Exception("Label not found in " + instruction);

        int labelAddress = st.getLabel(label);
        int offset = labelAddress - currentAddress;

        String opcode = instructionsOperations.getOpcode(instruction);
        String offsetString = BinaryOperations.binaryString(offset, 5, 1);

        int rs = registers[0];
        int rt = registers[1];

        String rtString = instructionsOperations.getRegister(rt);
        String rsString = instructionsOperations.getRegister(rs);

        return opcode
                + offsetString
                + rsString
                + rtString;
    }
}
