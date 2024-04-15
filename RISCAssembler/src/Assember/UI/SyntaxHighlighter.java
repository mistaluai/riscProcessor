package Assember.UI;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class SyntaxHighlighter {
    public static void highlightSyntax(JTextPane textPane) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = textPane.getStyledDocument();
            Style instructionStyle = textPane.addStyle("Instructions", null);
            StyleConstants.setForeground(instructionStyle, Color.BLUE);

            String[] keywords = {"AND", "OR", "XOR", "NOR",
                    "ANDI", "ORI", "XORI", "NORI",
                    "ADD", "SUB", "SLT", "SLTU",
                    "SLL", "SRL", "SRA", "ROR",
                    "DRAW",
                    "LW", "SW",
                    "BEQ", "BNE", "BLT", "BGE",
                    "LUI",
                    "J", "JR", "JAL"
            };

            for (String keyword : keywords) {
                highlightWord(textPane, keyword, instructionStyle);
                highlightWord(textPane, keyword.toLowerCase(), instructionStyle);
            }
            // Words starting with "$" or "#"
            Style registerStyle = textPane.addStyle("Registers", null);
            StyleConstants.setForeground(registerStyle, Color.RED);
            highlightRegister(textPane, registerStyle);

            Style commentsStyle = textPane.addStyle("comments", null);
            StyleConstants.setForeground(commentsStyle, Color.GREEN);
            highlightComment(textPane, commentsStyle);
        });


    }


    private static void highlightWord(JTextPane textPane, String word, Style style) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = textPane.getStyledDocument();
            String text;
            try {
                text = doc.getText(0, doc.getLength());
                int pos = 0;
                while ((pos = text.indexOf(word, pos)) >= 0) {
                    // Check if the word is surrounded by whitespace or if it's at the start/end of the text
                    boolean isStart = (pos == 0 || !Character.isLetterOrDigit(text.charAt(pos - 1)));
                    boolean isEnd = (pos + word.length() == text.length() || !Character.isLetterOrDigit(text.charAt(pos + word.length())));
                    if (isStart && isEnd) {
                        doc.setCharacterAttributes(pos, word.length(), style, false);
                    }
                    pos += word.length();
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }


    private static void highlightRegister(JTextPane textPane, Style style) {
        StyledDocument doc = textPane.getStyledDocument();
        String text;
        try {
            text = doc.getText(0, doc.getLength());
            int pos = 0;
            while ((pos = text.indexOf("$", pos)) >= 0) {
                // Find the start and end index of the word starting with the prefix
                int start = pos;
                int end = pos + 1;
                if (end < text.length() && Character.isDigit(text.charAt(end))) {
                    // If the character after "$" is a digit, extend the highlight
                    while (end < text.length() && Character.isDigit(text.charAt(end))) {
                        if (text.charAt(end) == ',')
                            break;

                        end++;
                    }
                    // Apply style to the word
                    doc.setCharacterAttributes(start, end - start, style, false);
                }
                pos = end;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }


    private static void highlightComment(JTextPane textPane,Style style) {
        StyledDocument doc = textPane.getStyledDocument();
        String text;
        try {
            text = doc.getText(0, doc.getLength());
            int pos = 0;
            while ((pos = text.indexOf("#", pos)) >= 0) {
                // Find the start and end index of the word starting with the prefix
                int start = pos;
                int end = pos + 1;
                while (end < text.length()) {
                    if (text.charAt(end) == '\n')
                        break;
                    end++;
                }
                // Apply style to the word
                doc.setCharacterAttributes(start, end - start, style, false);
                pos = end;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

}
