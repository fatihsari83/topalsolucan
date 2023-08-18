package stepDefinitions;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import utilities.ConfigReader;
import utilities.DB_Utils;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utilities.DB_Utils.closeConnection;
import static utilities.DB_Utils.getStatement;

public class DB_StepDefinition {

    ResultSet resultSet11;
    ResultSet resultSet12;

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

       int sayi= getStatement().executeUpdate(ConfigReader.getProperty("db_us11Query"));





    }

    @Given("DB US11 Query results are validated.")
    public void db_us11_query_results_are_validated() throws SQLException {
        String query = "SELECT * FROM wonderworld_qa2.transport_feemaster WHERE id=7;";
        resultSet11 = getStatement().executeQuery(query);
        while (resultSet11.next()) {
            System.out.println(resultSet11.getString("month") + "--" + resultSet11.getFloat("fine_amount"));
        }


    }

    // US12

    @Given("DB US12 Query is run and the results are taken.")
    public void db_us12_query_is_run_and_the_results_are_taken() throws SQLException {

        resultSet12=  getStatement().executeQuery(ConfigReader.getProperty("db_us12Query"));

    }

    @Given("DB US12 Query results are validated.")
    public void db_us12_query_results_are_validated() throws SQLException {
        while (resultSet12.next()) {
            System.out.println(resultSet12.getString("name") + " -- " + resultSet12.getInt("id"));
        }

    }




}



