@AddToCart @Positive
Feature: Standard User Add To Cart
  As a standard user, I want to add products to cart so that I can make an order.

  @CART001
  Scenario: Verify to add products to cart using valid credentials
    Given I already login to the Saucedemo Website
    And I on the homepage
    When I select one of products
    And I click the Add To Cart button
    Then The product will be added to cart