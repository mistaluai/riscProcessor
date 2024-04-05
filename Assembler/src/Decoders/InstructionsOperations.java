package Decoders;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionsOperations {

    Map<String, String> instructionOpcode;
    Map<String, String> instructionFunction;
    Map<String, InstructionType> instructionType;
    Map<Integer, String> registerString;
    Map<String , Integer> integerOpcode;

    public enum InstructionType {
        RType,
        IType,
        JType,
        LoadStoreType,
        BType
    }

    public InstructionsOperations() {
        initializeOpcodes();
        initializeFunctions();
        initializeTypes();
        initializeRegisters();
        initializeIntegerOpcodes();
    }
private void initializeRegisters() {
        registerString = new HashMap<>();
        registerString.put(0, "000");
        registerString.put(1, "001");
        registerString.put(2, "010");
        registerString.put(3, "011");
        registerString.put(4, "100");
        registerString.put(5, "101");
        registerString.put(6, "110");
        registerString.put(7, "111");
}
    private void initializeOpcodes() {
        instructionOpcode = new HashMap<>();

        //R-Type
        instructionOpcode.put("AND", "00000"); //0
        instructionOpcode.put("OR", "00000"); //0
        instructionOpcode.put("XOR", "00000"); //0
        instructionOpcode.put("NOR", "00000"); //0

        instructionOpcode.put("ADD", "00001"); //1
        instructionOpcode.put("SUB", "00001"); //1
        instructionOpcode.put("SLT", "00001"); //1
        instructionOpcode.put("SLTU", "00001");//1

        instructionOpcode.put("JR", "00010"); //2

        //I-Type
        instructionOpcode.put("ANDI", "00100"); //4
        instructionOpcode.put("ORI", "00101"); //5
        instructionOpcode.put("XORI", "00110"); //6
        instructionOpcode.put("ADDI", "00111"); //7
        instructionOpcode.put("SLL", "01000"); //8
        instructionOpcode.put("SRL", "01001"); //9
        instructionOpcode.put("SRA", "01010"); //10
        instructionOpcode.put("ROR", "01011"); //11
        instructionOpcode.put("LW", "01100"); //12
        instructionOpcode.put("SW", "01101"); //13
        instructionOpcode.put("BEQ", "01110"); //14
        instructionOpcode.put("BNE", "01111"); //15
        instructionOpcode.put("BLT", "10000"); //16
        instructionOpcode.put("BGE", "10001"); //17

        //J-Type
        instructionOpcode.put("LUI", "10010"); //18
        instructionOpcode.put("J", "11110"); //30
        instructionOpcode.put("JAL", "11111"); //31
    }

    private void initializeIntegerOpcodes() {
        integerOpcode = new HashMap<>();

        //R-Type
        integerOpcode.put("AND", 0);
        integerOpcode.put("OR", 0);
        integerOpcode.put("XOR", 0);
        integerOpcode.put("NOR", 0);

        integerOpcode.put("ADD", 1);
        integerOpcode.put("SUB", 1);
        integerOpcode.put("SLT", 1);
        integerOpcode.put("SLTU", 1);

        integerOpcode.put("JR", 2);

        //I-Type
        integerOpcode.put("ANDI", 4);
        integerOpcode.put("ORI", 5);
        integerOpcode.put("XORI", 6);
        integerOpcode.put("ADDI", 7);
        integerOpcode.put("SLL", 8);
        integerOpcode.put("SRL", 9);
        integerOpcode.put("SRA", 10);
        integerOpcode.put("ROR", 11);
        integerOpcode.put("LW", 12);
        integerOpcode.put("SW", 13);
        integerOpcode.put("BEQ", 14);
        integerOpcode.put("BNE", 15);
        integerOpcode.put("BLT", 16);
        integerOpcode.put("BGE", 17);

        //J-Type
        integerOpcode.put("LUI", 18);
        integerOpcode.put("J", 30);
        integerOpcode.put("JAL", 31);
    }

    private void initializeTypes() {
        instructionType = new HashMap<>();

        //R-Type
        instructionType.put("AND", InstructionType.RType); //0
        instructionType.put("OR", InstructionType.RType); //0
        instructionType.put("XOR", InstructionType.RType); //0
        instructionType.put("NOR", InstructionType.RType); //0

        instructionType.put("ADD", InstructionType.RType); //1
        instructionType.put("SUB", InstructionType.RType); //1
        instructionType.put("SLT", InstructionType.RType); //1
        instructionType.put("SLTU", InstructionType.RType);//1

        instructionType.put("JR", InstructionType.RType); //2

        //I-Type
        instructionType.put("ANDI", InstructionType.IType); //4
        instructionType.put("ORI", InstructionType.IType); //5
        instructionType.put("XORI", InstructionType.IType); //6
        instructionType.put("ADDI", InstructionType.IType); //7
        instructionType.put("SLL", InstructionType.IType); //8
        instructionType.put("SRL", InstructionType.IType); //9
        instructionType.put("SRA", InstructionType.IType); //10
        instructionType.put("ROR", InstructionType.IType); //11
        instructionType.put("LW", InstructionType.LoadStoreType); //12
        instructionType.put("SW", InstructionType.LoadStoreType); //13
        instructionType.put("BEQ", InstructionType.BType); //14
        instructionType.put("BNE", InstructionType.BType); //15
        instructionType.put("BLT", InstructionType.BType); //16
        instructionType.put("BGE", InstructionType.BType); //17

        //J-Type
        instructionType.put("LUI", InstructionType.JType); //18
        instructionType.put("J", InstructionType.JType); //30
        instructionType.put("JAL", InstructionType.JType); //31
    }
    private void initializeFunctions() {
        instructionFunction = new HashMap<>();

        //Opcode 0
        instructionFunction.put("AND", "00");
        instructionFunction.put("OR", "01");
        instructionFunction.put("XOR", "10");
        instructionFunction.put("NOR", "11");

        //Opcode 1
        instructionFunction.put("ADD", "00");
        instructionFunction.put("SUB", "01");
        instructionFunction.put("SLT", "10");
        instructionFunction.put("SLTU", "11");

        //Opcode 2
        instructionFunction.put("JR", "00");
    }

    public String getInstruction(String instruction) throws Exception {
        Pattern instructionPattern = Pattern.compile("[\\w]+ ", Pattern.CASE_INSENSITIVE);
        Matcher instructionMatcher = instructionPattern.matcher(instruction);
        if (!instructionMatcher.find())
            throw new Exception("Instruction not found in " + instruction);

        instruction = instructionMatcher.group();
        instruction = instruction.substring(0, instruction.length() - 1);
        instruction = instruction.toUpperCase();

        if (!instructionOpcode.containsKey(instruction))
            throw new Exception("Unknown Instruction " + instruction);

        return instruction;
    }

    public InstructionType getInstructionType(String instruction) throws Exception {
        instruction = getInstruction(instruction);
        return instructionType.get(instruction);
    }

    public String getOpcode(String instruction) throws Exception {
        instruction = getInstruction(instruction);
        return instructionOpcode.get(instruction);
    }

    public String getFunction(String instruction) throws Exception {
        instruction = getInstruction(instruction);
        return instructionFunction.get(instruction);
    }

    public String getRegister(int register) {
        return registerString.get(register);
    }

    public int getIntegerOpcode(String instruction) throws Exception {
        instruction = getInstruction(instruction);
        return integerOpcode.get(instruction);
    }

    public int[] extractRegisters(String instruction, int n) throws Exception {
        Pattern registersPattern = Pattern.compile("[$]+\\d");
        Matcher registersMatcher = registersPattern.matcher(instruction);

        int[] registers = new int[n];
        int i = 0;
        for (;registersMatcher.find(); i++) {
            registers[i] = Integer.parseInt(registersMatcher.group().substring(1));
        }

        if (i < n)
            throw new Exception("Unsupported or missing Parameters in " + instruction);

        return registers;
    }
}
