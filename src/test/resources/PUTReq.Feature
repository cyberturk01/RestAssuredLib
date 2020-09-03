Feature: PUT Request
  Test the put operation

  Scenario: Verify DELETE operation after POST
    Given Ensure to Perform POST operation for "/posts" with body as
      | id | title       | author |
      | 6  | API Testing | Demo   |
    And Perform PUT operation for "/posts/{postid}"
      | id | title         | author |
      | 6  | API Testing 2 | Demo   |
    And Perform GET operation with path parameter for "/posts/{postid}"
      | postid |
      | 6      |
    Then user "should" see the body with title as "API Testing 2"

