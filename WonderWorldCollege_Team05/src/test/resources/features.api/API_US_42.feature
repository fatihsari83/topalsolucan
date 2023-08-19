Feature: US42 As an administrator, I want to be able to delete a Notice record from the system through API connection.


  Scenario: TC01 When a valid authorization and correct data (id)
  are sent with a DELETE body to the api/deleteNotice endpoint,
  the response status code should be 200, and the response body's message should be "Success" to be verified.

    * Set "api/deleteNotice" parameters
    * For admin, On the relevant page, the last delete query is sent with the valid authorization information and the response is recorded
    * For admin, verifies that status code is 200
    * For admin, verifies that the message information is "Success"


  Scenario: TC02 When invalid authorization or incorrect data (id)
  is sent with a DELETE body to the api/deleteNotice endpoint, the response
  status code should be 403, and the response body's message should be "failed" to be verified.

    * Set "api/deleteNotice" parameters
    * For admin, On the relevant page, the last delete query is sent with the invalid authorization information and the response is recorded
    * For admin, verifies that status code is 403
    * For admin, verifies that the message information is "failed"


  Scenario: TC03 The DeletedId in the response body should be the same as the id sent in the DELETE request body to the api/deleteNotice
  endpoint to be verified.

    * Set "api/deleteNotice" parameters
    * For admin, On the relevant page, the last delete query is sent with the valid authorization information and the response is recorded
    * It is verified that the DeletedId information in the response body is the same as the ID information in the DELETE request body sent to the endpoint.


  Scenario: TC04 The deletion of the desired notice record through the API should be validated.

    * Set "api/deleteNotice" parameters
    * For admin, On the relevant page, the last delete query is sent with the valid authorization information and the response is recorded
    * Set "api/getNoticeById" parameters
    * For admin a post query is sent to the getNoticeById endpoint with the valid id in the returned response.
    * It is verified that the status of the returned response is 403 and the message information is failed.
