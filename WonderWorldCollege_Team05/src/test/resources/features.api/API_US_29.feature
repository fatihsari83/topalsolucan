Feature: US_29 As an administrator, I want to access the book information of a book with a given ID through API connection.

  Scenario: TC_01 When a valid authorization information and correct data (id) are sent with a POST body to the api/booksId endpoint,
            the expected status code is 200, and the response body's message should be "Success."

    * Set "api/booksId" parameters
    * Prepare request body for admin api_bookIssueId endpoint and record response
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"


  Scenario: TC_02 When invalid authorization information or invalid data (id) is sent with a POST body to the api/booksId endpoint,
            the expected status code is 403, and the response body's message should be "failed."


    * Set "api/booksId" parameters
    * Prepare request body for admin api_alumniEventsId endpoint with invalid authorization information and record response
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


  Scenario: TC_03 The content of the list in the response body should contain data with the following attributes:
            id, book_title, book_no, isbn_no, subject, rack_no, publish, author, qty, perunitcost, postdate, description,
            available, is_active, created_at, and updated_at.

    * Set "api/booksId" parameters
    * Prepare request body for admin api_bookIssueId endpoint and record response
    * List data is verified in response from Admin api_booksList endpoint

