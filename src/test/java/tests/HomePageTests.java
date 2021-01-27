package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePageTests extends BaseTest {
    @Test
    public void checkAbilityChangeCityLocation() {
        testSetUp();
        baseOperations.clickButton($(By.xpath(".//button[contains(@class, 'cities__label')]")));

        Random rand = new Random();
        int randomCity = rand.nextInt($$(By.xpath(".//a[@class = 'header-location__popular-link']")).size());

        String actualResult = $$(By.xpath(".//a[@class = 'header-location__popular-link']")).get(randomCity).getText();
        baseOperations.clickButton($$(By.xpath(".//a[@class = 'header-location__popular-link']")).get(randomCity));
        baseOperations.clickButton($(By.xpath(".//button[contains(@class, 'medium button')]")));
        $(By.xpath(".//button[contains(@class, 'cities__label')]")).shouldHave(text(actualResult));
    }
}