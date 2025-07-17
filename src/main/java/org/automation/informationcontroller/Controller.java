package org.automation.informationcontroller;

import org.json.JSONObject;

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

    public void setInformation(String[] args) {
        //expected value of args
        //args[0] = json of member information
        //args[1] = json of mother information
        //args[2] = json of baby information
        //args[3] = true or false
        //args[4] = true of false

        JSONObject member = new JSONObject(args[0]);
        JSONObject mother = new JSONObject(args[1]);
        JSONObject baby = new JSONObject(args[2]);

        setMemberInfo(
                member.getString("memberShipType"),
                member.getString("memberId"),
                member.getString("memberFirstName"),
                member.getString("memberMiddleName"),
                member.getString("memberLastName"),
                member.getString("memberSuffix"),
                member.getString("memberBirthDate"),
                member.getString("memberGender"),
                member.getString("memberAddress"),
                member.getString("memberZip"),
                member.getString("memberMobileNo"),
                member.getString("memberEmployeeNo"),
                member.getString("memberEmployeeName")
        );

        //args[3] == File mother claim if true
        if (Boolean.parseBoolean(args[3])) {
            ArrayList<LocalDate> checkupDates = new ArrayList<>();
            checkupDates.add(LocalDate.parse(mother.getString("motherCheckUp1")));
            checkupDates.add(LocalDate.parse(mother.getString("motherCheckUp2")));
            checkupDates.add(LocalDate.parse(mother.getString("motherCheckUp3")));
            checkupDates.add(LocalDate.parse(mother.getString("motherCheckUp4")));

            setPatientInfo(
                    LocalDate.parse(mother.getString("admissionDay")),
                    LocalDate.parse(mother.getString("dischargeDay")),
                    LocalTime.parse(mother.getString("admissionTime")),
                    LocalTime.parse(mother.getString("dischargeTime")),
                    mother.getString("motherPatientId"),
                    mother.getString("motherFirstName"),
                    mother.getString("motherMiddleName"),
                    mother.getString("motherLastName"),
                    mother.getString("motherSuffix"),
                    mother.getString("motherBirthDate"),
                    mother.getString("motherGender"),
                    checkupDates,
                    Integer.parseInt(mother.getString("gravida")),
                    Integer.parseInt(mother.getString("para")),
                    mother.getString("tpal"),
                    mother.getString("aog"),
                    mother.getString("babyGender"),
                    mother.getString("accreNumber")
            );
        }

        //args[4] == file baby claim if true
        if (Boolean.parseBoolean(args[4])) {
            setBabyInfo(
                    LocalDate.parse(baby.getString("admissionDay")),
                    LocalDate.parse(baby.getString("dischargeDay")),
                    LocalTime.parse(baby.getString("admissionTime")),
                    LocalTime.parse(baby.getString("dischargeTime")),
                    baby.getString("firstName"),
                    baby.getString("middleName"),
                    baby.getString("lastName"),
                    baby.getString("suffix"),
                    baby.getString("gender"),
                    Integer.parseInt(baby.getString("weight")),
                    baby.getString("nbs"),
                    baby.getString("accreditationNo")
            );
        }

    }

    public void setMemberInfo(String membershipType,
                              String id,
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

        patient.setServiceDate(admissionDay);
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

        baby.setServiceDate(admissionDay);
        patient.setServiceDate(admissionDay);

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
