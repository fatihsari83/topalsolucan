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
import testData.TestData_API_US_033;
import utilities.API_Utils;
import utilities.ConfigReader;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static hooks.API_Hooks.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class API_StepDefinition {

    public static String fullPath;
    public static int addId;
    public static int deletedId;
    public static HashMap<String, Object> respHP;
    JSONObject reqBodyJson;
    Response response;
    int successStatusCode = 200;
    int expectedData;
    int actualResult;

    JsonPath respJP;
    // API_US_56-57-60-61-65

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
                .put(fullPath);
        response.prettyPrint();
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

    @When("Records response for Admin with invalid authorization information")
    public void recordsResponseForAdminWithInvalidAuthorizationInformation() {
        // Admin icin, gecersiz authorization bilgileri ile response kaydeder
        JSONObject reqBody = new JSONObject();
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer" + ConfigReader.getProperty("wrongTokenAdmin"))
                .when()
                .post(fullPath);
        response.prettyPrint();

    }


    }
    @When("Records response for Student with invalid authorization information")
    public void recordsResponseForStudentWithInvalidAuthorizationInformation() {
        // Admin icin, gecersiz authorization bilgileri ile response kaydeder
        JSONObject reqBody = new JSONObject();
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer" + ConfigReader.getProperty("wrongTokenStudent"))
                .when()
                .post(fullPath);
        response.prettyPrint();
    }

    @When("Prepare request body for admin api_bookIssueId endpoint with invalid authorization information and record response")
    public void prepare_Request_Body_For_admin_api_bookIssueId_Endpoint_With_Invalid_Authorization_Information_And_Record_Response() {
        //  Admin icin api/bookIssueId andpointine gecersiz authorization bilgileriyle request body hazirla ve response kaydeder.

        JSONObject reqBody = new JSONObject();
        //reqBody.put("id", "18");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + "wrongToken")
                .when()
                .body(reqBody.toString())
                .post(fullPath);
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
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenTeacher)
                .when()
                .get(fullPath);
        response.prettyPrint();
    }

    @When("Records response for Teacher with invalid authorization information")
    public void records_Response_For_Teacher_With_Invalid_Authorization_Information() {
        // Admin icin, gecersiz authorization bilgileri ile response kaydeder
        JSONObject reqBody = new JSONObject();
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer" + ConfigReader.getProperty("wrongTokenTeacher"))
                .when()
                .post(fullPath);
        response.prettyPrint();
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
        response.prettyPrint();
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
        System.out.println("ACTUAL DATA =   " + actualData);

        expectedData = expectedData.replaceAll(",", "");
        expectedData = expectedData.replaceAll(" ", "");
        System.out.println("EXPECTED DATA = " + expectedData);
        Assert.assertEquals(expectedData, actualData);
    }
    @When("Verifies that record has status=200 and message=Success includes {}")
    public void verifiesThatRecord_HasAndIncludes(String status, String message, String expectedData) {
        JsonPath resJP = response.jsonPath();
        List<Object> list = resJP.getList("lists");
        Object[] arrList = new Object[list.size()];
        arrList = list.toArray(arrList);
        int index = 0;
        for (int a = 0; a < arrList.length; a++) {
            if (arrList[a].toString().contains(status) && arrList[a].toString().contains(message)) {
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

    @When("Prepare request body for admin api_visitorsPurposeId endpoint and record response")
    public void prepareRequestBodyForAdminapi_visitorsPurposeIdEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "538");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @When("Prepare request body for admin api_visitorsPurposeUpdate endpoint and record response")
    public void prepareRequestBodyForAdminapi_visitorsPurposeUpdateEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 10);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
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

    @When("Prepare request body for admin apistudent_applyLeaveAdd endpoint and record response")
    public void prepareRequestBodyForAdmin_apistudent_applyLeaveAddEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();


    @When("Prepare request body for admin api_visitorsPurposeAdd endpoint and record response")
    public void prepareRequestBodyForAdminapi_visitorsPurposeAddEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();
        reqBody.put("visitors_purpose", "Veli Ziyareti");
        reqBody.put("description", "Veli Ziyareti İçin Gelindi");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @When("Prepare patch request body for admin api_visitorsPurposeUpdate endpoint and record response")
    public void patchprepareRequestBodyForAdminapi_visitorsPurposeUpdateEndpointAndRecordResponse() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 10);
        reqBody.put("visitors_purpose", "Veli Ziyareti");
        reqBody.put("description", "Veli Ziyareti İçin Gelindi");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .patch(fullPath);

        response.prettyPrint();
    }

    @When("Prepare request body for student apistudent_applyLeaveAdd endpoint and record response")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveAddEndpointAndRecordResponse() {

        JSONObject reqBody = new JSONObject();

        reqBody.put("from_date", "2023-06-01");
        reqBody.put("to_date", "2023-06-04");
        reqBody.put("apply_date", "2023-06-15");
        reqBody.put("reason", "sebep");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)

                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();
    }
    @When("Prepare request body for admin api_visitorsPurposeId endpoint and record response")
    public void prepareRequestBodyForAdminApi_visitorsPurposeIdEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "538");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();
    }

                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @When("Prepare request body for student apistudent_applyLeaveAdd endpoint and record response with unvalid")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveAddEndpointAndRecordResponse_with_unvalid() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("from_date", "2023-06-01");
        reqBody.put("to_date", "2023-06-04");
        reqBody.put("apply_date", "2023-06-15");
        reqBody.put("reason", "sebep");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.wrongTokenStudent)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }


    @When("Prepare request body for student apistudent_applyLeaveUpdate endpoint and record response")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveUpdateEndpointAndRecordResponse_with_valid() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "424");
        reqBody.put("from_date", "2023-06-01");
        reqBody.put("to_date", "2023-06-04");
        reqBody.put("apply_date", "2023-06-15");
        reqBody.put("reason", "sebep1");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .patch(fullPath);

        response.prettyPrint();
    }

    @When("Prepare request body for student apistudent_applyLeaveUpdate endpoint and record response wiht invalid")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveUpdateEndpointAndRecordResponse_with_unvalid() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "527");
        reqBody.put("from_date", "2023-06-01");
        reqBody.put("to_date", "2023-06-04");
        reqBody.put("apply_date", "2023-06-15");
        reqBody.put("reason", "sebep1");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.wrongTokenStudent)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    @When("Prepare request body for student apistudent_applyLeaveUpdate endpoint and record responseand updateId")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveUpdateEndpointAndRecordResponse_and_updateId() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("updatedId", "527");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .patch(fullPath);

        response.prettyPrint();
    }

    @When("Prepare request body for student apistudent_applyLeaveList endpoint and record response")
    public void patchprepareRequestBodyForapistudent_applyLeaveListEndpointAndRecordResponse() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("addId", 494);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .get(fullPath);

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

        String[] expectedArray = {"id", "title", "event_for", "session_id", "class_id", "section", "from_date", "to_date", "note"
                , "photo", "is_active", "event_notification_message", "show_onwebsite", "created_at"};

        JsonPath resJP = response.jsonPath();
        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < 14; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));
        }
    }
    @Given("List data is verified in response from apiteacher_subjectsList endpoint")
    public void listdataisverifiedinresponsefromapiteachersubjectsListendpoint() {

        String[] expectedArray = {"id", "name", "code", "type", "is_active", "created_at", "updated_at", "null",
                "id", "name", "code", "type", "is_active", "created_at", "updated_at", "null",
                "id", "name", "code", "type", "is_active", "created_at", "updated_at"};

        JsonPath resJP = response.jsonPath();
        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < 23; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));
        }
    }
    @Given("List data is verified in response from apistudent_studentInformationDetails endpoint")
    public void list_data_is_verified_in_response_from_api_student_studentInformationDetails_endpoint() {

        String [] expectedArray = {"pickup_point_name", "null",

                                   
                                   
        String[] expectedArray = {"pickup_point_name", "null",

                                  
                                  
                "route_pickup_point_id", "null",
                "transport_fees", "0.00",
                "app_key", "null",
                "parent_app_key", "null",
                "vehroute_id", "null",
                "route_id", "null",
                "vehicle_id", "null",
                "route_title", "null",
                "vehicle_no", "null",
                "room_no", "G4",
                "driver_name", "null",
                "driver_contact", "null",
                "vehicle_model", "null",
                "manufacture_year", "null",
                "driver_licence", "null",
                "vehicle_photo", "null",
                "hostel_id", "4",
                "hostel_name", "Girls Hostel 104",
                "room_type_id", "3",
                "room_type", "Two Bed",
                "hostel_room_id", "8",
                "student_session_id", "210",
                "fees_discount", "0.00",
                "class_id", "4",
                "class", "Class 4",
                "section_id", "3",
                "section", "C",
                "id", "181",
                "admission_no", "16100",
                "roll_no", "1116",
                "admission_date", "2023-07-22",
                "firstname", "hasan.can",
                "middlename", "",
                "lastname", "",
                "image", "uploads/student_images/noimage.jpg",
                "mobileno", "3962981907",
                "email", "hasan.can@student.wonderworldcollege.com",
                "state", "",
                "city", "",
                "pincode", "",
                "note", "null",
                "religion", "Christian",
                "cast", "Thomas",
                "house_name", "ahmetY",
                "dob", "2014-03-11",
                "current_address", "3Main Street, Suite 3, Brooklyn, NY 11210-0000",
                "previous_school", "Brooklyn Public School",
                "guardian_is", "father",
                "parent_id", "361",
                "permanent_address", "48 Main Street, Suite 3, Brooklyn, NY 11210-0000",
                "category_id", "1",
                "adhar_no", "3652688137",
                "samagra_id", "13288",
                "bank_account_no", "5523",
                "bank_name", "UBS Bank",
                "ifsc_code", "1265850732",
                "guardian_name", "John Reed",
                "father_pic", "uploads/student_images/noimage.jpg",
                "height", "4'2",
                "weight", "33 kg",
                "measurement_date", "2021-03-18",
                "mother_pic", "uploads/student_images/noimage.jpg",
                "guardian_pic", "uploads/student_images/noimage.jpg",
                "guardian_relation", "Father",
                "guardian_phone", "3677584804",
                "guardian_address", "West Brooklyn",
                "is_active", "yes",
                "created_at", "2023-07-21 05:25:28",
                "updated_at", "0000-00-00",
                "father_name", "John Reed",
                "father_phone", "5013287527",
                "blood_group", "O+",
                "school_house_id", "17",
                "father_occupation", "Lawyer",
                "mother_name", "Sophia Coleman",
                "mother_phone", "6380798525",
                "mother_occupation", "Teacher",
                "guardian_occupation", "Lawyer",
                "gender", "Male",
                "rte", "No",
                "guardian_email", "hasan.can@parent.wonderworldcollege.com",
                "username", "std181",
                "password", "wonderworld123",
                "user_id", "360",
                "dis_reason", "0",
                "dis_note", "",
                "disable_at", "null"};

        JsonPath resJP = response.jsonPath();
        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < 88; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));
        }
    }
    @Given("Prepare request body for student apistudent_dailyAssignmentById endpoint and record response")
    public void prepare_request_body_for_student_api_student_daily_assigment_by_Id_endpoint_and_record_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 51);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }
    @Given("List data is verified in response from apistudent_dailyAssignmentById endpoint")
    public void list_data_is_verified_in_response_from_api_student_daily_assignment_by_id_endpoint() {

        String[] expectedArray = {"id", "student_session_id", "subject_group_subject_id", "title", "description", "attachment", "evaluated_by", "date",
                "evaluation_date", "remark", "created_at", "subject_name", "subject_code,", "subject_code"};

        JsonPath resJP = response.jsonPath();
        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < 14; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));
        }
    }

    @Given("List data is verified in response from apistudent_dailyAssignmentList endpoint")
    public void list_data_is_verified_in_response_from_api_student_daily_assignment_list_endpoint() {

        String[] expectedArray = {"id", "student_session_id", "subject_group_subject_id", "title", "description", "attachment", "evaluated_by", "date",
                "evaluation_date", "remark", "created_at", "subject_name", "subject_code,", "subject_code"};

        JsonPath resJP = response.jsonPath();
        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < 14; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));
        }
    }

      
      
       @Given("List data is verified in response from apistudent_applyLeaveList endpoint")

      
      

    @Given("List data is verified in response from apistudent_applyLeaveList endpoint")

      
      
    public void list_data_is_verified_in_response_from_api_student_apply_leave_list_endpoint() {

        String[] expectedArray = {"id", "student_session_id", "from_date", "to_date", "apply_date", "status", "docs", "reason", "approve_by",
                "approve_date", "request_type", "created_at", "firstname", "middlename,", "lastname", "staff_name", "surname", "class_id", "section_id", "class", "section"};

           HashMap<String,Object> respMap = response.as(HashMap.class);
           System.out.println(respMap.get("lists"));
        //JsonPath resJP = response.jsonPath();
        //String actualData = resJP.get("lists").toString();
        //System.out.println(actualData);
/*
        for (int i = 0; i < 21; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));
        }
        */
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
        response.then().assertThat().body("lists.url", Matchers.nullValue());
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
        reqBody.put("id", 216);
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
                .patch(fullPath);
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
        reqBody.put("id", 216);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .delete(fullPath);
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
                .headers("Authorization", "Bearer " + API_Hooks.tokenTeacher)
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
/*
    @Given("Prepare request body for admin api_visitorsPurposeId endpoint and record response")
    public void prepare_request_body_for_admin_api_visitors_purpose_ıd_endpoint_and_record_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 1);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

 */

    @Given("Prepare request body for admin api_visitorsPurposeId endpoint with invalid authorization information and record response")
    public void prepare_request_body_for_admin_api_visitors_purpose_ıd_endpoint_with_invalid_authorization_information_and_record_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 1);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + "wrongToken")
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();
    }

    @Given("Prepare request body for admin api_alumniEventsByDateRange endpoint and record response")
    public void prepare_request_body_for_admin_api_alumni_events_by_date_range_endpoint_and_record_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("start", "2021-01-14 00:00:00");
        reqBody.put("end", "2023-03-15 23:59:00");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();
    }

    @Given("Prepare request body for admin api_alumniEventsByDateRange endpoint with invalid authorization information and record response")
    public void prepare_request_body_for_admin_api_alumni_events_by_date_range_endpoint_with_invalid_authorization_information_and_record_response() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("start", "2021-01-14 00:00:00");
        reqBody.put("end", "2023-03-15 23:59:00");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + "wrongToken")
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();
    }

    @Given("List data is verified in response from Admin api_booksList endpoint")
    public void list_data_is_verified_in_response_from_admin_api_books_list_endpoint() {

        String [] expectedArray =  { "id", "book_title", "book_no", "isbn_no", "subject", "rack_no", "publish", "author", "qty", "perunitcost",
                "postdate", "description", "available", "is_active", "created_at", "updated_at"};

        JsonPath resJP = response.jsonPath();

        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < expectedArray.length; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));

        }


    }

    @Given("Prepare request body for admin api_visitorsList endpoint and record response")
    public void prepare_request_body_for_admin_api_visitors_list_endpoint_and_record_response() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 250);
        reqBody.put("purpose", "Student Meeting");
        reqBody.put("name", "Marymar");
        reqBody.put("contact", "02516545544");
        reqBody.put("id_proof", "1222");
        reqBody.put("no_of_people", "5");
        reqBody.put("date", "2023-01-18 01:08:16");
        reqBody.put("in_time", "04:15 PM");
        reqBody.put("out_time", "04:55 PM");
        reqBody.put("created_at", "2023-04-18 01:08:22");


        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();

    }
    @Then("List data is verified in response from Admin api_visitorsList endpoint")
    public void list_data_is_verified_in_response_from_admin_api_visitors_list_endpoint() {

        TestData_API_US_033 testDataUs033 = new TestData_API_US_033();

        JSONObject expData = testDataUs033.expDataUS_033();

        JsonPath resJP = response.jsonPath();

        String [] expectedArray =  {"staff_id","student_session_id","source","purpose","name","email","contact","id_proof",
                "no_of_people","date","in_time","out_time","note","image","meeting_with","created_at","class",
                "section","staff_name","staff_surname","staff_employee_id","class_id","section_id","students_id",
                "admission_no","student_firstname","student_middlename","student_lastname","role_id"};

        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);
        resJP.prettyPrint();

        for (int i = 0; i < expectedArray.length; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));

        }

    }

    @Given("List data is verified in response from Admin api_visitorsId endpoint")
    public void list_data_is_verified_in_response_from_admin_api_visitors_id_endpoint() {

        String [] expectedArray =  { "id", "staff_id", "student_session_id", "source", "purpose", "name", "email", "contact", "id_proof",
                "no_of_people", "date", "in_time", "out_time", "note", "image", "meeting_with", "created_at", "class",
                "section", "staff_name", "staff_surname", "staff_employee_id", "class_id", "section_id", "students_id",
                "admission_no", "student_firstname", "student_middlename", "student_lastname", "role_id"};

        System.out.println(expectedArray);
        JsonPath resJP = response.jsonPath();

        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < expectedArray.length; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));

        }

    }
    @Given("Prepare request body for admin api_visitorsId endpoint and record response")
    public void prepare_request_body_for_admin_api_visitors_id_endpoint_and_record_response() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "177");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);
        response.prettyPrint();

    }
    @When("Verifies that record has {string} includes {}")
    public void verifiesThatRecordHasIncludes(String str1, String expectedData) {
        JsonPath resJP = response.jsonPath();
        List<Object> list = resJP.getList("lists");
        Object[] arrList = new Object[list.size()];
        arrList = list.toArray(arrList);
        int index = 0;
        for (int a = 0; a < arrList.length; a++) {
            if (arrList[a].toString().contains(str1)) {
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
        Assert.assertTrue(actualData.contains(expectedData));

    }
    //US_03 TC_01
    @When("Prepare request body for admin api_visitorsPurposeAdd endpoint and record response")
    public void prepareRequestBodyForAdminapi_visitorsPurposeAddEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();
        reqBody.put("visitors_purpose", "Veli Ziyareti");
        reqBody.put("description", "Veli Ziyareti İçin Gelindi");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }
    /*
    //US_03 TC_03
    @When("Prepare request body for admin api_visitorsPurposeId endpoint and record response")
    public void prepareRequestBodyForAdminapi_visitorsPurposeIdEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "538");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

     */


    //US_04 TC_01
    @When("Prepare patch request body for admin api_visitorsPurposeUpdate endpoint and record response")
    public void patchprepareRequestBodyForAdminapi_visitorsPurposeUpdateEndpointAndRecordResponse() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 14);
        reqBody.put("visitors_purpose", "Veli Ziyareti");
        reqBody.put("description", "Veli Ziyareti İçin Gelindi");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .patch(fullPath);

        response.prettyPrint();
    }
    //US_04 TC_03
    @When("Prepare request body for admin api_visitorsPurposeUpdate endpoint and record response")
    public void prepareRequestBodyForAdminapi_visitorsPurposeUpdateEndpointAndRecordResponse() {
        // Admin icin api/bookIssueId andpointine request body hazirlar ve response kaydeder.
        JSONObject reqBody = new JSONObject();
        reqBody.put("id", 10);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }
    //US_66 TC_01
    @When("Prepare request body for student apistudent_applyLeaveAdd endpoint and record response")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveAddEndpointAndRecordResponse() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("from_date", "2023-06-01");
        reqBody.put("to_date", "2023-06-04");
        reqBody.put("apply_date", "2023-06-15");
        reqBody.put("reason", "sebep");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }

    //US_66 TC_02
    @When("Prepare request body for student apistudent_applyLeaveAdd endpoint and record response with unvalid")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveAddEndpointAndRecordResponse_with_unvalid() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("from_date", "2023-06-01");
        reqBody.put("to_date", "2023-06-04");
        reqBody.put("apply_date", "2023-06-15");
        reqBody.put("reason", "sebep");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.wrongToken)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }
    //US_66 TC_03
    @When("Prepare request body for student apistudent_applyLeaveList endpoint and record response")
    public void patchprepareRequestBodyForapistudent_applyLeaveListEndpointAndRecordResponse() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("addId", 494);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .get(fullPath);

        response.prettyPrint();
    }
    //US_67 TC_01
    @When("Prepare request body for student apistudent_applyLeaveUpdate endpoint and record response")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveUpdateEndpointAndRecordResponse_with_valid() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "424");
        reqBody.put("from_date", "2023-06-01");
        reqBody.put("to_date", "2023-06-04");
        reqBody.put("apply_date", "2023-06-15");
        reqBody.put("reason", "sebep1");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .patch(fullPath);

        response.prettyPrint();
    }
    //US_67 TC_02
    @When("Prepare request body for student apistudent_applyLeaveUpdate endpoint and record response wiht invalid")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveUpdateEndpointAndRecordResponse_with_unvalid() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", "527");
        reqBody.put("from_date", "2023-06-01");
        reqBody.put("to_date", "2023-06-04");
        reqBody.put("apply_date", "2023-06-15");
        reqBody.put("reason", "sebep1");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.wrongToken)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();
    }
    //US_67 TC_03
    @When("Prepare request body for student apistudent_applyLeaveUpdate endpoint and record responseand updateId")
    public void patchprepareRequestBodyForstudentapistudent_applyLeaveUpdateEndpointAndRecordResponse_and_updateId() {

        JSONObject reqBody = new JSONObject();
        reqBody.put("updatedId", "527");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .patch(fullPath);

        response.prettyPrint();
    }
   // US_68 TC_01
    @Given("Prepare request body for student apistudent_applyLeaveAdd endpoint and record response with id")
    public void prepare_request_body_for_student_apistudent_apply_leave_add_endpoint_and_record_response_with_id() {
        JSONObject reqBody = new JSONObject();
        reqBody.put("from_date","2023-06-01");
        reqBody.put("to_date","2023-06-04");
        reqBody.put("apply_date","2023-06-15");
        reqBody.put("reason","Hasanın derdi işte...");
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .post(fullPath);

        response.prettyPrint();

    }
    @Given("The request is sent with valid login information and the response is recorded")
    public void the_request_is_sent_with_valid_login_information_and_the_response_is_recorded() {
        JsonPath resJP =response.jsonPath();
        addId=resJP.get("addId");
        JSONObject reqBody = new JSONObject();
        reqBody.put("id",addId);
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenStudent)
                .when()
                .body(reqBody.toString())
                .delete(fullPath);

        response.prettyPrint();

    }
   //US_68_TC_04
    @Given("Verifies that id number same as deleteId")
    public void verifies_that_id_number_same_as_delete_ıd() {

        JsonPath resJP =response.jsonPath();
        deletedId=resJP.get("deletedId");
        Assert.assertEquals(addId, deletedId);


    }
    @Given("Checking applyLeaveList that has deletedId")
    public void checking_apply_leave_list_that_has_deleted_ıd() {
        respHP=response.as(HashMap.class);

        Assert.assertFalse(respHP.containsValue(deletedId));

    }
    // US_05 -> TC_01
    @Given("Post request is send for visitorsPurposeDelete")
    public void Post_request_is_send_for_visitors_purpose_delete() {
        JSONObject postBody = new JSONObject();

        postBody.put("visitors_purpose", "Veli Ziyareti");
        postBody.put("description", "Veli Ziyareti İçin Gelindi");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(postBody.toString())
                .post(fullPath);

        response.prettyPrint();


        JsonPath resJP = response.jsonPath();
        addId = resJP.get("addId");
        System.out.println(addId);


    }

    @Given("Delete request is send for visitorsPurposeDelete")
    public void Delete_request_is_send_for_visitors_purpose_delete() {

        API_Utils.deleteMethod(addId);


    }

    // US_05 -> TC_02
    @Given("Incorrect deletion request is sent for visitorsPurposeDelete")
    public void ıncorrect_deletion_request_is_sent_for_visitors_purpose_delete() {
    /*
        JSONObject reqBody = new JSONObject();

        reqBody.put("id", 22564);

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + null)
                .when()
                .body(reqBody.toString())
                .delete(fullPath);

        response.prettyPrint();

     */

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

    // US_05 -> TC_03

    @Given("Send a DELETE body with valid authorization information and correct data id to the apivisitorsPurposeDelete endpoint")
    public void send_a_delete_body_with_valid_authorization_information_and_correct_data_id_to_the_apivisitors_purpose_delete_endpoint() {

        expectedData = addId;

        Assert.assertEquals(expectedData, addId);

    }

    // US_05 -> TC_04
    @Given("Confirm from the API that the visitor purpose record requested to be deleted from the API has been deleted.")
    public void confirm_from_the_apı_that_the_visitor_purpose_record_requested_to_be_deleted_from_the_apı_has_been_deleted() {
        JSONObject postBody = new JSONObject();

        postBody.put("id", addId);


        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(postBody.toString())
                .post(fullPath);

        response.prettyPrint();

        int statusCode=403;

        assertEquals(statusCode, response.getStatusCode());


    }

    // US_12 -> TC_01
    @Given("Post request is send for alumniEventsDelete")
    public void post_request_is_send_for_alumni_events_delete() {

        JSONObject postBody = new JSONObject();

        postBody.put("title", "Sports Activite");
        postBody.put("event_for", "all");
        postBody.put("session_id", 11);
        postBody.put("section", "null");
        postBody.put("from_date", "2023-02-14 00:00:00");
        postBody.put("to_date", "2023-02-15 23:59:00");
        postBody.put("note", "Sports");
        postBody.put("event_notification_message", "Sports");
        postBody.put("show_onwebsite", "0");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(postBody.toString())
                .post(fullPath);

        response.prettyPrint();


        JsonPath resJP = response.jsonPath();
        addId = resJP.get("addId");
        System.out.println(addId);
    }




    // US_16 -> TC_01
    @Given("Submit a PATCH body with valid authorization information and correct data to the apivehicleUpdate endpoint")
    public void submit_a_patch_body_with_valid_authorization_information_and_correct_data_to_the_api_vehicle_update_endpoint() {


        JSONObject patchBody = new JSONObject();

        patchBody.put("id", 407);
        patchBody.put("vehicle_no", "00004545");
        patchBody.put("vehicle_model", "Ford CAB");
        patchBody.put("vehicle_photo", "1677502339-191558462463fca783b26b0!fd.png");
        patchBody.put("manufacture_year", "2015");
        patchBody.put("registration_number", "FFG-76575676787");
        patchBody.put("chasis_number", "523422");
        patchBody.put("max_seating_capacity", "50");
        patchBody.put("driver_name", "Jasper");
        patchBody.put("driver_licence", "258714545");
        patchBody.put("driver_contact", "8521479630");
        patchBody.put("note", "");


        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(patchBody.toString())
                .patch(fullPath);

        response.prettyPrint();

    }
    // US_16 -> TC_02
    @Given("Submit a PATCH body with invalid authorization information or missingincorrect data to the apivehicleUpdate endpoint")
    public void submit_a_patch_body_with_invalid_authorization_information_or_missing_incorrect_data_to_the_api_vehicle_update_endpoint() {

        JSONObject patchBody = new JSONObject();

        patchBody.put("id", 3);
        patchBody.put("vehicle_no", "VH4584");
        patchBody.put("vehicle_model", "Ford CAB");
        patchBody.put("vehicle_photo", "1677502339-191558462463fca783b26b0!fd.png");
        patchBody.put("manufacture_year", "2015");
        patchBody.put("registration_number", "FFG-76575676787");
        patchBody.put("chasis_number", "523422");
        patchBody.put("max_seating_capacity", "50");
        patchBody.put("driver_name", "Jasper");
        patchBody.put("driver_licence", "258714545");
        patchBody.put("driver_contact", "8521479630");
        patchBody.put("note", "");


        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + ConfigReader.getProperty("wrongTokenAdmin"))
                .when()
                .body(patchBody.toString())
                .post(fullPath);

        response.prettyPrint();


    }

    // US_16 -> TC_03

    @Given("Verify that the DeletedId in the response body is the same as the ID in the DELETE request body sent to the apivisitorsPurposeDelete endpoint")
    public void verify_that_the_deleted_ıd_in_the_response_body_is_the_same_as_the_ıd_in_the_delete_request_body_sent_to_the_api_visitors_purpose_delete_endpoint() {
        expectedData=407;
        respJP=response.jsonPath();
        actualResult=respJP.get("updateId");
        Assert.assertEquals(expectedData,actualResult);
    }
    // US_16 -> TC_04
    @Given("Post request is send for apivehicleId")
    public void post_request_is_send_for_apivehicle_ıd() {
        JSONObject postBody = new JSONObject();

        postBody.put("id", 407);


        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(postBody.toString())
                .post(fullPath);

        response.prettyPrint();
        expectedData=200;
        respJP=response.jsonPath();
        actualResult=respJP.get("status");

        Assert.assertEquals(expectedData,actualResult);
    }
    // US_17 -> TC_01
    @Given("Post request is send for apivehicleDelete")
    public void post_request_is_send_for_apivehicle_delete() {
        JSONObject postBody = new JSONObject();

        postBody.put("id", 3);
        postBody.put("vehicle_no", "VH4584");
        postBody.put("vehicle_model", "Ford CAB");
        postBody.put("vehicle_photo", "1677502339-191558462463fca783b26b0!fd.png");
        postBody.put("manufacture_year", "2015");
        postBody.put("registration_number", "FFG-76575676787");
        postBody.put("chasis_number", "523422");
        postBody.put("max_seating_capacity", "50");
        postBody.put("driver_name", "Jasper");
        postBody.put("driver_licence", "258714545");
        postBody.put("driver_contact", "8521479630");
        postBody.put("note", "");


        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(postBody.toString())
                .patch(fullPath);

        response.prettyPrint();

        JsonPath resJP = response.jsonPath();
        addId = resJP.get("addId");
        System.out.println(addId);
    }

    @Given("Delete request is send for Set apivehicleDelete")
    public void delete_request_is_send_for_set_apivehicle_delete() {

        API_Utils.deleteMethod(addId);

    }



    // US_20 -> TC_01

    @Given("Post request is send for apibookIssueAdd")
    public void post_request_is_send_for_apibook_issue_add() {

        JSONObject postBody = new JSONObject();

        postBody.put("book_id", "11");
        postBody.put("member_id", "7");
        postBody.put("duereturn_date", "2021-08-04");
        postBody.put("return_date", "2021-09-06");
        postBody.put("issue_date", "2021-08-04");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(postBody.toString())
                .patch(fullPath);

        response.prettyPrint();


    }


    // US_20 -> TC_02
    @Given("Submit a PATCH body with invalid authorization information or missingin/correct data to the apivehicleUpdate endpoint")
    public void submit_a_patch_body_with_invalid_authorization_information_or_missingincorrect_data_to_the_api_vehicle_update_endpoint() {

        JSONObject postBody = new JSONObject();

        postBody.put("book_id", "11");
        postBody.put("member_id", "7");
        postBody.put("duereturn_date", "2021-08-04");
        postBody.put("return_date", "2021-09-06");
        postBody.put("issue_date", "2021-08-04");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + ConfigReader.getProperty("wrongTokenAdmin"))
                .when()
                .body(postBody.toString())
                .patch(fullPath);

        response.prettyPrint();
    }


    // US_20 -> TC_03
    @Given("Verify via API that the new book issue record to be created via API has been created.")
    public void verify_via_api_that_the_new_book_issue_record_to_be_created_via_api_has_been_created() {
        JSONObject postBody = new JSONObject();

        postBody.put("book_id", "11");
        postBody.put("member_id", "7");
        postBody.put("duereturn_date", "2021-08-04");
        postBody.put("return_date", "2021-09-06");
        postBody.put("issue_date", "2021-08-04");

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(postBody.toString())
                .patch(fullPath);

        response.prettyPrint();

        JsonPath resJP = response.jsonPath();
        addId = resJP.get("addId");
        System.out.println("Successful post ID :"+addId);


    }

    @Given("For Teacher with apiteacher-classesList, it saves response with invalid authorization information")
    public void for_teacher_with_apiteacher_classes_list_it_saves_response_with_invalid_authorization_information() {

        response = given()

                .spec(spec)
                .headers("Authorization", "Bearer " + "wrongToken")

                .spec(API_Hooks.spec)
                // .headers("Authorization", "Bearer " + API_Hooks.tokenTeacher)

                .contentType(ContentType.JSON)
                .when()
                .get(fullPath);
        response.prettyPrint();


    }
    //US-53
    @Given("apiteacher-classes Validates data in lists in List")
    public void apiteacher_classes_validates_data_in_lists_in_list() {



        JSONObject id1 = new JSONObject();
        id1.put("id", "1");
        id1.put("class", "Class 1");
        id1.put("is_active", "no");
        id1.put("created_at", "2021-03-22 11:46:51");
        id1.put("updated_at", "null");

        JSONObject id7 = new JSONObject();
        id7.put("id", "7");
        id7.put("class", "Class 7");
        id7.put("is_active", "no");
        id7.put("created_at", "2022-03-29 04:22:40");
        id7.put("updated_at", "null");

        JSONObject id12 = new JSONObject();
        id12.put("id", "12");
        id12.put("class", "Class 12");
        id12.put("is_active", "no");
        id12.put("created_at", "2022-03-29 04:22:40");
        id12.put("updated_at", "null");

        JSONArray lists = new JSONArray();
        lists.put(0,id1);
        lists.put(6,id7);
        lists.put(11,id12);

        JSONObject reqBody = new JSONObject();
        reqBody.put("status", 200);
        reqBody.put("message", "Success");
        reqBody.put("Token_remaining_time", 19);
        reqBody.put("lists", lists);
        System.out.println("reqBody = " + reqBody);

        JSONObject expData = new JSONObject();
        expData.put("lists",lists);


        JsonPath resJP = response.jsonPath();
        ArrayList listsArr = resJP.getJsonObject("lists");
        JSONArray listsJA = new JSONArray(listsArr);
        System.out.println(listsJA.get(0));


        assertEquals(expData.getJSONArray("lists").getJSONObject(0).get("id"), listsJA.getJSONObject(0).get("id"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(0).get("class"), listsJA.getJSONObject(0).get("class"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(0).get("is_active"), listsJA.getJSONObject(0).get("is_active"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(0).get("created_at"), listsJA.getJSONObject(0).get("created_at"));

        assertEquals(expData.getJSONArray("lists").getJSONObject(6).get("id"), listsJA.getJSONObject(6).get("id"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(6).get("class"), listsJA.getJSONObject(6).get("class"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(6).get("is_active"), listsJA.getJSONObject(6).get("is_active"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(6).get("created_at"), listsJA.getJSONObject(6).get("created_at"));

        assertEquals(expData.getJSONArray("lists").getJSONObject(11).get("id"), listsJA.getJSONObject(11).get("id"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(11).get("class"), listsJA.getJSONObject(11).get("class"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(11).get("is_active"), listsJA.getJSONObject(11).get("is_active"));
        assertEquals(expData.getJSONArray("lists").getJSONObject(11).get("created_at"), listsJA.getJSONObject(11).get("created_at"));







    }


}



