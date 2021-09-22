Feature: Testing a REST API
  As a user
  I want to look up a non-existent user's profile on github
  So that I can be sure that the username can not be found on github

  @GithubFailed @Github
  Scenario Outline: when a user checks a non-existent user on github, github would respond 'not found'
    Given github user profile api
    And a random non-existent username
    When I look for <username> via the api
    Then github respond: <statusCode>
    Examples:
      |username |statusCode|
      |eugenp2  |404       |

  @GithubSuccess @Github
  Scenario Outline: when a user checks a non-existent user on github, github would respond 'found'
    Given github user profile api
    When I look for <username> via the api
    Then github respond: <statusCode>
    Examples:
      |username |statusCode|
      |mhabiib  |200       |