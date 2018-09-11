package com.serenemountain.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ForecastTable extends BasePage {

    private static final String CURRENT_TIME_KEY = "Time";

    private PageElement forecastTable = new PageElement(SelectorType.XPATH, "//div[@id='root']/div/div[last()]/div/table");
    private Map<String, String> forecastTableContent;

    public ForecastTable(WebDriver webDriver) {
        super(webDriver);
    }

    public void validateForecastTableDisplayed() {
        waitForElementVisible(forecastTable);
        Assert.assertTrue("Forecast table is not displayed", findElement(forecastTable).isDisplayed());
    }

    public void validateCurrentTimeHasExpectedFormat(final String expectedDateFormat) {
        String currentTime = getForecastTableContent().get(CURRENT_TIME_KEY);
        Assert.assertNotNull("Time object is not displayed", currentTime);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedDateFormat);
        try {
            simpleDateFormat.parse(currentTime);
        } catch (ParseException e) {
            Assert.fail(String.format("String '%s' has incorrect format. Expected format: %s",
                    currentTime, expectedDateFormat));
        }
    }

    private Map<String, String> getForecastTableContent() {

        if (Objects.isNull(forecastTableContent)) {

            forecastTableContent = findElement(forecastTable).findElements(By.xpath(".//tbody/tr"))
                    .stream()
                    .collect(Collectors.toMap(
                            key -> key.findElement(By.xpath(".//th")).getText().replace(":", ""),
                            val -> val.findElement(By.xpath(".//td")).getText()));
        }
        return forecastTableContent;
    }


}
