Feature: US39 As an administrator, I want to access the Notice information of a user with a given ID through API connection.


  Scenario: TC01 When a valid authorization and correct data (id)
  are sent with a POST body to the api/getNoticeById endpoint, the response status code should be 200,  and the response body's message should be "Success" to be verified.

    * Set "api/getNoticeById" parameters
    * For admin, the post query is sent with valid authorization information and the response is saved
    * For admin, verifies that status code is 200
    * For admin, verifies that the message information is "Success"


  Scenario: TC02 When invalid authorization or invalid data (id)
  is sent with a POST body to the api/getNoticeById endpoint, the response status code should be 403, and the response body's message should be "failed" to be verified.

    * Set "api/getNoticeById" parameters
    * For admin, prepare request body for admin endpoint with invalid authorization information and record response
    * For admin, verifies that status code is 403
    * For admin, verifies that the message information is "failed"


  Scenario: TC03 The content of the list data in the response body should be validated. It should contain fields such as id,
  type, slug, url, title, date, event_start, event_end, event_venue, description, is_active, created_at, meta_title,
  meta_description, meta_keyword, feature_image, publish_date, publish, and sidebar, and the values of these fields
  should be verified accordingly.

    * Set "api/getNoticeById" parameters
    * For admin, the body post query is sent with valid authorization information and the response is saved
    * For admin, verifies that id is 95
    * For admin, verifies that the type information is "notice"
    * For admin, verifies that the slug information is "wonder"
    * For admin, verifies that the title information is "notice wonder"
    * For admin, verifies that the date information is "2023-08-12"
    * For admin, verifies that the description information is "notice deneme"
    * For admin, verifies that the is_active information is "no"
    * For admin, verifies that the created_at information is "2023-08-12 09:36:41"
    * For admin, verifies that the meta_title information is ""
    * For admin, verifies that the meta_description information is ""
    * For admin, verifies that the meta_keyword information is ""
    * For admin, verifies that the feature_image information is ""
    * For admin, verifies that the publish_date information is "2023-08-12"
    * For admin, verifies that the publish information is "0"
    * For admin, verifies that the sidebar information is "0"
    * For the administrator, the contents of the list data in the Response body that are null are validated.
