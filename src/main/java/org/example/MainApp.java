package org.example;

import org.example.Windows.MainWindow;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Color;

public class MainApp {
    private static Process process;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);

            mainWindow.getRunTestsButton().addActionListener(e -> {
                mainWindow.getRunTestsButton().setEnabled(false);
                mainWindow.setProgressBarVisible(true);
                mainWindow.appendResult("Tests Running...", Color.WHITE);

                new Thread(() -> {
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("mvn", "clean", "test", "-Dsurefire.suiteXmlFiles=src/test/resources/testng.xml");
                        processBuilder.redirectErrorStream(true);
                        process = processBuilder.start();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        StringBuilder cucumberResults = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                            if (line.startsWith("Scenario")) {
                                String finalLine = "Running Test: " + line;
                                SwingUtilities.invokeLater(() -> {
                                    mainWindow.appendResult(finalLine + "\n\n", Color.WHITE);
                                });
                            } else if (line.startsWith("[INFO] Tests run:")) {
                                cucumberResults.append(line).append("\n");
                            }
                        }

                        int exitCode = process.waitFor();
                        System.out.println("Exited with code: " + exitCode);

                        SwingUtilities.invokeLater(() -> {
                            mainWindow.appendResult(cucumberResults.toString(), Color.WHITE);
                        });

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        SwingUtilities.invokeLater(() -> {
                            mainWindow.appendResult("Tests Completed", Color.WHITE);
                            mainWindow.setProgressBarIndeterminate(false);
                            mainWindow.getRunTestsButton().setEnabled(true);
                        });
                    }
                }).start();
            });
        });
    }
}