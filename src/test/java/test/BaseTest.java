package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;


public class BaseTest {
    private static final int TIMEOUT = 10;
    private static final int POLLING_TIME = 1;
    protected WebDriver driver;
    protected Wait wait;

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofSeconds(POLLING_TIME))
                .ignoring(Exception.class);
    }

    @AfterEach
    public void afterTest() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
