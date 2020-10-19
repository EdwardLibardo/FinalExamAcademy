package Booking.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.yecht.Data;

public class InformationPage extends BasePage {
    @FindBy(id = "firstname")
    private WebElement _nameTextBox;

    @FindBy(id = "lastname")
    private WebElement _lastNameTextBox;

    @FindBy(id = "email")
    private WebElement _emailTextBox;

    @FindBy(id = "email_confirm")
    private WebElement _emailConfirmTextBox;

    @FindBy(css = ".bui-checkbox__label")
    private WebElement _dontShowAgainCheckbox;

    @FindBy(css = ".bui-modal__close")
    private WebElement _closePopUp;

    @FindBy(css = ".bui-group .bui-button--primary")
    private WebElement _nextLastDataButton;

    @FindBy(id = "cc1")
    private WebElement _country;

    @FindBy(id = "phone")
    private WebElement _phoneTextBox;

    @FindBy(css = ".field_name_full_name .book-form-field-value")
    private WebElement _verifyName;

    @FindBy(css = "#label_email .book-form-field-value")
    private WebElement _verifyEmail;

    private final String _name = "Edward";
    private final String _lastName = "Corregidor";
    private final String _email = "hjfhfdks@gmail.com";
    private final String _phone = "363782543";

    public InformationPage(WebDriver driver) {
        super(driver);
    }

    public InformationPage closePopUp() {
        try {
            _dontShowAgainCheckbox.click();
            _closePopUp.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public InformationPage setTheName() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        _nameTextBox.sendKeys(_name);
        return this;
    }

    public InformationPage setLastName() {
        _lastNameTextBox.sendKeys(_lastName);
        return this;
    }

    public InformationPage setEmail() {
        _emailTextBox.sendKeys(_email);
        _emailConfirmTextBox.sendKeys(_email);
        return this;
    }

    public InformationPage clickOnNextButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        _nextLastDataButton.click();
        return this;
    }

    public InformationPage verifyCountry() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.visibilityOf(_country)).isDisplayed();
        Assert.assertTrue(_country.getText().contains("Colombia"));
        return this;
    }

    public InformationPage verifyNameAndLastName() {
        String name = _name + " " + _lastName;
        Assert.assertTrue(_verifyName.getText().contains(name));
        return this;
    }

    public InformationPage verifyEmail() {
        Assert.assertTrue(_verifyEmail.getText().contains(_email));
        return this;
    }

    public void setPhone() {
        _phoneTextBox.sendKeys(_phone);
        driver.quit();
    }

}
