package com.serenemountain.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class describing base interaction with forecast table and it model.
 *
 */
public class ForecastTable extends BasePage {

    /**
     * Current time property name in forecast table.
     *
     */
    private static final String CURRENT_TIME_KEY = "Time";

    /**
     * Forecast table page element.
     *
     */
    private PageElement forecastTable =
            new PageElement(SelectorType.XPATH, "//div[@id='root']/div/div[last()]/div/table");

    /**
     * Forecast table content.
     *
     */
    private Map<String, String> forecastTableContent;

    /**
     * Constructor.
     *
     * @param webDriver - web driver instance.
     */
    public ForecastTable(final WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Validate if forecast table is displayed.
     *
     */
    public void validateForecastTableDisplayed() {
        waitForElementVisible(forecastTable);
        Assert.assertTrue("Forecast table is not displayed", findElement(forecastTable).isDisplayed());
    }

    /**
     * Validate if current time has date and time in expected format.
     *
     * @param expectedDateFormat - expected date format.
     */
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

    /**
     * Validate if all properties in forecast table have values.
     *
     */
    public void validateForecastTableHasNoEmptyProperties() {

        List<String> propertiesWithEmptyValue = getForecastTableContent().entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Assert.assertTrue("Following properties has no value in forecast table:" + propertiesWithEmptyValue,
                propertiesWithEmptyValue.isEmpty());
    }

    /**
     * Validate if forecast table has property under test.
     *
     * @param propertyName - property name.
     */
    public void validateForecastTableHasProperty(final String propertyName) {
        Assert.assertTrue(propertyName + " property is missed in forecast table",
                getForecastTableContent().containsKey(propertyName));
    }

    /**
     * Analyse forecast table displayed on main page and collect all data into map.
     *
     * @return Map.
     */
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
