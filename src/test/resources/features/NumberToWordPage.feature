@Portfolio @NumberToWord
Feature: These tests will test the functionality of the number to word converter page on my portfolio site

  @tensTest
  Scenario Outline: This test will verify that the correct output is received for the <output_Word> power of ten
    Given I navigate to the numberToWord page
    When I enter the value <input_Number> into the number to word converter entry field
    Then the expected word value of <output_Word> is returned
    Examples:
      | input_Number | output_Word          |
      | 0            | zero                 |
      | 10           | ten                  |
      | 100          | one hundred          |
      | 1000         | one thousand         |
      | 10000        | ten thousand         |
      | 100000       | one hundred thousand |
      | 1000000      | one million          |
      | 10000000     | ten million          |
      | 100000000    | one hundred million  |
      | 1000000000   | one billion          |

  @negativeNumTest
  Scenario Outline: This test will verify that negative numbers are expected
    Given I navigate to the numberToWord page
    When I enter the value <input_Number> into the number to word converter entry field
    Then the expected word value of <output_Word> is returned
    Examples:
      | input_Number | output_Word  |
      | -1           | negative one |


  @andTest
  Scenario Outline: This test will check the <output_Word> condition in which the word 'and' should be displayed
    Given I navigate to the numberToWord page
    When I enter the value <input_Number> into the number to word converter entry field
    Then the expected word value of <output_Word> is returned
    Examples:
      | input_Number | output_Word                          |
      | 101          | one hundred and one                  |
      | 1100         | one thousand and one hundred         |
      | 1110         | one thousand one hundred and ten     |
      | 1111         | one thousand one hundred and eleven  |
      | 1101         | one thousand one hundred and one     |
      | 10001        | ten thousand and one                 |
      | 10101        | ten thousand one hundred and one     |
      | 101001       | one hundred and one thousand and one |
      | 110010       | one hundred and ten thousand and ten |
      | 1000010      | one million and ten                  |
      | 1000000010   | one billion and ten                  |
      | 1001000000   | one billion and one million          |
      | 1000001000   | one billion and one thousand         |
      | 1010000000   | one billion and ten million          |
      | 1000010000   | one billion and ten thousand         |

  @outOfBoundsTest
  Scenario Outline: This test will check that the error message is displayed for numbers out of bounds such as <input_Number>
    Given I navigate to the numberToWord page
    When I enter the value <input_Number> into the number to word converter entry field
    Then an error message is displayed
    Examples:
      | input_Number |
      | 10000000000  |
      | -10000000000 |

  @normalEntryTest
  Scenario Outline: Standard set of regression numbers such as <input_Number>
    Given I navigate to the numberToWord page
    When I enter the value <input_Number> into the number to word converter entry field
    Then the expected word value of <output_Word> is returned
    Examples:
      | input_Number | output_Word                                                                                                             |
      | 123456789    | One Hundred And Twenty Three Million Four Hundred And Fifty Six Thousand Seven Hundred And Eighty Nine                  |
      | 2378458923   | Two Billion Three Hundred And Seventy Eight Million Four Hundred And Fifty Eight Thousand Nine Hundred And Twenty Three |
      | 2034056      | Two Million Thirty Four Thousand And Fifty Six                                                                          |


