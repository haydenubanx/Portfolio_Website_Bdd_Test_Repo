package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        // Define the target folder path (relative to the project root)
        String targetFolder = System.getProperty("user.dir") + "/target/screenshots/";

        // Create the target folder if it doesn't exist
        File directory = new File(targetFolder);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the folder structure
        }

        // Capture the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            // Define the target file with the provided screenshot name
            File targetFile = new File(targetFolder + screenshotName + ".png");

            // Save the screenshot to the target folder
            FileUtils.copyFile(screenshot, targetFile);

            System.out.println("Screenshot saved to: " + targetFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}