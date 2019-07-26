package com.habr.property;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    private static final String LOGIN_URL = "login.url";
    private static final String LOGIN_EMAIL = "login.email";
    private static final String LOGIN_PASSWORD = "login.password";
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String PROFILE_URL = "profile.url";
    private static final String PROFILE_PATH_TO_PICTURE = "profile.path.to.picture";

    private final Properties properties;

    public PropertyManager() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
            System.setProperty(WEBDRIVER_GECKO_DRIVER, properties.getProperty(WEBDRIVER_GECKO_DRIVER));
        } catch (IOException e) {
            throw new IllegalArgumentException("Configuration properties file cannot be found");
        }
    }

    public String getLoginUrl() {
        return properties.getProperty(LOGIN_URL);
    }

    public String getLoginEmail() {
        return properties.getProperty(LOGIN_EMAIL);
    }

    public String getLoginPassword() {
        return properties.getProperty(LOGIN_PASSWORD);
    }

    public String getProfileUrl() {
        return properties.getProperty(PROFILE_URL);
    }

    public String getProfilePathToPicture() {
        return properties.getProperty(PROFILE_PATH_TO_PICTURE);
    }
}
