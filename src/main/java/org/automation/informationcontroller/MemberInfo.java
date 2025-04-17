package org.automation.informationcontroller;

public class MemberInfo extends GeneralInformation {
    private String type;
    private String address;
    private String zipCode;
    private String mobileNo;
    private String employerNo;
    private String employerName;

    public MemberInfo() {
        this.type = "";
        this.address = "";
        this.zipCode = "";
        this.mobileNo = "";
        this.employerNo = "";
        this.employerName = "";
    }

    public void setInfo(String type, String add, String zip, String moNo, String empNo, String empName) {
        this.type = type;
        this.address = add;
        this.zipCode = zip;
        this.mobileNo = moNo;
        this.employerNo = empNo;
        this.employerName = empName;
    }

    public String getType() {
        return this.type;
    }

    public String getAddress() {
        return this.address;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public String getEmployerNo() {
        return this.employerNo;
    }

    public String getEmployerName() {
        return this.employerName;
    }
}
