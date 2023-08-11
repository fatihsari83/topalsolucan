Feature: US_019  As an administrator, I want to access the Book Issue information of a book issue with a given ID through API connection.
                 Bir yönetici (admin) olarak API bağlantısı üzerinden id'si verilen Book Issue bilgilerine erisebilmek istiyorum.

     Scenario: TC_01 api/bookIssueId endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren bir POST body gönderildiğinde
                  dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Success" olduğu doğrulanmali

        Path parametrelerini set eder.
        Admin icin api/bookIssueId andpointine request body hazirla ve response kaydeder.
        Donen status kodunun 200 oldugunu dogrular
        Donen response message bilgisinin Success oldugunu dogrular


       * Set "api/bookIssueId" parameters
       * Prepare request body for admin api_bookIssueId endpoint and record response
       * Verifies that status code is 200
       * Verifies that the message information is "Success"


    Scenario: TC_02  api/bookIssueId endpoint'ine gecersiz authorization bilgileri veya gecersiz data (id) iceren bir POST body gönderildiğinde
                     dönen status code'in 403 oldugu  ve response body'deki message bilgisinin "failed" olduğu doğrulanmali

        Path parametrelerini set eder.
        Admin icin api/bookIssueId andpointine gecersiz authorization bilgileriyle request body hazirla ve response kaydeder.
        Donen status kodunun 403 oldugunu dogrular
        Donen response message bilgisinin failed oldugunu dogrular


      * Set "api/bookIssueId" parameters
      * Prepare request body for admin api_bookIssueId endpoint with invalid authorization information and record response
      * Verifies that status code is 403
      * Verifies that the message information is "failed"



   Scenario: TC_03 Response body icindeki list datalarının
                  (id,book_id,member_id,duereturn_date,return_date,issue_date,is_returned,is_active,created_at) içerikleri doğrulanmali.

           Path parametrelerini set eder.
           Admin icin api/bookIssueId andpointine request body hazirla ve response kaydeder.
           Admin api/bookIssueId andpointinen gelen response da list datalari dogrulanir

      * Set "api/bookIssueId" parameters
      * Prepare request body for admin api_bookIssueId endpoint and record response
      * Verifies that record includes "id,book_id,member_id,duereturn_date,return_date,issue_date,is_returned,is_active,created_at"
