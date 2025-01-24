package StepDefinitions;

import Context.TestContext;
import Pages.BattleShipPage;
import Pages.NumberToWordPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.*;

public class BattleshipStepDefs {

    TestContext testContext;
    BattleShipPage battleShipPage;

    Actions actions;

    public BattleshipStepDefs(TestContext testContext) {
        this.testContext = testContext;
        this.battleShipPage = new BattleShipPage(testContext);
        this.actions = new Actions(testContext.getWebDriver());
    }


    @And("^I reset the board$")
    public void iResetTheBoard() {

        battleShipPage.getRefreshBoard().click();
    }


    @When("^I (?:select|deselect) the (.*) powerup$")
    public void iSelectThePowerup(String powerup) {

        switch (powerup.toLowerCase()) {
            case "cannon":
                battleShipPage.getCannonPowerup().click();
                break;

            case "air raid":
                battleShipPage.getAirRaidPowerup().click();
                break;

            case "torpedo":
                battleShipPage.getTorpedoPowerup().click();
                break;
        }

        testContext.setLastUsedPowerUp(powerup);


    }


    @And("^I (?:use the powerup|take a shot)$")
    public void iUseThePowerup() {


        shootCell(testContext.getCurrShotX(), testContext.getCurrShotY());

    }

    @And("^I (?:use the powerup on|take a shot at) cell X:(.*) Y:(.*)$")
    public void iUseThePowerupOnCell(int xCord, int yCord) {

        shootCell(xCord, yCord);

    }

    @And("^I hover over the cell X:(.*) Y:(.*)$")
    public void iHoverOverCell(int xCord, int yCord) {

        hoverCell(xCord, yCord);

    }

    @Then("^the (.*) (?:powerup|hover effect) is applied correctly$")
    public void powerupAppliedCorrectly(String powerup) {

        int startingXcord = testContext.getCurrShotX();
        int startingYcord = testContext.getCurrShotY();
        int boardSize = 25;


        switch (powerup.toLowerCase()) {
            case "cannon":

                //Iterate over cannon xcoordinates containing left, right, and center
                for (int i = startingXcord - 1; i < startingXcord + 1; i++) {

                    //Iterate over cannon ycoordinates containing above, below, and center
                    for (int j = startingYcord - 1; j < startingYcord + 1; j++) {
                        if (i >= 1 && i <= boardSize && j >= 1 && j <= boardSize) {
                            String currCellColor = checkCellColor(i, j);
                            assertFalse(currCellColor.equalsIgnoreCase(battleShipPage.getNuetralColor()), "Color of cell was color of neutral cell indicating shot did not take place");
                        }
                    }
                }

                break;

            case "air raid":

                int k = startingYcord - 2;
                //check left slant
                for (int i = startingXcord - 2; i < startingXcord + 2; i++) {

                    if (i >= 1 && i <= boardSize && k >= 1 && k <= boardSize) {
                        String currCellColor = checkCellColor(i, k);
                        assertFalse(currCellColor.equalsIgnoreCase(battleShipPage.getNuetralColor()), "Color of cell was color of neutral cell indicating shot did not take place");
                    }
                    k++;
                }


                k = startingYcord + 2;
                //check left slant
                for (int i = startingXcord - 2; i < startingXcord + 2; i++) {

                    if (i >= 1 && i <= boardSize && k >= 1 && k <= boardSize) {
                        String currCellColor = checkCellColor(i, k);
                        assertFalse(currCellColor.equalsIgnoreCase(battleShipPage.getNuetralColor()), "Color of cell" + i + " " + k + " was color of neutral cell indicating shot did not take place");
                    }
                    k--;
                }

                break;

            case "torpedo":
                //Iterate over cannon xcoordinates for an entire row
                for (int j = 1; j < 26; j++) {

                        String currCellColor = checkCellColor(startingXcord, j);
                        assertFalse(currCellColor.equalsIgnoreCase(battleShipPage.getNuetralColor()), "Color of cell " + j + " " + startingYcord + " was color of neutral cell indicating shot did not take place");

                }
                break;
        }


    }


    @And("the shot coordinates text is updated correctly")
    public void theShotCoordinatesTextIsUpdatedCorrectly() {

        String battleshipText = battleShipPage.getShotOutputText().getText();

        assertTrue(battleshipText.contains("(" + testContext.getCurrShotX() + ", " + testContext.getCurrShotY()+")"), "Expected value of " + "(" + testContext.getCurrShotX() + ", " + testContext.getCurrShotY()+")" + " but observed value of " + battleshipText);


    }


