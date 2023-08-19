Feature: US_61 As a user (student), I want to access my daily assignment information with the 'id' through API connection.


  Scenario: TC_01 When a valid authorization and correct data (id) are sent in a POST body to the "apistudent/dailyAssignmentById" endpoint,
            the response status code should be 200, and the response body's message should be "Success."

    * Set "apistudent/dailyAssignmentById" parameters
    * Prepare request body for student apistudent_dailyAssignmentById endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02 When invalid authorization or invalid data (id) is sent in a POST body to the "apistudent/dailyAssignmentById" endpoint,
              the response status code should be 403, and the response body's message should be "failed."

    * Set "apistudent/dailyAssignmentById" parameters
    * Records response for Student with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


  Scenario: TC_03 The content of the list data (id, student_session_id, subject_group_subject_id, title, description, attachment,
                evaluated_by, date, evaluation_date, remark, created_at, subject_name, subject_code, subject_code)
                in the response body should be verified to ensure its correctness.

    * Set "apistudent/dailyAssignmentById" parameters
    * Records response for Student with valid authorization information
    * List data is verified in response from apistudent_dailyAssignmentById endpoint







