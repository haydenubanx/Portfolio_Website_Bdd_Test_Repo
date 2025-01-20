package Hooks;

import Context.TestContext;
import Utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private final TestContext testContext;
    private Scenario scenario;  // Store the scenario object


    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }


    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @After
    public void closeWebDriver() {

        if (scenario.isFailed()) {
            // Capture screenshot on failure
            ScreenshotUtil.takeScreenshot(testContext.getWebDriver(), String.valueOf(scenario.getName()));
        }

        System.out.println("Closing WebDriver");

        TestContext.quitDriver();
    }
}
