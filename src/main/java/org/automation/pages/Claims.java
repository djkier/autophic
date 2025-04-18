package org.automation.pages;

import org.automation.Utility.Utils;
import org.automation.informationcontroller.Controller;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.pages.claim.CF1;
import org.automation.pages.claim.NewClaim;
import org.openqa.selenium.WebDriver;

public class Claims {
    private WebDriver driver;
    private Controller info;
    private PatientBabyInterface pbInfo;
    private Utils utils;

    public Claims(WebDriver driver, Controller info) {
        this.driver = driver;
        this.info = info;
        this.pbInfo = pbInfoSelector(info);
        this.utils = new Utils(driver);
    }

    public PatientBabyInterface pbInfoSelector(Controller pbi){
        if (pbi.isMemberAPatient()) {
            return pbi.getPatient();
        } else {
            return pbi.getBaby();
        }
    }

    public void claimsAction() {

//        NewClaim newClaim = new NewClaim(this.driver, this.pbInfo, this.info.getMember().getId());
//        newClaim.createClaim();

        CF1 cf1 = new CF1(driver, pbInfo);
        cf1.action();





    }

}


