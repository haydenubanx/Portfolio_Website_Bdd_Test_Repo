package StepDefinitions;

import Context.TestContext;
import Pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PageNavigationStepDefs {

    private final TestContext testContext;
    private final HomePage homePage;
    private String url = "https://haydeneubanks.co.uk/index.php";

    public PageNavigationStepDefs(TestContext testContext, HomePage homePage) {
        this.testContext = testContext;
        this.homePage = homePage;
    }


    @Given("^I navigate to the (.*) page$")
    public void navigateToPage(String inputPage) {

        // Open a new tab using JavaScript
        ((JavascriptExecutor) testContext.getWebDriver()).executeScript("window.open();");

        // Switch to the new tab
        for (String tab : testContext.getWebDriver().getWindowHandles()) {
            testContext.getWebDriver().switchTo().window(tab);
        }

        String tempUrl = url;


        switch (inputPage) {
            case "Home":
                tempUrl += "?clicked=Home";
                break;

            case "aboutMe":
                tempUrl += "?clicked=aboutMe";
                break;

            case "cyberSecurity":
                tempUrl += "?clicked=cyberSecurityHome";
                break;

            case "sentimentAnalysis":
                tempUrl += "?clicked=sentimentAnalysis";
                break;

            case "battleShip":
                tempUrl += "?clicked=battleship";
                break;

            case "SQL":
                tempUrl += "?clicked=SQLHome";
                break;

            case "numberToWord":
                tempUrl += "?clicked=NumberToWord";
                break;
        }

        testContext.getWebDriver().get(tempUrl);

    }

    @When("^I click the (.*) button$")
    public void iClickTheButtonButton(String inputButton) {

        Actions actions = new Actions(testContext.getWebDriver());

        switch (inputButton) {
            case "Home_Name_Icon":
                homePage.getTitleNameButton().click();
                break;

            case "Home_Cyber_Icon":
                homePage.getTitleJobButton().click();
                break;

            case "Home_Menu":
                homePage.getHomeMenuButton().click();
                break;

            case "About_Menu":
                homePage.getAboutMeMenuButton().click();
                break;

            case "Cyber_Menu":
                homePage.getCybersecurityMenuButton().click();
                break;

            case "Sentiment_Menu":
                actions.moveToElement(homePage.getDropdownTriggerButton()).perform();
                homePage.getDropdownTriggerButton().click();
                testContext.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Sentiment Menu")));
                homePage.getSentimentAnalysisMenuButton().click();
                break;

            case "Battleship_Menu":
                actions.moveToElement(homePage.getDropdownTriggerButton()).perform();
                homePage.getDropdownTriggerButton().click();
                testContext.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Sentiment Menu")));
                homePage.getBattleshipMenuButton().click();
                break;
            case "SQL_Menu":
                actions.moveToElement(homePage.getDropdownTriggerButton()).perform();
                homePage.getDropdownTriggerButton().click();
                testContext.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Sentiment Menu")));
                homePage.getSqlMenuButton().click();
                break;
            case "Num_To_Word_Menu":
                actions.moveToElement(homePage.getDropdownTriggerButton()).perform();
                homePage.getDropdownTriggerButton().click();
                testContext.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("Sentiment Menu")));
                homePage.getNumToWordConverterMenuButton().click();
                break;
            case "GitHub":
                homePage.getGitHubMenuButton().click();
                break;
        }

    }

    @Then("^I am directed to the (.*) page$")
    public void iAmDirectedToThePagePage(String inputPage) {

        String tempUrl = url;
        String actualUrl = testContext.getWebDriver().getCurrentUrl();

        switch (inputPage) {
            case "Index":
                break;

            case "Home":
                tempUrl += "?clicked=Home";
                break;

            case "aboutMe":
                tempUrl += "?clicked=aboutMe";
                break;

            case "cyberSecurity":
                tempUrl += "?clicked=cyberSecurityHome";
                break;

            case "sentimentAnalysis":
                tempUrl += "?clicked=sentimentAnalysis";
                break;

            case "battleship":
                tempUrl += "?clicked=battleship";
                break;

            case "sql":
                tempUrl += "?clicked=SQLHome";
                break;

            case "numberToWordConverter":
                tempUrl += "?clicked=NumberToWord";
                break;

            case "github":
                tempUrl = "https://github.com/haydenubanx";
                break;
        }


        assertEquals(actualUrl, tempUrl, "Expected URL of " + tempUrl + " but received actual URL of " + actualUrl);

    }
}
