Feature: Booking page

  Background:
    Given i am in the home page of "https://www.booking.com/index.es.html"
    And i look for the city "Bogota"
    And the check-in day is "30" days after today's day and checkout "15" days later check-in
    When i select "3" adults, "1" child and "1" room
    And i click on the button to search hotels


  Scenario: booking an hotel
    Then i can filter results by stars and choosing the "2" hotel
    And i verify the data of the hotel is correct

  Scenario: selecting again the number of rooms and fill in the form
    And i can filter results by stars and choosing the "2" hotel
    And i verify the data of the hotel is correct
    Then i confirm the number of rooms
    And continue clicking on the button to confirm
    And complete the mandatory data and continue
    And finally add the phone and verify the data
