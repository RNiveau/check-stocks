Feature: If stock rsi is 2% around high or low std, it's eligible

  Scenario: Last rsi is over high std
    Given a generated stock list with a mobile avg 50
    And a last stock price 56
    And a volume for last stock of 100001
    When I run eligibility DRSI
    Then The stock is not eligible
