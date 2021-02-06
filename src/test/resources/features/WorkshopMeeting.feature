@setmeetings
Feature: Create a recurring meeting using Native Android Calendar App

  Scenario: Create a workshop meeting event
    Given I have launched the Calendar App
    And I scroll through the newly launched items
    And I select the monthly view from the top left menu
    Then I select the next working "Friday" and click on tap to create
    Then I give the meeting title as "Workshop"
    And I set the meeting time from "9:30 AM" to "1:30 PM"
    And I set the meeting recurrence for "3" months
    Then I save the meeting
    And I check if meeting with title "Workshop" is present
    Then I select the monthly view from the top left menu
    And I select the next working "Monday" and click on tap to create
    Then I give the meeting title as "Stand Up"
    And I set the meeting time from "11:30 AM" to "11:45 AM"
    And I set the meeting recurrence for "3" months
    Then I save the meeting
    And I check if meeting with title "Stand Up" is present