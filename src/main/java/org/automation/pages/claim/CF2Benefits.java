package org.automation.pages.claim;

import org.automation.Utility.Utility;
import org.automation.informationcontroller.PatientBabyInterface;
import org.openqa.selenium.By;

public class CF2Benefits {
    private PatientBabyInterface pbInfo;

    public CF2Benefits(PatientBabyInterface pbInfo) {
        this.pbInfo = pbInfo;
    }

    public void action() {
        //Select available case rates
        Utility.waitAndClickElement(By.xpath("//*[@id=\"case-rates\"]//label[normalize-space(text())='Use This']"));
        //Select accreditation number
        Utility.waitAndClickElement(By.xpath("//*[@id=\"s2id_form_two_professionals_attributes_0_accreditation_code\"]/a/span[1]"));
        Utility.waitReplaceInputValues(By.xpath("//*[@id=\"select2-drop\"]/div/input"), pbInfo.getAccreNo());
        Utility.waitAndClickElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div"));
        //Date signed
        Utility.waitReplaceInputValues(By.xpath("//*[@id=\"form_two_professionals_attributes_0_signed_on\"]"),
                pbInfo.getDischargeDate().toString()
        );
        //Covered by benefit
        Utility.clickElement(By.id("form_two_covered_by_benefit"));
        Utility.waitReplaceInputValues(By.id("form_two_covered_hci_fee"), String.valueOf(pbInfo.getHciFee()));
        Utility.waitReplaceInputValues(By.id("form_two_covered_professional_fee"), String.valueOf(pbInfo.getProfFee()));
        //Submit
        Utility.clickElement(By.cssSelector("input[name=\"commit\"]"));
    }
}
