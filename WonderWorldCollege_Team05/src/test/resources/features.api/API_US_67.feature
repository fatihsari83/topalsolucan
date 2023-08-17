Feature: US_67 As a user (student), I want to update my registered Apply Leave information in the system through API connection.

  Scenario: TC_01 When valid authorization information and correct data (id, from_date, to_date, apply_date, reason) are
  sent with a PATCH request to the "apistudent/applyLeaveUpdate" endpoint, the response status code should be 200, and
  the response body's message should be "Success."




  Scenario: TC_02 When invalid authorization information or incomplete/incorrect data (id) are sent with a PATCH
  request to the "apistudent/applyLeaveUpdate" endpoint, the response status code should be 403, and the response
  body's message should be "failed."




  Scenario: TC_03 The value of the updateId in the response body should be the same as the id sent in the PATCH
  request body to the "apistudent/applyLeaveUpdate" endpoint.




  Scenario: TC_04 "The update of the apply leave record through the API should be verified.
  (Response body's updateId can be used to send a GET request to the ""apistudent/applyLeaveList"" endpoint to
  confirm the update of the record.)"