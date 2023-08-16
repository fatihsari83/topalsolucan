package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;



    public class API_Utils {
        public static RequestSpecification spec;
        public static String generateTokenAdmin(){
            spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
            spec.pathParams("pp1","api","pp2","getToken");
            Map<String,Object> dataCredential = new HashMap<>();
            dataCredential.put("email", ConfigReader.getProperty("emailAdmin"));
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
            dataCredential.put("email", ConfigReader.getProperty("emailTeacher"));
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

            dataCredential.put("username", ConfigReader.getProperty("emailStudent"));
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




    }
