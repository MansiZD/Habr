package com.habr.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage {

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By REAL_NAME_FIELD = By.xpath("//form[@class='h-form']/div[1]/div[1]/div[1]//input");
    private static final By SPECIALIZATION_FIELD = By.xpath("//form[@class='h-form']/div[1]/div[1]/div[2]//input");
    private static final By SEX_FIELD = By.xpath("//form[@class='h-form']/div[2]/div[1]//select");
    private static final By SAVE_BUTTON = By.xpath("//form[@class='h-form']/div[4]/button/span/span");
    private static final By DATE_FIELD = By.xpath("//div[@class='h-form__user-birthdate']/div/div/div[1]/select");
    private static final By MONTH_FIELD = By.xpath("//div[@class='h-form__user-birthdate']/div/div/div[2]/select");
    private static final By YEAR_FIELD = By.xpath("//div[@class='h-form__user-birthdate']/div/div/div[3]/select");
    private static final By COUNTRY_FIELD = By.xpath("//span[text()='Местоположение']/parent::label/following::div[1]/div[1]/select[1]");
    private static final By REGION_FIELD = By.xpath("//span[text()='Местоположение']/parent::label/following::div[1]/div[2]/select");
    private static final By CITY_FIELD = By.xpath("//span[text()='Местоположение']/parent::label/following::div[1]/div[3]/select");
    private static final By LOAD_IMG_BUTTON = By.xpath("//input[@type='file']");
    private static final By DELETE_IMG = By.xpath("//span[text()='Удалить']");

    public void selectSex(String option) {
        String optionXpath = String.format("//form[@class='h-form']/div[2]/div[1]/div/div/select/option[text()='%s']", option);
        driver.findElement(SEX_FIELD).click();
        driver.findElement(By.xpath(optionXpath)).click();
        driver.findElement(SAVE_BUTTON).click();
    }

    public void selectDateBirth(String date, String month, String year) {
        String selectedDate = String.format("//div[@class='h-form__user-birthdate']/div/div/div[1]/select/option[@value='%s']", date);
        String selectedMonth = String.format("//div[@class='h-form__user-birthdate']/div/div/div[2]/select/option[@value='%s']", month);
        String selectedYear = String.format("//div[@class='h-form__user-birthdate']/div/div/div[3]/select/option[@value='%s']", year);
        driver.findElement(DATE_FIELD).click();
        driver.findElement(By.xpath(selectedDate)).click();
        driver.findElement(MONTH_FIELD).click();
        driver.findElement(By.xpath(selectedMonth)).click();
        driver.findElement(YEAR_FIELD).click();
        driver.findElement(By.xpath(selectedYear)).click();
        driver.findElement(SAVE_BUTTON).click();
    }

    public void selectLocation(String country, String region, String city) {
        String selectedCountry = String.format("//span[text()='Местоположение']/parent::label/following::div[1]/div[1]/select[1]/option[text()='%s']", country);
        String selectedRegion = String.format("//span[text()='Местоположение']/parent::label/following::div[1]/div[2]/select/option[contains(text(), '%s')]", region);
        String selectedCity = String.format("//span[text()='Местоположение']/parent::label/following::div[1]/div[3]/select/option[text()='%s']", city);
        driver.findElement(COUNTRY_FIELD).click();
        driver.findElement(By.xpath(selectedCountry)).click();
        driver.findElement(REGION_FIELD).click();
        driver.findElement(By.xpath(selectedRegion)).click();
        driver.findElement(CITY_FIELD).click();
        driver.findElement(By.xpath(selectedCity)).click();
        driver.findElement(SAVE_BUTTON).click();
    }

    public void typeRealName(String name) {
        WebElement realNameElement = driver.findElement(REAL_NAME_FIELD);
        realNameElement.clear();
        realNameElement.sendKeys(name);
        driver.findElement(SAVE_BUTTON).click();
    }

    public void typeSpecialization(String specialization) {
        WebElement specializationElement = driver.findElement(SPECIALIZATION_FIELD);
        specializationElement.clear();
        specializationElement.sendKeys(specialization);
        driver.findElement(SAVE_BUTTON).click();
    }

    public void loadImg(String pathToPicture) {
        driver.findElement(LOAD_IMG_BUTTON).sendKeys(pathToPicture);
    }

    public void deleteImg() {
        driver.findElement(DELETE_IMG).click();
        driver.findElement(SAVE_BUTTON).click();
    }

    public String getRealName() {
        return driver.findElement(REAL_NAME_FIELD).getAttribute("value");
    }

    public String getSpecialization() {
        return driver.findElement(SPECIALIZATION_FIELD).getAttribute("value");
    }

    public String getSex() {
        return getTextFromSelect(SEX_FIELD);
    }

    private String getTextFromSelect(By element) {
        Select select = new Select(driver.findElement(element));
        WebElement option = select.getFirstSelectedOption();
        return option.getText();
    }

    public String getDateOfBirth() {
        return driver.findElement(DATE_FIELD).getAttribute("value");
    }

    public String getMonthOfBirth() {
        return driver.findElement(MONTH_FIELD).getAttribute("value");
    }

    public String getYearOfBirth() {
        return driver.findElement(YEAR_FIELD).getAttribute("value");
    }

    public String getRegion() {
        return getTextFromSelect(REGION_FIELD);
    }

    public String getCountry() {
        return getTextFromSelect(COUNTRY_FIELD);
    }

    public String getCity() {
        return getTextFromSelect(CITY_FIELD);
    }

    public Boolean existButtonDelete() {
        return driver.findElements(DELETE_IMG).size() != 0;
    }
}



