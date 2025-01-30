package Pages;

import Context.TestContext;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CyberSecurityPage {

    TestContext testContext;

    private final WebDriver driver;

    @Setter
    @Getter
    @FindBy(id = "MD5")
    private WebElement md5Section;

    @Setter
    @Getter
    @FindBy(id = "security-plan")
    private WebElement securityPlanSection;

    @Setter
    @Getter
    @FindBy(id = "mobile-vulnerabilities")
    private WebElement mobileVulnerabilitiesSection;

    @Setter
    @Getter
    @FindBy(id = "fix-mobile-vulnerabilities")
    private WebElement fixMobileVulnerabilitiesSection;

    @Setter
    @Getter
    @FindBy(id = "fix-security-vulnerabilities")
    private WebElement fixMobileFixSecurityVulnerabilitiesSection;

    @Setter
    @Getter
    @FindBy(id = "hash-length")
    private WebElement hashLengthSection;

    @Setter
    @Getter
    @FindBy(id = "dynamic-security")
    private WebElement dynamicSecuritySection;

    @Setter
    @Getter
    @FindBy(id = "static-security")
    private WebElement staticSecuritySection;

    @Setter
    @Getter
    @FindBy(id = "public-key")
    private WebElement publicKeySection;

    @Setter
    @Getter
    @FindBy(id = "selecting-test-cases")
    private WebElement selectingTestCasesSection;

    @Setter
    @Getter
    @FindBy(id = "secret-key")
    private WebElement secretKeySection;

    @Setter
    @Getter
    @FindBy(id = "acceptable-use")
    private WebElement acceptableUseSection;

    @Setter
    @Getter
    @FindBy(id = "clean-desk")
    private WebElement cleanDeskSection;


    @Setter
    @Getter
    @FindBy(id = "MD5-button")
    private WebElement md5Button;

    @Setter
    @Getter
    @FindBy(id = "security-plan-button")
    private WebElement securityPlanButton;

    @Setter
    @Getter
    @FindBy(id = "mobile-vulnerabilities-button")
    private WebElement mobileVulnerabilitiesButton;

    @Setter
    @Getter
    @FindBy(id = "fix-mobile-vulnerabilities-button")
    private WebElement fixMobileVulnerabilitiesButton;

    @Setter
    @Getter
    @FindBy(id = "fix-security-vulnerabilities-button")
    private WebElement fixMobileFixSecurityVulnerabilitiesButton;

    @Setter
    @Getter
    @FindBy(id = "hash-length-button")
    private WebElement hashLengthButton;

    @Setter
    @Getter
    @FindBy(id = "dynamic-security-button")
    private WebElement dynamicSecurityButton;

    @Setter
    @Getter
    @FindBy(id = "static-security-button")
    private WebElement staticSecurityButton;

    @Setter
    @Getter
    @FindBy(id = "public-key-button")
    private WebElement publicKeyButton;

    @Setter
    @Getter
    @FindBy(id = "selecting-test-cases-button")
    private WebElement selectingTestCasesButton;

    @Setter
    @Getter
    @FindBy(id = "secret-key-button")
    private WebElement secretKeyButton;

    @Setter
    @Getter
    @FindBy(id = "acceptable-use-button")
    private WebElement acceptableUseButton;

    @Setter
    @Getter
    @FindBy(id = "clean-desk-button")
    private WebElement cleanDeskButton;

    public CyberSecurityPage(TestContext testContext) {

        this.driver = testContext.getWebDriver();

        this.testContext = testContext;


        PageFactory.initElements(driver, this);
    }
}
