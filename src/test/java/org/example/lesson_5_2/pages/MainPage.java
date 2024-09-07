package org.example.lesson_5_2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://www.bing.com/

public class MainPage {

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    private WebDriver driver;

    public void search(String searchQuery){
        searchField.sendKeys(searchQuery);
        System.out.println("Введён следующий поисковый запрос: " + searchQuery);
        searchField.submit();
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
