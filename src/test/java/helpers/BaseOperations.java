package helpers;

import enums.Languages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BaseOperations {
    public String languageLocator = ".//a[text() = '%s']";

    public void clickButton(WebElement element) {
        element.click();
    }

    public void clickButton(By by) {
        $(by).click();
    }

    public void setAppLanguage(Languages language) {
        switch (language) {
            case RU -> clickButton(By.xpath(String.format(languageLocator, Languages.RU)));
            case UA -> clickButton(By.xpath(String.format(languageLocator, Languages.UA)));
        }
    }

    public void clickAddVisibleProductInCartButton() {
        for (WebElement element : $$(By.xpath(".//button[contains(@class, 'buy-button')]"))) {
            clickButton(element);
        }
    }
}