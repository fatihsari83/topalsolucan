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

    String query22;

    String query23;
    String query24;

    @Given("Database bağlantısı kurulur.")
    public void database_baglantisi_kurulur() {
        DB_Utils.createConnection();

    }

    @Given("Query hazırlanır")
    public void query_hazirlanir() {
        query19 = "SELECT email FROM students ORDER BY LENGTH(email) DESC LIMIT 5;";

    }

    @Given("Query çalıştırılır,sonuclari alınır ve yazdırılır.")
    public void query_calistirilir_sonuclari_alinir_ve_yazdirilir() throws SQLException {

        rs = DB_Utils.getStatement().executeQuery(query19);
        List<String> mailList = new ArrayList<>();

        while (rs.next()) {
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

        rs = DB_Utils.getStatement().executeQuery(query20);
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

        int verify = 0;
        if (sonuc > 0) {
            verify++;
        }
        assertEquals(verify, 1);


    }
    @Given("Database connection is established")
    public void database_connection_is_established() {
        DB_Utils.createConnection();
    }

    @Given("Query is prepared")
    public void query_is_prepared() {
        query22 = "SELECT id, name FROM income ORDER BY amount DESC LIMIT 10";
    }

    @Given("Query is executed and results are obtained")
    public void query_is_executed_and_results_are_obtained() throws SQLException {
        rs = getStatement().executeQuery(query22);
    }

    @Given("Query results are verified")
    public void query_results_are_verified() throws SQLException {
        int rowCount = 0;
        while (rs.next()) {
            rowCount++;
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("ID: " + id + ", Name: " + name);
        }

        Assert.assertEquals("Top 10 income values are not fetched as expected.", 10, rowCount);
    }

    @Given("Query for longest employment duration is prepared")
    public void query_for_longest_employment_duration_is_prepared() {
        query23 = "SELECT id, name, department, date_of_joining FROM staff ORDER BY DATEDIFF(NOW(), date_of_joining) DESC LIMIT 3";
    }

    @Given("Query for longest employment duration is executed and results are obtained")
    public void query_for_longest_employment_duration_is_executed_and_results_are_obtained() throws SQLException {
        rs = getStatement().executeQuery(query23);
    }

    @Given("Query results for longest employment duration are verified")
    public void query_results_for_longest_employment_duration_are_verified() throws SQLException {
        int rowCount = 0;
        while (rs.next()) {
            rowCount++;
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String department = rs.getString("department");
            String dateOfJoining = rs.getString("date_of_joining");
            System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department + ", Date of Joining: " + dateOfJoining);
        }

        Assert.assertEquals("Top 3 staff members with longest employment duration are not fetched as expected.", 3, rowCount);
    }

    @Given("Prepare query for oldest staff member")
    public void prepare_query_for_oldest_staff_member() {
        query24 = "SELECT email, contact_no, basic_salary FROM staff WHERE dob = (SELECT MIN(dob) FROM staff)";
    }

    @Given("Execute query for oldest staff member and obtain results")
    public void execute_query_for_oldest_staff_member_and_obtain_results() throws SQLException {
        rs = getStatement().executeQuery(query24);
    }

    @Given("Verify query results for oldest staff member")
    public void verify_query_results_for_oldest_staff_member() throws SQLException {
        if (rs.next()) {
            String email = rs.getString("email");
            String contactNo = rs.getString("contact_no");
            double basicSalary = rs.getDouble("basic_salary");

            System.out.println("Email: " + email + ", Contact No: " + contactNo + ", Basic Salary: " + basicSalary);
        } else {
            Assert.fail("No data found for the oldest staff member.");
        }
    }
}

