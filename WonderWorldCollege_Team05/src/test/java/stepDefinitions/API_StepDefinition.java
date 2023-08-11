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

}

