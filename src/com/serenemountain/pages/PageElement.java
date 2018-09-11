package com.serenemountain.pages;


import org.openqa.selenium.By;

public class PageElement {

    private SelectorType selectorType;
    private String selectorValue;

    public PageElement(SelectorType selectorType, String selectorValue) {
        this.selectorType = selectorType;
        this.selectorValue = selectorValue;
    }

    public By getBy() {

        switch (this.selectorType) {
            case CSS:
                return By.cssSelector(this.selectorValue);
            case ID:
                return By.id(this.selectorValue);
            case XPATH:
                return By.xpath(this.selectorValue);

            default:
                throw new IllegalArgumentException("Unknown selector type: " + this.selectorValue);
        }
    }
}