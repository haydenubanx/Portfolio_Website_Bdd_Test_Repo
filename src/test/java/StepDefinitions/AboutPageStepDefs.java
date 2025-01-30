package StepDefinitions;

import Context.TestContext;
import Pages.AboutPage;
import Pages.BattleShipPage;
import Pages.CyberSecurityPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class AboutPageStepDefs {

    private final TestContext testContext;
    private final AboutPage aboutPage;
    private final CyberSecurityPage cyberSecurityPage;
    private final Actions actions;

    public AboutPageStepDefs(TestContext testContext) {
        this.testContext = testContext;
        this.aboutPage = new AboutPage(testContext);
        this.cyberSecurityPage = new CyberSecurityPage(testContext);
        this.actions = new Actions(testContext.getWebDriver());
    }

    @When("^I scroll to the (.*) element$")
    public void scrollToElement(String element) {

        WebElement startingCell = switch (element.toLowerCase()) {
            case "certifications & awards" -> aboutPage.getCertAwardsSection();
            case "education" -> aboutPage.getEducationSection();
            case "job experience" -> aboutPage.getJobExperienceSection();
            case "programming languages" -> aboutPage.getProgrammingLangSection();
            case "acceptable use" -> cyberSecurityPage.getAcceptableUseSection();
            case "dynamic security" -> cyberSecurityPage.getDynamicSecuritySection();
            case "clean desk" -> cyberSecurityPage.getCleanDeskSection();
            case "md5" -> cyberSecurityPage.getMd5Section();
            case "hash length" -> cyberSecurityPage.getHashLengthSection();
            case "fix mobile vulnerabilities" -> cyberSecurityPage.getFixMobileVulnerabilitiesSection();
            case "security plan" -> cyberSecurityPage.getSecurityPlanSection();
            case "security key" -> cyberSecurityPage.getSecretKeySection();
            case "public key" -> cyberSecurityPage.getPublicKeySection();
            case "static security" -> cyberSecurityPage.getStaticSecuritySection();
            case "mobile vulnerabilities" -> cyberSecurityPage.getMobileVulnerabilitiesSection();
            case "selecting tests" -> cyberSecurityPage.getSelectingTestCasesSection();
            default -> null;
        };


        //Scroll into view
        ((JavascriptExecutor) testContext.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", startingCell);

        testContext.getWebDriverWait().until(ExpectedConditions.visibilityOf(startingCell));


    }


    @Then("^the (.*) element is displayed$")
    public void elementIsDisplayed(String element) {

        WebElement startingCell = switch (element.toLowerCase()) {
            case "certifications & awards" -> aboutPage.getCertAwardsSection();
            case "education" -> aboutPage.getEducationSection();
            case "job experience" -> aboutPage.getJobExperienceSection();
            case "programming languages" -> aboutPage.getProgrammingLangSection();
            case "acceptable use" -> cyberSecurityPage.getAcceptableUseSection();
            case "dynamic security" -> cyberSecurityPage.getDynamicSecuritySection();
            case "clean desk" -> cyberSecurityPage.getCleanDeskSection();
            case "md5" -> cyberSecurityPage.getMd5Section();
            case "hash length" -> cyberSecurityPage.getHashLengthSection();
            case "fix mobile vulnerabilities" -> cyberSecurityPage.getFixMobileVulnerabilitiesSection();
            case "security plan" -> cyberSecurityPage.getSecurityPlanSection();
            case "security key" -> cyberSecurityPage.getSecretKeySection();
            case "public key" -> cyberSecurityPage.getPublicKeySection();
            case "static security" -> cyberSecurityPage.getStaticSecuritySection();
            case "mobile vulnerabilities" -> cyberSecurityPage.getMobileVulnerabilitiesSection();
            case "selecting tests" -> cyberSecurityPage.getSelectingTestCasesSection();
            default -> null;
        };

        assert startingCell != null;
        assertTrue(startingCell.isDisplayed(), "Expected element for " + element + " to be displayed but text for element was '" + startingCell.getText() + "'\n");
    }
}
