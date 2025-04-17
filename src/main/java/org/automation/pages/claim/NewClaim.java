package org.automation.pages.claim;

import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.automation.pages.Utils;

import java.time.LocalDate;

public class NewClaim {
    private WebDriver driver;
    private Utils utils;
    private PatientBabyInterface pbInfo;
    private String memberIdNo;

    public NewClaim(WebDriver driver, PatientBabyInterface pbInfo, String memberIdNo) {
        this.driver = driver;
        this.utils = new Utils(driver);
        this.pbInfo = pbInfo;
        this.memberIdNo = memberIdNo;
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
        String admissionTag = "//div[2]/div/div[1]";
        String dischargeTag = "//div[3]/div";
        makeCalendarAppear(admissionTag);
        checkMonthAndYear(admissionTag, pbInfo.getAdmissionDate());

    }

    public void checkMonthAndYear(String tag, LocalDate date) {
        WebElement monthYear = utils.waitForElement(By.xpath(tag + "/div[1]/div[1]/table/thead/tr[1]/th[2]"));

        //Visible Month and Year
        String visibleMonth = extractMonth(monthYear);
        int visibleYear = extractYear(monthYear);
        System.out.println(visibleMonth + " " + visibleYear);

        //Info for the target month and year
        String pbMonth = date.getMonth().toString().toLowerCase();
        int pbYear = date.getYear();


        while (pbYear != visibleYear) {
            driver.findElement(By.xpath(tag + "/div[1]/table/thead/tr[1]/th[1]")).click();
            visibleYear = extractYear(monthYear);
        }

//        /html/body/div[2]/div/div[1]/div[1]/table/thead/tr[1]/th[2] addmision
//        /html/body/div[3]/div/div[1]/div[1]/table/thead/tr[1]/th[2] discharge
//        /html/body/div[3]/div/div[1]/table/thead/tr[1]/th[2] form2
//        /html/body/div[77]/div/div[1]/table/thead/tr[1]/th[2]
//        /html/body/div[78]/div/div[1]/table/thead/tr[1]/th[2]
//        /html/body/div[3]/div/div[1]/table/thead/tr[1]/th[2]
//
//        /html/body/div[2]/div/div[1]/div[2]/table/tbody/tr/td
//        /html/body/div[3]/div/div[1]/div[2]/table/tbody/tr/td
//        /html/body/div[3]/div/div[2]/table/tbody/tr/td



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
        WebElement calendarUIAppear = utils.waitForElement(By.xpath(tag + "/div/span"));
        //*[@id="new_claim"]/fieldset[2]/div[2]/div/div/span
        //*[@id="new_claim"]/fieldset[2]/div[3]/div/div/span
        //*[@id="discharge_diagnoses"]/div/div[2]/table/tbody/tr/td[3]/div/span
        //*[@id="professionals"]/div/div[1]/div/div[2]/div/div[2]/dl/div/dd/div/span
        calendarUIAppear.click();
    }

}
