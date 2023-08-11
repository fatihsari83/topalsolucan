Feature: US_23 As an administrator, I want to access the Student List through API connection.
               Yönetici olarak Öğrenci Listesine API bağlantısı üzerinden erişmek istiyorum.

  Scenario: TC_01 api/studentList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
                  dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

      Path parametrelerini set eder.
      Admin icin, gecerli authorization bilgileri ile  response kaydeder
      Donen status kodunun 200 oldugunu dogrular
      Donen response message bilgisinin Success oldugunu dogrular


     * Set "api/studentList" parameters
     * Records response for Admin with valid authorization information
     * Verifies that status code is 200
     * Verifies that the message information is "Success"



  Scenario: TC_02  api/studentList endpoint'ine gecersiz authorization bilgileri ile bir GET Request gönderildiginde dönen
                   status code'un 403 oldugu ve response message bilgisinin "failed" oldugu dogrulanmali

      Path parametrelerini set eder.
      Admin icin, gecersiz authorization bilgileri ile  response kaydeder
      Donen status kodunun 200 oldugunu dogrular
      Donen response message bilgisinin Success oldugunu dogrular


     * Set "api/studentList" parameters
     * Records response for Admin with invalid authorization information
     * Verifies that status code is 403
     * Verifies that the message information is "failed"



  Scenario Outline: TC_03  Response body icindeki lists icerigi class id'si = "3" ve  student_session_id "44" olan ogrencinin
                           datalari asagidaki expected data gibi dogrulanmali.


     Path parametrelerini set eder.
     Admin icin, gecerli authorization bilgileri ile response kaydeder
     Response icinde class_id=3 olan kaydin datalarini dogrular


    * Set "api/studentList" parameters
    * Records response for Admin with valid authorization information
    * Verifies that record has "class_id=3" and "student_session_id=44"includes <expectedData>

    Examples:
      | expectedData                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
      | class_id=3student_session_id=44id=3class=Class3section_id=2section=Badmission_no=18003roll_no=201admission_date=2021-03-20firstname=Nicolasmiddlename=nulllastname=Flemingimage=uploads/student_images/3.jpgmobileno=54564645564email=nicolas@gmail.comstate=nullcity=nullpincode=nullreligion=dob=2015-05-12current_address=WestBrooklynpermanent_address=category_id=category=adhar_no=564564samagra_id=4564654bank_account_no=65456465454bank_name=CapitalBankifsc_code=645646guardian_name=DorianFlemingguardian_relation=Fatherguardian_email=dorian@gmail.comguardian_phone=546465477guardian_address=WestBrooklynis_active=yescreated_at=2023-08-0207:06:07updated_at=nullfather_name=DorianFlemingrte=nullgender=Maleuser_tbl_id=5username=std3user_tbl_password=passworduser_tbl_active=yes |

