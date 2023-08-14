Feature: US43 As an administrator (teacher), I want to access the Homework List through API connection.

  Scenario: TC01 When a valid authorization is provided with a GET request to the "apiteacher/homeworkList" endpoint,
            the response status code should be 200, and the response message should be "Success."

         Path parametrelerini set eder.
         Teacher icin, gecerli authorization bilgileri ile  response kaydeder
         Donen status kodunun 200 oldugunu dogrular
         Donen response message bilgisinin Success oldugunu dogrular


    * Set "apiteacher/homeworkList" parameters
    * Records response for Teacher with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"
