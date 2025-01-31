package org.example.Windows;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JButton runTestsButton;
    private JTextArea resultArea;
    private JProgressBar progressBar;

    public MainWindow() {
        setTitle("Test Runner");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set Look and Feel to system default (macOS)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Override scrollbar background
        UIManager.put("ScrollBar.background", Color.BLACK);

        runTestsButton = new RoundButton("Run Tests");
        runTestsButton.setPreferredSize(new Dimension(100, 30)); // Set button size
        runTestsButton.setBackground(new Color(40, 205, 65)); // Set button color to Mac maximize green
        runTestsButton.setForeground(Color.WHITE); // Set text color to white
        runTestsButton.setFont(new Font("Arial", Font.BOLD, 12));

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setForeground(Color.WHITE); // Set text color to white
        resultArea.setBackground(Color.BLACK); // Set background color to near black
        resultArea.setLineWrap(true); // Enable line wrap
        resultArea.setWrapStyleWord(true); // Enable word wrap

        progressBar = new JProgressBar();
        progressBar.setVisible(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK); // Set panel background color to near black

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.getViewport().setBackground(Color.BLACK); // Set scroll pane viewport background color to near black
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the button horizontally
        bottomPanel.add(runTestsButton);

        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getRunTestsButton() {
        return runTestsButton;
    }

    public void appendResult(String text) {
        resultArea.append(text);
    }

    public void setProgressBarVisible(boolean visible) {
        progressBar.setVisible(visible);
    }

    public void setProgressBarIndeterminate(boolean indeterminate) {
        progressBar.setIndeterminate(indeterminate);
    }

    // Custom button class with rounded edges
    private static class RoundButton extends JButton {
        public RoundButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            g2.setColor(getForeground());
            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - 2;
            g2.drawString(getText(), x, y);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            g2.dispose();
        }
    }
}