Feature: US12 List the first 5 employees in the staff table sorted by work experience from the oldest to the newest.


  Scenario: TC01 Rank the first 5 employees in the staff table according to the work_exp value from the oldest to the newest employee.


    * Database connection is established.
    * DB US12 Query is run and the results are taken.
    * DB US12 Query results are validated.
    * Database connection is closed