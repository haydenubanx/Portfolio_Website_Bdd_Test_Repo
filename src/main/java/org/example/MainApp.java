package org.example;

import org.example.Windows.MainWindow;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainApp {
    private static Process process;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);

            mainWindow.getRunTestsButton().addActionListener(e -> {
                mainWindow.getRunTestsButton().setEnabled(false);
                mainWindow.getStopTestsButton().setEnabled(true);
                mainWindow.setProgressBarVisible(true);
                mainWindow.appendResult("Tests Running...\n");

                new Thread(() -> {
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("mvn", "clean", "test", "-Dsurefire.suiteXmlFiles=src/test/resources/testng.xml");
                        processBuilder.redirectErrorStream(true);
                        process = processBuilder.start();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                            String finalLine = line;
                            SwingUtilities.invokeLater(() -> {
                                mainWindow.appendResult(finalLine + "\n");
                            });
                        }

                        int exitCode = process.waitFor();
                        System.out.println("Exited with code: " + exitCode);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        SwingUtilities.invokeLater(() -> {
                            mainWindow.appendResult("Tests Completed\n");
                            mainWindow.setProgressBarIndeterminate(false);
                            mainWindow.getRunTestsButton().setEnabled(true);
                            mainWindow.getStopTestsButton().setEnabled(false);
                        });
                    }
                }).start();
            });

            mainWindow.getStopTestsButton().addActionListener(e -> {
                if (process != null) {
                    process.destroyForcibly();
                    mainWindow.appendResult("Tests Stopped\n");
                    mainWindow.setProgressBarIndeterminate(false);
                    mainWindow.getRunTestsButton().setEnabled(true);
                    mainWindow.getStopTestsButton().setEnabled(false);
                }
            });
        });
    }
}