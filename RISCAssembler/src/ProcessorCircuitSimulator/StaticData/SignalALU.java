package ProcessorCircuitSimulator.StaticData;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the signal mappings for ALU operations.
 */
public class SignalALU {
    private Map<String, String> signals;

    /**
     * Constructs a new SignalALU object and initializes the signal mappings.
     */
    public SignalALU() {
        signals = new HashMap<>();

        // Logical operations
        signals.put("AND", "00");
        signals.put("OR", "01");
        signals.put("XOR", "02");
        signals.put("NOR", "03");

        // Arithmetic operations
        signals.put("ADD", "04");
        signals.put("SUB", "05");
        signals.put("SLT", "06");
        signals.put("SLTU", "07");

        // Shifting operations
        signals.put("SLL", "08");
        signals.put("SRL", "09");
        signals.put("SRA", "0A");
        signals.put("ROR", "0B");
    }

    /**
     * Retrieves the signal corresponding to the given operation.
     *
     * @param op the operation for which to retrieve the signal.
     * @return the signal corresponding to the operation.
     */
    public String getSignal(String op) {
        return signals.get(op);
    }
}
