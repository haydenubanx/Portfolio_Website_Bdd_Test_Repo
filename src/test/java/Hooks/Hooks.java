package Hooks;

import Context.TestContext;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks extends TestContext {

    private TestContext testContext;
    private WebDriver driver;


    public Hooks(TestContext testContext) {
        this.testContext = testContext;
        this.driver = this.testContext.getWebDriver();
    }


    @AfterAll
    public void closeWebDriver() {

        driver.quit();
    }
}
