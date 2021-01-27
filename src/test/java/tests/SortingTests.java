package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.assertEquals;

public class SortingTests extends BaseTest {
    private static final String SORTING_BY_ASCENDING_KEYWORD = "От дешевых к дорогим";
    private static final String SORTING_BY_DESCENDING_KEYWORD = "От дорогих к дешевым";
    private static final String CHOSEN_NOTEBOOK_FIRM = "Acer";

    @Test
    public void checkCorrectSortingProductAscending() {
        testSetUp();
        baseOperations.clickButton($(By.xpath(".//button[@class = 'menu-toggler']")));
        baseOperations.clickButton($(By.xpath(".//a[@class ='menu__hidden-title'][not(contains(@href, 'preset=game')) and contains(@href, '/notebooks/')]")));
        $(By.xpath(".//select[contains(@class, 'select')]")).selectOption(SORTING_BY_ASCENDING_KEYWORD);
        List<Integer> actualProductPriceList = new ArrayList<>();

        for (int i = 0; i < $$(By.xpath(".//span[contains(@class, 'price-value')]")).size(); i++) {
            actualProductPriceList.add(Integer.parseInt($$(By.xpath(".//span[contains(@class, 'price-value')]")).get(i)
                    .getText().replaceAll("[^0-9]", "")));
        }
        List<Integer> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        Collections.sort(expectedProductPriceList);

        for (int i = 0; i < actualProductPriceList.size(); i++) {
            assertEquals(actualProductPriceList.get(i), expectedProductPriceList.get(i));
        }
    }

    @Test
    public void checkCorrectSortingProductDescending() {
        testSetUp();
        baseOperations.clickButton($(By.xpath(".//button[@class = 'menu-toggler']")));
        baseOperations.clickButton($(By.xpath(".//a[@class ='menu__hidden-title'][not(contains(@href, 'preset=game')) and contains(@href, '/notebooks/')]")));
        $(By.xpath(".//select[contains(@class, 'select')]")).selectOption(SORTING_BY_DESCENDING_KEYWORD);
        List<Integer> actualProductPriceList = new ArrayList<>();

        for (int i = 0; i < $$(By.xpath(".//span[contains(@class, 'price-value')]")).size(); i++) {
            actualProductPriceList.add(Integer.parseInt($$(By.xpath(".//span[contains(@class, 'price-value')]")).get(i)
                    .getText().replaceAll("[^0-9]", "")));
        }
        List<Integer> expectedProductPriceList = new ArrayList<>(actualProductPriceList);
        expectedProductPriceList.sort(Collections.reverseOrder());

        for (int i = 0; i < actualProductPriceList.size(); i++) {
            assertEquals(actualProductPriceList.get(i), expectedProductPriceList.get(i));
        }
    }

    @Test
    public void checkCorrectSortingProductByFirmName() {
        testSetUp();
        baseOperations.clickButton($(By.xpath(".//button[@class = 'menu-toggler']")));
        baseOperations.clickButton($(By.xpath(".//a[@class ='menu__hidden-title'][not(contains(@href, 'preset=game')) and contains(@href, '/notebooks/')]")));
        baseOperations.clickButton($(By.xpath(".//a[contains(@class, 'filter__link')]/label[@for = 'Acer']")));

        for (int i = 0; i < $$(By.xpath(".//span[@class = 'goods-tile__title']")).size(); i++) {
            $$(By.xpath(".//span[@class = 'goods-tile__title']")).get(i).shouldHave(text(CHOSEN_NOTEBOOK_FIRM));
        }
    }
}