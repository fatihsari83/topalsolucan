Feature: US_33 As an administrator, I want to access the Visitor List through API connection.

  Scenario: TC_01 When a valid authorization information is used to send a GET request to the api/visitorsList endpoint,
  the expected status code is 200, and the response message should be "Success."

    * Set "api/visitorsList" parameters
    * Records response for Admin with valid authorization information
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: TC_02 When invalid authorization information is used to send a GET request to the api/visitorsList endpoint,
  the expected status code is 403, and the response message should be "failed."

    * Set "api/visitorsList" parameters
    * Prepare request body for admin api_alumniEventsId endpoint with invalid authorization information and record response
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"
  @11
  Scenario: TC_03 To validate the response body, we should check that the lists content contains the data with id equal to "250"
  and the following information: staff_id, student_session_id, source, purpose, name, email, contact, id_proof, no_of_people, date,
  in_time, out_time, note, image, meeting_with, created_at, class, section, staff_name, staff_surname, staff_employee_id, class_id,
  section_id, students_id, admission_no, student_firstname, student_middlename, student_lastname, and role_id. The values for this data should be as specified in the request.

    * Set "api/visitorsList" parameters
    * Prepare request body for admin api_visitorsList endpoint and record response
    Then List data is verified in response from Admin api_visitorsList endpoint