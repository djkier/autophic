package org.automation.pages;

import org.automation.Utility.Utils;
import org.automation.informationcontroller.Controller;
import org.automation.informationcontroller.MemberInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

    public void memberAction() throws InterruptedException {
        WebElement memberTab = utils.waitForElement(By.cssSelector("a[href=\"/53/members\""));
        memberTab.click();

        if (checkMember()) {
            memberExist();
        } else {
            existing = false;
            createNewMember();
        }

        WebElement mainPage = utils.waitForElement(By.cssSelector("a[href=\"/53/claims\""));
        mainPage.click();
    }

    public boolean checkMember() throws InterruptedException {
        WebElement inputIdNo = utils.waitForElement(By.cssSelector("input[id=\"q_phic_id_number_start\""));
        inputIdNo.sendKeys(info.getId());
        driver.findElement(By.cssSelector("button[type=\"submit\"")).click();
        Thread.sleep(500);
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody"));

        return rows.size() > 1;
    }

    public void memberExist() {
        By editSelect = By.cssSelector("table tbody tr td:last-of-type a:nth-of-type(2)");
        WebElement edit = utils.waitForElement(editSelect);
        edit.click();
        editMemberInfo();
    }

    public void createNewMember() {
        WebElement newMember = utils.waitForElement(By.cssSelector("a[href='/53/members/new']"));
        newMember.click();
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
