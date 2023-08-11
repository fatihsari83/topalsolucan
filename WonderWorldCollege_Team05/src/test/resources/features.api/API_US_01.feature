Feature: US_01 As an administrator, I want to access the Purpose List through an API connection.


  Scenario: TC_01 api/visitorsPurposeList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde dönen
                   status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

       Path parametrelerini set eder.
       Admin icin, gecerli authorization bilgileri ile  response kaydeder
       Donen status kodunun 200 oldugunu dogrular
       Donen response message bilgisinin Success oldugunu dogrular


    * Set "api/visitorsPurposeList" parameters
    * Records response for Admin with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"

  Scenario:TC_02




  Scenario: TC_03