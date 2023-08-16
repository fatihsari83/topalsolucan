package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.ConfigReader;
import utilities.DB_Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static utilities.DB_Utils.getColumnData;
import static utilities.DB_Utils.getStatement;


public class DB_StepDefinition {

    Connection connection;
    Statement st;
    ResultSet rs;
    String query19;
    String query20;
    String query21;
    String query16, query17, query18 ;
    ResultSet rs16 ,rs17, rs18;
    @Given("Database bağlantısı kurulur.")
    public void database_baglantisi_kurulur() {
        DB_Utils.createConnection();

    }
    @Given("Query hazırlanır")
    public void query_hazirlanir() {
        query19= "SELECT email FROM students ORDER BY LENGTH(email) DESC LIMIT 5;";

    }
    @Given("Query çalıştırılır,sonuclari alınır ve yazdırılır.")
    public void query_calistirilir_sonuclari_alinir_ve_yazdirilir() throws SQLException {

        rs= DB_Utils.getStatement().executeQuery(query19);
        List< String> mailList= new ArrayList<>();

        while (rs.next()){
            mailList.add(rs.getString(1));
            System.out.println(rs.getString(1));
        }
        System.out.println(mailList);
    }
    @Given("Database bağlantısı kapatılır.")
    public void database_baglantisi_kapatilir() {
        DB_Utils.closeConnection();

    }
    @Given("Amount Query'si hazırlanır")
    public void amount_query_si_hazirlanir() {
        query20 = "SELECT name, amount FROM expenses ORDER BY amount DESC LIMIT 1;";

    }
    @Given("Query sonuclari alinir ve doğrulanır.")
    public void query_sonuclari_alinir_ve_dogrulanir() throws SQLException {

        rs= DB_Utils.getStatement().executeQuery(query20);
        rs.next();
        System.out.println(rs.getString(1));

    }
    @Given("Update Query'si hazırlanır.")
    public void update_query_si_hazirlanir() {

        query21 = "UPDATE general_calls SET description = CONCAT(description, 'Fatih aradi.') WHERE id = '7';";

    }
    @Given("Sonuclari alinir ve doğrulanır.")
    public void sonuclari_alinir_ve_dogrulanir() throws SQLException {

        int sonuc = DB_Utils.getStatement().executeUpdate(query21);

        int verify=0;
        if (sonuc>0){
            verify++;
        }
        assertEquals(verify,1);


    }


    @Given("Query16 is being prepared")
    public void query16_is_being_prepared() {
        query16="SELECT * FROM wonderworld_qa.online_admissions ORDER BY admission_date DESC LIMIT 10";

    }
    @Given("The query is sent to online_admissions table and results are validated")
    public void the_query_is_sent_to_online_admissions_table_and_results_are_validated() throws SQLException {
        rs16 = DB_Utils.getStatement().executeQuery(query16);
        while (rs16.next()) {
            int id = rs16.getInt("id");
            String name=rs16.getNString("firstname");
            System.out.println(id +" "+ name);
        }

    }
    @Given("Database connection is closed")
    public void database_connection_is_closed() {
        DB_Utils.closeConnection();

    }

    @Given("Query17 is being prepared")
    public void query17_is_being_prepared() {
        query17 ="SELECT AVG(passing_percentage) FROM wonderworld_qa.onlineexam";

    }
    @Then("The query is sent to onlineexam and results are validated")
    public void the_query_is_sent_to_onlineexam_and_results_are_validated() throws SQLException {
        rs17 = DB_Utils.getStatement().executeQuery(query17);
        int expected17 =34;
        rs17.next();
        int actualRS17=rs17.getInt(1);
        assertEquals(expected17,actualRS17);
        System.out.println(actualRS17);

    }
    @Given("Query18 is being prepared")
    public void query18_is_being_prepared() {
        query18 ="SELECT COUNT(DISTINCT student_session_id) FROM wonderworld_qa.onlineexam_students;";

    }
    @Then("The query is sent to onlineexam_students and results are validated.")
    public void the_query_is_sent_to_onlineexam_students_and_results_are_validated() throws SQLException {

        rs18 = DB_Utils.getStatement().executeQuery(query18);
        int expected18 = 266;

        rs18.next();
        int actualRS18=rs18.getInt(1);
        System.out.println(actualRS18);

        assertEquals(expected18,actualRS18);

        System.out.println(actualRS18);

    }


    @Given("Creates a new database connection.")
    public void creates_a_new_database_connection() {
        DB_Utils.createConnection();

    }








}