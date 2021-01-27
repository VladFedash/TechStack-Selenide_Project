package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.Assert.assertEquals;

public class ActionWithCartTests extends BaseTest {


    private static final int EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_ADD = 1;
    private static final String EXPECTED_CART_EMPTY_MESSAGE_RU = "Корзина пуста";
    private static final String NOKIA_SEARCH_WORD = "nokia";
    private static final String XIOMI_SEARCH_WORD = "xiomi";

    @Test
    public void checkAddProductToCart() {
        testSetUp();
        $(By.xpath(".//input[@name = 'search']")).sendKeys(NOKIA_SEARCH_WORD, Keys.ENTER);
        baseOperations.clickButton($$(By.xpath(".//button[contains(@class, 'buy-button')]")).get(0));
        int actualResult = Integer.parseInt($(By.xpath(".//div[@class = 'js-rz-cart']//span[contains(@class, 'button-counter')]")).getText().trim());
        assertEquals(EXPECTED_AMOUNT_OF_PRODUCTS_IN_CART_AFTER_ADD, actualResult);
    }

    @Test
    public void checkRemoveProductFromCart() {
        testSetUp();
        $(By.xpath(".//input[@name = 'search']")).sendKeys(NOKIA_SEARCH_WORD, Keys.ENTER);
        baseOperations.clickButton($$(By.xpath(".//button[contains(@class, 'buy-button')]")).get(0));
        baseOperations.clickButton($(By.xpath(".//div[@class = 'js-rz-cart']")));
        baseOperations.clickButton($(By.xpath(".//button[contains(@class, 'context-menu__toggle')]")));
        baseOperations.clickButton($(By.xpath(".//button[contains(@class, 'context-menu-actions')]")));
        $(By.xpath(".//h4[@class = 'cart-dummy__heading']")).shouldHave(text(EXPECTED_CART_EMPTY_MESSAGE_RU));
    }

    @Test
    public void checkSubtotalElementsInCart() {
        int expectedResult = 0;
        testSetUp();
        $(By.xpath(".//input[@name = 'search']")).sendKeys(XIOMI_SEARCH_WORD, Keys.ENTER);
        baseOperations.clickAddVisibleProductInCartButton();
        baseOperations.clickButton($(By.xpath(".//div[@class = 'js-rz-cart']")));

        for (WebElement element : $$(By.xpath(".//div[@class = 'cart-product__coast']"))) {
            expectedResult += Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
        }
        int actualResult = Integer.parseInt($(By.xpath(".//div[contains(@class, 'sum-price')]")).getText().replaceAll("[^0-9]", ""));
        assertEquals(actualResult, expectedResult);
    }
}