Feature: US_38 As an administrator, I want to access the Notice List through API connection.


  Scenario: TC_01 When a valid authorization is sent with a GET request to the api/getNotice endpoint, the response status code
  should be 200, and the response message should be "Success" to be verified.

    * Set "api/getNotice" parameters
    * Records response for Admin with valid authorization information
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"

  Scenario: TC_02 When invalid authorization is sent with a GET request to the api/getNotice endpoint, the response status code should be 403,
  and the response message should be "failed" to be verified.

    * Set "api/getNotice" parameters
    * Prepare request body for admin api_alumniEventsId endpoint with invalid authorization information and record response
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"


  Scenario Outline: TC_03 The content of the lists in the response body should be verified. For example, the id should be "34", the type should be "notice",
  the slug should be "wonder-world-college-receives-accreditation-for-its-outstanding-business-program", the title should be
  "Wonder World College Receives Accreditation for its Outstanding Business Program", the date should be "2023-07-04", and so on.
  The "is_active" field should be "no", and the "created_at" field should be "2023-05-30 17:47:20". Other fields such as event_start,
  event_end, event_venue, description, meta_title, meta_keyword, feature_image, publish_date, publish, and sidebar should also be validated accordingly.

    * Set "api/getNotice" parameters
    * Records response for Admin with valid authorization information
    Then Verifies that record has "id=34" includes <expectedData>

    Examples:
      | expectedData     |
      |id=349type=noticeslug=nullurl=nulltitle=noticedeneme12345date=2023-08-17event_start=nullevent_end=nullevent_venue=nulldescription=noticedenemeis_active=nocreated_at=2023-08-1702:14:55meta_title=meta_description=meta_keyword=feature_image=publish_date=2023-08-17publish=0sidebar=0|