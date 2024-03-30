import java.util.HashMap;
import java.util.Map;

public class Assembler {
 Map<String, Integer> InstructionOPcode;

 Assembler() {
     InitializeOpcodes();
 }
 private void InitializeOpcodes() {
     InstructionOPcode = new HashMap<>();

     //R-Type
     InstructionOPcode.put("AND", 0);
     InstructionOPcode.put("OR", 0);
     InstructionOPcode.put("XOR", 0);
     InstructionOPcode.put("NOR", 0);

     InstructionOPcode.put("ADD", 1);
     InstructionOPcode.put("SUB", 1);
     InstructionOPcode.put("SLT", 1);
     InstructionOPcode.put("SLTU", 1);

     InstructionOPcode.put("JR", 2);

     //I-Type
     InstructionOPcode.put("ANDI", 4);
     InstructionOPcode.put("ORI", 5);
     InstructionOPcode.put("XORI", 6);
     InstructionOPcode.put("ADDI", 7);
     InstructionOPcode.put("SLL", 8);
     InstructionOPcode.put("SRL", 9);
     InstructionOPcode.put("SRA", 10);
     InstructionOPcode.put("ROR", 11);
     InstructionOPcode.put("LW", 12);
     InstructionOPcode.put("SW", 13);
     InstructionOPcode.put("BEQ", 14);
     InstructionOPcode.put("BNE", 15);
     InstructionOPcode.put("BLT", 16);
     InstructionOPcode.put("BGE", 17);

     //J-Type
     InstructionOPcode.put("LUI", 18);
     InstructionOPcode.put("J", 30);
     InstructionOPcode.put("JAL", 31);
 }
}
