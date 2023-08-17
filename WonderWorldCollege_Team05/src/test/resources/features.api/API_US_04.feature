Feature: US_04 As an administrator, I want to update the registered visitor purpose information in the system through API connection.


  Scenario: TC_01 When valid authorization information and correct data (id, visitors_purpose, description) are sent in
  the PATCH body to the api/visitorsPurposeUpdate endpoint, the expected status code is 200, and the message in the
  response body should be "Success."


    * Set "api/visitorsPurposeUpdate" parameters
    * Prepare patch request body for admin api_visitorsPurposeUpdate endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02 When invalid authorization information or missing/wrong data (id) is sent in the PATCH body (with visitors_purpose,
  description) to the api/visitorsPurposeUpdate endpoint,the expected status code is 403, and the message in the response body
  should be "failed."

    * Set "api/visitorsPurposeUpdate" parameters
    * Records response for Admin with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


  Scenario: TC_03 The updateId information in the response body should be validated to be the same as the id information
  in the PATCH request body sent to the api/visitorsPurposeUpdate endpoint.

    * Set "api/visitorsPurposeUpdate" parameters
    * Records response for Admin with valid authorization information
    * Prepare request body for admin api_visitorsPurposeUpdate endpoint and record response


  Scenario: TC_04 The successful update of the visitor purpose record via the API should be validated. (This can be confirmed by using
  the updateId returned in the response body to send a POST body to the api/visitorsPurposeId endpoint and verify the record is updated.)

    * Set "api/alumniEventsId" parameters
    * Records response for Admin with valid authorization information
    * Prepare request body for admin api_alumniEventsId endpoint and record response