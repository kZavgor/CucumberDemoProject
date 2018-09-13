Feature: Post code validation

  @UI
  Scenario Outline: Website search engine handles correctly invalid or non existing post codes.

    Given I am on a base page
    When  I enter "<post code>" post code
    Then I should see "<error text>" error message

    Examples:

      | post code | error text                   |
      | B99 9AA   | Unable to find the postcode. |
      | W6        | Invalid postcode.            |
      | EC1A 1BB  | Invalid postcode.            |
      | W6$ 0NW   | Invalid postcode.            |
      |           | Invalid postcode.            |

  @UI
  Scenario Outline: Website returns a weather forecast for valid post code.

    Given I am on a base page
    When  I enter "<post code>" post code
    Then  I should see weather forecast table

    Examples:

      | post code |
      | W6 0NW    |
      | W60NW     |
      | w6 0nw    |


  @API
  Scenario Outline: Weather app handles correctly invalid or non existing post codes.

    Given I send "<post code>" post code to weather app
    Then  I receive an error message "<error text>"

    Examples:

      | post code | error text                  |
      | B99 9AA   | Unable to find that address |
      | W6        | Invalid Address             |
      | EC1A 1BB  | Invalid Address             |
      | W6$ 0NW   | Invalid Address             |
      |           | Invalid Address             |

  @API
  Scenario Outline: Weather app handles correctly invalid or non existing post codes.

    Given I send "<post code>" post code to weather app
    Then  I receive current weather forecast

    Examples:

      | post code |
      | W6 0NW    |
      | W60NW     |
      | w6 0nw    |