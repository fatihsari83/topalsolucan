Feature: US_43As an administrator (teacher), I want to access the Homework List through API connection.

  Scenario: TC_01 apiteacher/homeworkList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
                 dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

         Path parametrelerini set eder.
         Teacher icin, gecerli authorization bilgileri ile  response kaydeder
         Donen status kodunun 200 oldugunu dogrular
         Donen response message bilgisinin Success oldugunu dogrular


    * Set "apiteacher/homeworkList" parameters
    * Records response for Teacher with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"






