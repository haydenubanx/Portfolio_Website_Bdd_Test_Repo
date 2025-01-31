package org.example.Windows;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JButton runTestsButton;
    private JTextPane resultArea;
    private JProgressBar progressBar;

    public MainWindow() {
        setTitle("Test Runner");
        setSize(800, 600); // Increase the default size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set Look and Feel to system default (macOS)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Override scrollbar background
        UIManager.put("ScrollBar.background", new Color(45, 45, 45));

        runTestsButton = new RoundButton("Run Tests");
        runTestsButton.setPreferredSize(new Dimension(120, 40)); // Set button size
        runTestsButton.setBackground(new Color(52, 152, 219)); // Set button color to a professional blue
        runTestsButton.setForeground(Color.WHITE); // Set text color to white
        runTestsButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        resultArea = new JTextPane();
        resultArea.setEditable(false);
        resultArea.setForeground(Color.WHITE); // Set text color to white
        resultArea.setBackground(new Color(30, 30, 30)); // Set background color to dark gray
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12)); // Set font to Consolas for better readability

        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        progressBar.setBackground(new Color(30, 30, 30));
        progressBar.setForeground(new Color(52, 152, 219)); // Set progress bar color to match button

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30)); // Set panel background color to dark gray

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.getViewport().setBackground(new Color(30, 30, 30)); // Set scroll pane viewport background color to dark gray
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the button horizontally
        bottomPanel.setBackground(new Color(30, 30, 30)); // Set bottom panel background color to dark gray
        bottomPanel.add(runTestsButton);

        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getRunTestsButton() {
        return runTestsButton;
    }

    private boolean testsRunPrinted = false;

    public void appendResult(String text, Color color) {
        StyledDocument doc = resultArea.getStyledDocument();
        Style style = resultArea.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);
        try {
            if (text.startsWith("[INFO] Tests run:") && !testsRunPrinted) {
                String[] parts = text.split(", ");
                for (String part : parts) {
                    if (part.startsWith("[INFO] Tests run:")  && !testsRunPrinted) {
                        StyleConstants.setForeground(style, Color.GREEN);
                        doc.insertString(doc.getLength(), part.split(": ")[0] + ": ", style);
                        doc.insertString(doc.getLength(), part.split(": ")[1] + ", ", style);
                    } else if (part.startsWith("Failures:")  && !testsRunPrinted) {
                        StyleConstants.setForeground(style, Color.RED);
                        doc.insertString(doc.getLength(), part.split(": ")[0] + ": ", style);
                        doc.insertString(doc.getLength(), part.split(": ")[1] + ", ", style);
                    } else if (part.startsWith("Errors:")  && !testsRunPrinted) {
                        StyleConstants.setForeground(style, Color.ORANGE);
                        doc.insertString(doc.getLength(), part.split(": ")[0] + ": ", style);
                        doc.insertString(doc.getLength(), part.split(": ")[1] + ", ", style);
                    } else if (part.startsWith("Skipped:")  && !testsRunPrinted) {
                        StyleConstants.setForeground(style, Color.GRAY);
                        doc.insertString(doc.getLength(), part.split(": ")[0] + ": ", style);
                        doc.insertString(doc.getLength(), part.split(": ")[1], style);
                        doc.insertString(doc.getLength(), "\n\n", style);
                        testsRunPrinted = true;
                    }
                }
            } else if (text.startsWith("[INFO] Total time:")) {
                StyleConstants.setForeground(style, Color.BLUE);
                doc.insertString(doc.getLength(), text + "\n", style);
            } else {
                doc.insertString(doc.getLength(), text + "\n", style);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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