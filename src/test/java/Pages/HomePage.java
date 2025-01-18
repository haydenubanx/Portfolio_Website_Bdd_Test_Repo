package Pages;

import Context.TestContext;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Data
public class HomePage {

    TestContext testContext;

    private final WebDriver driver;


    @Getter
    @FindBy(id = "Title Name")
    private WebElement titleNameButton;

    @Getter
    @FindBy(id = "Title Job")
    private WebElement titleJobButton;

    @Getter
    @FindBy(id = "Home Menu")
    private WebElement homeMenuButton;

    @Getter
    @FindBy(id = "About Me Menu")
    private WebElement aboutMeMenuButton;

    @Getter
    @FindBy(id = "CyberSecurity Menu")
    private WebElement cybersecurityMenuButton;

    @Getter
    @FindBy(id = "Sentiment Menu")
    private WebElement sentimentAnalysisMenuButton;

    @Getter
    @FindBy(id = "Battleship Menu")
    private WebElement battleshipMenuButton;

    @Getter
    @FindBy(id = "SQL Menu")
    private WebElement sqlMenuButton;

    @Getter
    @FindBy(id = "NumToWord Menu")
    private WebElement numToWordConverterMenuButton;

    @Getter
    @FindBy(id = "GitHub Button")
    private WebElement gitHubMenuButton;

    @Getter
    @FindBy(id = "nav dropdown button")
    private WebElement dropdownTriggerButton;


    public HomePage(TestContext testContext) {

        this.driver = testContext.getWebDriver();

        this.testContext = testContext;


        PageFactory.initElements(driver, this);
    }
}
