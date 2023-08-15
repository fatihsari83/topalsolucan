package stepDefinitions;

import io.cucumber.java.en.Given;
import utilities.API_Utils;
import utilities.DB_Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DB_StepDefinition {

    String query19;
    String query20;
    String query21;
    ResultSet rs;
    ResultSet st;
    int flag;

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

        st= DB_Utils.getStatement().executeQuery(query19);
        List< String> mailList= new ArrayList<>();

        while (st.next()){
            mailList.add(st.getString(1));
            System.out.println(st.getString(1));
        }
        System.out.println(mailList);
    }
    @Given("Amount Query'si hazırlanır")
    public void amount_query_si_hazirlanir() {
        query20 = "SELECT name, amount FROM expenses ORDER BY amount DESC LIMIT 1;";

    }
    @Given("Query sonuclari alinir ve doğrulanır.")
    public void query_sonuclari_alinir_ve_dogrulanir() throws SQLException {

        st= DB_Utils.getStatement().executeQuery(query20);
        st.next();
        System.out.println(st.getString(1));

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
    @Given("Database bağlantısı kapatılır.")
    public void database_baglantisi_kapatilir() {
        DB_Utils.closeConnection();

    }

}
