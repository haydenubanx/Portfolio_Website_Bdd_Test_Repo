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
public class BattleShipPage {

    TestContext testContext;

    private final WebDriver driver;

    @Setter
    @Getter
    @FindBy(id = "cannon")
    private WebElement cannonPowerup;

    @Setter
    @Getter
    @FindBy(id = "airRaid")
    private WebElement airRaidPowerup;

    @Setter
    @Getter
    @FindBy(id = "torpedo")
    private WebElement torpedoPowerup;

    @Getter
    @FindBy(id = "ships-left")
    private WebElement shipsLeft;

    @Getter
    @FindBy(id = "shots-used")
    private WebElement shotsUsed;

    @Getter
    @FindBy(id = "fire-result")
    private WebElement shotOutputText;

    @Getter
    @FindBy(id = "reset-button")
    private WebElement refreshBoard;

    String hitColor = "rgba(255, 69, 58, 1.0)";
    String nuetralColor = "rgba(69, 76, 84, 1)";
    String missColor = "rgba(157, 165, 173, 1)";

    public BattleShipPage(TestContext testContext) {

        this.driver = testContext.getWebDriver();

        this.testContext = testContext;


        PageFactory.initElements(driver, this);
    }


}
