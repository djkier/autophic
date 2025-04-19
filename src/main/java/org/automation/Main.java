package org.automation;

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
        Utils utils = new Utils(driver);
        //open website
        driver.manage().window().maximize();
        driver.get(Config.get("website"));

        //log-in

        WebElement userEmail = utils.waitForElement(By.id("user_email"));
        userEmail.sendKeys(Config.get("userEmail"));
        driver.findElement(By.id("user_password")).sendKeys(Config.get("userPassword"));
        driver.findElement(By.name("commit")).click();

//        Member Automation
        Member member = new Member(driver, info);
        member.action();

        //delete after testing
        //Go back to the main page
        utils.clickerWait(By.cssSelector("a[href=\"/53/claims\"]"));


        //Patient Automation
        Claims mother = new Claims(driver, info, true);
        mother.action();

        //Baby Automation
        Claims baby = new Claims(driver, info, false);
        baby.action();



    }
}