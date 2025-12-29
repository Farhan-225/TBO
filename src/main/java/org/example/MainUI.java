package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainUI extends JFrame {

    private JTextArea inputArea;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel resultLabel;

    public MainUI() {
        setTitle("Syntax Analyzer SPOK - Bahasa Jawa Ngoko");
        setUndecorated(true); // borderless
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== FONT =====
        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        Font textFont  = new Font("Segoe UI Emoji", Font.PLAIN, 14);

        // ===== PANEL UTAMA =====
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // ===== HEADER =====
        JLabel title = new JLabel("SYNTAX ANALYZER SPOK", JLabel.CENTER);
        title.setFont(titleFont);

        JLabel subtitle = new JLabel("Bahasa Jawa Ngoko", JLabel.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);

        JPanel header = new JPanel(new GridLayout(2,1));
        header.setBackground(new Color(245,247,250));
        header.add(title);
        header.add(subtitle);

        // ===== INPUT =====
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

        // ===== BUTTONS =====
        JButton analyzeBtn = new JButton("ANALISIS");
        analyzeBtn.setFont(labelFont);
        analyzeBtn.setBackground(new Color(13, 110, 253));
        analyzeBtn.setForeground(Color.WHITE);
        analyzeBtn.setFocusPainted(false);

        JButton exitBtn = new JButton("KELUAR");
        exitBtn.setFont(labelFont);
        exitBtn.setBackground(new Color(220, 53, 69));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFocusPainted(false);

        // ===== RESULT LABEL =====
        resultLabel = new JLabel(" ", JLabel.CENTER);
        resultLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);

        // ===== TABLE =====
        tableModel = new DefaultTableModel(
                new Object[]{"Unsur Kalimat", "Status"}, 0
        );

        table = new JTable(tableModel);
        table.setFont(textFont);
        table.setRowHeight(28);
        table.setEnabled(false);

        JScrollPane tableScroll = new JScrollPane(table);

        // ===== ACTION ANALYZE =====
        analyzeBtn.addActionListener(e -> {
            tableModel.setRowCount(0);

            AnalysisResult r = Automata.analyze(inputArea.getText());

            tableModel.addRow(new Object[]{"👤 Subjek (S)", r.adaS ? "✅" : "❌"});
            tableModel.addRow(new Object[]{"⚙️ Predikat (P)", r.adaP ? "✅" : "❌"});
            tableModel.addRow(new Object[]{"📦 Objek (O)", r.adaO ? "✅" : "❌"});
            tableModel.addRow(new Object[]{"📍 Keterangan Tempat", r.adaKT ? "✅" : "❌"});
            tableModel.addRow(new Object[]{"⏰ Keterangan Waktu", r.adaKW ? "✅" : "❌"});

            if (r.validSPOK) {
                resultLabel.setText(
                        "<html><span style='color:#198754;'>✅ <b>KALIMAT VALID SPOK</b></span></html>"
                );
            } else {
                resultLabel.setText(
                        "<html><span style='color:#dc3545;'>❌ <b>KALIMAT TIDAK VALID SPOK</b></span></html>"
                );
            }
        });

        // ===== ACTION EXIT =====
        Runnable exitAction = () -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Apakah Anda yakin ingin keluar?",
                    "Konfirmasi Keluar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        };

        exitBtn.addActionListener(e -> exitAction.run());

        // ===== ESC SHORTCUT =====
        mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "EXIT");
        mainPanel.getActionMap().put("EXIT", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                exitAction.run();
            }
        });

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(new Color(245,247,250));
        buttonPanel.add(analyzeBtn);
        buttonPanel.add(exitBtn);

        // ===== CENTER PANEL =====
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(245,247,250));

        centerPanel.add(inputCard);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(buttonPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(resultLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(tableScroll);

        // ===== ADD =====
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
}
