Feature:US_24 List the name and ID of the top 10 income values from the income table based on the highest amount.

  Scenario: TC_01 List the name and ID of the top 10 income values from the income table based on the highest amount.


    * Connect to database
    * A query prepared with the given data "SELECT  FROM onlineexam_students ;" and Column name "student_session_id" is performed.
    * find the size of rowdata list
    * Close database