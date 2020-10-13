package ru.dexsys;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WeatherPage {
    private final WebDriver webDriver;
    private final String URL = "https://yandex.ru/pogoda/izhevsk";

    public WeatherPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(URL);
        PageFactory.initElements(webDriver,this);
    }

    public void getTempTenDays() {
        List<WebElement> dayNumber = webDriver.findElements(By.xpath("//strong"));
        List<WebElement> monthAndDayOfWeek = webDriver.findElements(By.xpath("//small"));
        List<WebElement> tempDayPart = webDriver.findElements(By.className("weather-table__body-cell_type_daypart"));
        int j=0;
        for(int i=0; i < tempDayPart.size();i++) {
            if(i % 4 == 0) {
                System.out.print(dayNumber.get(j).getText());
                System.out.println(monthAndDayOfWeek.get(j).getText());
                j++;
            }
            System.out.println(tempDayPart.get(i).getText());
        }
    }

    public void exit() {
        webDriver.close();
    }
}