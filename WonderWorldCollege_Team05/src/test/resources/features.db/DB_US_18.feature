Feature: List the number of students in the onlineexam_students table based on unique student_session_id values
  @yy
  Scenario: List the number of students in the onlineexam_students table based on unique student_session_id values

    Given Creates a new database connection.
    And Query18 is being prepared
    Then The query is sent to onlineexam_students and results are validated.
    And Database connection is closed
