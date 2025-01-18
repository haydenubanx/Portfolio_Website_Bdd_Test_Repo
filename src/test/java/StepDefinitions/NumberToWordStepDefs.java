package StepDefinitions;

import Context.TestContext;
import Pages.NumberToWordPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NumberToWordStepDefs  {

    private final TestContext testContext;
    private final NumberToWordPage numberToWordPage;

    public NumberToWordStepDefs(TestContext testContext) {
        this.testContext = testContext;
        this.numberToWordPage = new NumberToWordPage(this.testContext);
    }

    @When("^I enter the value (.*) into the number to word converter entry field$")
    public void enterValueIntoConverter(String inputNumber) {


        numberToWordPage.getInputNumField().sendKeys(inputNumber);

        testContext.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(numberToWordPage.getSubmitButton()));
        numberToWordPage.getSubmitButton().click();


    }


    @Then("^the expected word value of (.*) is returned$")
    public void theExpectedWordValueOfOutput_WordIsReturned(String expectedWord) {
        testContext.getWebDriverWait().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//py-script/div[2]"))
        );


        numberToWordPage.setOutputWordText(testContext.getWebDriver().findElement(By.xpath("//section//py-script/div[2]")));

        String outputText = numberToWordPage.getOutputWordText().getText();


        Assert.assertTrue("Expected output text to contain string value: " + expectedWord + "\n" +
                "But actual value was: " + "\n" + outputText, outputText.toLowerCase().contains(expectedWord.toLowerCase()));


    }
}
