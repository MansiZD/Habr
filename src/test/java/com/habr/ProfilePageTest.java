package com.habr;

import com.habr.page.LoginPage;
import com.habr.page.ProfilePage;
import com.habr.property.PropertyManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ProfilePageTest {

    private WebDriver driver;
    private ProfilePage profilePage;
    private PropertyManager propertyManager;

    @Before
    public void setUp() {
        propertyManager = new PropertyManager();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(propertyManager.getLoginUrl());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signIn(propertyManager.getLoginEmail(), propertyManager.getLoginPassword());
        driver.get(propertyManager.getProfileUrl());

        profilePage = new ProfilePage(driver);
    }

    @Test
    public void getRealNameTest() {
        String expectedUserName = "Мария";

        profilePage.typeRealName(expectedUserName);
        String userName = profilePage.getRealName();

        Assert.assertEquals(expectedUserName, userName);
    }

    @Test
    public void getSpecializationTest() {
        String expectedSpecialization = "Пользователь";

        profilePage.typeSpecialization(expectedSpecialization);
        String specialization = profilePage.getSpecialization();

        Assert.assertEquals(expectedSpecialization, specialization);
    }

    @Test
    public void getSexTest() {
        String expectedSex = "женский";

        profilePage.selectSex(expectedSex);
        String sex = profilePage.getSex();

        Assert.assertEquals(expectedSex, sex);
    }

    @Test
    public void getDateBirthTest() {
        String expectedDate = "22";
        String expectedMonth = "12";
        String expectedYear = "1992";

        profilePage.selectDateBirth(expectedDate, expectedMonth, expectedYear);
        String date = profilePage.getDateOfBirth();
        String month = profilePage.getMonthOfBirth();
        String year = profilePage.getYearOfBirth();

        Assert.assertEquals(expectedDate, date);
        Assert.assertEquals(expectedMonth, month);
        Assert.assertEquals(expectedYear, year);
    }

    @Test
    public void getLocationTest() {
        String expectedCountry = "Россия";
        String expectedRegion = "Санкт-Петербург и область";
        String expectedCity = "Санкт-Петербург";

        profilePage.selectLocation(expectedCountry, expectedRegion, expectedCity);
        String region = profilePage.getRegion();
        String country = profilePage.getCountry();
        String city = profilePage.getCity();

        Assert.assertEquals(expectedCity, city);
        Assert.assertEquals(expectedCountry, country);
        Assert.assertEquals(expectedRegion, region);
    }

    @Test
    public void loadImg() {
        if (profilePage.existButtonDelete()) {
            profilePage.deleteImg();
        }

        profilePage.loadImg(propertyManager.getProfilePathToPicture());

        Assert.assertTrue(profilePage.existButtonDelete());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
