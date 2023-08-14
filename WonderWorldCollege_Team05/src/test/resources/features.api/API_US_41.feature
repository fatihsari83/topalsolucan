Feature: US41 As an administrator, I want to update the registered Notice information in the system through API connection.

  Scenario: TC01 When a valid authorization and correct data (id, type, title, description, slug) are sent with a PATCH body to the
            api/updateNotice endpoint, the response status code should be 200, and the response body's message should be "Success" to be verified.

    * Set "api/updateNotice" parameters
    * For admin, On the relevant page, the last patch query is sent with the valid authorization information and the response is recorded
    * For admin, verifies that status code is 200
    * For admin, verifies that the message information is "Success"

    Scenario: TC02 When invalid authorization or missing/incorrect data (id) is sent with a PATCH body (type, title, description, slug) to the
              api/updateNotice endpoint, the response status code should be 403, and the response body's message should be "failed" to be verified.

      * Set "api/updateNotice" parameters
      * For admin, prepare request body for admin endpoint with invalid authorization information and record response
      * For admin, verifies that status code is 403
      * For admin, verifies that the message information is "failed"

      Scenario: TC03 The updateId in the response body should be the same as the id sent in the PATCH request body to the
                api/updateNotice endpoint to be verified.

      * Set "api/updateNotice" parameters
      * For admin, On the relevant page, the last patch query is sent with the valid authorization information and the response is recorded
      * It should be verified that the updateId information in the response body is the same as the id information in the sent PATCH request body.


    Scenario: TC04    The update of the desired notice record through the API should be validated.

      * Set "api/updateNotice" parameters
      * For admin, On the relevant page, the last patch query is sent with the valid authorization information and the response is recorded
      * Set "api/getNoticeById" parameters
      * For admin A post query is sent to the getNoticeById endpoint with the id in the returned response.
      * The update of the desired notice record through the API should be validated





