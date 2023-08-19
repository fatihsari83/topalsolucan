Feature: As an administrator, I want to access the Alumni Events List through API connection.


  Scenario: TC_01 api/alumniEventsList endpoint'ine gecerli authorization bilgileri ile bir
  GET request gönderildiginde dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

    * Set "api/alumniEventsList" parameters
    * Records response for Admin with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02 api/alumniEventsList endpoint'ine gecersiz authorization bilgileri ile bir
  GET Request gönderildiginde dönen status code'un 403 oldugu ve response message bilgisinin "failed" oldugu dogrulanmali

    * Set "api/alumniEventsList" parameters
    * Records response for Admin with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


  Scenario: TC_03 Response body icindeki lists icerigi (id'si = "2", olan veri içeriğindeki title:
  "Reunion For 2020-21 Batch",  event_for: "class", session_id: "15", class_id: "1", section:
  "[\"1\",\"2\",\"3\"]", from_date: "2021-03-07 00:00:00", to_date: "2021-03-10 00:00:00", note: "", photo: "",
  is_active: "0", event_notification_message: "", show_onwebsite: "0" ve created_at: "2021-03-23 02:53:47"  olduğu)
  doğrulanmalı.

    * Set "api/alumniEventsList" parameters
    * Records response for Admin with valid authorization information
    * Verifies that record includes "id=2,title=Reunion For 2020-21 Batch,event_for=class,session_id=15,class_id=1,section=[\"1\",\"2\",\"3\"],from_date=2021-03-07 00:00:00,to_date=2021-03-10 00:00:00"