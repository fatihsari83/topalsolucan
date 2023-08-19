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
import static utilities.DB_Utils.*;


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
    ResultSet resultSet;
    String query16, query17, query18 ;
    ResultSet rs16 ,rs17, rs18;
    String query01;
    String query02;
    String query03;
    ResultSet rs01;
    ResultSet rs02;
    ResultSet rs03;
    String query25;
    String query26;
    String query27;
    String query13;
    String query14;
    String query15;

    String query22;

    String query23;
    String query24;

    // DB_US_19-20-21
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
        closeConnection();

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

        query21 = "INSERT INTO wonderworld_qa3.general_calls VALUES (180,'team5fatih','21234512','2023-08-16','successed test','2023-08-15','50','yeter artik','the coming','2023-08-15')";

    }

    @Given("Sonuclari alinir ve doğrulanır.")
    public void sonuclari_alinir_ve_dogrulanir() throws SQLException {

        int sonuc = DB_Utils.getStatement().executeUpdate(query21);

        int verify = 0;
        if (sonuc > 0) {
            verify++;
        }

      
      
        assertEquals(verify,1);
}

    // US10
    @Given("Database connection is established.")
    public void database_connection_is_established() {

        DB_Utils.createConnection();
    }
    @Given("DB US10 Query is run and the results are taken.")
    public void db_us10_query_is_run_and_the_results_are_taken() {
        DB_Utils.deleteRecord("wonderworld_qa3.visitors_book","id",40);

    }
    @Given("DB US10 Query results are validated.")
    public void db_us10_query_results_are_validated() {



    }
    @Given("Database connection is closed")
    public void database_connection_is_closed() {
        closeConnection();
    }
    // US11
    @Given("DB US11 Query is run and the results are taken.")
    public void db_us11_query_is_run_and_the_results_are_taken() throws SQLException {

        DB_Utils.updateQuery(ConfigReader.getProperty("db_us11Query"));


    }

    @Given("DB US11 Query results are validated.")
    public void db_us11_query_results_are_validated() {
        int sonuç =0;
        Assert.assertEquals(sonuç,1);
    }

    // US12

    @Given("DB US12 Query is run and the results are taken.")
    public void db_us12_query_is_run_and_the_results_are_taken() throws SQLException {
        DB_Utils.executeQuery(ConfigReader.getProperty("db_us12Query"));
        rs= DB_Utils.getStatement().executeQuery(ConfigReader.getProperty("db_us12Query"));
        List< String> workList= new ArrayList<>();

        while (rs.next()){
            workList.add(rs.getString(1));
            System.out.println(rs.getString(1));
        }
        System.out.println(workList);

    }
