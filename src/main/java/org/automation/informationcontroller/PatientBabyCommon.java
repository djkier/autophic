package org.automation.informationcontroller;

import java.time.LocalDate;
import java.time.LocalTime;

public class PatientBabyCommon extends GeneralInformation{
    private LocalDate adDay;
    private LocalDate disDay;
    private LocalTime adTime;
    private LocalTime disTime;
    private String accreNo;
    private int hciFee;
    private int profFee;

    public PatientBabyCommon() {
        this.adDay = LocalDate.now().minusDays(1);
        this.disDay = LocalDate.now();
        this.adTime = LocalTime.now().minusHours(24);
        this.disTime = LocalTime.now();
        this.accreNo = "";
        this.hciFee = 0;
        this.profFee = 0;
    }

    public void setDate(LocalDate ad, LocalDate dis, LocalTime adT, LocalTime disT) {
        this.adDay = ad;
        this.disDay = dis;
        this.adTime = adT;
        this.disTime = disT;
    }

    public void setAccreNo(String acc){
        this.accreNo = acc;
    }

    public void setPayment(int hci, int prof) {
        this.hciFee = hci;
        this.profFee = prof;
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
