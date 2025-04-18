package org.automation.pages.claim;

import org.automation.Utility.*;
import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;
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
        //create new claim from the main page
        WebElement newClaim = utils.waitForElement(By.cssSelector("a[href=\"/53/claims/new\"]"));
        newClaim.click();

        //search for member
        WebElement findMember = utils.waitForElement(By.xpath("//span[text()=\"Enter PhilHealth ID...\"]"));
        findMember.click();
        utils.replaceInputValues(By.cssSelector("div[class=\"select2-search\"] input"), memberIdNo);
        WebElement memberAppeared = utils.waitForElement(By.cssSelector("ul.select2-results li:first-of-type div"));
        memberAppeared.click();

        //Calendar set Up
        //Constant Paths
        CalendarPath admission = calendar.getAdmission();
        TimePath adTime = time.getAdmission();
        CalendarPath discharge = calendar.getDischarge();
        TimePath disTime = time.getDischarge();

        chooseDateAndTime(admission, adTime, pbInfo.getAdmissionDate(), pbInfo.getAdmissionTime());
        //inpatient radio picker
        driver.findElement(By.id("claim_admission_type_inpatient")).click();
        chooseDateAndTime(discharge, disTime, pbInfo.getDischargeDate(), pbInfo.getDischargeTime());

        //submit
        driver.findElement(By.xpath("//*[@id=\"new_claim\"]/div/div/input")).click();


    }

    public void chooseDateAndTime(CalendarPath visDate, TimePath visTime, LocalDate date, LocalTime time) {

        makeCalendarAppear(visDate.getCalSelector());
        checkMonthAndYear(visDate, date);
        chooseTime(visTime, time);
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

    public void checkMonthAndYear(CalendarPath tag, LocalDate date) {
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
        calendarUIAppear.click();
    }

}
