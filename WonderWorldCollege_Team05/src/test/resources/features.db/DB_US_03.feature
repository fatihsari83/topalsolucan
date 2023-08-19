Feature: DB_US_03 Verify that the email of the student in the students table with firstname Brian and lastname Kohlar is brain@gmail.com.


  Scenario:TC_01 Verify that the email of the student whose first name is Brian and last
  name is Kohlar in the Students table is brain@gmail.com

    * Database connection established.
    * Query03 is being prepared
    * The query is sent to students table and results are validated
    * Database connection is closed