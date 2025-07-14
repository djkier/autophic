package org.automation.pages;

import org.automation.Utility.Utility;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.pages.claim.*;
import org.openqa.selenium.By;


public class Claims {
    private PatientBabyInterface pbInfo;
    private String memberNumber;

    public Claims(PatientBabyInterface specificInfo, String memberNumber) {
        this.pbInfo = specificInfo;
        this.memberNumber = memberNumber;
    }


    public void action() throws InterruptedException {

        //delete after testing
        Utility.waitAndClickElement(By.cssSelector("a[href=\"/53/claims\"]"));
        //Click testing claim
//        Utility.waitAndClickElement(By.xpath("/html/body/main/div/table/tbody/tr[1]/td[1]/a"));

        NewClaim newClaim = new NewClaim(this.pbInfo, this.memberNumber);
        newClaim.createClaim();

        //Claim Form 1 page
//        Utility.waitAndClickElement(By.xpath("//*[@id=\"claimTabs\"]/li[3]/a"));
//        Utility.waitAndClickElement(By.xpath("//*[@id=\"form-one\"]/div[1]/div/a"));
        CF1 cf1 = new CF1(pbInfo);
        cf1.action();

        //Claim Form 2 page
        Utility.waitAndClickElement(By.xpath("//*[@id=\"claimTabs\"]/li[4]/a"));
        Utility.waitAndClickElement(By.xpath("//*[@id=\"form-two\"]//a[normalize-space(text())='Edit CF2']"));
        CF2Information cf2Info = new CF2Information(pbInfo);
        cf2Info.action();

        //Claim Form 2 Benefits page
        Utility.waitAndClickElement(By.xpath("//*[@id=\"claimTabs\"]/li[5]/a"));
        Utility.waitAndClickElement(By.xpath("//*[@id=\"form-two\"]//a[normalize-space(text())='Edit CF2 Benefits']"));
        CF2Benefits cf2Benefits = new CF2Benefits(pbInfo);
        cf2Benefits.action();

        //eSOA
        Esoa eSoa = new Esoa(pbInfo);
        eSoa.action();

        //Main Page
        Utility.waitAndClickElement(By.cssSelector("a[href=\"/53/claims\"]"));
    }

}


