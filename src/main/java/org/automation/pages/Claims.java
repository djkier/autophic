package org.automation.pages;

import org.automation.informationcontroller.Controller;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Claims {
    private WebDriver driver;
    private Controller info;
    private WebDriverWait wait;
    private boolean mother;
    private Utils utils;

    public Claims(WebDriver driver, Controller info, WebDriverWait wait, boolean mother) {
        this.driver = driver;
        this.info = info;
        this.wait = wait;
        this.mother = mother;
        this.utils = new Utils(driver, wait);
    }

    public void claimsAction(){
        WebElement newClaim = utils.waitForElement(By.cssSelector("a[href=\"/53/claims/new\"]"));
        newClaim.click();

        WebElement findMember = utils.waitForElement(By.xpath("//span[text()=\"Enter PhilHealth ID...\"]"));
        findMember.click();;

        utils.replaceInputValues(By.cssSelector("div[class=\"select2-search\"] input"), info.getMember().getId());
        WebElement memberAppeared = utils.waitForElement(By.cssSelector("ul.select2-results li:first-of-type div"));
        memberAppeared.click();

        driver.findElement(By.id("claim_admission_type_inpatient")).click();


    }
}
