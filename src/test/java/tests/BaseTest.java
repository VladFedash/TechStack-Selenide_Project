package tests;

import enums.Languages;
import helpers.BaseOperations;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    BaseOperations baseOperations = new BaseOperations();

    public void testSetUp() {
        open("https://rozetka.com.ua/");
        baseOperations.setAppLanguage(Languages.RU);
    }
}