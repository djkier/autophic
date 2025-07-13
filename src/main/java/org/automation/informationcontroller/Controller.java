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

    public void setMemberInfo(String id,
                              String membershipType,
                              String firstName,
                              String middleName,
                              String lastName,
                              String suffix,
                              String birthDate,
                              String gender,
                              String address,
                              String zip,
                              String mobileNo,
                              String employeeNo,
                              String employeeName) {

        member.setGenInfo(id, firstName, middleName, lastName, suffix, birthDate, gender);
        member.setInfo(membershipType, address, zip, mobileNo, employeeNo, employeeName);
    }

    public MemberInfo getMember(){
        return member;
    }

    public void setPatientInfo(LocalDate admissionDay,
                               LocalDate dischargeDay,
                               LocalTime admissionTime,
                               LocalTime dischargeTime,
                               String patientId,
                               String firstName,
                               String middleName,
                               String lastName,
                               String suffix,
                               String birthDate,
                               String gender,
                               ArrayList<LocalDate> checkupDates,
                               int gravida,
                               int para,
                               String tpal,
                               String aog,
                               String babyGender,
                               String accreNumber
    ) {
        patient.setGenInfo(patientId, firstName, middleName, lastName, suffix, birthDate, gender);
        patient.setDate(admissionDay, dischargeDay, admissionTime, dischargeTime);

        patient.setPatientInfo(gravida, para, tpal, aog, babyGender, checkupDates);
        patient.setAccreNo(accreNumber);
    }

    public PatientInfo getPatient() {
        return patient;
    }

    public void setBabyInfo(LocalDate admissionDay,
                            LocalDate dischargeDay,
                            LocalTime admissionTime,
                            LocalTime dischargeTime,
                            String firstName,
                            String middleName,
                            String lastName,
                            String suffix,
                            String gender,
                            int weight,
                            String nbs,
                            String accreditationNo
    ) {
        baby.setGenInfo("000000000000", firstName, middleName, lastName, suffix, admissionDay.toString(), gender);
        baby.setDate(admissionDay, dischargeDay, admissionTime, dischargeTime);
        baby.setBabyInfo(weight, nbs);
        baby.setAccreNo(accreditationNo);

    }

    public BabyInfo getBaby() {
        return baby;
    }

    public boolean isMemberAPatient(){
        return member.getId().equals(patient.getId()) &&
                member.getFN().equals(patient.getFN()) &&
                member.getLN().equals(patient.getLN());
    }

}
