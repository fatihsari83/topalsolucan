Feature: US_09 As an administrator, I want to access the Alumni Events information with a given ID through API connection.


  Scenario: TC_01 api/alumniEventsId endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren bir POST body
  gönderildiğinde dönen status code'in 200 olduguve response body'deki message bilgisinin "Success" olduğu doğrulanmali


    * Set "api/alumniEventsId" parameters
    * Prepare request body for admin api_alumniEventsId endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02 api/alumniEventsId  endpoint'ine gecersiz authorization bilgileri veya gecersiz data (id) iceren bir
  POST body gönderildiğinde dönen status code'in 403 oldugu ve response body'deki message bilgisinin "failed" olduğu doğrulanmali


    * Set "api/alumniEventsId" parameters
    * Prepare request body for admin api_alumniEventsId endpoint with invalid authorization information and record response
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


 Scenario:  TC_03 API uzerinden olusturulmak istenen yeni Alumni Events kaydinin olustugu API uzerinden dogrulanmali.

   * Set "api/alumniEventsId" parameters
   * Prepare request body for admin api_alumniEventsId endpoint and record response
   * List data is verified in response from Admin api_alumniEventsId andpointin