
Feature: US_01 As an administrator, I want to access the Purpose List through an API connection.


   Scenario: TC_01 api/visitorsPurposeList endpoint'ine gecerli authorization bilgileri ile bir GET request
            gönderildiginde dönen status code'un 200 oldugu ve response message bilgisinin
            "Success" oldugu dogrulanmali

       Path parametrelerini set eder.
       Admin icin, gecerli authorization bilgileri ile  response kaydeder
       Donen status kodunun 200 oldugunu dogrular
       Donen response message bilgisinin Success oldugunu dogrular

     * Set "api/visitorsPurposeList" parameters
     * Records response for Admin with valid authorization information
     * Verifies that status code is 200
     * Verifies that the message information is "Success"


  Scenario: TC_02 api/visitorsPurposeList endpoint'ine gecersiz authorization bilgileri ile bir
                GET Request gönderildiginde dönen status code'un 403 oldugu ve
                response message bilgisinin "failed" oldugu dogrulanmali

      * Set "api/visitorsPurposeList" parameters
      * Records response for Admin with valid authorization information
      * Verifies that status code is 403
      * Verifies that the message information is "failed"




  Scenario: TC_03 Response body icindeki lists icerigi (id'si = "1", olan veri içeriğindeki
              visitors_purpose: "Marketing " ve created_at: "2023-01-18 01:07:12") olduğu doğrulanmalı.




     * Set "api/visitorsPurposeList" parameters
     * Records response for Admin with valid authorization information
     * Verifies that record includes "id=1,visitors_purpose=Marketing,created_at=2023-01-18 01:07:12"
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |


