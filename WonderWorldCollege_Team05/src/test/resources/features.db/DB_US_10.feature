Feature: US10 A specific entry should be deletable from the visitors_book table.


  Scenario: TC01 Delete a requested data from the visitors_book table

    * Database connection is established.
    * DB US10 Query is run and the results are taken.
    * DB US10 Query results are validated.
    * Database connection is closed