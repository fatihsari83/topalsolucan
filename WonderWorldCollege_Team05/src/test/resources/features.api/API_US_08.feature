Feature: As an administrator, I want to access the alumni events information between the specified start date and end date through API connection.

  Scenario: TC_01 api/alumniEventsByDateRange endpoint'ine gecerli authorization bilgileri ve
          dogru datalar (start, end) iceren bir POST body gönderildiğinde dönen status code'in 200 oldugu
          ve response body'deki message bilgisinin "Success" olduğu doğrulanmali


    * Set "api/alumniEventsByDateRange" parameters
    * Prepare request body for admin api_alumniEventsByDateRange endpoint and record response
    * Verifies that status code is 200
    * Verifies that the message information is "Success"





  Scenario: TC_02 api/alumniEventsByDateRange  endpoint'ine gecersiz authorization bilgileri veya gecersiz datalar
             (start, end) iceren bir POST body gönderildiğinde dönen status code'in
             403 oldugu ve response body'deki message bilgisinin "failed" olduğu doğrulanmali


    * Set "api/alumniEventsByDateRange" parameters
    * Prepare request body for admin api_alumniEventsByDateRange endpoint with invalid authorization information and record response
    * Verifies that status code is 403
    * Verifies that the message information is "failed"






  Scenario: TC_03 api/alumniEventsByDateRange endpoint'ine gecerli authorization bilgileri ve dogru datalar
  (start: "2021-01-14 00:00:00", end: "2023-03-15 23:59:00") iceren bir
  POST body gönderildiğinde dönen response bodydeki list içinde dataların (id'si = "1", olan veri içeriğindeki
  title: "Covid-19 Seminar",  event_for: "class", session_id:"16", class_id: "1", section: "[\"1\"]",
  from_date: "2021-03-01 00:00:00", to_date: "2021-03-16 00:00:00",
  note: "COVID-19 is the disease caused by a new coronavirus called SARS-CoV-2.  WHO first learned of this new virus
  on 31 December 2019, following a report of a cluster of cases of â€˜viral pneumoniaâ€™ in Wuhan, Peopleâ€™s
  Republic of China.", photo: "", is_active: "0", event_notification_message: "GAVI'S RESPONSE\r\nRespond and protect:
  With COVID-19 now reported in almost all Gavi-eligible countries, the Vaccine Alliance is providing immediate funding
  to health systems, enabling countries to protect health care workers, perform vital surveillance and training,
  and purchase diagnostic tests.\r\n\r\nMaintain, restore and strengthen: Gavi will support countries to adapt and restart
  immunisation services, rebuild community trust and catch up those who have been missed both before and during the pandemic,
  while also investing in strengthening immunisation systems to be more resilient and responsive to the communities they serve.
  \r\n\r\nEnsure global equitable access: Gavi is co-leading COVAX, the global effort to securing a global response to COVID-19
  that is effective and fair, using its unique expertise to help identify and rapidly accelerate development, production and
  delivery of COVID-19 vaccines so that anyone that needs them, gets them.", show_onwebsite: "0" ve
  created_at: "2021-03-23 02:54:29" olduğu ) içerikleri doğrulanmali

    * Set "api/visitorsPurposeId" parameters
    * Prepare request body for admin api_visitorsPurposeId endpoint and record response
    * Verifies that record includes ""