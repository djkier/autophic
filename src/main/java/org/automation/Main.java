package org.automation;

import org.automation.informationcontroller.Controller;
import org.automation.pages.Member;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Controller info = new Controller();

        //create
        WebDriver driver = new EdgeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //open website
        driver.manage().window().maximize();
        driver.get(Config.get("website"));

        //log-in
        WebElement userEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        userEmail.sendKeys(Config.get("userEmail"));
        driver.findElement(By.id("user_password")).sendKeys(Config.get("userPassword"));
        driver.findElement(By.name("commit")).click();

        //Member Automation
        Member member = new Member(driver, info.getMember(), wait);
        member.memberAction();

        //Patient Automation

        //Baby Automation




    }
}