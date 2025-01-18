@Portfolio @HomePage
Feature: These tests will test the functionality of the Home Page


  Scenario Outline: These tests will test the navigation through links from the home site
    Given I navigate to the Home page
    When I click the <button> button
    Then I am directed to the <page> page
    Examples:
      | button           | page                  |
      | Home_Name_Icon   | Index                 |
      | Home_Cyber_Icon  | Index                 |
      | Home_Menu        | Home                  |
      | About_Menu       | aboutMe               |
      | Cyber_Menu       | cyberSecurity         |
      | Sentiment_Menu   | sentimentAnalysis     |
      | Battleship_Menu  | battleship            |
      | SQL_Menu         | sql                   |
      | Num_To_Word_Menu | numberToWordConverter |
      | GitHub           | github                |