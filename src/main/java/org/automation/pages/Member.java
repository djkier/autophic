package org.automation.pages;

import org.automation.Utility.Utility;
import org.automation.Utility.Utils;
import org.automation.informationcontroller.Controller;
import org.automation.informationcontroller.MemberInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Member {
    private final WebDriver driver;
    private final MemberInfo info;
    private boolean existing;
    private final Utils utils;

    public Member(WebDriver driver, Controller info){
        this.driver = driver;
        this.info = info.getMember();
        this.existing = true;
        this.utils = new Utils(driver);
    }

    public void action() {
        By mainPagePath = By.cssSelector("a[href=\"/53/members\"");
        WebElement memberTab = Utility.waitForElement(mainPagePath);
        memberTab.click();

        if (!checkMember()) {
            memberExist();
        } else {
            existing = false;
            createNewMember();
        }


//
//        WebElement mainPage = utils.waitForElement(By.cssSelector("a[href=\"/53/claims\""));
//        mainPage.click();
    }

    public boolean checkMember(){
        //Select input box for philhealth number
        WebElement inputIdNo = Utility.waitForElement(By.id("q_phic_id_number_start"));
        inputIdNo.sendKeys(info.getId());

        //Get the footer info for reference
        String footerXPath = "/html/body/main/div/table/tfoot/tr/td[1]/div";
        String initialFooterText = Utility.waitForElement(By.xpath(footerXPath)).getText();

        //Click Apply filter
        Utility.waitAndClickElement(By.cssSelector("#member_search > button"));

        //Wait for the footer with different text
        WebElement footerWithDiffText = Utility.waitElementAndGetIfDiffText(By.xpath(footerXPath), initialFooterText);

        return footerWithDiffText.getText().equalsIgnoreCase("No members found");
    }

    public void memberExist() {
        By editMemberInfoAnchor = By.cssSelector("table tbody tr td:last-of-type a:nth-of-type(2)");
        Utility.waitForElement(editMemberInfoAnchor).click();
        editMemberInfo();
    }

    public void createNewMember() {
        By newMemberButton = By.cssSelector("a[href='/53/members/new']");
        Utility.waitForElement(newMemberButton).click();
        editMemberInfo();
    }

    public void editMemberInfo() {
        WebElement membershipType = utils.waitForElement(By.id("member_phic_membership_type"));

        if (!existing){
            utils.replaceInputValues(By.id("member_phic_id_number"), info.getId());
        }

        Select dropDown = new Select(membershipType);
        dropDown.selectByValue(info.getType());

        utils.replaceInputValues(By.id("member_first_name"), info.getFN());
        utils.replaceInputValues(By.id("member_middle_name"), info.getMN());
        utils.replaceInputValues(By.id("member_last_name"), info.getLN());
        utils.replaceInputValues(By.id("member_name_suffix"), info.getSuffix());
        utils.replaceInputValues(By.id("member_birth_date"), info.getbDate());

        //gender
        driver.findElement(By.id("member_gender_" + info.genderValue())).click();
        utils.replaceInputValues(By.id("member_mailing_address"), info.getAddress());
        utils.replaceInputValues(By.id("member_zip_code"), info.getZipCode());
        mobileNoFormatting();
        utils.replaceInputValues(By.id("member_phic_employer_number"), info.getEmployerNo());
        utils.replaceInputValues(By.id("member_employer_name"), info.getEmployerName());

        //submit
        WebElement submit = utils.waitForElement(By.cssSelector("input[name=\"commit\""));
        submit.click();



    }

    public void mobileNoFormatting(){
        utils.replaceInputValues(By.id("member_mobile_number"), "");
        WebElement mobileNo = driver.findElement(By.id("member_mobile_number"));
        mobileNo.sendKeys(Keys.CONTROL + "a", "");
        for (char c : info.getMobileNo().toCharArray()){
            mobileNo.sendKeys(String.valueOf(c));
        }
    }

}
