package Pages;

import Context.TestContext;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Data
public class NumberToWordPage {

    TestContext testContext;
    WebElement inputNumField;
    WebElement submitButton;
    WebElement outputWordText;

    public NumberToWordPage(TestContext testContext) {

        this.testContext = testContext;

        inputNumField = testContext.getWebDriver().findElement(By.id("NumberToWord"));
        submitButton = testContext.getWebDriver().findElement(By.id("submit-Button"));
    }




}
