package testData;

import org.json.JSONObject;

public class TestData_API_US_033 {
    public JSONObject dataUS_033(){

        JSONObject data = new JSONObject();
        data.put("id", 250);
        data.put("staff_id", "null");
        data.put("student_session_id","null");
        data.put("source", "Online");
        data.put("purpose", "FREE fast ranks for wonderworldcollege.com");
        data.put("name", "Mike Creighton");
        data.put("email", "altunmetehan01@gmail.com");
        data.put("contact", "");
        data.put("id_proof", "");
        data.put("no_of_people", "0");
        data.put("date", "2023-07-30");
        data.put("in_time", "");
        data.put("out_time", "");
        data.put("note", "Hi there Just checked your wonderworldcollege.com baclink profile, I noticed a moderate percentage of toxic links pointing to your website We will investigate each link for its toxicity and perform a professional clean up for you free of charge. Start recovering your ranks today: https://www.hilkom-digital.de/professional-linksprofile-clean-up-service/ Regards Mike CreightonHilkom Digital SEO Experts https://www.hilkom-digital.de/ (Sent from online front site)");
        data.put("image", "null");
        data.put("meeting_with", "");
        data.put("created_at", "2023-07-23 09:14:13");
        data.put("class", "null");
        data.put("section", "null");
        data.put("staff_name", "null");
        data.put("staff_surname", "null");
        data.put("staff_employee_id", "null");
        data.put("class_id", "null");
        data.put("section_id", "null");
        data.put("students_id", "null");
        data.put("admission_no", "null");
        data.put("student_firstname", "null");
        data.put("student_middlename", "null");
        data.put("student_lastname", "null");
        data.put("role_id", "null");

        return data;

    }
    public JSONObject expDataUS_033(){

        JSONObject expData = new JSONObject();

        expData.put("status", 200);
        expData.put("message", "Success");
        expData.put("Token_remaining_time", 1);
        expData.put("lists", dataUS_033());

        return expData;
    }

}
