@Portfolio @BattleShipPage
Feature: These tests will test the functionality of the Battle Ship Page


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