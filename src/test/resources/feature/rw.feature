Feature: rw test

  Scenario: Search tickets
    Given Open home page
    When I enter from "Витебск"
    And I enter to "Минск"
    And Click on search
    Then I check result page, direction from "Витебск" to "Минск"