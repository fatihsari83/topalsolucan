Feature: US11 Update the fine_amount value to '200.00' for the record in the transport_feemaster table where the month value is 'October'.


  Scenario: TC01 Changing the fine_amount value of the record with 'October' month to '200.00' in the transport_feemaster table


    * Database connection is established.
    * DB US11 Query is run and the results are taken.
    * DB US11 Query results are validated.
    * Database connection is closed