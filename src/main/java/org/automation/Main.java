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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Controller info = new Controller();

        String address = "Street Name, Brgy. Name, Name City, Country Name";
        info.setMemberInfo("000000000010",
                "Individually Paying",
                "Test First",
                "Test Second",
                "Test Last",
                "",
                "1995-09-01",
                "female",
                address,
                "1116",
                "09565645645",
                "",
                ""
        );

        ArrayList<LocalDate> checkup = new ArrayList<>();
        checkup.add(LocalDate.of(2024,10,18));
        checkup.add(LocalDate.of(2024,11,14));
        checkup.add(LocalDate.of(2024,12,16));
        checkup.add(LocalDate.of(2025,5,14));

//        String accreNo = "3000";
        String accreNo = "1302";

        info.setPatientInfo(
                LocalDate.of(2025, 5, 22),
                LocalDate.of(2025, 5, 23),
                LocalTime.of(5, 51),
                LocalTime.of(9,1),
                "000000000010",
                "Test First",
                "Test Second",
                "Test Last",
                "",
                "1995-09-01",
                "female",
                checkup,
                5,
                4,
                "1001",
                "38.5",
                "female",
                accreNo
        );

        info.setBabyInfo(
                LocalDate.of(2025, 5, 22),
                LocalDate.of(2025, 5, 23),
                LocalTime.of(17, 6),
                LocalTime.of(20, 30),
                "JOHN DOE",
                "DELA CRUZ",
                "DEL MONTE",
                "",
                "female",
                3300,
                "00000000",
                accreNo
        );



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
        Member member = new Member(info.getMember());
        member.action();

        //Patient Automation
        Claims mother = new Claims(driver, info, info.getPatient());
        mother.action();

//        //Baby Automation
//        Claims baby = new Claims(driver, info, false);
//        baby.action();
//
//
        System.out.println("Close Browser");
        driver.quit();
    }
}