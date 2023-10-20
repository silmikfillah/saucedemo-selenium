@Login
Feature: User Login
  As a user, I want to login to Saucedemo Website so that I can shopping some fashion stuffs

  @LGN001
  Scenario Outline: Verify to login as a standard user using <condition>
    Given I access saucedemo website and go to Login page
    When I input username with <username>
    And I input password with <password>
    And I click the Login button
    Then I should see the next step for login: <status>

    Examples:
    | username      | password     | status            | condition          |
    | standard_user | secret_sauce | success           | valid credentials  |
    | standard_user | secretsauce  | incorrect account | incorrect password |
    | standard_user | empty        | required password | empty password     |

  @LGN002
  Scenario Outline: Verify to login as a locked out user using <condition>
    Given I access saucedemo website and go to Login page
    When I input username with <username>
    And I input password with <password>
    And I click the Login button
    Then I should see the next step for login: <status>

    Examples:
      | username        | password     | status            | condition          |
      | locked_out_user | secret_sauce | locked out account| valid credentials  |
      | locked_out_user | secretsau0   | incorrect account | incorrect password |
      | locked_out_user | empty        | required password | empty password     |

  @LGN003
  Scenario Outline: Verify to login as a problem user using <condition>
    Given I access saucedemo website and go to Login page
    When I input username with <username>
    And I input password with <password>
    And I click the Login button
    Then I should see the next step for login: <status>

    Examples:
      | username     | password     | status            | condition          |
      | problem_user | secret_sauce | success           | valid credentials  |
      | problem_user | secretsau    | incorrect account | incorrect password |
      | problem_user | empty        | required password | empty password     |

  @LGN004
  Scenario Outline: Verify to login as a performance glitch user using <condition>
    Given I access saucedemo website and go to Login page
    When I input username with <username>
    And I input password with <password>
    And I click the Login button
    Then I should see the next step for login: <status>

    Examples:
      | username                | password     | status            | condition          |
      | performance_glitch_user | secret_sauce | success           | valid credentials  |
      | performance_glitch_user | secret_s111  | incorrect account | incorrect password |
      | performance_glitch_user | empty        | required password | empty password     |

  @LGN005
  Scenario Outline: Verify to login as an unregistered user <condition>
    Given I access saucedemo website and go to Login page
    When I input username with <username>
    And I input password with <password>
    And I click the Login button
    Then I should see the next step for login: <status>

    Examples:
      | username          | password     | status            | condition                |
      | unregistered_user | secret_sauce | incorrect account | using incorrect username |
      | empty             | secret_sauce | required username | using empty username     |
      | empty             | empty        | required username | without enter any data   |