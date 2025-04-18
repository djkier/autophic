package org.automation.pages.claim;

import org.automation.Utility.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class cf1 {
    private WebDriver driver;
    private Utils util;

    public cf1(WebDriver driver) {
        this.driver = driver;
        this.util = new Utils(driver);
    }

    public void action() {
        String memFn = util.waitForElement(By.id("form_one_member_first_name")).getText();
        String memMn = driver.findElement(By.id("form_one_member_middle_name")).getText();
        String memLn = driver.findElement(By.id("form_one_member_last_name")).getText();



    }


}
