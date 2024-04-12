package ProcessorCircuitSimulator.DataPath;

public class RegisterFile {
    private String[] registers;
    public RegisterFile() {
        registers = new String[8];
        for (int i = 0;  i < 8; i++) {
            registers[i] = "0000";
        }
    }
    public String getRegister(int registerIndex) {
        return registers[registerIndex];
    }
    public void setRegister(int registerIndex, String value) {
        registers[registerIndex] =(registerIndex != 0)?value:"0000";
    }
    public String[] getRegisters() {
        return registers;
    }
}
