package StepDefinitions;

import Context.TestContext;
import Pages.NumberToWordPage;
import Utils.ScreenshotUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class NumberToWordStepDefs {

    private final TestContext testContext;
    private final NumberToWordPage numberToWordPage;

    public NumberToWordStepDefs(TestContext testContext) {
        this.testContext = testContext;
        this.numberToWordPage = new NumberToWordPage(this.testContext);
    }

    @When("^I enter the value (.*) into the number to word converter entry field$")
    public void enterValueIntoConverter(String inputNumber) {


        try {
            numberToWordPage.getInputNumField().sendKeys(inputNumber);

            testContext.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(numberToWordPage.getSubmitButton()));
            ((JavascriptExecutor) testContext.getWebDriver()).executeScript("arguments[0].click();", numberToWordPage.getSubmitButton());
//            numberToWordPage.getSubmitButton().click();
        } catch (Exception e) {
            ScreenshotUtil.takeScreenshot(testContext.getWebDriver(), testContext.getTestId() + "-error_on_submit");
            System.out.println("An error occurred: " + e.getMessage());

        }


    }


    @Then("^the expected word value of (.*) is returned$")
    public void theExpectedWordValueOfOutput_WordIsReturned(String expectedWord) {
        testContext.getWebDriverWait().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//py-script[@id='py-internal-0']/div[2]"))
        );


        numberToWordPage.setOutputWordText(testContext.getWebDriver().findElement(By.xpath("//py-script[@id='py-internal-0']/div[2]")));

        String outputText = numberToWordPage.getOutputWordText().getText();


        Assert.assertTrue("Expected output text to contain string value: " + expectedWord + "\n" +
                "But actual value was: " + "\n" + outputText, outputText.toLowerCase().contains(expectedWord.toLowerCase()));


    }

    @Then("an error message is displayed")
    public void anErrorMessageIsDisplayed() {

        String expectedPhrase = "Please enter a number between -10 Billion and 10 Billion";

        testContext.getWebDriverWait().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//py-script[@id='py-internal-0']/div[2]"))
        );


        numberToWordPage.setOutputWordText(testContext.getWebDriver().findElement(By.xpath("//py-script[@id='py-internal-0']/div[2]")));

        String outputText = numberToWordPage.getOutputWordText().getText();


        Assert.assertTrue("Expected output to contain an error value \n" +
                "But actual value was: " + "\n" + outputText, outputText.toLowerCase().contains(expectedPhrase.toLowerCase()));


    }
}
