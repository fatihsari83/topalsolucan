Feature: US_68 As a user (student), I want to be able to delete my Apply Leave record from the system through API connection.


  Scenario: TC_01 When valid authorization information and correct data (id)
  are sent with a DELETE request to the
  "apistudent/applyLeaveDelete" endpoint, the response status code should be 200, and the response body's message
  should be "Success."




    * Set "apistudent/applyLeaveAdd" parameters
    * Prepare request body for student apistudent_applyLeaveAdd endpoint and record response with id
    * Set "apistudent/applyLeaveDelete" parameters
    * The request is sent with valid login information and the response is recorded
    * Verifies that status code is 200
    * Verifies that the message information is "Success"

  Scenario: TC_02 When invalid authorization information or incorrect data (id)
  are sent with a DELETE request to the



    * Set "apistudent/applyLeaveDelete" parameters
    * Prepare request body for student apistudent_applyLeaveDelete endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"

  Scenario: TC_02 When invalid authorization information or incorrect data (id) are sent with a DELETE request to the



  "apistudent/applyLeaveDelete" endpoint, the response status code should be 403, and the response body's message
  should be "failed."

    * Set "apistudent/applyLeaveDelete" parameters
    * Records response for Student with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"

  Scenario: TC_03 The "DeletedId" information in the response body should be verified to be the same as the "id"
  information sent in the DELETE request body to the "apistudent/applyLeaveDelete" endpoint.

    * Set "apistudent/applyLeaveAdd" parameters
    * Prepare request body for student apistudent_applyLeaveAdd endpoint and record response with id
    * Set "apistudent/applyLeaveDelete" parameters
    * The request is sent with valid login information and the response is recorded
    * Verifies that id number same as deleteId


  Scenario: TC_04 "It should be confirmed that the apply leave record intended to be deleted is indeed deleted from
  the API.(Verification can be done by sending a GET request to the ""apistudent/applyLeaveList"" endpoint using the
  ""DeleteId"" obtained from the response body. If the record is successfully deleted, it will not be present in the
  response body of the GET request.)"

    * Set "apistudent/applyLeaveAdd" parameters
    * Prepare request body for student apistudent_applyLeaveAdd endpoint and record response with id
    * Set "apistudent/applyLeaveDelete" parameters
    * The request is sent with valid login information and the response is recorded
    * Set "apistudent/applyLeaveList" parameters
    * Checking applyLeaveList that has deletedId