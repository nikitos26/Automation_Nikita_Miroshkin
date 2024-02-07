Feature: rabota.by test

  Scenario: Job search
    Given Open main page
    When Enter job - "Водитель"
    And Click on search button
    Then Verify result greatest then 0
    Then Verify job in title "водител"