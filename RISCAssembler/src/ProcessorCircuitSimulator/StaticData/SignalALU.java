package ProcessorCircuitSimulator.StaticData;

import java.util.HashMap;
import java.util.Map;

public class SignalALU {
    private Map<String, String>  signals;

    public SignalALU() {
        signals = new HashMap<>();

        signals.put("AND", "00");
        signals.put("OR", "01");
        signals.put("XOR", "02");
        signals.put("NOR", "03");

        signals.put("ADD", "04");
        signals.put("SUB", "05");
        signals.put("SLT", "06");
        signals.put("SLTU", "07");

        signals.put("SLL", "08");
        signals.put("SRL", "09");
        signals.put("SRA", "0A");
        signals.put("ROR", "0B");

    }

    public String getSignal(String op) {
        return signals.get(op);
    }
}
