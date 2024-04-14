package ProcessorCircuitSimulator.DataPath;

import java.util.*;

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
        List<String> addresses = new ArrayList<>(memory.keySet());
        Collections.sort(addresses);
        int i = 0;
        for (String address : addresses) {
            output[i][0] = address;
            output[i][1] = memory.get(address);
            i++;
        }
        return output;
    }
}
