package org.automation.pages.claim;

import org.automation.Utility.Utils;
import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CF2Benefits {
    private WebDriver driver;
    private Utils utils;
    private PatientBabyInterface pbInfo;

    public CF2Benefits(WebDriver driver, PatientBabyInterface pbInfo) {
        this.driver = driver;
        this.utils = new Utils(driver);
        this.pbInfo = pbInfo;
    }

    public void action() {
        utils.clickerWait(By.xpath("//*[@id=\"case-rates\"]//label[normalize-space(text())='Use This']"));
        utils.clickerWait(By.xpath("//*[@id=\"s2id_form_two_professionals_attributes_0_accreditation_code\"]/a/span[1]"));
        WebElement accName = utils.waitForElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
        utils.replaceInputValues(accName, pbInfo.getAccreNo());
        utils.clickerWait(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div"));
        WebElement dateSigned = utils.waitForElement(By.xpath("//*[@id=\"form_two_professionals_attributes_0_signed_on\"]"));
        utils.replaceInputValues(dateSigned, pbInfo.getDischargeDate().toString());

        utils.clicker(By.id("form_two_covered_by_benefit"));
        WebElement hciFee = utils.waitForElement(By.id("form_two_covered_hci_fee"));
        WebElement profFee = utils.waitForElement(By.id("form_two_covered_professional_fee"));
        utils.replaceInputValues(hciFee, String.valueOf(pbInfo.getHciFee()));
        utils.replaceInputValues(profFee, String.valueOf(pbInfo.getProfFee()));

        utils.clicker(By.cssSelector("input[name=\"commit\"]"));





    }
}
