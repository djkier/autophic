package org.automation.informationcontroller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PatientInfo extends GeneralInformation{
    private LocalDate adDay;
    private LocalDate disDay;
    private LocalTime adTime;
    private LocalTime disTime;
    private int g;
    private int p;
    private String puWeeks;
    private String babyGender;
    private ArrayList<LocalDate> checkUp;
    private String accreNo;
    private final int hciFee;
    private final int profFee;


    public PatientInfo(){
        this.adDay = LocalDate.now().minusDays(1);
        this.disDay = LocalDate.now();
        this.adTime = LocalTime.now().minusHours(24);
        this.disTime = LocalTime.now();
        this.g = 0;
        this.p = 0;
        this.puWeeks = "";
        this.babyGender = "";
        this.checkUp = new ArrayList<>();
        this.accreNo = "";
        this.hciFee = 9360;
        this.profFee = 6240;

    }

    public void setDate(LocalDate ad, LocalDate dis, LocalTime adT, LocalTime disT) {
        this.adDay = ad;
        this.disDay = dis;
        this.adTime = adT;
        this.disTime = disT;
    }

    public void setPatientInfo(int gr, int par, String puW, String gender, ArrayList<LocalDate> cD, String accred) {
        this.g = gr;
        this.p = par;
        this.puWeeks = puW;
        this.babyGender = gender;
        this.checkUp = cD;
        this.accreNo = accred;

    }



    public LocalDate getAdmissionDate() {
        return adDay;
    }

    public LocalDate getDischargeDate() {
        return disDay;
    }

    public LocalTime getAdmissionTime() {
        return adTime;
    }

    public LocalTime getDischargeTime() {
        return disTime;
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

    public String getAccreNo() {
        return accreNo;
    }

    public int getHciFee() {
        return hciFee;
    }

    public int getProfFee() {
        return profFee;
    }



}
