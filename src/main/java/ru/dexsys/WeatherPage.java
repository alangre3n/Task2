package ru.dexsys;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.List;

public class WeatherPage {
    private final WebDriver webDriver;
    private final String URL = "https://yandex.ru/pogoda/izhevsk";

    public WeatherPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(URL);
        PageFactory.initElements(webDriver, this);
    }

    // Так как на сайте прогноз погоды на 10 дней, а нам нужно 7 - удаляем лишнее
    private void removeExtra(List<WebElement> removeList, int removeSize) {
        int removeIndex = removeList.size() - removeSize;
        for (int i = removeList.size() - 1; i >= removeIndex; i--) {
            removeList.remove(i);
        }
    }

    // Внутри коллекции данные вперемешку, требуют красивого вывода
    public void prettyPrint(List<WebElement> printList) {
        final int weekSize = 7;
        final int timeOfDaySize = 4;

        for (int i = 0; i < weekSize; i++) {
            System.out.printf("\n%s %s\n", printList.get(i).getText(), printList.get(i + weekSize).getText());

            for (int j = 0; j < timeOfDaySize; j++) {
                System.out.printf("%s\n", printList.get(i * timeOfDaySize + weekSize * 2 + j).getText()); // Выводим время суток и температуру (выглядит страшно, но вроде логичено)
            }
        }
    }

    // Берем температуру за неделю
    public void getWeekTemp() {
        final int extra = 10 - 7; // Берем температуру за 10 дней, а нужно за 7 - 3 лишние дни

        List<WebElement> weather = webDriver.findElements(By.xpath(("//strong")));
        removeExtra(weather, extra);
        weather.addAll(webDriver.findElements(By.xpath("//small")));
        removeExtra(weather, extra);
        weather.addAll(webDriver.findElements(By.className("weather-table__body-cell_type_daypart")));
        removeExtra(weather, extra * 4); // Умножаем количество дней на количество суток

        prettyPrint(weather);
    }

    public void exit() {
        webDriver.close();
    }
}