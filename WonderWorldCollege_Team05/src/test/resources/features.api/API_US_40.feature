Feature: US40 As an administrator, I want to create a new Notice record through API connection.


  Scenario: TC01 When a valid authorization and correct data (type, title, description, slug) are sent with a POST body to the api/addNotice endpoint,
            the response status code should be 200, and the response body's message should be "Success" to be verified.

    * Set "api/addNotice" parameters
    * For admin, related page the post query is sent with valid authorization information and the response is saved
    * For admin, verifies that status code is 200
    * For admin, verifies that the message information is "Success"

    Scenario:  TC02 When invalid authorization or missing data (type, title, description, slug) is sent with a POST body to the api/addNotice endpoint,
              the response status code should be 403, and the response body's message should be "failed" to be verified.

      * Set "api/addNotice" parameters
      * For admin, prepare request body for admin endpoint with invalid authorization information and record response
      * For admin, verifies that status code is 403
      * For admin, verifies that the message information is "failed"

      Scenario: TC03 The creation of a new notice record through the API should be validated.

        * Set "api/addNotice" parameters
        * For admin, related page the post query is sent with valid authorization information and the response is saved
        * Set "api/getNoticeById" parameters
        * It is verified that the record has been created by sending the POST body to the getNoticeById endpoint with addId returned in the response body.

