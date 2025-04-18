package org.automation.pages.claim;

import org.automation.Utility.Utils;
import org.automation.informationcontroller.BabyInfo;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.informationcontroller.PatientInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CF2Information {
    private WebDriver driver;
    private Utils utils;
    private PatientBabyInterface pbInfo;

    public CF2Information(WebDriver driver, PatientBabyInterface pbInfo) {
        this.driver = driver;
        this.utils = new Utils(driver);
        this.pbInfo = pbInfo;
    }


    public void action() throws InterruptedException {
        //Patient disposition
        utils.clickerWait(By.id("form_two_patient_disposition"));
        utils.clickerWait(By.xpath("//*[@id=\"form_two_patient_disposition\"]/option[2]"));
        Thread.sleep(1000);
        //Type of disposition
        utils.clickerWait(By.id("form_two_accommodation_type_private"));
        //Input admission diagnosis
        WebElement admissionDiagnosis = utils.waitForElement(By.id("form_two_admission_diagnosis"));
//        System.out.println(pbInfo.addDia());
        utils.replaceInputValues(admissionDiagnosis, pbInfo.addDia());

        //Open discharge diagnoses
        utils.clicker(By.className("add-discharge-diagnosis"));
        //Input discharge diagnosis
        WebElement dischargeDiagnosis = utils.waitForElement(By.cssSelector("#discharge_diagnoses textarea"));
        utils.replaceInputValues(dischargeDiagnosis, pbInfo.disDia());
        //ICD code
        utils.clicker(By.cssSelector("#discharge_diagnoses div.icd-codes-container div div a"));
        WebElement icdCode = utils.waitForElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
        utils.replaceInputValues(icdCode, pbInfo.getIcd());
        utils.clickerWait(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div"));

        //Add procedure
        utils.clicker(By.cssSelector("#discharge_diagnoses div div.panel-body table tfoot tr td a"));
        //RVS date first so the unchanged value of rvs could use
        String pathToRelativeRVS = "//*[@id='discharge_diagnoses']//span[normalize-space(text())='RVS Code or description']/ancestor::tr/td[3]/div/input";
        WebElement dateRVS = utils.waitForElement(By.xpath(pathToRelativeRVS));
        utils.replaceInputValues(dateRVS, pbInfo.getDischargeDate().toString());
        //RVS code
        utils.clickerWait(By.xpath("//*[@id='discharge_diagnoses']//span[normalize-space(text())='RVS Code or description']"));
        WebElement rvsCode = utils.waitForElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
        utils.replaceInputValues(rvsCode, pbInfo.getRvs());
        utils.clickerWait(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div"));

        if (pbInfo instanceof PatientInfo) {
            System.out.println("mother");
        }
        if (pbInfo instanceof BabyInfo) {
            System.out.println("Baby");
        }

        //submit
    }

    public void motherCF2(PatientBabyInterface pbInfo) {
        PatientInfo mother = (PatientInfo) pbInfo;
//*[@id="form_two_did_maternal_care_package_dates_"]
//*[@id="form_two_did_maternal_care_package_dates_"]
    }

    public void babyCF2(PatientBabyInterface pbInfo) {
        BabyInfo mother = (BabyInfo) pbInfo;

    }
}
