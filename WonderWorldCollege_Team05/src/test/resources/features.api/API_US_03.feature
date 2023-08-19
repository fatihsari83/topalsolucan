Feature: US_03 As an administrator, I want to create a new visitor purpose record through API connection.

  Scenario: TC_01 When valid authorization information and correct data (visitors_purpose, description)
  are sent in the POST body to the api/visitorsPurposeAdd endpoint, the expected status code is 200,
  and the message in the response body should be "Success."

    * Set "api/visitorsPurposeAdd" parameters
    * Prepare request body for admin api_visitorsPurposeAdd endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"

  Scenario: TC_02 When invalid authorization information or missing data (visitors_purpose, description) is sent in the
  POST body to the api/visitorsPurposeAdd endpoint, the expected status code is 403, and the message in the response body
  should be "failed."

    * Set "api/visitorsPurposeAdd" parameters
    * Records response for Admin with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"

  Scenario: TC_03 The successful creation of a new visitor purpose record via the API should be validated.
  (This can be confirmed by using the addId returned in the response body to send a POST body to the api/visitorsPurposeId
  endpoint and verify the record is created.)

    * Set "api/visitorsPurposeId" parameters
    * Records response for Admin with valid authorization information
    * Prepare request body for admin api_visitorsPurposeId endpoint and record response



