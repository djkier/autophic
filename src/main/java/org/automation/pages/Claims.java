package org.automation.pages;

import org.automation.informationcontroller.BabyInfo;
import org.automation.informationcontroller.Controller;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.informationcontroller.PatientInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Claims {
    private WebDriver driver;
    private Controller info;
    private PatientBabyInterface pbInfo;
    private WebDriverWait wait;
    private boolean mother;
    private Utils utils;

    public Claims(WebDriver driver, Controller info, WebDriverWait wait, boolean mother) {
        this.driver = driver;
        this.info = info;
        this.pbInfo = pbInfoSelector(info, mother);
        this.wait = wait;
        this.mother = mother;
        this.utils = new Utils(driver, wait);
    }

    public PatientBabyInterface pbInfoSelector(Controller pbi, boolean m){
        if (m) {
            return pbi.getPatient();
        } else {
            return pbi.getBaby();
        }
    }

    public void claimsAction(){
        WebElement newClaim = utils.waitForElement(By.cssSelector("a[href=\"/53/claims/new\"]"));
        newClaim.click();

        WebElement findMember = utils.waitForElement(By.xpath("//span[text()=\"Enter PhilHealth ID...\"]"));
        findMember.click();;

        utils.replaceInputValues(By.cssSelector("div[class=\"select2-search\"] input"), info.getMember().getId());
        WebElement memberAppeared = utils.waitForElement(By.cssSelector("ul.select2-results li:first-of-type div"));
        memberAppeared.click();

        driver.findElement(By.id("claim_admission_type_inpatient")).click();

        //Calendar Picker make a Util class for this.
        WebElement calendarAppear = utils.waitForElement(By.cssSelector("div.form-group.datetime_picker.required.claim_admitted_at span"));
        calendarAppear.click();
        //Check for Month and Year
        WebElement checkMY = utils.waitForElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/table/thead/tr[1]/th[2]"));
        String[] my = checkMY.getText().split(" ");
        //check year first
//        pbInfo.get



    }
}
