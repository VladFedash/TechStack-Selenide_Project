package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertTrue;

public class SearchResultsTests extends BaseTest {
    private static final String SEARCH_WORD_IPHONE = "iphone";
    private static final String SEARCH_WORD_PHONE = "phone";
    private static final String SEARCH_NON_EXISTENT_KEYWORD = "non existent request";
    private static final String EXPECTED_NO_MATCHES_MESSAGE = "По заданным параметрам не найдена ни одна модель";

    @Test
    public void checkSearchResultsContainsWordIphone() {
        testSetUp();
        $(By.xpath(".//input[@name = 'search']")).sendKeys(SEARCH_WORD_PHONE, Keys.ENTER);
        $(By.xpath(".//input[@name = 'search']")).clear();
        $(By.xpath(".//input[@name = 'search']")).sendKeys(SEARCH_WORD_IPHONE, Keys.ENTER);

        for (WebElement element : $$(By.xpath(".//span[@class = 'goods-tile__title']"))) {
            assertTrue(element.getText().contains(SEARCH_WORD_IPHONE));
        }
    }

    @Test
    public void checkCorrectElementsAmountOnSearchPage() {
        testSetUp();
        $(By.xpath(".//input[@name = 'search']")).sendKeys(SEARCH_WORD_IPHONE, Keys.ENTER);
        int expectedResult = Integer.parseInt($(By.xpath(".//p[@class = 'catalog-selection__label']"))
                .getText().replaceAll("[^0-9]", ""));
        $$(By.xpath(".//span[@class = 'goods-tile__title']")).shouldHaveSize(expectedResult);
    }

    @Test
    public void checkSearchForNoMatches() {
        testSetUp();
        $(By.xpath(".//input[@name = 'search']")).sendKeys(SEARCH_NON_EXISTENT_KEYWORD, Keys.ENTER);
        $(By.xpath(".//rz-empty")).shouldHave(text(EXPECTED_NO_MATCHES_MESSAGE));
    }
}