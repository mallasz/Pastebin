package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import pageobject_model.pagefactory.PastebinMainPage;
import pageobject_model.pagefactory.PastebinResultPage;


public class PastebinTests extends BaseTest {


    @ParameterizedTest
    @MethodSource("provideTextAreaValues")
    public void textAreaAndComboboxTest(String text, String expiration, String resultExpiration, String expirationTimeForJS) {
        PastebinMainPage pastebinMainPage = new PastebinMainPage(driver, wait);
        PastebinResultPage pastebinResultPage = new PastebinResultPage(driver, wait);
        pastebinMainPage
                .agreePrivacyTerms()
                .closeBanner()
                .fillInTextArea(text)
                .selectExpiration(expiration, expirationTimeForJS)
                .createNewPaste();
        Assertions.assertEquals(resultExpiration, pastebinResultPage.getExpireText());
        Assertions.assertTrue(pastebinResultPage.getPastedTextLines().contains(text));

    }

    static Stream<Arguments> provideTextAreaValues() {
        return Stream.of(
                Arguments.of("Hello from WebDriver", "10 Minutes", "10 MIN", "10M")
        );
    }
}
