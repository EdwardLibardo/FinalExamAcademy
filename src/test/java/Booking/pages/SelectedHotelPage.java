package Booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SelectedHotelPage extends BasePage {
    @FindBy(id = "hp_hotel_name")
    private WebElement _nameOfTheHotelInThePage;

    @FindBy(css = ".totalPrice .bui-price-display__value")
    private WebElement _priceInThePageOfTheHotel;

    @FindBy(css = ".prco-wrapper .bui-price-display__label")
    private WebElement _numberOfPeople;

    @FindBy(css = ".txp-group-cta")
    private WebElement _bookTheRoomButton;

    @FindBy(css = ".txp-bui-main-pp")
    private WebElement _continueButton;

    public SelectedHotelPage(WebDriver driver) {
        super(driver);
    }

    public SelectedHotelPage verifyTheNameOfTheHotel(String nameExpected) {
        String winHandleBefore = driver.getWindowHandle();
        driver.switchTo().window(winHandleBefore).close();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(_nameOfTheHotelInThePage)).isDisplayed();
        String nameInThePage = _nameOfTheHotelInThePage.getText();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        Assert.assertTrue(nameInThePage.contains(nameExpected));
        return this;
    }

    public SelectedHotelPage verifyThePrice(String priceExpected) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(_priceInThePageOfTheHotel)).isDisplayed();
        String priceInThePage = _priceInThePageOfTheHotel.getText();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        Assert.assertEquals(priceInThePage, priceExpected);
        return this;
    }

    public SelectedHotelPage verifyTheNumberOfAdultsAndChildren(String adultsExpected, String childrenExpected) {
        String people = _numberOfPeople.getText();
        String[] adultsOrChildren = people.split(",");
        Assert.assertTrue(adultsOrChildren[1].contains(adultsExpected));
        Assert.assertTrue(adultsOrChildren[2].contains(childrenExpected));
        return this;
    }

    public SelectedHotelPage clickOnButtonToBookTheRoom() {
        _bookTheRoomButton.click();
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        return this;
    }

    public SelectedHotelPage confirmTheNumberOfRooms() {
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        List<WebElement> listOfRoomsDeployed = driver.findElements(By.cssSelector(".hprt-nos-select [value=\"1\"]"));
        listOfRoomsDeployed.get(0).click();
        return this;
    }

    public InformationPage clickOnContinue() {
        _continueButton.click();
        //driver.manage().timeouts().implicitlyWait(5, SECONDS);
        return new InformationPage(driver);
    }
}
