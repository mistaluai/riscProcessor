package Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolTable {
   Map<String, Integer> labels;

   public SymbolTable(String code) {
      labels = new HashMap<>();

      Pattern labelPattern = Pattern.compile("\\w+:");
      String[] lines = code.split("\n");

      int index = 0;
      for (String instruction : lines) {
         if (instruction.length() == 0)
            continue;
         if (instruction.charAt(0) == '#')
            continue;

         Matcher labelMatcher = labelPattern.matcher(instruction);
         if (labelMatcher.find()) {
            String label = labelMatcher.group();
            label = label.substring(0, label.length() - 1);
            labels.put(label, index + 1);
            System.out.print(label);
         }
         index++;
      }
   }

   public String[][] getSymbols() {
      String[][] symbols = new String[labels.size()][2];

      int i = 0;
      for (Map.Entry<String, Integer> entry : labels.entrySet())
      {
         symbols[i][0] = entry.getKey();
         symbols[i][1] = String.valueOf(entry.getValue());
         i++;
      }
      return symbols;
   }
}
