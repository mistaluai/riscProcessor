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
        assemble();
    }
    private void assemble() throws Exception {
        String[] lines = assemblyCode.split("\n");
        int i = 0;
        for (String instruction : lines) {
            if (instruction.length() == 0)
                continue;
            if (instruction.charAt(0) == '#')
                continue;
            if (st.isSkippable(instruction))
                continue;
             switch (instructionsOperations.getInstructionType(instruction)) {
                 case RType:
                     binaryCode.append(rtd.decodeInstruction(instruction, i));
                     break;
                 case IType:
                     binaryCode.append(itd.decodeInstruction(instruction, i));
                     break;
                 case JType:
                     binaryCode.append(jtd.decodeInstruction(instruction, i));
                     break;
                 case LoadStoreType:
                     binaryCode.append(lstd.decodeInstruction(instruction, i));
                     break;
                 case BType:
                     binaryCode.append(btd.decodeInstruction(instruction, i));
             }
        binaryCode.append("\n");
        i++;
        }
    }
    public String[][] getSymbols() {
        return st.getSymbols();
    }
    public String getBinaryCode() {
        return binaryCode.toString();
    }

}
