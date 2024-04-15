package Assember.Utils.DataTypes;

import static Assember.Utils.BinaryOperations.binaryString;

public abstract class DataDeclaration {
    public String data, address;
    public StringBuilder compiledInstructions;

    public DataDeclaration(String data, String address) {
        this.data = data;
        this.address = address;
    }

    abstract void initializeData();

    public abstract int compileInitialization();

    public abstract String getCompilation();
    String initializeMemoryPointer(int memoryPointer) {
        StringBuilder instructions = new StringBuilder();


        String binaryValue = binaryString(memoryPointer, 16, 0);
        int lui = Integer.parseInt(binaryValue.substring(0, 11), 2);
        int ori = Integer.parseInt(binaryValue.substring(11, 16), 2);
        instructions.append("LUI " + lui + "\n");
        instructions.append("ORI $3, $1, " + ori + "\n");


        /*
        instructions.append("ADD $1, $0, $0\n"); //clear the pointer
        int numberOfAdditions = memoryPointer/15;
        double remainder = memoryPointer%15;

        for (int count = 0; count < numberOfAdditions; count++)
            instructions.append("ADDI $1, $1, 15\n");

        if (remainder != 0)
        instructions.append("ADDI $1, $1, " + (int)remainder);
         */

        return instructions.toString();
    }
}
