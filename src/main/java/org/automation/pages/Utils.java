package org.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {
    private final WebDriver driver;

    public Utils(WebDriver driver) {
        this.driver = driver;
    }

    public void replaceInputValues(By element, String value) {
        WebElement input = driver.findElement(element);
        input.clear();
        input.sendKeys(value);
    }


}
