Feature: US_56 As an administrator (teacher), I want to access the Subjects List through API connection.
  @team05
  Scenario: TC_01 When a valid authorization is used to send a GET request to the "apiteacher/subjectsList" endpoint,
            the response status code should be 200, and the response message should be "Success."


    * Set "apiteacher/subjectsList" parameters
    * Records response for Teacher with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02  When a GET Request is sent to the apiteacher/subjectsList endpoint with invalid authorization information,
            it should be verified that the status code returned is 403 and the response message information is "failed"


    * Set "apiteacher/subjectsList" parameters
    * Records response for Teacher with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"



  Scenario: TC_03 Response body icindeki lists icerigi (id'si = "1", olan veri içeriğindeki name: "English", code: "210",
            type: "theory", is_active: "no", created_at: "2021-03-23 04:36:46", updated_at: null) ,
          (id'si = "4", olan veri içeriğindeki name: "Mathematics", code: "110", type: "practical", is_active: "no",
          created_at: "2021-03-23 04:37:22", updated_at: null),  (id'si = "6", olan veri içeriğindeki name: "Social Studies",
          code: "212", type: "theory", is_active: "no", created_at: "2021-03-23 04:37:51", updated_at: null) olduğu doğrulanmalı.



    * Set "apiteacher/subjectsList" parameters
    * Records response for Teacher with valid authorization information
    * List data is verified in response from apiteacher_subjectsList endpoint

