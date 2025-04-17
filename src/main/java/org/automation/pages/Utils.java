package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Utils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void replaceInputValues(By element, String value) {
        WebElement input = driver.findElement(element);
        input.clear();
        input.sendKeys(value);
    }

    public WebElement waitForElement (By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }




}
