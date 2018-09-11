package com.serenemountain.steps;


import com.serenemountain.pages.ForecastTable;
import com.serenemountain.pages.MainPage;
import com.serenemountain.util.Configuration;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Class describing ui cucumber steps.
 *
 */
public class UiSteps {

    /**
     * The instance of cucumber scenario reporter.
     *
     */
    private Scenario scenario;

    /**
     * WebDriver instance.
     *
     */
    private WebDriver webDriver;

    /**
     * Main page instance;
     *
     */
    private MainPage mainPage;

    /**
     * Forecast table instance;
     *
     */
    private ForecastTable forecastTable;

    /**
     * Set up method will be executed before each UI test.
     * Set up actions include:
     * <ol>
     *     <li>Chromedriver binary download, if not installed.</li>
     *     <li>Chrome web driver initialization.</li>
     *     <li>Assigning cucumber scenario reporter instance to local variable.</li>
     *     <li>Initialize pages under test instances.</li>
     * <ol/>
     *
     * @param scenario - cucumber scenario reporter instance.
     */
    @Before
    public void setUp(final Scenario scenario) {
        WebDriverManager.chromedriver().setup();
        this.webDriver = new ChromeDriver();
        this.webDriver.manage().window().maximize();

        this.scenario = scenario;
        this.mainPage = new MainPage(this.webDriver);
        this.forecastTable = new ForecastTable(this.webDriver);
    }

    /**
     * Tear down method to execute after each test. Closes webdriwer.
     *
     */
    @After
    public void tearDown() {
        this.webDriver.quit();
    }

    /**
     * Navigate to main page.
     *
     */
    @Given("^I am on a base page$")
    public void iAmOnABasePage() {
        String homeURL = Configuration.getEnvironmentData().getWebURL();
        this.scenario.write("Navigate to " + homeURL);
        this.webDriver.navigate().to(homeURL);
    }

    /**
     * Enter post code and submit form.
     *
     * @param postCode - post code value.
     */
    @When("^I enter \"([^\"]*)\" post code$")
    public void iEnterPostCode(final String postCode) {
        this.scenario.write("Submit postcode " + postCode);
        this.mainPage.submitPostCode(postCode);
    }

    /**
     * Validate error message is shown and has valid text.
     *
     * @param errorText - expected error text.
     */
    @Then("^I should see \"([^\"]*)\" error message$")
    public void iSeeErrorMessage(final String errorText) {
        this.scenario.write(String.format("Validate error with text '%s' is displayed", errorText));
        this.mainPage.validateErrorText(errorText);
    }

    @Then("^I should see weather forecast table$")
    public void iSeeWeatherForeCastTable() {
        this.forecastTable.validateForecastTableDisplayed();
    }

    @Then("^I current time having \"([^\"]*)\" format$")
    public void validateCurrentTimeFormatInForecastTable(final String expectedDateFormat) {
        this.forecastTable.validateCurrentTimeHasExpectedFormat(expectedDateFormat);
    }
}