    private void shootCell(int xcord, int ycord) {

        WebElement cellToSelect = testContext.getWebDriver().findElement(By.id("cell-" + xcord + "-" + ycord));

        WebElement startingCell = testContext.getWebDriver().findElement(By.id("cell-" + xcord + "-" + ycord));
        //Scroll into view
        ((JavascriptExecutor) testContext.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", startingCell);


        cellToSelect.click();

        testContext.setCurrShotX(xcord);
        testContext.setCurrShotY(ycord);
    }

    private void hoverCell(int xcord, int ycord) {

        WebElement cellToSelect = testContext.getWebDriver().findElement(By.id("cell-" + xcord + "-" + ycord));

        WebElement startingCell = testContext.getWebDriver().findElement(By.id("cell-" + xcord + "-" + ycord));
        //Scroll into view
        ((JavascriptExecutor) testContext.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", startingCell);

        testContext.setCurrShotX(xcord);
        testContext.setCurrShotY(ycord);


        actions.moveToElement(cellToSelect).perform();
    }

    private String checkCellColor(int xcord, int ycord) {

        WebElement cellToSelect = testContext.getWebDriver().findElement(By.id("cell-" + xcord + "-" + ycord));
        String color = cellToSelect.getCssValue("background-color");
        System.out.println(color);

        return color;
    }

    @And("I sink all of the ships")
    public void iSinkAllOfTheShips() {

        battleShipPage.getTorpedoPowerup().click();

        String shipsLeftText = battleShipPage.getShipsLeft().getText();
        String number = shipsLeftText.split(": ")[1];
        int shipsLeft = Integer.parseInt(number);
        int i = 1;

        shootCell(i,1);

        while (shipsLeft > 0 && i < 25) {
            battleShipPage.getTorpedoPowerup().click();
            i++;
            shootCell(i,1);

            shipsLeftText = battleShipPage.getShipsLeft().getText();
            number = shipsLeftText.split(": ")[1];
            shipsLeft = Integer.parseInt(number);
        }

    }

    @Then("the add to leaderboard popup appears")
    public void theAddToLeaderboardPopupAppears() {

        WebElement playerNameInputField = testContext.getWebDriver().findElement(By.id("player-name-input"));

        assertNotNull(playerNameInputField, "The game won pop-up window did not display");

    }

    @Then("the number of shots fired is updated correctly")
    public void theNumberOfShotsFiredIsUpdatedCorrectly() {

        String shotsUsedText = battleShipPage.getShotsUsed().getText();
        String number = shotsUsedText.split(": ")[1];
        int shotsUsed = Integer.parseInt(number);


        switch (testContext.getLastUsedPowerUp().toLowerCase()) {
            case "cannon":
                assertEquals(shotsUsed, 9, "Expected the cannon powerup to use 9 shots but instead used " + shotsUsed);
                break;

            case "air raid":
                assertEquals(shotsUsed, 9, "Expected the air raid powerup to use 9 shots but instead used " + shotsUsed);
                break;

            case "torpedo":
                assertEquals(shotsUsed, 25, "Expected the torpedo powerup to use 25 shots but instead used " + shotsUsed);
                break;

            default:
                assertEquals(shotsUsed, 1, "Expected 1 shot used but instead used " + shotsUsed);
                break;
        }

    }

    @Then("the effect is no longer applied")
    public void thePowerupEffectIsNoLongerApplied() {

        int startingXcord = testContext.getCurrShotX();
        int startingYcord = testContext.getCurrShotY();
        int boardSize = 25;

        hoverCell(startingXcord, startingYcord);

        //Iterate over cannon xcoordinates containing left, right, and center
        for (int i = startingXcord - 1; i < startingXcord + 1; i++) {

            //Iterate over cannon ycoordinates containing above, below, and center
            for (int j = startingYcord - 1; j < startingYcord + 1; j++) {
                if (i >= 1 && i <= boardSize && j >= 1 && j <= boardSize) {
                    if(i == startingXcord && j == startingYcord) {
                        continue;
                    }
                    else {
                        String currCellColor = checkCellColor(i, j);
                        assertTrue(currCellColor.equalsIgnoreCase(battleShipPage.getNuetralColor()), "Color of cell was color of neutral cell indicating shot did not take place");
                    }
                }
            }
        }
    }
}
