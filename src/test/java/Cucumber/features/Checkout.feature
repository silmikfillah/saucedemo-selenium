@Checkout
Feature: Standard User Checkout
  As a user, I want to checkout products so that I can make an order

  @Positive
  Scenario Outline: Verify to checkout products using valid credentials
    Given I already logged in to the Saucedemo Website to make an order
    And I already added products to cart and go to Cart page
    When I click Checkout button
    And I fill in address by input First Name with <first_name>
    And I fill in address by input Last Name with <last_name>
    And I fill in address by input Zip Postal Code with <postal_code>
    And I click Continue button
    Then I should see the next step for set address: <status>
    And I click Finish button
    And Order successfully created and a success message appear

    Examples:
    | first_name | last_name | postal_code | status |
    | Silmi      | Fillah    | 12345       | success|

  @Negative
  Scenario Outline: Verify to checkout products using invalid data: <condition>
    Given I already logged in to the Saucedemo Website to make an order
    And I already added products to cart and go to Cart page
    When I click Checkout button
    And I fill in address by input First Name with <first_name>
    And I fill in address by input Last Name with <last_name>
    And I fill in address by input Zip Postal Code with <postal_code>
    And I click Continue button
    Then I should see the next step for set address: <status>

    Examples:
    | first_name | last_name | postal_code | status            | condition             |
    | Silmi      | Fillah    | empty       | required postcode | empty zip postal code |
    | Silmi      | empty     | 12345       | required lname    | empty last name       |
    | empty      | Fillah    | 12333       | required fname    | empty first name      |
    | empty      | empty     | empty       | required fname    | without enter any data|

