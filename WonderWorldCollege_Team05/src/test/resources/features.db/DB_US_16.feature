Feature: List the last 10 records from the online_admission table.

  Scenario: List the last 10 records from the online_admissions table.

    Given Creates a new database connection.
    And Query16 is being prepared
    Then The query is sent to online_admissions table and results are validated
    And Database connection is closed