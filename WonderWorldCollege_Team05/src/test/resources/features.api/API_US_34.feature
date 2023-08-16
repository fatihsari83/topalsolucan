Feature: US_34 As an administrator, I want to access the Visitor information of a visitor with a given ID through API connection.
  Scenario: TC_01 api/visitorsId endpoint'ine gecerli authorization bilgileri ve dogru data (id) iceren bir POST body gönderildiğinde dönen
             status code'in 200 oldugu ve response body'deki message bilgisinin "Success" olduğu doğrulanmali



  Scenario: TC_02 api/visitorsId endpoint'ine gecersiz authorization bilgileri veya gecersiz data (id) iceren bir POST body gönderildiğinde dönen
             status code'in 403 oldugu ve response body'deki message bilgisinin "failed" olduğu doğrulanmali


  Scenario: TC_030

