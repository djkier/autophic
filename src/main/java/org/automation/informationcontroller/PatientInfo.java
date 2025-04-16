package org.automation.informationcontroller;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientInfo extends PatientBabyCommon{
    private int g;
    private int p;
    private String puWeeks;
    private String babyGender;
    private ArrayList<LocalDate> checkUp;

    public PatientInfo(){
        this.g = 0;
        this.p = 0;
        this.puWeeks = "";
        this.babyGender = "";
        this.checkUp = new ArrayList<>();
        super.setPayment(9360, 6240);
    }

    public void setPatientInfo(int gr, int par, String puW, String gender, ArrayList<LocalDate> cD) {
        this.g = gr;
        this.p = par;
        this.puWeeks = puW;
        this.babyGender = gender;
        this.checkUp = cD;
    }

    public int getGravida() {
        return g;
    }

    public int getParity() {
        return p;
    }

    public String getPUWeeks() {
        return puWeeks;
    }

    public String getBabyGender() {
        return babyGender;
    }

    public ArrayList<LocalDate> getCheckUpDates() {
        return checkUp;
    }

}
