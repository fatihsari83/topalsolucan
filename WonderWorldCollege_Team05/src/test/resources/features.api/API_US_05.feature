@AC
Feature: US05 As an administrator, I should be able to delete the visitor puspose record in the system via the API connection.

  Scenario: TC01 When a DELETE body containing valid authorization information and correct data (id)
  is sent to the api/visitorsPurposeDelete endpoint, it should be verified that the status code returned is 200 and the message information in the response body is "Success"

    * Set "api/visitorsPurposeAdd" parameters
    * Post request is send for visitorsPurposeDelete
    * Set "api/visitorsPurposeDelete" parameters
    * Delete request is send for visitorsPurposeDelete
    * Verifies that status code is 200
    * Verifies that the message information is "Success"

  Scenario: TC02 When a DELETE body containing invalid authorization information or wrong data (id)
  is sent to the api/visitorsPurposeDelete endpoint, it should be verified that the status code returned is 403 and the message information in the response body is "failed"

    * Set "api/visitorsPurposeAdd" parameters
    * Post request is send for visitorsPurposeDelete
    * Set "api/visitorsPurposeDelete" parameters
    * Incorrect deletion request is sent for visitorsPurposeDelete
    * Verifies that status code is 403
    * Verifies that the message information is "failed"

  Scenario: TC03 It should be verified that the DeletedId information in the response body is the same as the ID information in the DELETE request body sent to the api/visitorsPurposeDelete endpoint.

    * Set "api/visitorsPurposeAdd" parameters
    * Post request is send for visitorsPurposeDelete
    * Set "api/visitorsPurposeDelete" parameters
    * Delete request is send for visitorsPurposeDelete
    * Send a DELETE body with valid authorization information and correct data id to the apivisitorsPurposeDelete endpoint


  Scenario: TC04 "It should be verified via API that the visitor purpose record to be deleted via API is deleted.

    * Set "api/visitorsPurposeAdd" parameters
    * Post request is send for visitorsPurposeDelete
    * Set "api/visitorsPurposeDelete" parameters
    * Delete request is send for visitorsPurposeDelete
    * Confirm from the API that the visitor purpose record requested to be deleted from the API has been deleted.

