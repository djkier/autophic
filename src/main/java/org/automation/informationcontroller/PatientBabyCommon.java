package org.automation.informationcontroller;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class PatientBabyCommon extends GeneralInformation implements PatientBabyInterface{
    private LocalDate adDay;
    private LocalDate disDay;
    private LocalTime adTime;
    private LocalTime disTime;
    private String icd;
    private String rvs;
    private String accreNo;
    private double hciFee;
    private double profFee;
    private String relationship;

    public PatientBabyCommon() {
        this.adDay = LocalDate.now().minusDays(1);
        this.disDay = LocalDate.now();
        this.adTime = LocalTime.now().minusHours(24);
        this.disTime = LocalTime.now();
        this.icd = "";
        this.rvs = "";
        this.accreNo = "";
        this.hciFee = 0;
        this.profFee = 0;
        this.relationship = "";
    }

    @Override
    public void setDate(LocalDate ad, LocalDate dis, LocalTime adT, LocalTime disT) {
        this.adDay = ad;
        this.disDay = dis;
        this.adTime = adT;
        this.disTime = disT;
    }

    @Override
    public void setCode(String icd, String rvs) {
        this.icd = icd;
        this.rvs = rvs;
    }

    @Override
    public void setAccreNo(String acc){
        this.accreNo = acc;
    }

    @Override
    public void setPayment(double hci, double prof) {
        this.hciFee = hci;
        this.profFee = prof;
    }



    @Override
    public LocalDate getAdmissionDate() {
        return adDay;
    }

    @Override
    public LocalDate getDischargeDate() {
        return disDay;
    }

    @Override
    public LocalTime getAdmissionTime() {
        return adTime;
    }

    @Override
    public LocalTime getDischargeTime() {
        return disTime;
    }

    @Override
    public String getAccreNo() {
        return accreNo;
    }

    @Override
    public double getHciFee() {
        return hciFee;
    }

    @Override
    public double getProfFee() {
        return profFee;
    }

    @Override
    public String getRelationShipToMember() { return relationship; }

    @Override
    public void setRelationShipToMember(String relationship) {
        this.relationship = relationship;
    }

}
