Feature: Calculate and List the average passing_percentage values from the onlineexam table.

  Scenario: Calculate and list the average passing_percentage values from the onlineexam table.

    Given Creates a new database connection.
    And Query17 is being prepared
    Then The query is sent to onlineexam and results are validated
    And Database connection is closed