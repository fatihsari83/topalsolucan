Feature: US_066 As a user (student), I want to create a new Apply Leave record through API connection.


  Scenario: TC_01 When valid authorization information and correct data (from_date, to_date, apply_date, reason) are sent
  with a POST request to the "apistudent/applyLeaveAdd" endpoint, the response status code should be 200, and the response
  body's message should be "Success."

    * Set "apistudent/applyLeaveAdd" parameters
    * Prepare request body for student apistudent_applyLeaveAdd endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02 When invalid authorization information or incomplete data (from_date, to_date, apply_date, reason)
  are sent with a POST request to the "apistudent/applyLeaveAdd" endpoint, the response status code should be 403, and
  the response body's message should be "failed."

    * Set "apistudent/applyLeaveAdd" parameters
    * Prepare request body for student apistudent_applyLeaveAdd endpoint and record response with unvalid
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


  Scenario: TC_03 "The creation of the new apply leave record through the API should be verified.
  (Response body's addId can be used to send a GET request to the ""apistudent/applyLeaveList"" endpoint to confirm the
  creation of the record.)"

    * Set "apistudent/applyLeaveList" parameters
    * Prepare request body for student apistudent_applyLeaveList endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"