Feature: Verify different GET operations using REST-assured

  Scenario: Verify one author of the post
    Given use GET operation for "/posts"
    Then user should see the author name as "Gokhan"

  Scenario: Verify collection of authors in the post
    Given use GET operation for "/posts"
    Then user should see the author names

  Scenario: Verify Parameter of GET one author of the post
    Given use GET operation for "/posts"
    Then user should see verify GET parameter

  Scenario: Verify QueryParameter of GET one author of the post
    Given use GET operation for "/posts"
    Then user should see verify GET queryParameter


  Scenario: Verify Get operation with bearer authentication token
    Given Perform Authentication operation for "/auth/login" with body
      | email                  | password |
      | yigitgokhan1@gmail.com | abc123   |
    Given Perform GET operation for with Token "/posts/1"
    Then user should see the author name as "Gokhan"