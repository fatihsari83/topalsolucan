@20
Feature: US20 As an administrator, I want to create a new Book Issue record through API connection.

  Scenario: [TC_001] When a valid authorization information and correct data (id) are sent in a POST body to the api/bookIssueId endpoint,
  the expected status code is 200,and the message in the response body should be "Success."

    * Set "api/bookIssueAdd" parameters
    * Post request is send for apibookIssueAdd
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: [TC_002] POST Request Failed Response for the Book Issue ID

    * Set "api/bookIssueAdd" parameters
    * Submit a PATCH body with invalid authorization information or missingincorrect data to the apivehicleUpdate endpoint
    * Verifies that status code is 403
    * Verifies that the message information is "failed"

  Scenario:[TC_003] The First Item of the Book Issue List is validated

    * Set "api/bookIssueAdd" parameters
    * Post request is send for apibookIssueAdd
    * Verify via API that the new book issue record to be created via API has been created.