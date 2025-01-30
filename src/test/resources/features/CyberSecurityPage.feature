@Portfolio @CyberSecurityPage
Feature: These tests will test the functionality of the Cybersecurity Page

  Scenario Outline: This test will verify that the cyber page elements are interactable
    Given I navigate to the cyberSecurity page
    When I scroll to the <element> element
    Then the <element> element is displayed
    And the <element> download button is interactable
    Examples:
      | element                    |
      | Acceptable Use             |
      | dynamic security           |
      | clean desk                 |
      | md5                        |
      | hash length                |
      | fix mobile vulnerabilities |
      | security plan              |
      | security key               |
      | public key                 |
      | static security            |
      | mobile vulnerabilities     |
      | selecting tests            |