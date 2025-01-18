@Portfolio @NumberToWord
Feature: These tests will test the functionality of the number to word converter page on my portfolio site

  Scenario Outline: This test will verify that the correct output is received for a range of edge condition numbers
    Given I navigate to the numberToWord page
    When I enter the value <input_Number> into the number to word converter entry field
    Then the expected word value of <output_Word> is returned
    Examples:
      | input_Number | output_Word          |
      | 0            | zero                 |
      | 10           | ten               |
      | 100          | one hundred          |
      | 1000         | one thousand         |
      | 10000        | ten thousand         |
      | 100000       | one hundred thousand |

  Scenario Outline: This test will verify that negative numbers are expected
    Given I navigate to the numberToWord page
    When I enter the value <input_Number> into the number to word converter entry field
    Then the expected word value of <output_Word> is returned
    Examples:
      | input_Number | output_Word  |
      | -1           | negative one |