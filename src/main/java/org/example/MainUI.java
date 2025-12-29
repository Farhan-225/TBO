package org.example;

import javax.swing.*;
import java.awt.*;

public class MainUI extends JFrame {

    private JTextArea inputArea;
    private JTextArea outputArea;

    public MainUI() {
        setTitle("Syntax Analyzer SPOK - Bahasa Jawa Ngoko");
        setSize(650, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== FONT =====
        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        Font textFont  = new Font("Segoe UI", Font.PLAIN, 14);

        // ===== PANEL UTAMA =====
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // ===== JUDUL =====
        JLabel title = new JLabel("SYNTAX ANALYZER SPOK", JLabel.CENTER);
        title.setFont(titleFont);
        title.setForeground(new Color(33, 37, 41));

        JLabel subtitle = new JLabel("Bahasa Jawa Ngoko", JLabel.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(90, 90, 90));

        JPanel header = new JPanel(new GridLayout(2,1));
        header.setBackground(new Color(245,247,250));
        header.add(title);
        header.add(subtitle);

        // ===== INPUT CARD =====
        JPanel inputCard = new JPanel(new BorderLayout(8,8));
        inputCard.setBackground(Color.WHITE);
        inputCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));

        JLabel inputLabel = new JLabel("Input Kalimat");
        inputLabel.setFont(labelFont);

        inputArea = new JTextArea(3, 40);
        inputArea.setFont(textFont);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        inputCard.add(inputLabel, BorderLayout.NORTH);
        inputCard.add(new JScrollPane(inputArea), BorderLayout.CENTER);

        // ===== BUTTON =====
        JButton analyzeBtn = new JButton("ANALISIS KALIMAT");
        analyzeBtn.setFont(labelFont);
        analyzeBtn.setBackground(new Color(13, 110, 253));
        analyzeBtn.setForeground(Color.WHITE);
        analyzeBtn.setFocusPainted(false);

        analyzeBtn.addActionListener(e -> {
            String hasil = Automata.analyze(inputArea.getText());
            outputArea.setText(hasil);

            if (hasil.contains("VALID SPOK")) {
                outputArea.setForeground(new Color(25, 135, 84)); // hijau
            } else {
                outputArea.setForeground(new Color(220, 53, 69)); // merah
            }
        });

        // ===== OUTPUT CARD =====
        JPanel outputCard = new JPanel(new BorderLayout(8,8));
        outputCard.setBackground(Color.WHITE);
        outputCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220,220,220)),
                BorderFactory.createEmptyBorder(10,10,10,10)
        ));

        JLabel outputLabel = new JLabel("Hasil Analisis");
        outputLabel.setFont(labelFont);

        outputArea = new JTextArea(10, 40);
        outputArea.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        outputCard.add(outputLabel, BorderLayout.NORTH);
        outputCard.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // ===== TENGAH =====
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(245,247,250));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerPanel.add(inputCard);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(analyzeBtn);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(outputCard);

        // ===== ADD KE FRAME =====
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
}

