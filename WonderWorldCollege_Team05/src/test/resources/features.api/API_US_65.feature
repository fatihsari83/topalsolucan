Feature: US_65 As a user (student), I want to access my Apply Leave information through API connection.


  Scenario: TC_01 When a valid authorization is sent with a GET request to the "apistudent/applyLeaveList" endpoint,
            the response status code should be 200, and the response message should be "Success."

    * Set "apistudent/applyLeaveList" parameters
    * Records response for Student with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02 When invalid authorization information is sent with a GET request to the "apistudent/applyLeaveList" endpoint,
            the response status code should be 403, and the response message should be "failed."

      * Set "apistudent/applyLeaveList" parameters
      * Records response for Student with invalid authorization information
      * Verifies that status code is 403
      * Verifies that the message information is "failed"


    Scenario: TC_03 The content of the "lists" in the response body should be verified for the following data:
              id, student_session_id, from_date, to_date, apply_date, status, docs, reason, approve_by, approve_date,
              request_type, created_at, firstname, middlename, lastname, staff_name, surname, class_id, section_id, class, section.

      * Set "apistudent/applyLeaveList" parameters
      * Records response for Student with valid authorization information
      * List data is verified in response from apistudent_applyLeaveList endpoint


