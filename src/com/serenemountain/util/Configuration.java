package com.serenemountain.util;

import com.serenemountain.models.EnvironmentData;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * Util class read and provide access to configuration files.
 *
 */
public class Configuration {

    private static final String CONFIGURATION_FILE = "conf.json";

    private static final String DEFAULT_ENV_STAGE = "DEV";

    private static EnvironmentData environmentData;

    public static EnvironmentData getEnvironmentData() {

        if (environmentData == null) {

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                EnvironmentData[] configEnvData = objectMapper.readValue(
                        Configuration.class.getClassLoader().getResource(CONFIGURATION_FILE),
                        EnvironmentData[].class);

                EnvironmentData.EnvironmentStage environmentStage =
                        EnvironmentData.EnvironmentStage.valueOf(System.getProperty("env.stage", DEFAULT_ENV_STAGE));

                environmentData = Arrays.stream(configEnvData)
                        .filter(envData -> envData.getStage() == environmentStage)
                        .findAny()
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        String.format("Cannot find environment data for %s stage", environmentStage)));

            } catch (IOException e) {
                throw new IllegalArgumentException("Cannot read configuration data", e);
            }
        }

        return environmentData;
    }

}
