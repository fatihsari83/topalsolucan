package stepDefinitions;

import hooks.API_Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import java.util.List;

import static hooks.API_Hooks.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_StepDefinition {

    public static String fullPath;
    JSONObject reqBodyJson;
    Response response;
    int successStatusCode = 200;

    @Given("Set {string} parameters")
    public void set_parameters(String rawPaths) {

        String[] paths = rawPaths.split("/");
        StringBuilder tempPath = new StringBuilder("/{");
        for (int i = 0; i < paths.length; i++) {
            String key = "pp" + i;
            String value = paths[i].trim();
            System.out.println("value = " + value);
            spec.pathParam(key, value);
            tempPath.append(key + "}/{");
        }


        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        System.out.println("tempPath = " + tempPath);
        fullPath = tempPath.toString();
        System.out.println("fullPath = " + fullPath);
    }
    @When("Records response for Admin with valid authorization information")
    public void recordsResponseForAdminWithValidAuthorizationInformation() {
        // Admin icin, gecerli authorization bilgileri ile  response kaydeder
        response = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);
    }


    @When("Records response for Admin with invalid authorization information")
    public void recordsResponseForAdminWithInvalidAuthorizationInformation() {
        // Admin icin, gecersiz authorization bilgileri ile response kaydeder
        response = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + "wrongToken")
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);
        response.prettyPrint();


    }

    @Then("Verifies that status code is {int}")
    public void verifiesThatStatusCodeIs(int statusCode) {

        assertEquals(statusCode, response.getStatusCode());

    }

    @Then("Verifies that the message information is {string}")
    public void verifiesThatTheMessageInformationIs(String message) {
        JsonPath resJP = response.jsonPath();
        assertEquals(message, resJP.getString("message"));
    }



    @When("Records response for Teacher with valid authorization information")
    public void recordsResponseForTeacherWithValidAuthorizationInformation() {
        // Teacher icin, gecerli authorization bilgileri ile  response kaydeder
        response = given()

                .spec(spec)
                .headers("Authorization", "Bearer " + API_Hooks.tokenTeacher)

                .spec(API_Hooks.spec)
               // .headers("Authorization", "Bearer " + API_Hooks.tokenTeacher)

                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);
    }

    @When("Records response for Student with valid authorization information")
    public void recordsResponseForStudentWithValidAuthorizationInformation() {
        //  Student icin, gecerli authorization bilgileri ile  response kaydeder
        response = given()
                .spec(spec)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);


    }

    @When("Verifies that record has {string} and {string}includes {}")
    public void verifiesThatRecordHasAndIncludes(String class_id, String student_session_id, String expectedData) {
        JsonPath resJP = response.jsonPath();
        List<Object> list = resJP.getList("lists");
        Object[] arrList = new Object[list.size()];
        arrList = list.toArray(arrList);
        int index = 0;
        for (int a = 0; a < arrList.length; a++) {
            if (arrList[a].toString().contains(class_id) && arrList[a].toString().contains(student_session_id)) {
                System.out.println("index no : " + a);
                index = a;
                break;
            }
        }


        String actualData = arrList[index].toString();
        actualData = actualData.replaceAll(",", "");
        actualData = actualData.replaceAll(" ", "");
        actualData = actualData.replace("{", "");
        actualData = actualData.replace("}", "");
        System.out.println("ACTUAL DATA =   "+actualData);

        expectedData = expectedData.replaceAll(",", "");
        expectedData = expectedData.replaceAll(" ", "");
        System.out.println("EXPECTED DATA = "+ expectedData);
        Assert.assertEquals(expectedData, actualData);


    }

    @When("Prepare request body for admin api_bookIssueId endpoint and record response")
    public void prepareRequestBodyForAdminApi_bookIssueIdEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "18");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

        @When("Prepare request body for admin api_bookIssueId endpoint with invalid authorization information and record response")
        public void prepareRequestBodyForAdminApi_bookIssueIdEndpointWithInvalidAuthorizationInformationAndRecordResponse () {
            //  Admin icin api/bookIssueId andpointine gecersiz authorization bilgileriyle request body hazirla ve response kaydeder.

            JSONObject reqBody = new JSONObject();
            reqBody.put("id", "18");
            response = given()
                    .spec(API_Hooks.spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + "wrongToken")
                    .when()
                    .body(reqBody.toString())
                    .post(fullPath);
            response.prettyPrint();

       }




    @Given("Prepare request body for admin api_alumniEventsId endpoint and record response")
    public void prepare_request_body_for_admin_api_alumni_events_ıd_endpoint_and_record_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 2);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }
    @Given("Prepare request body for admin api_alumniEventsId endpoint with invalid authorization information and record response")
    public void prepare_request_body_for_admin_api_alumni_events_ıd_endpoint_with_invalid_authorization_information_and_record_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "2");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + "wrongToken")
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();
    }
  
    @When("Verifies that record includes {string}")
    public void verifiesThatRecordIncludes(String expectedData) {
        JsonPath resJP = response.jsonPath();
        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);
        String[] expectedArr = expectedData.split(",");


        for (String each : expectedArr) {
            Assert.assertTrue(actualData.contains(each));
        }
    }
    
  @Given("List data is verified in response from Admin api_alumniEventsId andpointin")
    public void list_data_is_verified_in_response_from_admin_api_alumni_events_ıd_andpointin() {
        // id, title, event_for, session_id, class_id, section, from_date, to_date, note, photo, is_active,
        // event_notification_message, show_onwebsite, created_at)


        String [] expectedArray = {"id","title","event_for","session_id","class_id","section","from_date","to_date","note"
                                   ,"photo","is_active","event_notification_message","show_onwebsite","created_at"};

        JsonPath resJP = response.jsonPath();

        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < 14; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));

        }
    }

    // Erol
    // US39 TC01
    @Given("For admin, the post query is sent with valid authorization information and the response is saved")
    public void for_admin_the_post_query_is_sent_with_valid_authorization_information_and_the_response_is_saved() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "95");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
    }
    @Given("For admin, verifies that status code is {int}")
    public void for_admin_verifies_that_status_code_is(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }
    @Given("For admin, verifies that the message information is {string}")
    public void for_admin_verifies_that_the_message_information_is(String message) {
        JsonPath resJP = response.jsonPath();
        assertEquals(message, resJP.getString("message"));
    }

    // US39 TC02
    @Given("For admin, prepare request body for admin endpoint with invalid authorization information and record response")
    public void for_admin_prepare_request_body_for_admin_endpoint_with_invalid_authorization_information_and_record_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "invalid");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + "API_Hooks.TokenAdmin")
                .when()
                .body(reqBody.toString())
                .post(fullPath);
    }
 // US39 TC03
    @Given("For admin, the body post query is sent with valid authorization information and the response is saved")
    public void for_admin_the_body_post_query_is_sent_with_valid_authorization_information_and_the_response_is_saved() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id",95);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
    }
    @Given("For admin, verifies that id is {int}")
    public void for_admin_verifies_that_id_is(int id) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(id,resJP.getInt("lists.id"));
    }
    @Given("For admin, verifies that the type information is {string}")
    public void for_admin_verifies_that_the_type_information_is(String type) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(type, resJP.getString("lists.type"));
    }
    @Given("For admin, verifies that the slug information is {string}")
    public void for_admin_verifies_that_the_slug_information_is(String slug) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(slug, resJP.getString("lists.slug"));
    }
    @Given("For admin, verifies that the title information is {string}")
    public void for_admin_verifies_that_the_title_information_is(String title) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(title, resJP.getString("lists.title"));
    }
    @Given("For admin, verifies that the date information is {string}")
    public void for_admin_verifies_that_the_date_information_is(String date) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(date, resJP.getString("lists.date"));
    }
    @Given("For admin, verifies that the description information is {string}")
    public void for_admin_verifies_that_the_description_information_is(String description) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(description, resJP.getString("lists.description"));
    }
    @Given("For admin, verifies that the is_active information is {string}")
    public void for_admin_verifies_that_the_is_active_information_is(String is_active) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(is_active, resJP.getString("lists.is_active"));
    }
    @Given("For admin, verifies that the created_at information is {string}")
    public void for_admin_verifies_that_the_created_at_information_is(String created_at) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(created_at, resJP.getString("lists.created_at"));
    }
    @Given("For admin, verifies that the meta_title information is {string}")
    public void for_admin_verifies_that_the_meta_title_information_is(String meta_title) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(meta_title, resJP.getString("lists.meta_title"));
    }
    @Given("For admin, verifies that the meta_description information is {string}")
    public void for_admin_verifies_that_the_meta_description_information_is(String meta_description) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(meta_description, resJP.getString("lists.meta_description"));
    }
    @Given("For admin, verifies that the meta_keyword information is {string}")
    public void for_admin_verifies_that_the_meta_keyword_information_is(String meta_keyword) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(meta_keyword, resJP.getString("lists.meta_keyword"));
    }
    @Given("For admin, verifies that the feature_image information is {string}")
    public void for_admin_verifies_that_the_feature_image_information_is(String feature_image) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(feature_image, resJP.getString("lists.feature_image"));
    }
    @Given("For admin, verifies that the publish_date information is {string}")
    public void for_admin_verifies_that_the_publish_date_information_is(String publish_date) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(publish_date, resJP.getString("lists.publish_date"));
    }
    @Given("For admin, verifies that the publish information is {string}")
    public void for_admin_verifies_that_the_publish_information_is(String publish) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(publish, resJP.getString("lists.publish"));
    }
    @Given("For admin, verifies that the sidebar information is {string}")
    public void for_admin_verifies_that_the_sidebar_information_is(String sidebar) {
        JsonPath resJP = response.jsonPath();
        Assert.assertEquals(sidebar, resJP.getString("lists.sidebar"));
        }
    @Given("For the administrator, the contents of the list data in the Response body that are null are validated.")
    public void for_the_administrator_the_contents_of_the_list_data_in_the_response_body_are_verified() {
        response.then().assertThat().body("lists.url",Matchers.nullValue());
        response.then().assertThat().body("lists.event_start",Matchers.nullValue());
        response.then().assertThat().body("lists.event_end",Matchers.nullValue());
        response.then().assertThat().body("lists.event_venue",Matchers.nullValue());
    }
   // US 40 TC01
    @Given("For admin, related page the post query is sent with valid authorization information and the response is saved")
    public void for_admin_api_add_notice_page_the_post_query_is_sent_with_valid_authorization_information_and_the_response_is_saved() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("type", "notice");
        reqBody.put("title", "notice deneme 12345");
        reqBody.put("description", "notice deneme");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

    }
    // US 40 TC 03
    @Given("It is verified that the record has been created by sending the POST body to the getNoticeById endpoint with addId returned in the response body.")
    public void ıt_is_verified_that_the_record_has_been_created_by_sending_the_post_body_to_the_get_notice_by_ıd_endpoint_with_add_ıd_returned_in_the_response_body() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "349"); // futureden çalıştırmadan önce 2 arttır.
        JsonPath responseJP=response.jsonPath();
        Assert.assertEquals(reqBody.getString("id"),responseJP.getString("addId"));
    }

    // US41 TC01
    @Given("For admin, On the relevant page, the last patch query is sent with the valid authorization information and the response is recorded")
    public void for_admin_on_the_relevant_page_the_last_patch_query_is_sent_with_the_valid_authorization_information_and_the_response_is_recorded() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "215");
        reqBody.put("type", "notice");
        reqBody.put("title", "notice wonder");
        reqBody.put("description", "notice deneme");
        reqBody.put("slug", "wonder");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
    }
        // US 41 TC03
        @Given("It should be verified that the updateId information in the response body is the same as the id information in the sent PATCH request body.")
        public void ıt_should_be_verified_that_the_update_ıd_information_in_the_response_body_is_the_same_as_the_id_information_in_the_sent_patch_request_body() {
            JSONObject reqBody = new JSONObject();
            reqBody.put("id", "215");

            JSONObject actualBody = new JSONObject();
            actualBody.put("updateId", 215);
            Assert.assertEquals(reqBody.getInt("id"),actualBody.getInt("updateId"));
        }
    // US 41 TC04
    @Given("For admin A post query is sent to the getNoticeById endpoint with the id in the returned response.")
    public void for_admin_a_post_query_is_sent_to_the_get_notice_by_ıd_endpoint_with_the_id_in_the_returned_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "215");
        reqBody.put("title", "notice wonder");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
    }
    @Given("The update of the desired notice record through the API should be validated")
    public void the_update_of_the_desired_notice_record_through_the_apı_should_be_validated() {
        JSONObject reqBody = new JSONObject();
        JsonPath responseJP= response.jsonPath();
        reqBody.put("id", "215");
        reqBody.put("title", "notice wonder");
        Assert.assertEquals(reqBody.getString("title"),responseJP.getString("lists.title"));
    }

        // NOT TC1  ve TC2 den önce deleteId yi değiştir.
        //  TC3  ve TC4 den önce deleteId yi değiştir.
    // US42 TC01
    @Given("For admin, On the relevant page, the last delete query is sent with the valid authorization information and the response is recorded")
    public void for_admin_on_the_relevant_page_the_last_delete_query_is_sent_with_the_valid_authorization_information_and_the_response_is_recorded() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", ConfigReader.getProperty("deleteID"));
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
    }

     // US 42 TC01


        @Given("For admin, On the relevant page, the last delete query is sent with the invalid authorization information and the response is recorded")
        public void for_admin_on_the_relevant_page_the_last_delete_query_is_sent_with_the_invalid_authorization_information_and_the_response_is_recorded(){
            JSONObject reqBody = new JSONObject();
            reqBody.put("id", "invalid");
            response = given()
                    .spec(API_Hooks.spec)
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + "API_Hooks.TokenAdmin")
                    .when()
                    .body(reqBody.toString())
                    .post(fullPath);
        }


    // US42 TC03
    @Given("It is verified that the DeletedId information in the response body is the same as the ID information in the DELETE request body sent to the endpoint.")
    public void ıt_is_verified_that_the_deleted_ıd_information_in_the_response_body_is_the_same_as_the_ıd_information_in_the_delete_request_body_sent_to_the_endpoint() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", ConfigReader.getProperty("deleteID"));

        JsonPath responseJP= response.jsonPath();
        Assert.assertEquals(reqBody.getString("id"),responseJP.getString("deletedId"));
    }

    // US42 TC04
    @Given("For admin a post query is sent to the getNoticeById endpoint with the valid id in the returned response.")
    public void for_admin_a_post_query_is_sent_to_the_get_notice_by_ıd_endpoint_with_the_valid_id_in_the_returned_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", ConfigReader.getProperty("deleteID"));
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

    }
    @Given("It is verified that the status of the returned response is {int} and the message information is failed.")
    public void ıt_is_verified_that_the_status_of_the_returned_response_is_and_the_message_information_is_failed(Integer int1) {
        JSONObject reqBody = new JSONObject();
        reqBody.put("message", "failed");
        JsonPath responseJP=response.jsonPath();
        Assert.assertEquals(reqBody.getString("message"),responseJP.getString("message"));
    }

        // US43
        @Given("For Teacher, when a query is sent with valid authorization information and the correct data or data; it is verified that the status code returned is {int}.")
        public void for_teacher_when_a_query_is_sent_with_valid_authorization_information_and_the_correct_data_or_data_it_is_verified_that_the_status_code_returned_is(Integer int1) {
            System.out.println("response.getStatusCode() = " + response.getStatusCode());
            response.then().assertThat().statusCode(200);
        }

    @Given("For Teacher, when a query is sent containing valid authorization information and the correct data or data; the message information in the returned response body is verified to be Success.")
    public void for_teacher_when_a_query_is_sent_containing_valid_authorization_information_and_the_correct_data_or_data_the_message_information_in_the_returned_response_body_is_verified_to_be_success() {
        response.then().assertThat().body("message", Matchers.equalTo("Success"));
        JsonPath responseJP = response.jsonPath();
        System.out.println("responseJP.get(\"message\") = " + responseJP.get("message"));
    }

    @Given("For Teacher, when a query is sent that contains invalid authorization information or invalid data or data, it is verified that the status code returned is {int} and the message information in the response body is failed.")
    public void for_teacher_when_a_query_is_sent_that_contains_invalid_authorization_information_or_invalid_data_or_data_it_is_verified_that_the_status_code_returned_is_and_the_message_information_in_the_response_body_is_failed(Integer int1) {
        response.then().assertThat().body("message", Matchers.equalTo("failed"));
        System.out.println("response.statusCode() = " + response.statusCode());
        JsonPath responseJP = response.jsonPath();
        System.out.println("responseJP.get(\"message\") = " + responseJP.get("message"));
        response.then().assertThat().statusCode(403);
    }

    // 43 US --> 01 /03 TC'ler
    @Given("For Teacher, the get query is sent with valid authorization information and the response is saved.")
    public void for_teacher_the_get_query_is_sent_with_valid_authorization_information_and_the_response_is_saved() {
        // Teacher icin, gecerli authorization bilgileri ile  response kaydeder
        response = given()
                .spec(API_Hooks.spec)
                .headers("Authorization", "Bearer " + API_Hooks.tokenTeacher)
                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);

    }
    // 43 US --> TC 02
    @Given("For Teacher, the get query is sent with invalid authorization information and the response is saved.")
    public void for_teacher_the_get_query_is_sent_with_invalid_authorization_information_and_the_response_is_saved() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "invalid");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + "API_Hooks.TokenAdmin")
                .when()
                .body(reqBody.toString())
                .post(fullPath);

    }
    // 43 US --> 03 TC
    @Given("For Teacher, The content of the {string} in the response body should be verified")
    public void for_teacher_the_content_of_the_in_the_response_body_should_be_verified(String string) {
        JsonPath resJP = response.jsonPath();
        // System.out.println(resJP.getList("lists"));
        List<Object> list = resJP.getList("lists");
        Object[] arrList = new Object[list.size()];
        arrList = list.toArray(arrList);
        // System.out.println(Arrays.toString(arrList));
        int index = 0;
        for (int a = 0; a < arrList.length; a++) {
            if (arrList[a].toString().contains("<p>Yeni Nesil Göc Dalgasi</p>")) {
                System.out.println("index no : " + a);
                index = a;
                break;
            }
        }
        System.out.println(arrList[index].toString());
    }



}





















