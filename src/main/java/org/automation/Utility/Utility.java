package org.automation.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;


public class Utility {
    private Utility() {}

    private static WebDriverWait webDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebElement findElement(By element, WebDriver driver) {
        return driver.findElement(element);
    }

    public static WebElement findElement(By element) {
        return findElement(element, DriverManager.getDriver());
    }

    public static WebElement waitForElement(By element, WebDriver driver) {
        WebDriverWait wait = webDriverWait(driver);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static WebElement waitForElement(By element) {
        return waitForElement(element, DriverManager.getDriver());
    }

    public static WebElement waitForElement(WebElement element, WebDriver driver) {
        WebDriverWait wait = webDriverWait(driver);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForElement(WebElement element) {
        return waitForElement(element, DriverManager.getDriver());
    }

    public static void waitAndClickElement (By element, WebDriver driver) {
        waitForElement(element, driver).click();
    }

    public static void waitAndClickElement (By element) {
        waitAndClickElement(element, DriverManager.getDriver());
    }

    public static WebElement waitElementAndGetIfDiffText (By element, String text, WebDriver driver) {
        WebDriverWait wait = webDriverWait(driver);
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(element, text)
        ));
        return driver.findElement(element);
    }

    public static WebElement waitElementAndGetIfDiffText (By element, String text) {
        return waitElementAndGetIfDiffText(element, text, DriverManager.getDriver());
    }


    public static void clickElement (By element, WebDriver driver) {
        driver.findElement(element).click();
    }

    public static void clickElement (By element) {
        clickElement(element, DriverManager.getDriver());
    }

    public static List<WebElement> findListOfElements (By element, WebDriver driver) {
        return driver.findElements(element);
    }

    public static List<WebElement> findListOfElements (By element) {
        return findListOfElements(element, DriverManager.getDriver());
    }

    public static void replaceInputValues (By element, String text, WebDriver driver) {
        WebElement inputBox = driver.findElement(element);
        inputBox.clear();
        inputBox.sendKeys(text);
    }

    public static void replaceInputValues (By element, String text) {
        replaceInputValues(element, text, DriverManager.getDriver());
    }

}
