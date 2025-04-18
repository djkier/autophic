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


        //Member Automation
//        Member member = new Member(driver, info);
//        member.memberAction();

        //delete after testing
        //Go back to the main page
        WebElement element = utils.waitForElement(By.cssSelector("a[href=\"/53/claims\"]"));
        element.click();

        //delete after testing
        //Click testing claim
        WebElement testClaim = utils.waitForElement(By.xpath("/html/body/main/div/table/tbody/tr[1]/td[1]/a"));
        testClaim.click();

        //delete after testing
        //Claim Form 1 page
        WebElement testCF1 = utils.waitForElement(By.xpath("//*[@id=\"claimTabs\"]/li[3]/a"));
        testCF1.click();
        WebElement editTestCf1 = utils.waitForElement(By.xpath("//*[@id=\"form-one\"]/div[1]/div/a"));
        editTestCf1.click();


        //Patient Automation
        Claims mother = new Claims(driver, info, true);
        mother.claimsAction();

        //Baby Automation
        Claims baby = new Claims(driver, info,false);



    }
}