package Assembler;

import Decoders.*;
import Utils.SymbolTable;

public class Assembler {
    private SymbolTable st;
    private InstructionsOperations instructionsOperations;

    private JTypeDecoder jtd;
    private RTypeDecoder rtd;
    private ITypeDecoder itd;
    private LoadStoreDecoder lstd;
    private BTypeDecoder btd;

    private String assemblyCode;
    private StringBuilder binaryCode;
    int currentInstruction;
    public Assembler(String code) throws Exception {
        st = new SymbolTable(code);
        instructionsOperations = new InstructionsOperations();

        rtd = new RTypeDecoder();
        itd = new ITypeDecoder();
        lstd = new LoadStoreDecoder();
        jtd = new JTypeDecoder(st);
        btd = new BTypeDecoder(st);

        assemblyCode = code;
        binaryCode = new StringBuilder();
        currentInstruction = 0;
        assemble();
    }
    private void assemble() throws Exception {
        String[] lines = assemblyCode.split("\n");
        for (String instruction : lines) {
            if (instruction.length() == 0)
                continue;
            if (instruction.charAt(0) == '#')
                continue;
            if (st.isSkippable(instruction))
                continue;

            instruction = instruction.trim();

             switch (instructionsOperations.getInstructionType(instruction)) {
                 case RType:
                     binaryCode.append(rtd.decodeInstruction(instruction, currentInstruction));
                     break;
                 case IType:
                     binaryCode.append(itd.decodeInstruction(instruction, currentInstruction));
                     break;
                 case JType:
                     binaryCode.append(jtd.decodeInstruction(instruction, currentInstruction));
                     break;
                 case LoadStoreType:
                     binaryCode.append(lstd.decodeInstruction(instruction, currentInstruction));
                     break;
                 case BType:
                     binaryCode.append(btd.decodeInstruction(instruction, currentInstruction));
             }
        binaryCode.append("\n");
        currentInstruction++;
        }
    }
    private String removeWhiteSpaces(String instruction) {
        while (instruction.charAt(0) == ' ') {
            instruction = instruction.substring(1);
        }
        return instruction;
    }
    public String[][] getSymbols() {
        return st.getSymbols();
    }
    public String getBinaryCode() {
        return binaryCode.toString();
    }
    public int getCurrentInstruction()
    {
        return currentInstruction;
    }
}
