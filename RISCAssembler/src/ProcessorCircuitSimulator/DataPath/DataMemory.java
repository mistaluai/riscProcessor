package ProcessorCircuitSimulator.DataPath;

import java.util.HashMap;

public class DataMemory {
    private HashMap<String, String> memory;

    public DataMemory() {
        memory = new HashMap<>();
    }

    public String loadFromMemory(String address) {
        return (memory.containsKey(address))?memory.get(address):"0000";
    }
    public void storeInMemory(String address, String value) {
        memory.put(address, value);
    }

}
