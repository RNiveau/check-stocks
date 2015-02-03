Feature: If stock price is between low mobile avg (20 days) and high mobile avg, it's eligible

  Scenario: Last stock is over high mobile avg
    Given a generated stock list with a mobile avg 50
    And a high mobile avg 55
    And a low mobile avg 48
    And a last stock price 56
    And a volume for last stock of 100001
    When I run eligibility MM20
    Then The stock is not eligible

  Scenario: Last stock is below low mobile avg
    Given a generated stock list with a mobile avg 50
    And a high mobile avg 55
    And a low mobile avg 48
    And a last stock price 47
    And a volume for last stock of 100001
    When I run eligibility MM20
    Then The stock is not eligible

  Scenario: Last stock is between the low and high mobile avg
    Given a generated stock list with a mobile avg 70
    And a high mobile avg 75
    And a low mobile avg 65
    And a last stock price 74
    And a volume for last stock of 100001
    When I run eligibility MM20
    Then The stock is eligible

  Scenario: Last stock should be eligible but volume is too low
    Given a generated stock list with a mobile avg 70
    And a high mobile avg 75
    And a low mobile avg 65
    And a last stock price 74
    And a volume for last stock of 1000
    When I run eligibility MM20
    Then The stock is not eligible