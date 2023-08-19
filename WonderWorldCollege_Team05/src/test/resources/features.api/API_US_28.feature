Feature: US_28 As an administrator, I want to access the Books List through API connection.


  Scenario: TC_01 When a valid authorization information is sent with a GET request to the api/booksList endpoint,
  the expected status code is 200 and the response message should be "Success."

    * Set "api/booksList" parameters
    * Records response for Admin with valid authorization information
    Then Verifies that status code is 200
    Then Verifies that the message information is "Success"


  Scenario: TC_02 When invalid authorization information is sent with a GET request to the api/booksList endpoint,
  the expected status code is 403, and the response message should be "failed."


    * Set "api/booksList" parameters
    * Prepare request body for admin api_alumniEventsId endpoint with invalid authorization information and record response
    Then Verifies that status code is 403
    Then Verifies that the message information is "failed"


  Scenario Outline: TC_03 Verify that it has a response body (id= "1", olan veri içeriğindeki book_title: "Multiplication and Division Grades 3-4",
  book_no: "78878", isbn_no: "", subject: "", rack_no: "110", publish: "Barbara Bando", author: "Barbara Bando",
  qty: "100", perunitcost: "12.00", postdate: "2022-05-04", description: " The duo dump her in a nearby river after a failed attempt to hang her.
  Tonya survives, and the two men are arrested by Sheriff Ozzie Walls.", available: "yes", is_active: "no", created_at: "2022-05-02 03:02:39", updated_at": null) .

    * Set "api/booksList" parameters
    * Records response for Admin with valid authorization information
    Then Verifies that record has "id=3" includes <expected>

    Examples:
      | expected                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
      | id=3book_title=TheAstronomyBook(BigIdeas)book_no=145520isbn_no=8-56652-98565subject=Sciencerack_no=102publish=Bluerosepublisherauthor=DKqty=50perunitcost=1800.00postdate=2022-05-06description=Plot.InthetownofCantonMississippiten-year-oldAfricanAmericanTonyaHaileyisabducted                                                                                                                                                                                                                                                                                                                                                                                     |
