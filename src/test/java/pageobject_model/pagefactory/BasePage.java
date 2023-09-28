package pageobject_model.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

public class BasePage {
    protected WebDriver driver;
    protected Wait wait;


    public BasePage(WebDriver driver, Wait wait) {
        this.driver = driver;
        this.wait = wait;
    }
}
