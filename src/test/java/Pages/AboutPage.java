package Pages;

import Context.TestContext;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage {

    TestContext testContext;

    private final WebDriver driver;

    @Setter
    @Getter
    @FindBy(id = "programming-lang-section")
    private WebElement programmingLangSection;

    @Setter
    @Getter
    @FindBy(id = "job-experience-section")
    private WebElement jobExperienceSection;

    @Setter
    @Getter
    @FindBy(id = "education-section")
    private WebElement educationSection;

    @Setter
    @Getter
    @FindBy(id = "cert-and-awards-section")
    private WebElement certAwardsSection;

    public AboutPage(TestContext testContext) {

        this.driver = testContext.getWebDriver();

        this.testContext = testContext;


        PageFactory.initElements(driver, this);
    }
}
