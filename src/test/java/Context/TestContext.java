package Context;


import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

        if (webDriverThreadLocal.get() == null) {
            String userDir = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/drivers/chromedriver-mac-arm64/chromedriver");
            webDriverThreadLocal.set(new ChromeDriver());
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
