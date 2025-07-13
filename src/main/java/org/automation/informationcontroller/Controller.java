package org.automation.informationcontroller;

import org.automation.Config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {
    private final MemberInfo member;
    private final PatientInfo patient;
    private final BabyInfo baby;
    private final String accreNo;

    public Controller() {
        this.member = new MemberInfo();
        this.patient = new PatientInfo();
        this.baby = new BabyInfo();
        //delete this
//        this.accreNo = Config.get("accreNo");
        this.accreNo = "3000";
//        this.accreNo = "1302";
    }

    public MemberInfo getMember(){
        member.setGenInfo("000000000001", "Test First", "Test Second", "Test Last", "", "1995-09-01", "female");
        String address = "Street Name, Brgy. Name, Name City, Country Name";
        member.setInfo("Individually Paying", address, "1116", "09565645645", "", "");

        return member;
    }





    public PatientInfo getPatient() {
        ArrayList<LocalDate> checkup = new ArrayList<>();
        LocalDate adDay = LocalDate.of(2025, 5, 22);
        LocalDate disDay = LocalDate.of(2025,5,23);
        LocalTime adTime = LocalTime.of(5,51);
        LocalTime disTime = LocalTime.of(9, 1);
        patient.setGenInfo("000000000001", "Test First", "Test Second", "Test Last", "", "1995-09-01", "female");
        patient.setDate(adDay, disDay, adTime, disTime);

        checkup.add(LocalDate.of(2024,10,18));
        checkup.add(LocalDate.of(2024,11,14));
        checkup.add(LocalDate.of(2024,12,16));
        checkup.add(LocalDate.of(2025,5,14));
        patient.setPatientInfo(5, 4, "4004", "38.1", "boy", checkup);
        patient.setAccreNo(accreNo);

        return patient;
    }

    public BabyInfo getBaby() {
        LocalDate adDay = LocalDate.of(2025, 5, 22);
        LocalDate disDay = LocalDate.of(2025,5,23);
        LocalTime adTime = LocalTime.of(17,6);
        LocalTime disTime = LocalTime.of(20, 30);
            baby.setGenInfo("000000000000","JOHN DOE", "DELA CRUZ", "DEL MONTE", "", adDay.toString(), "male");
        baby.setDate(adDay, disDay, adTime, disTime);
        baby.setBabyInfo(3300, "00000000");
        baby.setAccreNo(accreNo);

        return baby;
    }

    public boolean isMemberAPatient(){
        return member.getId().equals(patient.getId()) &&
                member.getFN().equals(patient.getFN()) &&
                member.getLN().equals(patient.getLN());
    }

}
