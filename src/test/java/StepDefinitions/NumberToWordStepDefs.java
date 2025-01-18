package StepDefinitions;

import Context.TestContext;
import Hooks.Hooks;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NumberToWordStepDefs  {

    private final TestContext testContext;

    public NumberToWordStepDefs(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("^I enter the value (.*) into the number to word converter entry field$")
    public void enterValueIntoConverter(String inputNumber) {

        WebElement inputNumField = testContext.getWebDriver().findElement(By.id("NumberToWord"));
        WebElement submitButton = testContext.getWebDriver().findElement(By.name("submit"));

        inputNumField.sendKeys(inputNumber);

        submitButton.click();


    }



}
