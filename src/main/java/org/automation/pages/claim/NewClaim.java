package org.automation.pages.claim;

import org.automation.Utility.Calendar;
import org.automation.Utility.CalendarPath;
import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.automation.Utility.Utils;

import java.time.LocalDate;

public class NewClaim {
    private WebDriver driver;
    private Utils utils;
    private PatientBabyInterface pbInfo;
    private String memberIdNo;
    private Calendar calendar;

    public NewClaim(WebDriver driver, PatientBabyInterface pbInfo, String memberIdNo) {
        this.driver = driver;
        this.utils = new Utils(driver);
        this.pbInfo = pbInfo;
        this.memberIdNo = memberIdNo;
        this.calendar = new Calendar();
    }

    public void createClaim() {
        //create new claim from the main page
        WebElement newClaim = utils.waitForElement(By.cssSelector("a[href=\"/53/claims/new\"]"));
        newClaim.click();

        //search for member
        WebElement findMember = utils.waitForElement(By.xpath("//span[text()=\"Enter PhilHealth ID...\"]"));
        findMember.click();
        utils.replaceInputValues(By.cssSelector("div[class=\"select2-search\"] input"), memberIdNo);
        WebElement memberAppeared = utils.waitForElement(By.cssSelector("ul.select2-results li:first-of-type div"));
        memberAppeared.click();

        //inpatient
        driver.findElement(By.id("claim_admission_type_inpatient")).click();

        //calendar set Up
        CalendarPath admission = calendar.getAdmission();
        String admissionUICal = calendar.getAdmission().getCalSelector();
        makeCalendarAppear(admission.getCalSelector());


        checkMonthAndYear(admission, pbInfo.getAdmissionDate());

    }

    public void checkMonthAndYear(CalendarPath tag, LocalDate date) {
        WebElement monthYear = utils.waitForElement(By.xpath(tag.getMYSelector()));

        //Visible Month and Year
        String visibleMonth = extractMonth(monthYear);
        int visibleYear = extractYear(monthYear);
        System.out.println(visibleMonth + " " + visibleYear);

        //Info for the target month and year
        String pbMonth = date.getMonth().toString().toLowerCase();
        int pbYear = date.getYear();


        while (pbYear != visibleYear) {
            driver.findElement(By.xpath(tag.getLeftArrow())).click();
            visibleYear = extractYear(monthYear);
        }

    }

    public int extractYear(WebElement monthYear) {
        String[] my = monthYear.getText().split(" ");
        return Integer.parseInt(my[1]);
    }

    public String extractMonth(WebElement monthYear) {
        String[] my = monthYear.getText().split(" ");
        return my[0].toLowerCase();
    }

    public void makeCalendarAppear(String tag){
        WebElement calendarUIAppear = utils.waitForElement(By.xpath(tag));
        calendarUIAppear.click();
    }

}
