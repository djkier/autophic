package org.automation.pages.claim;

import org.automation.Utility.Utility;
import org.automation.Utility.Utils;
import org.automation.informationcontroller.BabyInfo;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.informationcontroller.PatientInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class CF2Information {
    private PatientBabyInterface pbInfo;

    public CF2Information(PatientBabyInterface pbInfo) {
        this.pbInfo = pbInfo;
    }


    public void action() {
        //Patient disposition
        Utility.waitAndClickElement(By.id("form_two_patient_disposition"));
        Utility.waitAndClickElement(By.xpath("//*[@id=\"form_two_patient_disposition\"]/option[2]"));
//        Thread.sleep(1000);
        //Type of accomodation
        Utility.waitAndClickElement(By.id("form_two_accommodation_type_private"));
        //Input admission diagnosis
        Utility.waitReplaceInputValues(By.id("form_two_admission_diagnosis"), pbInfo.addDia());

        //Open discharge diagnoses
        Utility.waitAndClickElement(By.className("add-discharge-diagnosis"));
        //Input discharge diagnosis
        Utility.waitReplaceInputValues(By.cssSelector("#discharge_diagnoses textarea"), pbInfo.disDia());
        //ICD code
        Utility.waitAndClickElement(By.cssSelector("#discharge_diagnoses div.icd-codes-container div div a"));
        Utility.waitReplaceInputValues(By.xpath("//*[@id=\"select2-drop\"]/div/input"), pbInfo.getIcd());
        Utility.waitAndClickElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div"));

        //Add procedure
        Utility.waitAndClickElement(By.cssSelector("#discharge_diagnoses div div.panel-body table tfoot tr td a"));
        //RVS date first so the unchanged value of rvs could use
        String pathToRelativeRVS = "//*[@id='discharge_diagnoses']//span[normalize-space(text())='RVS Code or description']/ancestor::tr/td[3]/div/input";
        Utility.waitReplaceInputValues(By.xpath(pathToRelativeRVS), pbInfo.getAdmissionDate().toString());
        //RVS code
        Utility.waitAndClickElement(By.xpath("//*[@id='discharge_diagnoses']//span[normalize-space(text())='RVS Code or description']"));
        Utility.waitReplaceInputValues(By.xpath("//*[@id=\"select2-drop\"]/div/input"), pbInfo.getRvs());
        Utility.waitAndClickElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div"));

        if (pbInfo instanceof PatientInfo) {
            PatientInfo mother = (PatientInfo) pbInfo;
            motherCF2(mother);
        } else  {
            BabyInfo baby = (BabyInfo) pbInfo;
            babyCF2(baby);
        }

        //submit
        Utility.clickElement(By.cssSelector("input[name=\"commit\"]"));
    }

    public void motherCF2(PatientInfo mother) {
        Utility.clickElement(By.id("form_two_did_maternal_care_package"));
        String targetInput = "//*[@id=\"special-considerations\"]//label[normalize-space(text())='Prenatal Checkup Date']/following-sibling::div//input";
        Utility.waitForElement(By.xpath(targetInput));
        List<WebElement> prenatalDate = Utility.findListOfElements(By.xpath(targetInput));
        List<LocalDate> datesOfCheckUps = mother.getCheckUpDates();
        for (int i = 0; i < datesOfCheckUps.size(); i++) {
            WebElement prenatalDateTextField = Utility.waitForElement(prenatalDate.get(i));
            Utility.replaceInputValues(prenatalDateTextField, datesOfCheckUps.get(i).toString());
        }
    }

    public void babyCF2(BabyInfo baby) {
        //Click Newborn Care Package
        Utility.clickElement(By.id("form_two_did_ncp"));

        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_newborn_care"));
//        Newborn hearing screening test
//        Utility.waitAndClickElement(By.id("form_two_did_ncp_newborn_hearing_screening_test"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_newborn_screening_test"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_drying"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_skin_to_skin"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_cord_clamping"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_prophylaxis"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_weighing"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_vitamin_k"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_bcg"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_non_separation"));
        Utility.waitAndClickElement(By.id("form_two_did_ncp_essential_hepatitis_b"));
        Utility.replaceInputValues(By.id("form_two_did_ncp_newborn_screening_test_filter_card_number"), baby.getNbs());
    }
}
