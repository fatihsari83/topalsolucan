package stepDefinitions;

import io.cucumber.java.en.Given;
import utilities.DB_Utils;

import java.sql.ResultSet;

public class DB_StepDefinition {

    ResultSet resultSet;
    @Given("Database baglantisi kurulur.")
    public void database_baglantisi_kurulur() {

        DB_Utils.createConnection();

    }
    @Given("DB_US05 Query calistirilir ve sonuclari alinir.")
    public void db_us05_query_calistirilir_ve_sonuclari_alinir() {

    }
    @Given("DB_US05 Query sonuclari dogrulanir.")
    public void db_us05_query_sonuclari_dogrulanir() {

    }
    @Given("Database baglantisi kapatilir.")
    public void database_baglantisi_kapatilir() {

    }


}
