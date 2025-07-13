package org.automation.pages;

import org.automation.Utility.Utility;
import org.automation.informationcontroller.Controller;
import org.automation.informationcontroller.MemberInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Member {
    private final MemberInfo info;
    private boolean existing;

    public Member(Controller info){
        this.info = info.getMember();
        this.existing = true;
    }

    public void action() {
        //Click member tab
        Utility.waitAndClickElement(By.cssSelector("a[href=\"/53/members\""));

        //Check for membership existence
        if (!checkMember()) {
            memberExist();
        } else {
            existing = false;
            createNewMember();
        }

        //Go to main page
        Utility.waitAndClickElement(By.cssSelector("a[href=\"/53/claims\""));
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
        //Membership type
        WebElement membershipType = Utility.waitForElement(By.id("member_phic_membership_type"));
        Select dropDown = new Select(membershipType);
        dropDown.selectByValue(info.getType());

        //Write ID info if not existing
        if (!existing){
            Utility.replaceInputValues(By.id("member_phic_id_number"), info.getId());
        }

        Utility.replaceInputValues(By.id("member_first_name"), info.getFN());
        Utility.replaceInputValues(By.id("member_middle_name"), info.getMN());
        Utility.replaceInputValues(By.id("member_last_name"), info.getLN());
        Utility.replaceInputValues(By.id("member_name_suffix"), info.getSuffix());
        Utility.replaceInputValues(By.id("member_birth_date"), info.getbDate());
        //gender
        Utility.clickElement(By.id("member_gender_" + info.genderValue()));

        Utility.replaceInputValues(By.id("member_mailing_address"), info.getAddress());
        Utility.replaceInputValues(By.id("member_zip_code"), info.getZipCode());

        Utility.replaceInputValues(By.id("member_phic_employer_number"), info.getEmployerNo());
        Utility.replaceInputValues(By.id("member_employer_name"), info.getEmployerName());

        mobileNumberFormatting();

        //Submit
        Utility.waitAndClickElement(By.cssSelector("input[name=\"commit\""));


    }

    public void mobileNumberFormatting() {
        //Input of number should be done 1 by 1 to ensure no overlapping of number
        By mobileNumberPath = By.id("member_mobile_number");
        Utility.replaceInputValues(mobileNumberPath, "");
        WebElement mobileNo = Utility.findElement(mobileNumberPath);
        mobileNo.sendKeys(Keys.CONTROL + "a", "");
        for (char c : info.getMobileNo().toCharArray()){
            mobileNo.sendKeys(String.valueOf(c));
        }
    }

}
