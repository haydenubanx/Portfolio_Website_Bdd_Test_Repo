package Context;


import io.cucumber.java.BeforeAll;
import lombok.Data;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.picocontainer.Disposable;
import org.picocontainer.Startable;

import java.time.Duration;

@Data
public class TestContext {

    private static WebDriver webDriver;
    // Define an explicit wait
    private WebDriverWait wait;
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public TestContext() {


    }

    public WebDriver getWebDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        options.addArguments("--disable-gpu"); // Disable GPU usage (optional, improves compatibility)
        options.addArguments("--window-size=1920,1080"); // Optional: Set window size


        if (webDriverThreadLocal.get() == null) {
            String userDir = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/drivers/chromedriver-mac-arm64/chromedriver");

            //Choose webDriver set method based on if headless mode is desired
            webDriverThreadLocal.set(new ChromeDriver(options));
//            webDriverThreadLocal.set(new ChromeDriver());

            this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

            webDriverThreadLocal.get().manage().window().maximize();
        }

        return webDriverThreadLocal.get();
    }

    public WebDriverWait getWebDriverWait() {


        this.wait = new WebDriverWait(webDriverThreadLocal.get(), Duration.ofSeconds(10));


        return wait;
    }

    public static void quitDriver() {
        if (webDriverThreadLocal.get() != null) {
            webDriverThreadLocal.get().quit();
            webDriverThreadLocal.remove();
        }
    }


}
