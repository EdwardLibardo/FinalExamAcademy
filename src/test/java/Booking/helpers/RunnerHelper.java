package Booking.helpers;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunnerHelper {
    public static WebDriver driver;


    @Before
    public WebDriver setUp(String url) {
        driver = new ChromeDriver();
        driver.get(url);
        String _url = url;
        return driver;
    }

    public WebDriver driver() {
        return driver;
    }

    @After
    public void tearDown() {
        driver().quit();
    }
}
