package org.automation.informationcontroller;

import java.time.LocalDate;
import java.time.LocalTime;

public interface PatientBabyInterface {
    String addDia();

    String disDia();

    void setDate(LocalDate ad, LocalDate dis, LocalTime adT, LocalTime disT);

    void setCode(String icd, String rvs);

    void setAccreNo(String acc);

    void setPayment(double hci, double prof);

    LocalDate getAdmissionDate();

    LocalDate getDischargeDate();

    LocalTime getAdmissionTime();

    LocalTime getDischargeTime();

    public String getAccreNo();

    public double getHciFee();

    public double getProfFee();

}

