package Runners;

import Context.TestContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


//@RunWith(Cucumber.class)
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty,html:target/cucumber-reports/cucumber.html,json:target/cucumber-reports/cucumber.json,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = "cucumber.options", value = "--threads 4")
@ConfigurationParameter(key = "cucumber.glue", value = "classpath:StepDefinitions,classpath:Hooks")
@ConfigurationParameter(key = "cucumber.tags", value = "@Portfolio")
@ConfigurationParameter(key = "cucumber.execution.parallel.enabled", value = "true")
@Execution(ExecutionMode.CONCURRENT)
public class TestRunner {


    @AfterAll
    public static void tearDownAfterClass() {
        System.out.println("Test suite finished. Closing WebDriver.");
        TestContext.quitDriver();
    }


}
