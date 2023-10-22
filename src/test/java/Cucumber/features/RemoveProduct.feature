Feature: Standard User Remove Product from Cart
  As as user, I want to remove products from Cart

  Scenario: Verify to remove product from Cart
    Given I already login to the Website
    And I already added a product to cart
    When I go to Cart page
    And I click Remove button
    Then The product should be removed from cart