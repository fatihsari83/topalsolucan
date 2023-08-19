Feature: US16 As an administrator, I want to update the registered Vehicle information in the system through API connection

  Scenario: TC01 api/vehicleUpdate endpoint'ine gecerli authorization bilgileri ve dogru datalar (id, vehicle_no, vehicle_model, vehicle_photo, manufacture_year, registration_number, chasis_number, max_seating_capacity, driver_name, driver_licence, driver_contact, note) iceren bir PATCH body gönderildiginde dönen status code'in 200 oldugu ve response body'deki message bilgisinin "Success" oldugu dogrulanmali

    * Set "api/vehicleUpdate" parameters
    * Submit a PATCH body with valid authorization information and correct data to the apivehicleUpdate endpoint
    * Verifies that status code is 200
    * Verifies that the message information is "Success"

  Scenario: TC02 Status code returned when sending a PATCH body (vehicle_no, vehicle_model, vehicle_photo, manufacture_year, registration_number, chasis_number, max_seating_capacity, driver_name, driver_licence, driver_contact, note) containing invalid authorization information or missing/wrong data (id)
  to the api/vehicleUpdate endpoint It should be verified that it is 403 and the message information in the response body is "failed"

    * Set "api/vehicleUpdate" parameters
    * Submit a PATCH body with invalid authorization information or missingincorrect data to the apivehicleUpdate endpoint
    * Verifies that status code is 403
    * Verifies that the message information is "failed"

  Scenario: TC03 It should be verified that the updateId information in the response body is the same as the id information in the PATCH request body sent to the api/vehicleUpdate endpoint.

    * Set "api/vehicleUpdate" parameters
    * Submit a PATCH body with valid authorization information and correct data to the apivehicleUpdate endpoint
    * Verify that the DeletedId in the response body is the same as the ID in the DELETE request body sent to the apivisitorsPurposeDelete endpoint


  Scenario: TC04 "It should be verified via the API that the Vehicle record that is desired to be updated via the API is updated.

    * Set "api/vehicleUpdate" parameters
    * Submit a PATCH body with valid authorization information and correct data to the apivehicleUpdate endpoint
    * Post request is send for apivehicleId

