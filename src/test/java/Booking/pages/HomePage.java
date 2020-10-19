package Booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.LocalDate;

public class HomePage extends BasePage {
    @FindBy(css = "#cross-product-bar span[data-et-click=\"goal:xpb_accommodation goal:xpb_total_clicks\"]")
    private WebElement _dormirLabel;

    @FindBy(id = "ss")
    private WebElement _textBoxFinder;

    @FindBy(css = ".sb-date-field[data-mode=\"checkin\"]")
    private WebElement _checkinDate;

    @FindBy(css = "[data-bui-ref =\"calendar-next\"]")
    private WebElement _nextMonth;

    @FindBy(id = "xp__guests__toggle")
    private WebElement _peopleAndRoomsAmount;

    @FindBy(css = ".sb-group__field-adults .bui-stepper__display")
    private WebElement _numberOfAdultsByDefault;

    @FindBy(css = ".sb-group-children .bui-stepper__display")
    private WebElement _numberOfChildrenByDefault;

    @FindBy(css = ".sb-group__field-rooms .bui-stepper__display")
    private WebElement _numberOfRoomsByDefault;

    @FindBy(name = "age")
    private WebElement _ageOfFirstChild;

    @FindBy(css="[aria-label=\"Edad del niño 1\"] :nth-child(11)")
    private WebElement _choosingTheAge;

    @FindBy(css = ".sb-searchbox__button")
    private WebElement _searchButton;


    private String _increaseTheNumber = ".bui-stepper__add-button[aria-label=\"Aumenta el número de variable\"]";
    private String _decreaseTheNumber = ".bui-stepper__subtract-button[aria-label=\"Reduce el número de variable\"]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage verifyTheUserIsInTheHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(_dormirLabel)).isDisplayed();
        if (!_dormirLabel.isSelected()) {
            _dormirLabel.click();
        }
        Assert.assertEquals("Dormir", _dormirLabel.getText());
        return new HomePage(driver);
    }

    public HomePage lookingForTheCity(String city) {
        _textBoxFinder.sendKeys(city);
        return new HomePage(driver);
    }

    public HomePage selectingCheckinCheckoutDate(String checkinDate, String checkoutDate) {
        _checkinDate.click();
        _nextMonth.click();
        String date = currentLocalDate(checkinDate);
        driver.findElement(By.cssSelector(".bui-calendar__date[data-date=\"" + date + "\"]")).click();
        int checkoutDateDays = Integer.parseInt(checkoutDate) + Integer.parseInt(checkinDate);
        date = currentLocalDate(String.valueOf(checkoutDateDays));
        driver.findElement(By.cssSelector(".bui-calendar__date[data-date=\"" + date + "\"]")).click();
        return new HomePage(driver);
    }

    public HomePage addPeopleAndRooms(String numberOfAdultsDesired, String numberOfChildrenDesired, String numberOfRoomsDesired) {
        _peopleAndRoomsAmount.click();
        int numberOfAdultsDefault = Integer.parseInt(_numberOfAdultsByDefault.getText());
        int numberOfChildrenDefault = Integer.parseInt(_numberOfChildrenByDefault.getText());
        int numberOfRoomsDefault = Integer.parseInt(_numberOfRoomsByDefault.getText());
        increaseDecreaseTheUsersAndRooms(numberOfAdultsDefault, Integer.parseInt(numberOfAdultsDesired), "Adultos");
        increaseDecreaseTheUsersAndRooms(numberOfChildrenDefault, Integer.parseInt(numberOfChildrenDesired), "Niños");
        increaseDecreaseTheUsersAndRooms(numberOfRoomsDefault, Integer.parseInt(numberOfRoomsDesired), "Habitaciones");
        return new HomePage(driver);
    }

    public HomePage selectingTheAgeOfTheFirstchild(){
        _ageOfFirstChild.click();
        _choosingTheAge.click();
        return new HomePage(driver);
    }


    public String currentLocalDate(String days) {
        LocalDate now = LocalDate.now().plusDays(Long.parseLong(days));
        return String.valueOf(now);
    }

    public HotelsPage clickOnSearchButton(){
        _searchButton.click();
        return new HotelsPage(driver);
    }

    public void increaseDecreaseTheUsersAndRooms(int numberByDefault, int desiredNumber, String type) {
        int difference = desiredNumber - numberByDefault;
        String target = null;
        if (difference > 0) {
            target = _increaseTheNumber.replace("variable", type);
            for (int i = 0; i <= difference - 1; i++) {
                driver.findElement(By.cssSelector(target)).click();
            }
        } else if (difference < 0) {
            _decreaseTheNumber = _decreaseTheNumber.replace("variable", type);
            for (int i = 0; i >= difference - 1; i--) {
                driver.findElement(By.cssSelector(target)).click();
            }
        } else {
            return;
        }
    }

}


