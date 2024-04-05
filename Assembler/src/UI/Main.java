package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main extends JFrame {
    private JTextArea assemblyCodeTextArea;
    private JTextArea binaryCodeTextArea;
    private JTable codeTable;
    private JTextArea errorDetailsTextArea;

    public Main() {
        setTitle("RISC Assembler");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        Font globalFont = new Font("Lucida Grande", Font.BOLD, 13);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        assemblyCodeTextArea = new JTextArea();
        assemblyCodeTextArea.setRows(10);
        assemblyCodeTextArea.setColumns(50);
        

        JScrollPane assemblyScrollPane = new JScrollPane(assemblyCodeTextArea);

        binaryCodeTextArea = new JTextArea();
        binaryCodeTextArea.setRows(10);
        binaryCodeTextArea.setColumns(16);
        binaryCodeTextArea.setEditable(false);
        JScrollPane binaryScrollPane = new JScrollPane(binaryCodeTextArea);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Label", "Address"}, 0);
        codeTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(codeTable);

        errorDetailsTextArea = new JTextArea();
        errorDetailsTextArea.setRows(5);
        errorDetailsTextArea.setColumns(66);
        JScrollPane errorScrollPane = new JScrollPane(errorDetailsTextArea);

        JButton assembleButton = new JButton("Assemble");
        JButton hexButton = new JButton("Hex");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5; // Make assembly code take half of the width
        gbc.weighty = 0.75; // Make assembly code take 75% of the height
        mainPanel.add(assemblyScrollPane, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.25; // Make binary code take quarter of the width
        mainPanel.add(binaryScrollPane, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(tableScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1; // Make error details stretch across all columns
        gbc.weighty = 0.1; // Make error details take 25% of the height
        mainPanel.add(errorScrollPane, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.15; // Make button take quarter of the width
        gbc.weighty = 0.15; // Make button take quarter of the width
        mainPanel.add(assembleButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.15; // Make button take quarter of the width
        gbc.weighty = 0.15; // Make button take quarter of the width
        mainPanel.add(hexButton, gbc);

        add(mainPanel);



    setVisible(true);

    }




    public static void main(String[] args) {
        new Main();
    }
}
