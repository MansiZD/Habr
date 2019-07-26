package com.habr;

import com.habr.page.LoginPage;
import com.habr.property.PropertyManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private PropertyManager propertyManager;

    @Before
    public void setUp() {
        propertyManager = new PropertyManager();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(propertyManager.getLoginUrl());
        loginPage = new LoginPage(driver);
    }

    @Test
    public void signInSuccessTest() {
        loginPage.signIn(propertyManager.getLoginEmail(), propertyManager.getLoginPassword());
        loginPage.existUserInfo();
    }

    @Test
    public void signInFailTest() {
        loginPage.signIn(propertyManager.getLoginEmail(), "testpass");
        String error = loginPage.getErrorIncorrectData();
        Assert.assertEquals("Пользователь с такой электронной почтой или паролем не найден", error);
    }

    @Test
    public void signInWithEmptyEmail() {
        loginPage.signIn("", "pass");
        String error = loginPage.getErrorIncorrectEmail();
        Assert.assertEquals("Введите корректный e-mail", error);
    }

    @Test
    public void signInWithEmptyPass() {
        loginPage.signIn(propertyManager.getLoginEmail(), "");
        String error = loginPage.getErrorIncorrectPassword();
        Assert.assertEquals("Введите пароль", error);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
