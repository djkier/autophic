package org.automation.pages.claim;

import org.automation.Utility.*;
import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class NewClaim {
    private WebDriver driver;
    private Utils utils;
    private PatientBabyInterface pbInfo;
    private String memberIdNo;
    private Calendar calendar;
    private Time time;

    public NewClaim(WebDriver driver, PatientBabyInterface pbInfo, String memberIdNo) {
        this.driver = driver;
        this.utils = new Utils(driver);
        this.pbInfo = pbInfo;
        this.memberIdNo = memberIdNo;
        this.calendar = new Calendar();
        this.time = new Time();
    }

    public void createClaim() {
        //Click the "New Claim" button from the main page
        WebElement newClaim = utils.waitForElement(By.cssSelector("a[href=\"/53/claims/new\"]"));
        newClaim.click();

        //Search for Member then choose
        WebElement findMember = utils.waitForElement(By.xpath("//span[text()=\"Enter PhilHealth ID...\"]"));
        findMember.click();
        utils.replaceInputValues(By.cssSelector("div[class=\"select2-search\"] input"), memberIdNo);
        WebElement memberAppeared = utils.waitForElement(By.cssSelector("ul.select2-results li:first-of-type div"));
        memberAppeared.click();

        //Calendar set Up
        //Constant Paths
        CalendarPath admissionDayPath = calendar.getAdmission();
        TimePath admissionTimePath = time.getAdmission();
        CalendarPath dischargeDayPath = calendar.getDischarge();
        TimePath dischargeTimePath = time.getDischarge();

        //Choose date and time of Admission
        chooseDateAndTime(admissionDayPath, admissionTimePath, pbInfo.getAdmissionDate(), pbInfo.getAdmissionTime());
        //Pick from the type of patient to close the Date and Time UI of admission
        driver.findElement(By.id("claim_admission_type_inpatient")).click();
        //Choose date and time of Discharge
        chooseDateAndTime(dischargeDayPath, dischargeTimePath, pbInfo.getDischargeDate(), pbInfo.getDischargeTime());

        //Create Claim
        utils.clicker(By.cssSelector("input[name=\"commit\"]"));


    }

    public void chooseDateAndTime(CalendarPath visDate, TimePath visTime, LocalDate date, LocalTime time) {

        //Make calendar and time ui visible
        makeCalendarAppear(visDate.getCalSelector());
//        chooseMonthAndYear(visDate, date);
//        chooseTime(visTime, time);
    }

    public void chooseTime(TimePath tag, LocalTime getTime) {
        WebElement minutes = utils.waitForElement(By.xpath(tag.getMinMiddle()));
        int diffInMinutes = getTime.getMinute() - Integer.parseInt(minutes.getText());
        timeDifference(tag.getMinBottom(), tag.getMinTop(), diffInMinutes);

        WebElement hours = utils.waitForElement(By.xpath(tag.getHourMiddle()));
        int diffInHours = getTime.getHour() - Integer.parseInt(hours.getText());
        timeDifference(tag.getHourBottom(), tag.getHourTop(), diffInHours);
    }

    public void timeDifference(String bottom, String top, int diff) {
        if (diff < 0) {
            changeHourOrMinuteValue(bottom, diff);
        } else if (diff > 0) {
            changeHourOrMinuteValue(top, diff);
        }
    }

    public void changeHourOrMinuteValue(String a, int noOfClicks) {
        int absClick = Math.abs(noOfClicks);
        int i = 0;
        while (i < absClick) {
            //use this in case arrow might disappear
            //WebElement arrow = utils.waitForElement(By.xpath(a));
            WebElement arrow = driver.findElement(By.xpath(a));
            arrow.click();
            i++;
        }
    }

    public void chooseMonthAndYear(CalendarPath tag, LocalDate date) {
        //Get web element for checking the month and year
        WebElement monthYear = utils.waitForElement(By.xpath(tag.getMYSelector()));
        WebElement leftArrow = driver.findElement(By.xpath(tag.getLeftArrow()));

        //Info for the target month and year
        String pbMonth = date.getMonth().toString().toLowerCase();
        int pbYear = date.getYear();

        int visibleYear = extractYear(monthYear);
        while (pbYear != visibleYear) {
            leftArrow.click();
            monthYear = utils.waitForElement(By.xpath(tag.getMYSelector()));
            visibleYear = extractYear(monthYear);
        }

        String visibleMonth = extractMonth(monthYear);
        while(!pbMonth.equals(visibleMonth)){
            leftArrow.click();
            monthYear = utils.waitForElement(By.xpath(tag.getMYSelector()));
            visibleMonth = extractMonth(monthYear);
        }


        List<WebElement> visibleDays = driver.findElements(By.xpath(tag.getDays()));
        for (WebElement d : visibleDays) {
            int visDate = Integer.parseInt(d.getText());
            if (visDate == date.getDayOfMonth()) {
                d.click();
                break;
            }
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", calendarUIAppear);
//        calendarUIAppear.click();
    }

}
