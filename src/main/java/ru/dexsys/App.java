package ru.dexsys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App
{
    public static void main( String[] args ) {
        WebDriver driver = new ChromeDriver();
        WeatherPage page = new WeatherPage(driver);
        page.getTempTenDays();
        page.exit();
    }
}
