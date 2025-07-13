package org.automation.pages;

import org.automation.Utility.Utility;
import org.automation.Utility.Utils;
import org.automation.informationcontroller.Controller;
import org.automation.informationcontroller.PatientBabyInterface;
import org.automation.pages.claim.CF1;
import org.automation.pages.claim.CF2Benefits;
import org.automation.pages.claim.CF2Information;
import org.automation.pages.claim.NewClaim;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Claims {
    private WebDriver driver;
    private PatientBabyInterface pbInfo;
    private String memberNumber;
    private Utils utils;

    public Claims(WebDriver driver, PatientBabyInterface specificInfo, String memberNumber) {
        this.driver = driver;
        this.pbInfo = specificInfo;
        this.memberNumber = memberNumber;
        this.utils = new Utils(driver);
    }


    public void action() throws InterruptedException {

        //delete after testing
        //Click testing claim
        Utility.waitAndClickElement(By.cssSelector("a[href=\"/53/claims\"]"));
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

        Utility.waitAndClickElement(By.cssSelector("a[href=\"/53/claims\"]"));
    }

}


