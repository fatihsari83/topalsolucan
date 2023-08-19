package utilities;

import hooks.API_Hooks;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static stepDefinitions.API_StepDefinition.fullPath;

public class API_Utils {
    static Response response;
    public static RequestSpecification spec;
    public static String generateTokenAdmin(){
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp1","api","pp2","getToken");
        Map<String,Object> dataCredential = new HashMap<>();
        dataCredential.put("email", ConfigReader.getProperty("admin_FatihSari"));
        dataCredential.put("password", ConfigReader.getProperty("password"));
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(dataCredential)
                .post("/{pp1}/{pp2}");
        JsonPath respJP = response.jsonPath();
        String token = respJP.getString("token");
        return token;
    }

    public static String generateTokenTeacher(){
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        spec.pathParams("pp1","api","pp2","getToken");
        Map<String,Object> dataCredential = new HashMap<>();
        dataCredential.put("email", ConfigReader.getProperty("teacher_FatihSari"));
        dataCredential.put("password", ConfigReader.getProperty("password"));
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(dataCredential)
                .post("/{pp1}/{pp2}");
        JsonPath respJP = response.jsonPath();
        String token = respJP.getString("token");
        return token;
    }
    public static String generateTokenStudent(){


        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","apistudent","pp2","getToken");

        Map<String,Object> dataCredential = new HashMap<>();

        dataCredential.put("username", ConfigReader.getProperty("std_FatihSari"));
        dataCredential.put("password", ConfigReader.getProperty("password"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(dataCredential)
                .post("/{pp1}/{pp2}");

        JsonPath respJP = response.jsonPath();

        String token = respJP.getString("token");

        return token;
    }
    public static String wrongToken(){


        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","apistudent","pp2","getToken");

        Map<String,Object> dataCredential = new HashMap<>();

        dataCredential.put("username", ConfigReader.getProperty("wrongTokenStudent"));
        dataCredential.put("password", ConfigReader.getProperty("password"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(dataCredential)
                .post("/{pp1}/{pp2}");

        JsonPath respJP = response.jsonPath();

        String token = respJP.getString("token");

        return token;
    }
    public static void deleteMethod(int id) {

        JSONObject reqBody = new JSONObject();

        reqBody.put("id", id);

        response = given()
                .spec(API_Hooks.spec)
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + API_Hooks.tokenAdmin)
                .when()
                .body(reqBody.toString())
                .delete(fullPath);

        response.prettyPrint();
    }




}