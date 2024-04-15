package Assember.Utils;

import Assember.Utils.DataTypes.DataDeclaration;

import java.util.List;

public class DataInitializer {
private List<DataDeclaration> dataDeclarations;
private String delcarationCompiled;

    public DataInitializer(List<DataDeclaration> dataDeclarations) {
        this.dataDeclarations = dataDeclarations;
        setDelcarationCompiled();
    }
    public String getDeclarationsCompiled() {
        return delcarationCompiled;
    }
    private void setDelcarationCompiled() {
        StringBuilder compiled = new StringBuilder();
        int memoryAddress = 0;
        for (DataDeclaration dd : dataDeclarations) {
            memoryAddress += dd.compileInitialization();
            compiled.append(dd.getCompilation());
        }
        if (dataDeclarations.size() > 0)
            compiled.append(clearRegisters());

        delcarationCompiled = compiled.toString();
    }
    public int getInitializationLength() {
         String[] lines = delcarationCompiled.split("\n");
         int length = 0;
         for (String line: lines)
         {
             if (line.length() != 0)
                 length++;
         }
         return length;
    }
    private String clearRegisters() {
        return "ADD $1, $0, $0\nADD $2, $0, $0\nADD $3, $0, $0\n";
    }
}
