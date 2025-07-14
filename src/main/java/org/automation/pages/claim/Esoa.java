package org.automation.pages.claim;

import org.automation.Config;
import org.automation.Utility.DriverManager;
import org.automation.Utility.Utility;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.informationcontroller.PatientInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Esoa {
    private PatientBabyInterface pbInfo;

    public Esoa(PatientBabyInterface pbInfo) {
        this.pbInfo = pbInfo;
    }

    public void action() {
        String claim = Utility.waitForElement(By.cssSelector("h1")).getText();
        String[] claimParts = claim.split("#");
        String claimNumber = claimParts[1];
        int profFee = 978;
        int otherFundSource = 0;


        if (pbInfo instanceof PatientInfo) {
            profFee = Integer.parseInt(pbInfo.getAccreNo()) == 3000 ?
                    6240 : 10000;
            otherFundSource = 45;
            newTabAction(
                    Config.get("motherWebsite"),
                    claimNumber
            );
        } else {
            newTabAction(
                    Config.get("babyWebsite"),
                    claimNumber
            );
        }

        Utility.waitAndClickElement(By.cssSelector("#claimTabs > li:nth-child(8) > a"));
        Utility.waitAndClickElement(By.cssSelector("#esoa > div.pull-right > a:nth-child(1)"));
        Utility.waitReplaceInputValues(By.cssSelector("#esoa_other_fund_source_description"), "HCI Subsidy");
        Utility.waitReplaceInputValues(By.cssSelector("#esoa_other_fund_source_amount"), String.valueOf(otherFundSource));
        Utility.waitReplaceInputValues(By.cssSelector("#esoa_professional_fees_attributes_0_charges_net_of_applicable_vat"),
                String.valueOf(profFee));
        Utility.waitReplaceInputValues(By.cssSelector("#esoa_professional_fees_attributes_0_other_fund_source_amount"),
                String.valueOf(profFee-6240));
        Utility.waitAndClickElement(By.cssSelector("input[label='esoa[professional_fees_attributes][0][primary]']"));

        //Target service date
        List<WebElement> itemizedCharges = Utility.findListOfElements(By.xpath("//fieldset[3]/div[2]/table/tbody/tr"));
        //Replace service date with the admission date
        for (int i = 0; i < itemizedCharges.size(); i++) {
            Utility.waitReplaceInputValues(
                    By.cssSelector("#esoa_itemized_billing_items_attributes_" + i + "_service_date"),
                    pbInfo.getAdmissionDate().toString()
            );
        }
        //Update ESOA
        Utility.waitAndClickElement(By.name("commit"));

        //Refactor service date of mother since it must be based on the admission of the baby than the mother



    }

    private void newTabAction(String url, String claimNumber) {
        WebDriver newTab = DriverManager.newTab(url);
        Utility.waitReplaceInputValues(
                By.xpath("//*[@id=\"esoa\"]/div[2]/div[3]/div[1]/div/div[2]/div/input"),
                claimNumber);
        Utility.waitAndClickElement(By.cssSelector("#copy-esoa-itemized-billing-items-button"));

        newTab.switchTo().alert().accept();
        try {
            new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.alertIsPresent())
                    .accept();;
        } catch (TimeoutException e) {

        }
        newTab.close();
        DriverManager.switchToMainTab();
    }


}
