package Assembler;

import Utils.SymbolTable;

public class Assembler {
private SymbolTable st;
    public Assembler(String code) {
        st = new SymbolTable(code);
    }
    public String[][] getSymbols() {
        return st.getSymbols();
    }
}
