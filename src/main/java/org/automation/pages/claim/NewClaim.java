package org.automation.pages.claim;

import org.automation.Utility.*;
import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Driver;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class NewClaim {
    private PatientBabyInterface pbInfo;
    private String memberIdNo;

    public NewClaim(PatientBabyInterface pbInfo, String memberIdNo) {
        this.pbInfo = pbInfo;
        this.memberIdNo = memberIdNo;
    }

    public void createClaim() {
        //Click the "New Claim" button from the main page
        Utility.waitAndClickElement(By.cssSelector("a[href=\"/53/claims/new\"]"));

        //Search for Member then choose
        Utility.waitAndClickElement(By.xpath("//span[text()=\"Enter PhilHealth ID...\"]"));
        Utility.replaceInputValues(By.cssSelector("div[class=\"select2-search\"] input"), memberIdNo);
        Utility.waitAndClickElement(By.cssSelector("ul.select2-results li:first-of-type div"));
        Utility.clickElement(By.id("claim_admission_type_inpatient"));

        //Admission
        chooseDateAndTime(
                By.cssSelector("#claim_admitted_at"),
                pbInfo.getAdmissionDate(),
                pbInfo.getAdmissionTime()
        );

        //Discharge
        chooseDateAndTime(
                By.cssSelector("#claim_discharged_at"),
                pbInfo.getDischargeDate(),
                pbInfo.getDischargeTime()
        );

//        Create Claim
        Utility.waitAndClickElement(By.cssSelector("input[name=\"commit\"]"));
    }

    private void chooseDateAndTime(By element, LocalDate date, LocalTime time) {
        WebElement dateAndTimeInputBox = Utility.waitForElement(element);
        String dateAndTimeFormat = date.toString() + " " + time.toString();

        //Use JS to remove attribute for readonly
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript(
                "arguments[0].removeAttribute('readonly');" +
                "arguments[0].value = arguments[1];" +
                "arguments[0].dispatchEvent(new Event('input',  { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                dateAndTimeInputBox, dateAndTimeFormat);
    }


}
