package org.example;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {

    JTextArea inputArea, outputArea;

    public MainUI() {
        setTitle("Syntax Analyzer SPOK - Bahasa Jawa Ngoko");
        setSize(550, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputArea = new JTextArea(3, 40);
        outputArea = new JTextArea(12, 40);
        outputArea.setEditable(false);

        JButton btn = new JButton("ANALISIS KALIMAT");

        btn.addActionListener(e -> {
            String hasil = Automata.analyze(inputArea.getText());
            outputArea.setText(hasil);
        });

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        panel.add(new JLabel("Input Kalimat:"), BorderLayout.NORTH);
        panel.add(new JScrollPane(inputArea), BorderLayout.CENTER);

        JPanel bawah = new JPanel(new BorderLayout());
        bawah.add(btn, BorderLayout.NORTH);
        bawah.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        panel.add(bawah, BorderLayout.SOUTH);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
}

