package pageobject_model.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.stream.Collectors;

public class PastebinResultPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'expire')]")
    private WebElement expire;
    @FindAll({@FindBy(xpath = "//div[@class='de1']")})
    private List<WebElement> pastedTextLines;


    public PastebinResultPage(WebDriver driver, Wait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public String getExpireText() {
        wait.until(ExpectedConditions.visibilityOf(expire));
        return expire.getText().trim();
    }

    public List<String> getPastedTextLines() {
        wait.until(ExpectedConditions.visibilityOfAllElements(pastedTextLines));
        return pastedTextLines.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
