Feature: Post Request samples
  Test POST operation using REST-assured library

  Scenario: Verify POST Operation
    Given use POST operation for "/posts"

  Scenario: Verify Post operation for Profile
    Given use POST operation for "/posts/{profileNo}/profile" with body
#      | name | profile |
      | Elif | 3 |
    Then user should see the body has name as "Elif"