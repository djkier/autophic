package org.automation.pages.claim;

import org.automation.Utility.Utility;
import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CF1 {
    private PatientBabyInterface pbInfo;

    public CF1(PatientBabyInterface pbInfo) {
        this.pbInfo = pbInfo;
    }

    public void action() {
        String memFn = Utility.waitForElement(By.id("form_one_member_first_name")).getDomAttribute("value");
        String memMn = Utility.waitForElement(By.id("form_one_member_middle_name")).getDomAttribute("value");
        String memLn = Utility.waitForElement(By.id("form_one_member_last_name")).getDomAttribute("value");

        //Check if patient is same as the member
        if (pbInfo.getFN().equalsIgnoreCase(memFn) &&
                pbInfo.getMN().equalsIgnoreCase(memMn) &&
                pbInfo.getLN().equalsIgnoreCase(memLn)) {
            Utility.waitAndClickElement(By.id("form_one_member_is_patient"));
        } else {
            fillPatientInfo();
        }

        //OPD hemodialysis claim
        WebElement hemodialysisClaim = Utility.waitForElement(By.id("form_one_for_opd_hemodialysis"));
        Select dropDown = new Select(hemodialysisClaim);
        dropDown.selectByValue("false");

        Utility.clickElement(By.cssSelector("input[name=\"commit\"]"));
    }

    public void fillPatientInfo() {
        Utility.replaceInputValues(By.id("form_one_patient_phic_id_number"), pbInfo.getId());
        Utility.replaceInputValues(By.id("form_one_patient_first_name"), pbInfo.getFN());
        Utility.replaceInputValues(By.id("form_one_patient_middle_name"), pbInfo.getMN());
        Utility.replaceInputValues(By.id("form_one_patient_last_name"), pbInfo.getLN());
        Utility.replaceInputValues(By.id("form_one_patient_name_suffix"), pbInfo.getSuffix());
        Utility.replaceInputValues(By.id("form_one_patient_birth_date"), pbInfo.getbDate());

        //Pick gender
        WebElement patientGender = Utility.waitForElement(By.id("form_one_patient_gender"));
        Select dropDown = new Select(patientGender);
        dropDown.selectByContainsVisibleText(pbInfo.getGender().toLowerCase());

        //Pick relationship to member
        if (pbInfo.getPatientType().equalsIgnoreCase("child")) {
            Utility.waitAndClickElement(By.id("form_one_patient_relationship_child"));
        } else {
            Utility.waitAndClickElement(By.id("form_one_patient_relationship_spouse"));
        }

    }
}
