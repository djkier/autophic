package org.automation.informationcontroller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Controller {
    private final MemberInfo member;
    private final PatientInfo patient;
    private final BabyInfo baby;

    public Controller() {
        this.member = new MemberInfo();
        this.patient = new PatientInfo();
        this.baby = new BabyInfo();
    }

    public MemberInfo getMember(){
        member.setGenInfo("000000000012", "Firstest", "Middletest", "Lastest", "", "1990-02-28", "female");

        String address = "169 Sauyo Road, Novaliches, Quezon City";
        member.setInfo("indigent", address, "1116", "09569123456", "", "");

        return member;
    }

    public PatientInfo getPatient() {
        ArrayList<LocalDate> checkup = new ArrayList<>();
        patient.setGenInfo("000000000012","PatientF", "PatientMiddletest", "PatientLastest", "", "1990-02-28", "female");
        patient.setDate(LocalDate.now().minusDays(1), LocalDate.now(), LocalTime.now().minusHours(2), LocalTime.now());

        checkup.add(LocalDate.of(2024,4,21));
        checkup.add(LocalDate.of(2024,5,27));
        checkup.add(LocalDate.of(2024,6,28));
        checkup.add(LocalDate.of(2025,1,31));
        patient.setPatientInfo(1, 0, "36.5", "female", checkup);
        patient.setAccreNo("accreNo");

        return patient;
    }





}
