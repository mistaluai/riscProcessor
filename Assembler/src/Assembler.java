import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembler {
    Map<String, Integer> instructionOPcode;
    Map<String, Integer> instructionFunction;
    enum InstructionType {
        RType,
        IType,
        JType
    }

    Assembler() {
        initializeOpcodes();
        initializeFunctions();
    }

    private void initializeOpcodes() {
        instructionOPcode = new HashMap<>();

        //R-Type
        instructionOPcode.put("AND", 0);
        instructionOPcode.put("OR", 0);
        instructionOPcode.put("XOR", 0);
        instructionOPcode.put("NOR", 0);

        instructionOPcode.put("ADD", 1);
        instructionOPcode.put("SUB", 1);
        instructionOPcode.put("SLT", 1);
        instructionOPcode.put("SLTU", 1);

        instructionOPcode.put("JR", 2);

        //I-Type
        instructionOPcode.put("ANDI", 4);
        instructionOPcode.put("ORI", 5);
        instructionOPcode.put("XORI", 6);
        instructionOPcode.put("ADDI", 7);
        instructionOPcode.put("SLL", 8);
        instructionOPcode.put("SRL", 9);
        instructionOPcode.put("SRA", 10);
        instructionOPcode.put("ROR", 11);
        instructionOPcode.put("LW", 12);
        instructionOPcode.put("SW", 13);
        instructionOPcode.put("BEQ", 14);
        instructionOPcode.put("BNE", 15);
        instructionOPcode.put("BLT", 16);
        instructionOPcode.put("BGE", 17);

        //J-Type
        instructionOPcode.put("LUI", 18);
        instructionOPcode.put("J", 30);
        instructionOPcode.put("JAL", 31);
    }

    private void initializeFunctions() {
        instructionFunction = new HashMap<>();

        //Opcode 0
        instructionFunction.put("AND", 0);
        instructionFunction.put("OR", 1);
        instructionFunction.put("XOR", 2);
        instructionFunction.put("NOR", 3);

        //Opcode 1
        instructionFunction.put("ADD", 0);
        instructionFunction.put("SUB", 1);
        instructionFunction.put("SLT", 2);
        instructionFunction.put("SLTU", 3);

        //Opcode 2
        instructionFunction.put("JR", 0);
    }

    private String getInstruction(String instruction) throws Exception {
        Pattern instructionPattern = Pattern.compile("[\\w]+ ", Pattern.CASE_INSENSITIVE);
        Matcher instructionMatcher = instructionPattern.matcher(instruction);
        if (!instructionMatcher.find())
            throw new Exception("Instruction not found in" + instruction);

        instruction = instructionMatcher.group();
        instruction = instruction.substring(0, instruction.length() - 1);
        instruction = instruction.toUpperCase();

        if (!instructionOPcode.containsKey(instruction))
            throw new Exception("Unknown Instruction " + instruction);

        return instruction;
    }
    private InstructionType getInstructionType(String instruction) {

        int opcode = instructionOPcode.get(instruction);

        if (opcode >= 0 && opcode <= 2)
            return InstructionType.RType;

        if (opcode >= 4 && opcode <= 17)
            return InstructionType.IType;

        if (opcode >= 18 && opcode <= 31)
            return InstructionType.JType;

        else return null;
    }

    //Testing
    public static void main(String[] args) throws Exception {
        Assembler assembler = new Assembler();
        String instruction = "and $3, $2, $5";
        instruction = assembler.getInstruction(instruction);
        System.out.println(instruction);
        System.out.println(assembler.getInstructionType(instruction));
    }

}
