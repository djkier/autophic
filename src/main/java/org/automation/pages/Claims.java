package org.automation.pages;

import org.automation.Utility.Utils;
import org.automation.informationcontroller.Controller;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.informationcontroller.PatientInfo;
import org.automation.pages.claim.CF1;
import org.automation.pages.claim.CF2Benefits;
import org.automation.pages.claim.CF2Information;
import org.automation.pages.claim.NewClaim;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Claims {
    private WebDriver driver;
    private Controller info;
    private PatientBabyInterface pbInfo;
    private Utils utils;

    public Claims(WebDriver driver, Controller info, boolean mother) {
        this.driver = driver;
        this.info = info;
        this.pbInfo = pbInfoSelector(info, mother);
        this.utils = new Utils(driver);
    }

    public PatientBabyInterface pbInfoSelector(Controller pbi, boolean mother){
        if (mother) {
            return pbi.getPatient();
        } else {
            return pbi.getBaby();
        }
    }

    public void action() throws InterruptedException {

        //delete after testing
        //Click testing claim
//        utils.clickerWait(By.cssSelector("a[href=\"/53/claims\"]"));

        NewClaim newClaim = new NewClaim(this.driver, this.pbInfo, this.info.getMember().getId());
        newClaim.createClaim();

        //delete after testing
        //Claim Form 1 page
//        utils.clickerWait(By.xpath("//*[@id=\"claimTabs\"]/li[3]/a"));
//        utils.clickerWait(By.xpath("//*[@id=\"form-one\"]/div[1]/div/a"));

        CF1 cf1 = new CF1(driver, pbInfo);
        cf1.action();

        //delete after testing
        //Claim Form 2 page
        utils.clickerWait(By.xpath("//*[@id=\"claimTabs\"]/li[4]/a"));
        Thread.sleep(500);
        utils.clickerWait(By.xpath("//*[@id=\"form-two\"]//a[normalize-space(text())='Edit CF2']"));

        CF2Information cf2Info = new CF2Information(driver, pbInfo);
        cf2Info.action();

        //delete after testing
        //Claim Form 2 Benefits page

        utils.clickerWait(By.xpath("//*[@id=\"claimTabs\"]/li[5]/a"));
        Thread.sleep(500);
        utils.clickerWait(By.xpath("//*[@id=\"form-two\"]//a[normalize-space(text())='Edit CF2 Benefits']"));

        CF2Benefits cf2Benefits = new CF2Benefits(driver, pbInfo);
        cf2Benefits.action();

        utils.clickerWait(By.cssSelector("a[href=\"/53/claims\"]"));
    }

}


