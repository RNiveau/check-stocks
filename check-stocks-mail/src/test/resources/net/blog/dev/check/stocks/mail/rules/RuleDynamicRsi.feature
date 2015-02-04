Feature: If stock rsi is 2% around high or low std, it's eligible

  Scenario: Last rsi is over high std
    Given a scenario from file ALT
    When I run eligibility DRSI
    Then The stock is not eligible

  Scenario: Last rsi is below low std
    Given a scenario from file EI
    When I run eligibility DRSI
    Then The stock is not eligible

  Scenario: Last rsi is near to low std
    Given a scenario from file AF
    When I run eligibility DRSI
    Then The stock is eligible

  Scenario: Last rsi is near to high std
    Given a scenario from file AI
    When I run eligibility DRSI
    Then The stock is eligible

  Scenario: Last rsi is between std but near enough
    Given a scenario from file ACA
    When I run eligibility DRSI
    Then The stock is not eligible
