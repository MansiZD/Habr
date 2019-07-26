package com.habr.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By EMAIL_FIELD = By.xpath("//input[@id='email_field']");
    private static final By PASSWORD_FIELD = By.xpath("//input[@id='password_field']");
    private static final By LOG_IN = By.xpath("//button[@type='submit']");
    private static final By ERROR_INCORRECT_EMAIL = By.xpath("//div[text()='Введите корректный e-mail']");
    private static final By ERROR_INCORRECT_PASSWORD = By.xpath("//div[text()='Введите пароль']");
    private static final By ERROR_INCORRECT_DATA = By.xpath("//div[@class='notice__text']");
    private static final By USER_INFO = By.xpath("//div[@class='user-info']");

    private void typeEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    private void typePassword(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    public void signIn(String email, String password) {
        typeEmail(email);
        typePassword(password);
        driver.findElement(LOG_IN).click();
    }

    public void existUserInfo() {
        driver.findElement(USER_INFO);
    }

    public String getErrorIncorrectData() {
        return driver.findElement(ERROR_INCORRECT_DATA).getText();
    }

    public String getErrorIncorrectEmail() {
        return driver.findElement(ERROR_INCORRECT_EMAIL).getText();
    }

    public String getErrorIncorrectPassword() {
        return driver.findElement(ERROR_INCORRECT_PASSWORD).getText();
    }

}