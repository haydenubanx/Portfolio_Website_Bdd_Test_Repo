package org.example.Windows;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JButton runTestsButton;
    private JButton stopTestsButton;
    private JProgressBar progressBar;
    private JTextArea resultArea;
    private JScrollPane scrollPane;

    public MainWindow() {
        setTitle("Test Runner");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        runTestsButton = new JButton("Run Tests");
        runTestsButton.setBackground(Color.GREEN);
        stopTestsButton = new JButton("Stop Tests");
        stopTestsButton.setBackground(Color.RED);
        stopTestsButton.setEnabled(false);
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setPreferredSize(new Dimension(780, 550));

        scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
        add(progressBar, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(runTestsButton);
        buttonPanel.add(stopTestsButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getRunTestsButton() {
        return runTestsButton;
    }

    public JButton getStopTestsButton() {
        return stopTestsButton;
    }

    public void setProgressBarVisible(boolean visible) {
        progressBar.setVisible(visible);
    }

    public void setProgressBarIndeterminate(boolean indeterminate) {
        progressBar.setIndeterminate(indeterminate);
    }

    public void appendResult(String result) {
        resultArea.append(result);
        autoScroll();
    }

    private void autoScroll() {
        resultArea.setCaretPosition(resultArea.getDocument().getLength());
    }
}