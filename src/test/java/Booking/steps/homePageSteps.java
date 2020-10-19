package Booking.steps;

import Booking.helpers.RunnerHelper;
import Booking.pages.HomePage;
import Booking.pages.HotelsPage;
import Booking.pages.InformationPage;
import Booking.pages.SelectedHotelPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class homePageSteps {
    private HomePage _homePage;
    private RunnerHelper _runner = new RunnerHelper();
    private HotelsPage _hotelsPage;
    private SelectedHotelPage _selectedHotelPage;
    private InformationPage _informationPage;
    private String _hotelName;
    private String _hotelPrice;
    private String _hotelScore;
    private String _adults;
    private String _children;
    private String _rooms;

    @Given("^i am in the home page of \"([^\"]*)\"$")
    public void iAmInTheHomePageOf(String url) {
        _homePage = new HomePage(_runner.setUp(url));
        _homePage.verifyTheUserIsInTheHomePage();
    }

    @And("^i look for the city \"([^\"]*)\"$")
    public void iLookForTheCity(String city) {
        _homePage.lookingForTheCity(city);
    }

    @And("^the check-in day is \"([^\"]*)\" days after today's day and checkout \"([^\"]*)\" days later check-in$")
    public void theCheckInDayIsDaysAfterTodaySDayAndCheckoutDaysLaterCheckIn(String checkinDate, String checkoutDate) throws Throwable {
        _homePage.selectingCheckinCheckoutDate(checkinDate, checkoutDate);
    }

    @When("^i select \"([^\"]*)\" adults, \"([^\"]*)\" child and \"([^\"]*)\" room$")
    public void iSelectAdultsChildAndRoom(String adults, String children, String rooms) {
        _adults = adults;
        _children = children;
        _rooms = rooms;
        _homePage.addPeopleAndRooms(adults, children, rooms);
        _homePage.selectingTheAgeOfTheFirstchild();
    }

    @And("^i click on the button to search hotels$")
    public void iClickOnTheButtonToSearchHotels() {
        _homePage.clickOnSearchButton();
    }

    @Then("^i can filter results by stars and choosing the \"([^\"]*)\" hotel$")
    public void iCanFilterResultsByStarsAndChoosingTheHotel(String hotelNumber) throws InterruptedException {
        _hotelsPage = new HotelsPage(_runner.driver());
        _hotelsPage.filterByStars();
        _hotelName = _hotelsPage.getTheHotelTitle(hotelNumber);
        _hotelPrice = _hotelsPage.getThePrice(hotelNumber);
        _hotelScore = _hotelsPage.getTheScore(hotelNumber);
        _hotelsPage.clickOnChooseTheRoom(hotelNumber);
    }

    @And("^i verify the data of the hotel is correct$")
    public void iVerifyTheDataOfTheHotelIsCorrect() {
        _selectedHotelPage = new SelectedHotelPage(_runner.driver());
        _selectedHotelPage.verifyTheNameOfTheHotel(_hotelName);
        _selectedHotelPage.verifyThePrice(_hotelPrice);
        _selectedHotelPage.verifyTheNumberOfAdultsAndChildren(_adults, _children);
        _selectedHotelPage.clickOnButtonToBookTheRoom();
    }

    @Then("^i confirm the number of rooms$")
    public void iConfirmTheNumberOfRooms() {
        _selectedHotelPage.confirmTheNumberOfRooms();
    }

    @And("^continue clicking on the button to confirm$")
    public void continueClickingOnTheButtonToConfirm() {
        _selectedHotelPage.clickOnContinue();
    }

    @And("^complete the mandatory data and continue$")
    public void completeTheMandatoryDataAndContinue() {
        _informationPage = new InformationPage(_runner.driver());
        _informationPage.closePopUp();
        _informationPage.setTheName();
        _informationPage.setLastName();
        _informationPage.setEmail();
        _informationPage.clickOnNextButton();
    }

    @And("^finally add the phone and verify the data$")
    public void finallyAddThePhoneAndVerifyTheData() {
        _informationPage.setPhone();
        _informationPage.verifyCountry();
        _informationPage.verifyNameAndLastName();
        _informationPage.verifyEmail();
    }
}