/*
    @Given("DB US12 Query results are validated.")
    public void db_us12_query_results_are_validated() {
        int sonuç =0;
        Assert.assertEquals(sonuç,5);

    }

 */
    @Given("Query16 is being prepared")
    public void query16_is_being_prepared() {
        query16="SELECT * FROM wonderworld_qa3.online_admissions ORDER BY admission_date DESC LIMIT 10";

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


    @Given("Query17 is being prepared")
    public void query17_is_being_prepared() {
        query17 ="SELECT AVG(passing_percentage) FROM wonderworld_qa3.onlineexam";

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
        query18 ="SELECT COUNT(DISTINCT student_session_id) FROM wonderworld_qa3.onlineexam_students;";

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
    // DB_US_01
    @Given("Database connection established.")
    public void database_connection_established() {
        DB_Utils.createConnection();
    }
    @Given("Query01 is being prepared")
    public void query01_is_being_prepared() {
        query01 = "SELECT COUNT(*) AS user_count FROM wonderworld_qa2.chat_users WHERE create_staff_id = 1;";

    }
    @Given("The query is sent to chat_users table and results are validated")
    public void the_query_is_sent_to_chat_users_table_and_results_are_validated() throws SQLException {

        rs01 = DB_Utils.getStatement().executeQuery(query01);
        int expected01 = 11;
        rs01.next();
        int actualRS01 = rs01.getInt(1);
        assertEquals(expected01, actualRS01);

        System.out.println(actualRS01);
    }



    // DB_US_02

    @Given("Query02 is being prepared")
    public void query02_is_being_prepared() {
        query02 = "SELECT id FROM wonderworld_qa2.class_sections WHERE class_id = section_id;";
    }

    @Given("The query is sent to class_sections table and results are validated")
    public void the_query_is_sent_to_class_sections_table_and_results_are_validated() throws SQLException {

        rs02 = DB_Utils.getStatement().executeQuery(query02);
        while (rs02.next()) {
            int id = rs02.getInt("id");
            System.out.println("ID: " + id);
        }
    }


    // DB_US_03

    @Given("Query03 is being prepared")
    public void query03_is_being_prepared() {
        query03 = "SELECT email FROM wonderworld_qa2.students WHERE firstname = 'Brian' AND lastname = 'Kohlar';";
    }


    @Given("The query is sent to students table and results are validated")
    public void the_query_is_sent_to_students_table_and_results_are_validated() throws SQLException {
        rs03 = DB_Utils.getStatement().executeQuery(query03);
        String expected03 = "brain@gmail.com";
        rs03.next();
        String actualRS03 = rs03.getString(1);
        assertEquals(expected03, actualRS03);
        System.out.println(actualRS03);

    }



    @Given("Query is prepared US_25")
    public void query_is_prepared_US_25() {

        query25 = "SELECT id, entrydt FROM staff_rating WHERE comment LIKE '%good%'";

    }

    @Given("The query is run, the results are taken and printed US_25")
    public void the_query_is_run_the_results_are_taken_and_printed() throws SQLException {

        rs = DB_Utils.getStatement().executeQuery(query25);
        List<String> commentList = new ArrayList<>();

        while (rs.next()) {
            commentList.add(rs.getString(1));
            commentList.add(rs.getString(2));
            System.out.println(rs.getString(1)+"-"+rs.getString(2));
        }

        //rs.next();

        //System.out.println(rs.getString(1));
    }



    // DB_US_26

    @Given("Query is prepared US_26")
    public void query_is_prepared_US_26() {

        query26 = "SELECT admission_no, firstname, lastname FROM online_admissions WHERE created_at >= '2023-01-01' AND created_at <= '2023-01-31'";

    }

    @Given("The query is run, the results are taken and printed US_26")
    public void the_query_is_run_the_results_are_taken_and_printed_US_26() throws SQLException {

        rs = DB_Utils.getStatement().executeQuery(query26);
        List<String> createList = new ArrayList<>();

        while (rs.next()) {
            createList.add(rs.getString(1));
            createList.add(rs.getString(2));
            createList.add(rs.getString(3));
            System.out.println(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3));
        }
    }

    // DB_US_27

    @Given("Query is prepared US_27")
    public void query_is_prepared_US_27() {

        query27 = " SELECT lastname " +
                "    From online_admissions" +
                "    WHERE lastname IN (" +
                "    SELECT lastname " +
                "    FROM online_admissions" +
                "    GROUP BY lastname " +
                "    HAVING COUNT(*) > 1" +
                "    )ORDER BY lastname ;";

    }

    @Given("The query is run, the results are taken and printed US_27")
    public void the_query_is_run_the_results_are_taken_and_printed_US_27() throws SQLException {

        rs = DB_Utils.getStatement().executeQuery(query27);
        List<String> lastnameList = new ArrayList<>();

        for (String each:lastnameList
        ) {
            if (!each.contains(" ")){
                lastnameList.add(each);
            }
        }

        while (rs.next()) {
            lastnameList.add(rs.getString(1));

            System.out.println(rs.getString(1));
        }
    }

    @Given("US_13 Query hazirlanir")
    public void US_13_query_hazirlanir() {
        query13 = "SELECT email FROM online_admissions WHERE firstname LIKE '%al%';";

    }

    @Given("US_13 Query calistirilir,sonuclari alinir ve yazdirilir.")
    public void US_13_query_calistirilir_sonuclari_alinir_ve_yazdirilir() throws SQLException {
        query13 = "SELECT email FROM online_admissions WHERE firstname LIKE '%al%';";

        resultSet= getStatement().executeQuery(query13);

    }

    @Given("US_13 Query results are verified")
    public void US_13_query_results_are_verified() throws SQLException {
        while(resultSet.next()){

            String  email= resultSet.getString("email");

            System.out.println("email:   "+ email);

            DB_Utils.createConnection();
        }
    }

    @Given("DB_US14 Query calistirilir ve sonuclari alinir.")
    public void db_us14_query_calistirilir_ve_sonuclari_alinir() throws SQLException {
        query14 = "SELECT book_title\n" +
                "FROM books\n" +
                "WHERE author = 'Rubina malik' OR author = 'Mien  Ali';";
        resultSet= getStatement().executeQuery(query14);

    }
    @Given("DB_US14 Query sonuclari dogrulanir.")
    public void db_us14_query_sonuclari_dogrulanir() throws SQLException {

        while(resultSet.next()){

            String  book_title= resultSet.getString("book_title");


            System.out.println("book_title:   "+ book_title );

        }

    }
    @Given("US_15 Query hazırlanır")
    public void US_15_query_hazirlanir() {
        query15 = "SELECT *\n" +
                "FROM books\n" +
                "WHERE qty BETWEEN 100 AND 500;";

    }

    @Given("US_15 Query çalıştırılır,sonuclari alınır ve yazdırılır.")
    public void US_15_query_calistirilir_sonuclari_alinir_ve_yazdirilir() throws SQLException {
        query15 = "SELECT *\n" +
                "FROM books\n" +
                "WHERE qty BETWEEN 100 AND 500;";

        resultSet = getStatement().executeQuery(query15);



    }

    @Given("US_15 Query results are verified")
    public void US_15_query_results_are_verified() throws SQLException {
        while(resultSet.next()){

            String  book_title= resultSet.getString("book_title");


            System.out.println("book_title:   "+ book_title );

        }


    }


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

