package Assember.Utils;

import Assember.Utils.DataTypes.DataDeclaration;

import java.util.List;

public class DataInitializer {
private List<DataDeclaration> dataDeclarations;

    public DataInitializer(List<DataDeclaration> dataDeclarations) {
        this.dataDeclarations = dataDeclarations;
    }
    public String getDeclarationsCompiled() {
        StringBuilder compiled = new StringBuilder();
        int memoryAddress = 0;
        for (DataDeclaration dd : dataDeclarations) {
            memoryAddress += dd.compileInitialization(memoryAddress);
            compiled.append(dd.getCompilation());
        }
        compiled.append(clearRegisters());

        return compiled.toString();
    }
    private String clearRegisters() {
        return "ADD $1, $0, $0\nADD $2, $0, $0\nADD $3, $0, $0\nADD $4, $0, $0\nADD $5, $0, $0\nADD $6, $0, $0\nADD $7, $0, $0";
    }
}
