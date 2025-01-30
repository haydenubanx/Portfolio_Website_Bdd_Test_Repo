@Portfolio @AboutPage
Feature: These tests will test the functionality of the About Page

  Scenario Outline: This test will verify that the page elements fly into view upon scroll
    Given I navigate to the aboutMe page
    When I scroll to the <element> element
    Then the <element> element is displayed
    Examples:
      | element                 |
      | Programming Languages   |
      | Job Experience          |
      | Education               |
      | Certifications & Awards |