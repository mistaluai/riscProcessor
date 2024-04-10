package Assember.Utils;


import Assember.Exceptions.SyntaxException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolTable {
   Map<String, Integer> labels;

   /**
    * Constructs a symbol table by detecting labels in the given assembly code and storing them
    * in a map with their corresponding addresses.
    *
    * @param code the assembly code in which labels are detected and stored.
    */
   public SymbolTable(String code) {
      // Initialize the map to store labels and their addresses
      labels = new HashMap<>();

      // Define a pattern to match label patterns in the assembly code
      Pattern labelPattern = Pattern.compile("\\w+:");

      // Split the assembly code into lines
      String[] lines = code.split("\n");

      // Initialize an index to track the current address
      int index = 0;

      // Iterate over each line of the assembly code
      for (String instruction : lines) {
         // Trim the instruction to remove leading and trailing whitespaces
         instruction = instruction.trim();

         // Skip empty lines or comments
         if (instruction.length() == 0 || instruction.charAt(0) == '#')
            continue;

         instruction = removeComments(instruction);

         // Match label patterns in the instruction
         Matcher labelMatcher = labelPattern.matcher(instruction);

         // If a label is found, extract it and store it in the map with its address
         if (labelMatcher.find()) {
            String label = labelMatcher.group();
            label = label.substring(0, label.length() - 1); // Remove the colon from the label
            labels.put(label, index);
         } else {
            // Increment the index to track the next instruction address
            index++;
         }
      }
   }


   /**
    * Retrieves the address associated with the given label from the symbol table.
    *
    * @param label the label whose address is to be retrieved.
    * @return the address associated with the given label.
    * @throws SyntaxException if the label is not found in the symbol table.
    */
   public int getLabel(String label) {
      // Check if the symbol table contains the given label
      if (labels.containsKey(label)) {
         // If found, return the address associated with the label
         return labels.get(label);
      } else {
         // If not found, throw an exception indicating that the label was not found
         throw new SyntaxException("Label not found: " + label);
      }
   }


   /**
    * Checks if the given instruction is skippable, i.e., if it contains a label.
    *
    * @param instruction the instruction to be checked for skippability.
    * @return true if the instruction contains a label and is skippable, false otherwise.
    */
   public boolean isSkippable(String instruction) {
      // Define a pattern to match label patterns in the instruction
      Pattern labelPattern = Pattern.compile("\\w+:");

      // Match label patterns in the instruction
      Matcher labelMatcher = labelPattern.matcher(instruction);

      // If a label pattern is found in the instruction, it is considered skippable
      if (labelMatcher.find()) {
         System.out.println(instruction + " is skippable");
         return true;
      }

      // If no label pattern is found, the instruction is not skippable
      return false;
   }

   /**
    * Retrieves the symbols (labels and their corresponding addresses) from the symbol table
    * to populate a 2D array for a JTable in the user interface.
    *
    * @return a 2D array containing the labels and their addresses to be displayed in a JTable.
    */
   public String[][] getSymbols() {
      // Create a 2D array to store the labels and their corresponding addresses
      String[][] symbols = new String[labels.size()][2];

      // Iterate over the entries in the symbol table and populate the array
      int i = 0;
      for (Map.Entry<String, Integer> entry : labels.entrySet()) {
         symbols[i][0] = entry.getKey(); // Label
         symbols[i][1] = String.valueOf(entry.getValue()); // Address
         i++;
      }

      // Return the populated array
      return symbols;
   }
   private String removeComments(String assemblyCode) {
      StringBuilder result = new StringBuilder();
      boolean inComment = false;

      for (int i = 0; i < assemblyCode.length(); i++) {
         char currentChar = assemblyCode.charAt(i);
         char nextChar = i < assemblyCode.length() - 1 ? assemblyCode.charAt(i + 1) : '\0';

         if (currentChar == '#' && (i == 0 || assemblyCode.charAt(i - 1) != '\\')) {
            inComment = true;
         }

         if (!inComment) {
            result.append(currentChar);
         }

         if (currentChar == '\n' || (currentChar == '\r' && nextChar == '\n')) {
            inComment = false;
         }
      }
      String output = result.toString();
      output = output.trim();
      return output;
   }
}
