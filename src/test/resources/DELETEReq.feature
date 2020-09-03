Feature: Delete Requests
  Test the delete operation

  Scenario: Verify DELETE operation after POST
    Given Ensure to Perform POST operation for "/posts" with body as
      | id | title       | author |
      | 6  | API Testing | Demo   |
    And Perform DELETE operation for "/posts/{postid}"
      | postid |
      | 6      |
    And Perform GET operation with path parameter for "/posts/{postid}"
      | postid |
      | 6      |
    Then user "should not" see the body with title as "API Testing"