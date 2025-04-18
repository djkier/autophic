package org.automation.pages;

import org.automation.Utility.Utils;
import org.automation.informationcontroller.Controller;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.pages.claim.NewClaim;
import org.openqa.selenium.WebDriver;

public class Claims {
    private WebDriver driver;
    private Controller info;
    private PatientBabyInterface pbInfo;
    private boolean mother;
    private Utils utils;

    public Claims(WebDriver driver, Controller info, boolean mother) {
        this.driver = driver;
        this.info = info;
        this.pbInfo = pbInfoSelector(info, mother);
        this.mother = mother;
        this.utils = new Utils(driver);
    }

    public PatientBabyInterface pbInfoSelector(Controller pbi, boolean m){
        if (m) {
            return pbi.getPatient();
        } else {
            return pbi.getBaby();
        }
    }

    public void claimsAction() {

//        NewClaim newClaim = new NewClaim(this.driver, this.pbInfo, this.info.getMember().getId());
//        newClaim.createClaim();





    }

}


