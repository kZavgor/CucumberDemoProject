package com.serenemountain.base;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Cucumber test runner.
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/report"},
        glue = {"com.serenemountain.steps."},
        features = {"classpath:features"})
public class RunCukesTest {

}
