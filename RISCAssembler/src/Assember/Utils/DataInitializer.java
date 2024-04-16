package Assember.Utils;

import Assember.Utils.DataTypes.DataDeclaration;

import java.util.List;

/**
 * A utility class for initializing data declarations.
 */
public class DataInitializer {

    private final List<DataDeclaration> dataDeclarations;
    private String declarationCompiled;

    /**
     * Constructs a new DataInitializer with the given list of data declarations.
     *
     * @param dataDeclarations the list of data declarations to initialize.
     */
    public DataInitializer(List<DataDeclaration> dataDeclarations) {
        this.dataDeclarations = dataDeclarations;
        setDeclarationCompiled();
    }

    /**
     * Gets the compiled declarations as a single string.
     *
     * @return the compiled declarations.
     */
    public String getDeclarationsCompiled() {
        return declarationCompiled;
    }

    /**
     * Sets the compiled declarations by compiling each data declaration and appending the results.
     */
    private void setDeclarationCompiled() {
        StringBuilder compiled = new StringBuilder();
        int memoryAddress = 0;
        for (DataDeclaration dd : dataDeclarations) {
            memoryAddress += dd.compileInitialization();
            compiled.append(dd.getCompilation());
        }
        if (!dataDeclarations.isEmpty())
            compiled.append(clearRegisters());

        declarationCompiled = compiled.toString();
    }

    /**
     * Gets the total number of initialization lines.
     *
     * @return the total number of initialization lines.
     */
    public int getInitializationLength() {
        String[] lines = declarationCompiled.split("\n");
        int length = 0;
        for (String line : lines) {
            if (!line.isEmpty())
                length++;
        }
        return length;
    }

    /**
     * Generates instructions to clear registers.
     *
     * @return instructions to clear registers.
     */
    private String clearRegisters() {
        return "ADD $1, $0, $0\nADD $2, $0, $0\nADD $3, $0, $0\n";
    }
}
