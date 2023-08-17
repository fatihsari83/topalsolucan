Feature: US43 As an administrator (teacher), I want to access the Homework List through API connection.

  Scenario: TC01 When a valid authorization is provided with a GET request to the "apiteacher/homeworkList" endpoint,
            the response status code should be 200, and the response message should be "Success."

    * Set "apiteacher/homeworkList" parameters
    * Records response for Teacher with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


    Scenario: TC02 When an invalid authorization is provided with a GET request to the "apiteacher/homeworkList" endpoint,
              the response status code should be 403, and the response message should be "failed."

      * Set "apiteacher/homeworkList" parameters
      * For Teacher, the get query is sent with invalid authorization information and the response is saved.
      * Verifies that status code is 403
      * Verifies that the message information is "failed"


    Scenario: TC_03_API_US_43 The content of the "lists" in the response body should be verified.
    * Set "apiteacher/homeworkList" parameters
    * For Teacher, the get query is sent with valid authorization information and the response is saved.
    * For Teacher, The content of the "lists" in the response body should be verified