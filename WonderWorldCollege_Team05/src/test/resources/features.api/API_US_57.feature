Feature: US_57 As a user (student), I want to access my Student Information Details through API connection.
  @team05

  Scenario: TC_01 When a valid authorization is used to send a GET request to the "apistudent/getStudentClass" endpoint,
  the response status code should be 200, and the response message should be "Success."

    * Set "apistudent/getStudentClass" parameters
    * Records response for Student with valid authorization information
    * Verifies that status code is 200
    * Verifies that the message information is "Success"


  Scenario: TC_02 When invalid authorization is used to send a GET request to the "apistudent/getStudentClass" endpoint,
  the response status code should be 403, and the response message should be "failed."

    * Set "apistudent/getStudentClass" parameters
    * Records response for Student with invalid authorization information
    * Verifies that status code is 403
    * Verifies that the message information is "failed"


  Scenario: TC_03 The response body should contain a field called "lists," which includes multiple data entries.
              Each entry should have various fields such as "pickup_point_name," "route_pickup_point_id," "transport_fees,
              " "app_key," "parent_app_key," "vehicle_id," "route_title," "vehicle_no," "room_no," "driver_name,"
              "driver_contact," "vehicle_model," "manufacture_year," "driver_license," "vehicle_photo," "hostel_id,"
              "hostel_name," "room_type_id," "room_type," "hostel_room_id," "student_session_id," "fees_discount,"
              "class_id," "class," "section_id," "section," "id," "admission_no," "roll_no," "admission_date," "firstname,"
              "middlename," "lastname," "image," "mobileno," "email," "state," "city," "pincode," "note," "religion," "cast,"
              "house_name," "dob," "current_address," "previous_school," "guardian_is," "parent_id," "permanent_address,"
              "category_id," "adhar_no," "samagra_id," "bank_account_no," "bank_name," "ifsc_code," "guardian_name,"
              "father_pic," "height," "weight," "measurement_date," "mother_pic," "guardian_pic," "guardian_relation,"
              "guardian_phone," "guardian_address," "is_active," "created_at," "updated_at," "father_name," "father_phone,"
              "blood_group," "school_house_idschool_house_id," "father_occupation," "mother_name," "mother_phone,"
              "mother_occupation," "guardian_occupation," "gender," "rte," "guardian_email," "username," "password," "user_id,"
              "dis_reason," "dis_note," and "disable_at." The content of each field should be verified to ensure the correctness of the response data.


      * Set "apistudent/studentInformationDetails" parameters
      * Records response for Student with valid authorization information
      * List data is verified in response from apistudent_studentInformationDetails endpoint







