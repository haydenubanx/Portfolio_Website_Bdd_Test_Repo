package Runners;

import Context.TestContext;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {"StepDefinitions", "Hooks"}, tags = "@HomePage")
public class TestRunner {


    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Test suite finished. Closing WebDriver.");
        TestContext.quitDriver();
    }


}
