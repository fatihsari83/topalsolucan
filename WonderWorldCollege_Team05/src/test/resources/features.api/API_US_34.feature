Feature: US_34 As an administrator, I want to access the Visitor information of a visitor with a given ID through API connection.


  Scenario: TC_01 When a POST body with valid authorization information and correct data (id)
  is sent to the api/visitorsId endpoint, it should be verified that the status code returned is 200 and in the response body is "Success".

    * Set "api/visitorsId" parameters
    * Prepare request body for admin api_bookIssueId endpoint and record response
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"


  Scenario: TC_02 When a POST body containing invalid authorization information or invalid data (id)
  is sent to the api/visitorsId endpoint, it should be verified that the status code returned is 403 and response body is "failed".

    * Set "api/visitorsId" parameters
    * Prepare request body for admin api_alumniEventsId endpoint with invalid authorization information and record response
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"


  Scenario: TC_03 The contents of the list data (id, staff_id, student_session_id, source, purpose, name, email, contact, id_proof, no_of_people,
  date, in_time, out_time, note, image, meeting_with, created_at, class, section, staff_name, staff_surname, staff_employee_id, class_id,
  section_id, students_id, admission_no, student_firstname, student_middlename, student_lastname, role_id)in the response body should be verified.
  The values of these contents must comply with the id in the POST body sent.

    * Set "api/visitorsId" parameters
    * Prepare request body for admin api_visitorsId endpoint and record response
    * List data is verified in response from Admin api_visitorsId endpoint