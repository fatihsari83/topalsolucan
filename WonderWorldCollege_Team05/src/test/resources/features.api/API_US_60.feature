Feature: US_60 As a user (student), I want to access my Daily Assignment List through API connection.


  Scenario: TC_01 When a valid authorization is used to send a GET request to the "apistudent/dailyAssignmentList" endpoint,
            the response status code should be 200, and the response message should be "Success."

    * Set "apistudent/dailyAssignmentList" parameters
    * Records response for Student with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC-02 When invalid authorization is used to send a GET request to the "apistudent/dailyAssignmentList" endpoint,
            the response status code should be 403, and the response message should be "failed."

    * Set "apistudent/dailyAssignmentList" parameters
    * Records response for Student with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


  Scenario: TC_03 The response body should contain a field called "lists," which includes multiple data entries.
            Each entry should have various fields such as "id," "student_session_id," "subject_group_subject_id," "title," "description,"
            "attachment," "evaluated_by," "date," "evaluation_date," "remark," "created_at," "subject_name," "subject_code," and "subject_code."
            The content of each field should be verified to ensure the correctness of the response data.

    * Set "apistudent/dailyAssignmentList" parameters
    * Records response for Student with valid authorization information
    * List data is verified in response from apistudent_dailyAssignmentList endpoint


