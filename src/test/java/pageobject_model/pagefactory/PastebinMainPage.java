package pageobject_model.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class PastebinMainPage extends BasePage {
    private static final String PAGE_URL = "https://www.pastebin.com";


    @FindBy(id = "postform-text")
    private WebElement textArea;
    @FindBy(id = "select2-postform-expiration-container")
    private WebElement pasteExpirationCombobox;
    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement submitButton;
    @FindBy(xpath = "//button[span[text()='AGREE']]")
    private WebElement agreeButton;
    @FindBy(id = "hideSlideBanner")
    private WebElement closeBanner;

    public PastebinMainPage(WebDriver driver, Wait wait) {
        super(driver, wait);
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public PastebinMainPage fillInTextArea(String text) {
        wait.until(ExpectedConditions.visibilityOf(textArea));
        textArea.clear();
        textArea.sendKeys(text);
        return this;
    }

    public PastebinMainPage selectExpiration(String value, String expirationTimeForJS) {
        try {
            wait.until(ExpectedConditions.visibilityOf(pasteExpirationCombobox));
            pasteExpirationCombobox.click();
            WebElement listElement = driver.findElement(By.xpath(String.format("//li[text()='%s']", value)));
            wait.until(ExpectedConditions.visibilityOf(listElement));
            listElement.click();
        } catch (Exception ignore) {
            //banners can hide the dropdown menu
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(String.format("document.getElementById('postform-expiration').value='%s';", expirationTimeForJS));

        }
        return this;
    }


    public PastebinMainPage createNewPaste() {
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        submitButton.click();
        return this;
    }

    public PastebinMainPage agreePrivacyTerms() {
        try {
            wait.until(ExpectedConditions.visibilityOf(agreeButton));
            agreeButton.click();
        } catch (Exception ignore) {
        }
        return this;
    }

    public PastebinMainPage closeBanner() {
        try {
            wait.until(ExpectedConditions.visibilityOf(closeBanner));
            closeBanner.click();

        } catch (Exception ignore) {
        }
        return this;
    }
}
