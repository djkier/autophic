package org.automation.informationcontroller;

public class GeneralInformation {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String bDate;
    private String gender;

    public GeneralInformation(){
        this.id = "";
        this.firstName = "";
        this.middleName ="";
        this.lastName = "";
        this.suffix = "";
        this.bDate = "";
        this.gender = "";
    }

    public void setGenInfo(String id, String fn, String mn, String ln, String suffix, String date, String gender){
        this.id = id;
        this.firstName = fn;
        this.middleName = mn;
        this.lastName = ln;
        this.suffix = suffix;
        this.bDate = date;
        this.gender = gender;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str){
        this.id = str;
    }

    public String getFN(){
        return this.firstName;
    }

    public String getMN(){
        return this.middleName;
    }

    public String getLN() {
        return this.lastName;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public String getbDate() {
        return this.bDate;
    }

    public String getGender() {
        return this.gender;
    }


    public int genderValue(){
        if (this.gender.equals("male")){
            return 0;
        } else {
            return 1;
        }
    }
}
