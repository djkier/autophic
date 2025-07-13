package org.automation;

import org.automation.Utility.DriverManager;
import org.automation.Utility.Utility;
import org.automation.informationcontroller.Controller;
import org.automation.pages.Claims;
import org.automation.Utility.Utils;
import org.automation.pages.Member;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Controller info = new Controller();

        //create
        WebDriver driver = new EdgeDriver();
        DriverManager.setDriver(driver);
        //open website
        driver.manage().window().maximize();
        driver.get(Config.get("website"));

        //delete this utils after refactoring
        Utils utils = new Utils(driver);

        //log-in
        WebElement userEmail = Utility.waitForElement(By.id("user_email"));
        WebElement userPassword = Utility.waitForElement(By.id("user_password"));
        WebElement submit = Utility.waitForElement(By.name("commit"));

        userEmail.sendKeys(Config.get("userEmail"));
        userPassword.sendKeys(Config.get("userPassword"));
        submit.click();

//        Member Automation
        Member member = new Member(driver, info);
        member.action();

        //delete after testing
        //Go back to the main page
//        utils.clickerWait(By.cssSelector("a[href=\"/53/claims\"]"));
//
//
//        //Patient Automation
//        Claims mother = new Claims(driver, info, true);
//        mother.action();
//
//        //Baby Automation
//        Claims baby = new Claims(driver, info, false);
//        baby.action();
//
//
//        driver.quit();
    }
}