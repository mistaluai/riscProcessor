package ProcessorCircuitSimulator.DataPath;

/**
 * Represents the register file component in a processor's data path.
 */
public class RegisterFile {
    private String[] registers;

    /**
     * Constructs a new RegisterFile object with 8 registers initialized to "0000".
     */
    public RegisterFile() {
        registers = new String[8];
        for (int i = 0; i < 8; i++) {
            registers[i] = "0000";
        }
    }

    /**
     * Retrieves the value stored in the specified register.
     *
     * @param registerIndex the index of the register to retrieve the value from.
     * @return the value stored in the specified register.
     */
    public String getRegister(int registerIndex) {
        return registers[registerIndex];
    }

    /**
     * Sets the value of the specified register.
     *
     * @param registerIndex the index of the register to set the value for.
     * @param value         the value to set in the register.
     */
    public void setRegister(int registerIndex, String value) {
        registers[registerIndex] = (registerIndex != 0) ? value : "0000";
    }

    /**
     * Retrieves all registers stored in the register file.
     *
     * @return an array containing all registers stored in the register file.
     */
    public String[] getRegisters() {
        return registers;
    }
}
