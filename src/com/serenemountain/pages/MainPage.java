package com.serenemountain.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    private PageElement weatherCheckerHeader = new PageElement(SelectorType.CSS, ".header");
    private PageElement searchBox = new PageElement(SelectorType.CSS, "#searchLocation input");
    private PageElement submitButton = new PageElement(SelectorType.CSS, "#searchLocation button");
    private PageElement errorMessage = new PageElement(SelectorType.CSS, "#root > div > h1");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void submitPostCode(final String postCode) {
        waitForElementVisible(searchBox).sendKeys(postCode);
        findElement(submitButton).click();
    }

    public void validateErrorText(String expectedErrorText) {
        WebElement errorElement = waitForElementVisible(errorMessage);
        Assert.assertEquals("Incorrect error message displayed", errorElement.getText(), expectedErrorText);
    }


}
