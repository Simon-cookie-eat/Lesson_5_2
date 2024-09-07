package org.example.lesson_5_2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage {

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(css = "h2 > a[href]")
    private List <WebElement> results;

    private WebDriver driver;

    public void clickLinkNumber(int number){
        results.get(number).click();
        System.out.println("Нажатие на результат по номером " + number);
    }

    public String getTextFromField(){
        String value = searchField.getAttribute("value");
        System.out.println("В строке поиска текст: " + value);
        return value;
    }

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
