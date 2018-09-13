package com.serenemountain.pages;

import org.openqa.selenium.By;

/**
 * Page element helper model. Provides basic mechanism of storing and handling page elements.
 */
class PageElement {

    /**
     * Element selector type.
     */
    private SelectorType selectorType;

    /**
     * Element selector value.
     */
    private String selectorValue;

    /**
     * Constructor.
     *
     * @param selectorType  - element selector type.
     * @param selectorValue - element selector value.
     */
    public PageElement(SelectorType selectorType, String selectorValue) {
        this.selectorType = selectorType;
        this.selectorValue = selectorValue;
    }

    /**
     * Get web driver specific search criteria By.
     *
     * @return By.
     */
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