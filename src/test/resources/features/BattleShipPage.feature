@Portfolio @BattleShipPage
Feature: These tests will test the functionality of the Battle Ship Page

  @powerUpApplied
  Scenario Outline: This test will test the functionality of the <powerup> powerup
    Given I navigate to the battleShip page
    And I reset the board
    When I select the <powerup> powerup
    And I use the powerup
    Then the <powerup> powerup is applied correctly
    And the shot coordinates text is updated correctly
    Examples:
      | powerup  |
      | cannon   |
      | air raid |
      | torpedo  |

  @powerUpHover
  Scenario Outline: This test will test the hover effect of the <powerup> powerup
    Given I navigate to the battleShip page
    And I reset the board
    When I select the <powerup> powerup
    And I hover over the cell X:13 Y:14
    Then the <powerup> hover effect is applied correctly
    Examples:
      | powerup  |
      | cannon   |
      | air raid |
      | torpedo  |

  @powerUpDeselect
  Scenario Outline: This test will validate that powerups are deselected on second selection
    Given I navigate to the battleShip page
    And I reset the board
    When I select the <powerup> powerup
    And I deselect the <powerup> powerup
    Then the effect is no longer applied
    Examples:
      | powerup  |
      | cannon   |
      | air raid |
      | torpedo  |

  @shotsApplied
  Scenario: This test will test that the number of shots updates correctly when no powerup is used
    Given I navigate to the battleShip page
    And I reset the board
    When I take a shot
    Then the number of shots fired is updated correctly

  @shotsAppliedPowerUp
  Scenario Outline: This test will test that the number of shots updates correctlyfor the <powerup> powerup
    Given I navigate to the battleShip page
    And I reset the board
    When I select the <powerup> powerup
    And I use the powerup
    Then the number of shots fired is updated correctly
    Examples:
      | powerup  |
      | cannon   |
      | air raid |
      | torpedo  |

  @leaderboardPopUp
  Scenario: This test will validate that the user is prompted to enter their score to the leaderboard upon sinking all ships
    Given I navigate to the battleShip page
    And I reset the board
    When I sink all of the ships
    Then the add to leaderboard popup appears
