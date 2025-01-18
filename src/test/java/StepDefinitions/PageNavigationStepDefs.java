package StepDefinitions;

import Context.TestContext;
import io.cucumber.java.en.Given;
import org.openqa.selenium.JavascriptExecutor;


public class PageNavigationStepDefs {

    private final TestContext testContext;

    public PageNavigationStepDefs(TestContext testContext) {
        this.testContext = testContext;
    }


    @Given("^I navigate to the (.*) page$")
    public void navigateToPage(String inputPage) {

        // Open a new tab using JavaScript
        ((JavascriptExecutor) testContext.getWebDriver()).executeScript("window.open();");

        // Switch to the new tab
        for (String tab : testContext.getWebDriver().getWindowHandles()) {
            testContext.getWebDriver().switchTo().window(tab);
        }

        String url = "https://haydeneubanks.co.uk/index.php";

        switch (inputPage) {
            case "Home":
                url += "?clicked=Home";
                break;

            case "aboutMe":
                url += "?clicked=aboutMe";
                break;

            case "cyberSecurity":
                url += "?clicked=cyberSecurityHome";
                break;

            case "sentimentAnalysis":
                url += "?clicked=sentimentAnalysis";
                break;

            case "battleShip":
                url += "?clicked=battleship";
                break;

            case "SQL":
                url += "?clicked=SQLHome";
                break;

            case "numberToWord":
                url += "?clicked=NumberToWord";
                break;
        }

        testContext.getWebDriver().get(url);

    }
}
