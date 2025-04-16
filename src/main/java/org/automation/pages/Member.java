package org.automation.pages;

import org.automation.informationcontroller.MemberInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Member {
    private final WebDriver driver;
    private final MemberInfo info;
    private final WebDriverWait wait;
    private boolean existing;

    public Member(WebDriver driver, MemberInfo info, WebDriverWait wait){
        this.driver = driver;
        this.info = info;
        this.wait = wait;
        this.existing = true;
    }

    public void memberAction() throws InterruptedException {
        WebElement memberTab = waitForElement(By.cssSelector("a[href=\"/53/members\""));
        memberTab.click();

        if (checkMember()) {
            memberExist();
        } else {
            existing = false;
            createNewMember();
        }
    }

    public boolean checkMember() throws InterruptedException {
        WebElement inputIdNo = waitForElement(By.cssSelector("input[id=\"q_phic_id_number_start\""));
        inputIdNo.sendKeys(info.getId());
        driver.findElement(By.cssSelector("button[type=\"submit\"")).click();
        Thread.sleep(500);
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody"));

        return rows.size() > 1;
    }

    public void memberExist() {
        By editSelect = By.cssSelector("table tbody tr td:last-of-type a:nth-of-type(2)");
        WebElement edit = waitForElement(editSelect);
        edit.click();
        editMemberInfo();
    }

    public void createNewMember() {
        WebElement newMember = waitForElement(By.cssSelector("a[href='/53/members/new']"));
        newMember.click();
        editMemberInfo();
    }

    public void editMemberInfo() {
        WebElement membershipType = waitForElement(By.id("member_phic_membership_type"));

        if (!existing){
            replaceInputValues(By.id("member_phic_id_number"), info.getId());
        }

        Select dropDown = new Select(membershipType);
        dropDown.selectByValue(info.getType());

        replaceInputValues(By.id("member_first_name"), info.getFN());
        replaceInputValues(By.id("member_middle_name"), info.getMN());
        replaceInputValues(By.id("member_last_name"), info.getLN());
        replaceInputValues(By.id("member_name_suffix"), info.getSuffix());
        replaceInputValues(By.id("member_birth_date"), info.getbDate());
        //gender
        driver.findElement(By.id("member_gender_" + info.genderValue())).click();
        replaceInputValues(By.id("member_mailing_address"), info.getAddress());
        replaceInputValues(By.id("member_zip_code"), info.getZipCode());
//        replaceInputValues(By.id("member_mobile_number"), info.getMobileNo());
        System.out.println(info.getMobileNo());
        WebElement mobile = waitForElement(By.id("member_phic_employer_number"));
        mobile.clear();
        mobile.sendKeys(info.getMobileNo());

        replaceInputValues(By.id("member_phic_employer_number"), info.getEmployerNo());
        replaceInputValues(By.id("member_employer_name"), info.getEmployerName());

        //submit
        WebElement submit = waitForElement(By.cssSelector("input[name=\"commit\""));
        submit.click();

        WebElement mainPage = waitForElement(By.cssSelector("a[href=\"/53/claims\""));
        mainPage.click();

    }

    public void replaceInputValues(By element, String value) {
        WebElement input = driver.findElement(element);
        input.clear();
        input.sendKeys(value);
    }

    public WebElement waitForElement (By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
