package org.automation;

import org.automation.Utility.DriverManager;
import org.automation.Utility.Utility;
import org.automation.informationcontroller.Controller;
import org.automation.pages.Claims;
import org.automation.pages.Member;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Create a new class for the information
        Controller info = new Controller();

        String address = "Street Name, Address, Name of the city";
        info.setMemberInfo("Individually Paying",
                "123456789121",
                "Test first",
                "Middle Test",
                "Last Test",
                "",
                "2000-01-01",
                "female",
                address,
                "1234",
                "09123456789",
                "",
                ""
        );

        ArrayList<LocalDate> checkup = new ArrayList<>();
        checkup.add(LocalDate.of(2024,11,30));
        checkup.add(LocalDate.of(2024,12,30));
        checkup.add(LocalDate.of(2025,1,29));
        checkup.add(LocalDate.of(2025,5,27));

        String accreNo = "3000";
//        String accreNo = "1302";

        info.setPatientInfo(
                LocalDate.of(2025, 5, 28),
                LocalDate.of(2025, 5, 29),
                LocalTime.of(12, 10),
                LocalTime.of(12,30),
                "123456789121",
                "Test first",
                "Middle Test",
                "Last Test",
                "",
                "2000-01-01",
                "female",
                checkup,
                5,
                4,
                "4004",
                "39",
                "boy",
                accreNo
        );

        info.setBabyInfo(
                LocalDate.of(2025, 5, 28),
                LocalDate.of(2025, 5, 29),
                LocalTime.of(12, 24),
                LocalTime.of(12, 30),
                "Baby first",
                "Baby middle",
                "Baby Last",
                "",
                "male",
                3000,
                "12345678",
                accreNo
        );



        //create
        WebDriver driver = new EdgeDriver();
        DriverManager.setDriver(driver);
        //open website
        driver.manage().window().maximize();
        driver.get(Config.get("website"));


        //log-in
        WebElement userEmail = Utility.waitForElement(By.id("user_email"));
        WebElement userPassword = Utility.waitForElement(By.id("user_password"));
        WebElement submit = Utility.waitForElement(By.name("commit"));

        userEmail.sendKeys(Config.get("userEmail"));
        userPassword.sendKeys(Config.get("userPassword"));
        submit.click();

        //Member Automation
        Member member = new Member(info.getMember());
        member.action();

        //Patient Automation
        Claims mother = new Claims(info.getPatient(), info.getMember().getId());
        mother.action();

        //Baby Automation
        Claims baby = new Claims(info.getBaby(), info.getMember().getId());
        baby.action();


        driver.quit();
    }
}