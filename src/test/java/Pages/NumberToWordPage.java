package Pages;

import Context.TestContext;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class NumberToWordPage {

    TestContext testContext;

    private final WebDriver driver;


    @Setter
    @Getter
    @FindBy(id = "NumberToWord")
    private WebElement inputNumField;

    @Setter
    @Getter
    @FindBy(id = "submit-Button")
    private WebElement submitButton;

    @Setter
    @Getter
    WebElement outputWordText = null;

    public NumberToWordPage(TestContext testContext) {

        this.driver = testContext.getWebDriver();

        this.testContext = testContext;


        PageFactory.initElements(driver, this);
    }


}
