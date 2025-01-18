package Context;


import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.it.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.picocontainer.Disposable;
import org.picocontainer.Startable;

public class TestContext  {

    private static WebDriver webDriver;

    public TestContext() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver-mac-arm64/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();

    }

    public WebDriver getWebDriver() {
        return webDriver;
    }





}
