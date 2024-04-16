package Assember.UI;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * The SyntaxHighlighter class provides methods to highlight syntax elements in a JTextPane.
 */
public class SyntaxHighlighter {

    /**
     * Highlights syntax elements in the given JTextPane.
     *
     * @param textPane The JTextPane to highlight syntax for.
     */
    public static void highlightSyntax(JTextPane textPane) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = textPane.getStyledDocument();

            // Style for instructions
            Style instructionStyle = textPane.addStyle("Instructions", null);
            StyleConstants.setForeground(instructionStyle, Color.BLUE);

            // Keywords to highlight
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

            // Highlight keywords
            for (String keyword : keywords) {
                highlightWord(textPane, keyword, instructionStyle);
                highlightWord(textPane, keyword.toLowerCase(), instructionStyle);
            }

            // Style for registers
            Style registerStyle = textPane.addStyle("Registers", null);
            StyleConstants.setForeground(registerStyle, Color.RED);
            highlightRegister(textPane, registerStyle);

            // Style for comments
            Style commentsStyle = textPane.addStyle("comments", null);
            StyleConstants.setForeground(commentsStyle, Color.GREEN);
            highlightComment(textPane, commentsStyle);
        });
    }

    /**
     * Highlights a specific word in the text pane.
     *
     * @param textPane The JTextPane containing the text.
     * @param word     The word to highlight.
     * @param style    The style to apply.
     */
    private static void highlightWord(JTextPane textPane, String word, Style style) {
        SwingUtilities.invokeLater(() -> {
            StyledDocument doc = textPane.getStyledDocument();
            String text;
            try {
                text = doc.getText(0, doc.getLength());
                int pos = 0;
                while ((pos = text.indexOf(word, pos)) >= 0) {
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

    /**
     * Highlights register elements in the text pane.
     *
     * @param textPane The JTextPane containing the text.
     * @param style    The style to apply.
     */
    private static void highlightRegister(JTextPane textPane, Style style) {
        StyledDocument doc = textPane.getStyledDocument();
        String text;
        try {
            text = doc.getText(0, doc.getLength());
            int pos = 0;
            while ((pos = text.indexOf("$", pos)) >= 0) {
                int start = pos;
                int end = pos + 1;
                if (end < text.length() && Character.isDigit(text.charAt(end))) {
                    while (end < text.length() && Character.isDigit(text.charAt(end))) {
                        if (text.charAt(end) == ',')
                            break;
                        end++;
                    }
                    doc.setCharacterAttributes(start, end - start, style, false);
                }
                pos = end;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Highlights comments in the text pane.
     *
     * @param textPane The JTextPane containing the text.
     * @param style    The style to apply.
     */
    private static void highlightComment(JTextPane textPane, Style style) {
        StyledDocument doc = textPane.getStyledDocument();
        String text;
        try {
            text = doc.getText(0, doc.getLength());
            int pos = 0;
            while ((pos = text.indexOf("#", pos)) >= 0) {
                int start = pos;
                int end = pos + 1;
                while (end < text.length()) {
                    if (text.charAt(end) == '\n')
                        break;
                    end++;
                }
                doc.setCharacterAttributes(start, end - start, style, false);
                pos = end;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
