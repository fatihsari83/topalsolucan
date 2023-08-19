Feature: As an administrator, I want to access the Visitor Purpose information of a user with a given ID through API connection.


  Scenario: TC_01 api/visitorsPurposeId endpoint'ine gecerli authorization bilgileri ve
  dogru data (id) iceren bir POST body gönderildiğinde dönen status code'in 200 oldugu ve
  response body'deki message bilgisinin "Success" olduğu doğrulanmali

    * Set "api/visitorsPurposeId" parameters
    * Prepare request body for admin api_visitorsPurposeId endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02 api_visitorsPurposeId  endpoint'ine gecersiz authorization bilgileri veya
  gecersiz data (id)   iceren bir POST body gönderildiğinde dönen status code'in 403 oldugu
  ve response body'deki message bilgisinin "failed" olduğu doğrulanmali

    * Set "api/visitorsPurposeId" parameters
    * Prepare request body for admin api_visitorsPurposeId endpoint with invalid authorization information and record response
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


  Scenario: TC_03 Response body icindeki list datalarının (id, visitors_purpose, description, created_at)
  içerikleri doğrulanmali.

    * Set "api/visitorsPurposeId" parameters
    * Prepare request body for admin api_visitorsPurposeId endpoint and record response
    * Verifies that record includes "id,visitors_purpose,description,created_at"