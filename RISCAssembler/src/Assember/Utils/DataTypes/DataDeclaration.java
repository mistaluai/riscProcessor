package Assember.Utils.DataTypes;

public abstract class DataDeclaration {
    public String data;
    public StringBuilder compiledInstructions;
    public DataDeclaration(String data) {
        this.data = data;
    }

    abstract void initializeData();

    public abstract int compileInitialization(int memoryPointer);

    public abstract String getCompilation();
    String initializeMemoryPointer(int memoryPointer) {
        StringBuilder instructions = new StringBuilder();

        instructions.append("ADD $1, $0, $0\n"); //clear the pointer
        int numberOfAdditions = memoryPointer/15;
        double remainder = memoryPointer%15;

        for (int count = 0; count < numberOfAdditions; count++)
            instructions.append("ADDI $1, $1, 15\n");

        if (remainder != 0)
        instructions.append("ADDI $1, $1, " + (int)remainder);

        return instructions.toString();
    }
}
