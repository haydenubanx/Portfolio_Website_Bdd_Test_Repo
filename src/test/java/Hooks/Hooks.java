package Hooks;

import Context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

    private final TestContext testContext;


    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }


    @After
    public static void closeWebDriver() {

        System.out.println("Closing WebDriver");

        TestContext.quitDriver();
    }
}
