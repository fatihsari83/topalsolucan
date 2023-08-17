package stepDefinitions;

import hooks.API_Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import java.util.ArrayList;
import java.util.Arrays;
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
        JSONObject reqBody = new JSONObject();
        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer" + ConfigReader.getProperty("wrongTokenAdmin"))
                .when()
                .post(fullPath);
        response.prettyPrint();

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
                .headers("Authorization", "Bearer " + API_Hooks.tokenTeacher)

                .spec(API_Hooks.spec)
                // .headers("Authorization", "Bearer " + API_Hooks.tokenTeacher)

                .contentType(ContentType.JSON)
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
        // id, title, event_for, session_id, class_id, section, from_date, to_date, note, photo, is_active,
        // event_notification_message, show_onwebsite, created_at)


        String[] expectedArray = {"id", "title", "event_for", "session_id", "class_id", "section", "from_date", "to_date", "note"
                , "photo", "is_active", "event_notification_message", "show_onwebsite", "created_at"};

        /*
        (id'si = "1", olan veri içeriğindeki name: "English", code: "210",
            type: "theory", is_active: "no", created_at: "2021-03-23 04:36:46", updated_at: null) ,
          (id'si = "4", olan veri içeriğindeki name: "Mathematics", code: "110", type: "practical", is_active: "no",
          created_at: "2021-03-23 04:37:22", updated_at: null),  (id'si = "6", olan veri içeriğindeki name: "Social Studies",
          code: "212", type: "theory", is_active: "no", created_at: "2021-03-23 04:37:51", updated_at: null)
         */

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
        reqBody.put("id", "51");
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
        // id, title, event_for, session_id, class_id, section, from_date, to_date, note, photo, is_active,
        // event_notification_message, show_onwebsite, created_at)


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
    public void list_data_is_verified_in_response_from_api_student_apply_leave_list_endpoint() {

        String[] expectedArray = {"id", "student_session_id", "from_date", "to_date", "apply_date", "status", "docs", "reason", "approve_by",
                "approve_date", "request_type", "created_at", "firstname", "middlename,", "lastname", "staff_name", "surname", "class_id", "section_id", "class", "section"};


        JsonPath resJP = response.jsonPath();

        String actualData = resJP.get("lists").toString();
        System.out.println(actualData);

        for (int i = 0; i < 21; i++) {
            Assert.assertTrue(actualData.contains(expectedArray[i]));

        }

    }


}
