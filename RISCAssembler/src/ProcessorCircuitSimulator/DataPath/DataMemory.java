package ProcessorCircuitSimulator.DataPath;

import java.util.*;

/**
 * Represents the data memory component in a processor's data path.
 */
public class DataMemory {
    private Map<String, String> memory;

    /**
     * Constructs a new DataMemory object with an empty memory map.
     */
    public DataMemory() {
        memory = new HashMap<>();
    }

    /**
     * Loads a value from the memory at the specified address.
     *
     * @param address the memory address from which to load the value.
     * @return the value stored at the specified memory address, or "0000" if the address is not found.
     */
    public String loadFromMemory(String address) {
        return (memory.containsKey(address)) ? memory.get(address) : "0000";
    }

    /**
     * Stores a value into the memory at the specified address.
     *
     * @param address the memory address where the value will be stored.
     * @param value   the value to be stored in memory.
     */
    public void storeInMemory(String address, String value) {
        memory.put(address, value);
    }

    /**
     * Retrieves the contents of the memory as a 2D array.
     *
     * @return a 2D array representing the memory contents, where each row contains an address-value pair.
     */
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
