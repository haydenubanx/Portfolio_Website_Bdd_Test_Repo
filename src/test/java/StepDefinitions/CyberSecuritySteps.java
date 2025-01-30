package StepDefinitions;

import Context.TestContext;
import Pages.CyberSecurityPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;

public class CyberSecuritySteps {

    private final CyberSecurityPage cyberSecurityPage;
    private final TestContext testContext;

    public CyberSecuritySteps(TestContext testContext) {
        this.testContext = testContext;
        this.cyberSecurityPage = new CyberSecurityPage(testContext);
    }


    @And("^the (.*) download button is interactable$")
    public void elementIsDisplayed(String element) {

        WebElement startingCell = switch (element.toLowerCase()) {
            case "acceptable use" -> cyberSecurityPage.getAcceptableUseButton();
            case "dynamic security" -> cyberSecurityPage.getDynamicSecurityButton();
            case "clean desk" -> cyberSecurityPage.getCleanDeskButton();
            case "md5" -> cyberSecurityPage.getMd5Button();
            case "hash length" -> cyberSecurityPage.getHashLengthButton();
            case "fix mobile vulnerabilities" -> cyberSecurityPage.getFixMobileVulnerabilitiesButton();
            case "security plan" -> cyberSecurityPage.getSecurityPlanButton();
            case "security key" -> cyberSecurityPage.getSecretKeyButton();
            case "public key" -> cyberSecurityPage.getPublicKeyButton();
            case "static security" -> cyberSecurityPage.getStaticSecurityButton();
            case "mobile vulnerabilities" -> cyberSecurityPage.getMobileVulnerabilitiesButton();
            case "selecting tests" -> cyberSecurityPage.getSelectingTestCasesButton();
            default -> null;
        };

        assert startingCell != null;
        assertTrue(startingCell.isEnabled(), "Expected element for " + element + " to be enabled but was not \n");
    }
}
