package Booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class HotelsPage extends BasePage {
    @FindBy(id = "sortbar_dropdown_button")
    private WebElement _sortOption;

    @FindBy(css = "#sortbar_dropdown_options .sort_class")
    private WebElement _orderByStars;

    @FindBy(css = ".sort_suboption_outer [data-sortname=\"class\"]")
    private WebElement _descOrder;

    public HotelsPage(WebDriver driver) {
        super(driver);
    }

    public HotelsPage filterByStars() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(15, SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(_sortOption)).click();
        wait.until(ExpectedConditions.visibilityOf(_orderByStars)).click();
        wait.until(ExpectedConditions.visibilityOf(_descOrder)).click();
        Thread.sleep(5000);
        return this;
    }

    public SelectedHotelPage clickOnChooseTheRoom(String hotelNumber) throws InterruptedException {
        int indexOfHotel = Integer.parseInt(hotelNumber);
        int retries = 5;
        while (!driver.findElement(By.cssSelector(".sr_cta_button")).isDisplayed() && retries > 0) {
            retries--;
        }
        List<WebElement> elementName = driver.findElements(By.cssSelector(".sr_cta_button"));
        elementName.get(indexOfHotel - 1).click();
        return new SelectedHotelPage(driver);
    }

    public String getTheHotelTitle(String hotelNumber) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        int indexOfHotel = Integer.parseInt(hotelNumber);
        List<WebElement> elementName = driver.findElements(By.cssSelector(".sr-hotel__name"));
        return elementName.get(indexOfHotel - 1).getText();
    }

    public String getTheScore(String hotelNumber) {
        int indexOfHotel = Integer.parseInt(hotelNumber);
        List<WebElement> elementName = driver.findElements(By.cssSelector(".bui-review-score__badge"));
        return elementName.get(indexOfHotel - 1).getText();
    }

    public String getThePrice(String hotelNumber) {
        int indexOfHotel = Integer.parseInt(hotelNumber);
        List<WebElement> elementName = driver.findElements(By.cssSelector(".bui-price-display__value"));
        return elementName.get(indexOfHotel - 1).getText();
    }
}
