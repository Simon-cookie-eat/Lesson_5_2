package org.example.lesson_5_2.tests;

import org.example.lesson_5_2.pages.MainPage;
import org.example.lesson_5_2.pages.ResultsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPageTest {
    private WebDriver driver;

    public void clickLinkNumber(List<WebElement> list, int number){
        list.get(number).click();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchFieldValueCheck() {
        String input = "text for search";
        MainPage mp = new MainPage(driver);
        mp.search(input);

        ResultsPage rp = new ResultsPage(driver);
        assertEquals(input, rp.getTextFromField(), "Текст не совпадает");
    }

    @RepeatedTest(10)
    public void firstSearchedLinkCheck() {
        String seleniumLink = "https://www.selenium.dev/";
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.search(input);

        ResultsPage rp = new ResultsPage(driver);
        rp.clickLinkNumber(0);

        ArrayList tabs = new ArrayList<> (driver.getWindowHandles());
        if (tabs.size() > 1) driver.switchTo().window(tabs.get(1).toString());

        assertEquals(seleniumLink, driver.getCurrentUrl());
    }
}
