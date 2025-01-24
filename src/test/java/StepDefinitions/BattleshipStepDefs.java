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


    @When("^I select the (.*) powerup$")
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


    }


    @And("^I use the powerup$")
    public void iUseThePowerup() {


        shootCell(testContext.getCurrShotX(), testContext.getCurrShotY());

    }

    @And("^I use the powerup on cell X:(.*) Y:(.*)$")
    public void iUseThePowerupOnCell(int xCord, int yCord) {

        WebElement startingCell = testContext.getWebDriver().findElement(By.id("cell-" + xCord + "-" + yCord));
        //Scroll into view
        ((JavascriptExecutor) testContext.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", startingCell);

        shootCell(xCord, yCord);

    }

    @And("^I hover over the cell X:(.*) Y:(.*)$")
    public void iHoverOverCell(int xCord, int yCord) {

        WebElement startingCell = testContext.getWebDriver().findElement(By.id("cell-" + xCord + "-" + yCord));
        //Scroll into view
        ((JavascriptExecutor) testContext.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", startingCell);

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


        cellToSelect.click();

        testContext.setCurrShotX(xcord);
        testContext.setCurrShotY(ycord);
    }

    private void hoverCell(int xcord, int ycord) {

        WebElement cellToSelect = testContext.getWebDriver().findElement(By.id("cell-" + xcord + "-" + ycord));

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
}
