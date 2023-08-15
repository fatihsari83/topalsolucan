Feature:US_23 List the first 3 staff members with the longest employment duration in the staff table according to their departments.


  Scenario: TC_01 List the first 3 staff members with the longest employment duration in the staff table according to their departments.


    * Connect to database
    * A query prepared with the given data "SELECT  FROM onlineexam_students ;" and Column name "student_session_id" is performed.
    * find the size of rowdata list
    * Close database