Feature: US_57 As a user (student), I want to access my Student Information Details through API connection.


  Scenario: TC_01 apistudent/getStudentClass endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde dönen
                  status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

  Path parametrelerini set eder.
  Student icin, gecerli authorization bilgileri ile  response kaydeder
  Donen status kodunun 200 oldugunu dogrular
  Donen response message bilgisinin Success oldugunu dogrular


    * Set "apistudent/getStudentClass" parameters
    * Records response for Student with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"





