
Feature: US_53 As an administrator (teacher), I want to access the Classes List through API connection.

  Scenario: TC_01 apiteacher/classesList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
  dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

  Path parametrelerini set eder.
  Teacher icin, gecerli authorization bilgileri ile  response kaydeder
  Donen status kodunun 200 oldugunu dogrular
  Donen response message bilgisinin Success oldugunu dogrular


    * Set "apiteacher/classesList" parameters
    * Records response for Teacher with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"

  Scenario: TC_02  apiteacher/classesList endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde dönen
  status code'un 403 oldugu ve response message bilgisinin "failed" oldugu dogrulanmali

  Path parametrelerini set eder.
  apiteacher/classesList ile Teacher icin, gecersiz authorization bilgileri ile  response kaydeder
  Donen status kodunun 403 oldugunu dogrular
  Donen response message bilgisinin Failed oldugunu dogrular


    * Set "apiteacher/classesList" parameters
    * For Teacher with apiteacher-classesList, it saves response with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"

  Scenario: TC_03 apiteacher/classesList Response body icindeki lists icerigi (id'si = "1", olan veri içeriğindeki class: "Class 1",
  is_active: "no", created_at: "2021-03-22 07:46:51", updated_at: null) ,
  (id'si = "7", olan veri içeriğindeki class: "Class 7", is_active: "no", created_at: "2022-03-29 00:22:40", updated_at: null),
  (id'si = "12", olan veri içeriğindeki class: "Class 12", is_active: "no", created_at: "2022-03-29 00:22:40", updated_at: null)
  olduğu doğrulanmalı.

  Path parametrelerini set eder.
  Teacher icin, gecerli authorization bilgileri ile  response kaydeder
  apiteacher-classesList'deki lists icegindeki datalar dogrular


    * Set "apiteacher/classesList" parameters
    * Records response for Teacher with valid authorization information
    * apiteacher-classes Validates data in lists in List
