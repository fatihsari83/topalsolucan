package hooks;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utilities.API_Utils;
import utilities.ConfigReader;

public class API_Hooks {
    public static RequestSpecification spec;
    public static String tokenAdmin;
    public static String tokenTeacher;
    public static String tokenStudent;

    public static String wrongToken;

    /*
    @Before(order = 1)
    public void beforeGenerateTokenAdmin(){
        tokenAdmin = API_Utils.generateTokenAdmin();
        System.out.println("Token Admin : " + tokenAdmin);
    }
    @Before(order = 1)
    public void beforeGenerateTokenTeacher(){
        tokenTeacher = API_Utils.generateTokenTeacher();
        System.out.println("Token Teacher: " + tokenTeacher);

    }
    @Before(order = 1)
    public void beforeGenerateTokenTeacherWrong(){
        tokenTeacher = API_Utils.generateTokenTeacher();
        System.out.println("Wrong Token Teacher: " + ConfigReader.getProperty("wrongTokenTeacher"));
    }
    @Before(order = 1)
    public void beforeGenerateTokenStudent(){
        tokenStudent = API_Utils.generateTokenStudent();
        System.out.println("Token Student: " + tokenStudent);

    }
    @Before(order = 1)
    public void beforeGenerateTokenStudentWrong() {
        tokenStudent = API_Utils.wrongToken();
        System.out.println("Wrong Token Student: " + ConfigReader.getProperty("wrongTokenStudent"));
    }

     */

    @Before (order = 0)
    public void setUpApi(){
        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();
        tokenAdmin = API_Utils.generateTokenAdmin();
        tokenTeacher = API_Utils.generateTokenTeacher();
        tokenStudent = API_Utils.generateTokenStudent();
        wrongToken
                = API_Utils.wrongToken();
    }


}