package stepDefinitions;

import hooks.API_Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.lv.Ja;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.API_Utils;
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
    int addId;
    int deleteId;
    int expectedData;
    int actualResult;
    JsonPath respJP;

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
        expectedData=3;
        respJP=response.jsonPath();
        actualResult=respJP.get("updateId");
        Assert.assertEquals(expectedData,actualResult);
    }
    // US_16 -> TC_04
    @Given("Post request is send for apivehicleId")
    public void post_request_is_send_for_apivehicle_ıd() {
        JSONObject postBody = new JSONObject();

        postBody.put("id", 3);


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
        actualResult=respJP.get();

        //Assert.assertTrue(expectedData,actualResult);
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


}
