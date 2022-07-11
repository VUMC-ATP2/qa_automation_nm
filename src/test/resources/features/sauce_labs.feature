Feature: Login feature
  As a user,
  I want to have the possibility to log in
  So that I can purchase products

  Scenario Outline: Success Login
    Given I have navigate to login page
    When I login with '<login>' and '<password>'
    Then I am successfully logged in
    Examples:
      | login                   | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |




