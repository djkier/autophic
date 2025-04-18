package org.automation.pages.claim;

import org.automation.Utility.Utils;
import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CF1 {
    private WebDriver driver;
    private Utils util;
    private PatientBabyInterface pbInfo;

    public CF1(WebDriver driver, PatientBabyInterface pbInfo) {
        this.driver = driver;
        this.util = new Utils(driver);
        this.pbInfo = pbInfo;
    }

    public void action() {
        String memFn = util.waitForElement(By.id("form_one_member_first_name")).getDomAttribute("value");
        String memMn = driver.findElement(By.id("form_one_member_middle_name")).getDomAttribute("value");
        String memLn = driver.findElement(By.id("form_one_member_last_name")).getDomAttribute("value");

        if (pbInfo.getFN().equalsIgnoreCase(memFn) &&
                pbInfo.getMN().equalsIgnoreCase(memMn) &&
                pbInfo.getLN().equalsIgnoreCase(memLn)) {
            util.clicker(By.id("form_one_member_is_patient"));
        } else {
            fillPatientInfo();
        }

        util.clicker(By.cssSelector("input[name=\"commit\"]"));
    }

    public void fillPatientInfo() {
        util.replaceInputValues(By.id("form_one_patient_phic_id_number"), pbInfo.getId());
        util.replaceInputValues(By.id("form_one_patient_first_name"), pbInfo.getFN());
        util.replaceInputValues(By.id("form_one_patient_middle_name"), pbInfo.getMN());
        util.replaceInputValues(By.id("form_one_patient_last_name"), pbInfo.getLN());
        util.replaceInputValues(By.id("form_one_patient_name_suffix"), pbInfo.getSuffix());
        util.replaceInputValues(By.id("form_one_patient_birth_date"), pbInfo.getbDate());

        //Pick gender
        util.clicker(By.id("form_one_patient_gender"));

        if (pbInfo.getGender().equalsIgnoreCase("male")) {
            util.clickerWait(By.xpath("//*[@id=\"form_one_patient_gender\"]/option[2]"));
        } else {
            util.clickerWait(By.xpath("//*[@id=\"form_one_patient_gender\"]/option[3]"));
        }

        //Pick relationship to member
        if (pbInfo.getRelationShipToMember().equalsIgnoreCase("child")) {
            util.clicker(By.id("form_one_patient_relationship_child"));
        } else {
            util.clicker(By.id("form_one_patient_relationship_spouse"));
        }

    }
}
