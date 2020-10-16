package ru.dexsys;

import org.openqa.selenium.chrome.ChromeDriver;

public class App
{
    public static void main( String[] args ) {
        WeatherPage page = new WeatherPage(new ChromeDriver());
        page.getWeekTemp();
        page.exit();
    }
}
