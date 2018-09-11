Feature: Post code validation

  @UI
  Scenario: Weather forecast table display current time in valid format

    Given I am on a base page
    When  I enter "W6 0NW" post code
    Then  I should see weather forecast table
    And   I current time having "DD/MM/YYYY HH:mm:ss" format
#
#  @UI
#  Scenario: Weather forecast table does not display weather properties with empty values
#
#    Given I am on a base page
#    When  I enter "" post code
#    Then  I should see weather forecast table
#    And   I current time having "" format