package ProcessorCircuitSimulator.DataPath;

import java.util.HashMap;
import java.util.Map;

public class DataMemory {
    private Map<String, String> memory;

    public DataMemory() {
        memory = new HashMap<>();
    }

    public String loadFromMemory(String address) {
        return (memory.containsKey(address))?memory.get(address):"0000";
    }
    public void storeInMemory(String address, String value) {
        memory.put(address, value);
    }

    public String[][] getMemory() {
        String[][] output = new String[memory.size()][2];
        int i = 0;
        for (Map.Entry<String, String> entry : memory.entrySet()) {
            output[i][0] = entry.getKey();
            output[i][1] = entry.getValue();
            i++;
        }
        return output;
    }
}
