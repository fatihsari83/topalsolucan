Feature: As an administrator, I want to access the Session List through API connection.

Scenario: TC_01 api/sessionList endpoint'ine gecerli authorization bilgileri ile bir
          GET request gönderildiginde dönen status code'un 200 oldugu
          ve response message bilgisinin "Success" oldugu dogrulanmali

       * Set "api/sessionList" parameters
       * Records response for Admin with valid authorization information
       * Verifies that status code is 200
       * Verifies that the message information is "Success"







  Scenario: TC_02 api/sessionList endpoint'ine gecersiz authorization bilgileri ile bir
            GET Request gönderildiginde dönen status code'un 403 oldugu ve response message bilgisinin
            "failed" oldugu dogrulanmali

       * Set "api/sessionList" parameters
       * Records response for Admin with valid authorization information
       * Verifies that status code is 403
       * Verifies that the message information is "failed"





    Scenario: TC_03 Response body icindeki lists icerigi (id'si = "11", olan veri içeriğindeki session: "2017-18",
              is_active: "no", created_at: "2017-04-20 02:41:37" ve updated_at: "0000-00-00" ) olduğu doğrulanmalı.


      * Set "api/sessionList" parameters
      * Records response for Admin with valid authorization information
      * Verifies that record includes "id=11,session=2017-18,is_active=no,created_at=2017-04-20 02:41:37,updated_at=0000-00-00"