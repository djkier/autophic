package org.automation.informationcontroller;

public class Controller {
    private MemberInfo member;
    private PatientInfo patient;
    private BabyInfo baby;

    public Controller() {
        this.member = new MemberInfo();
        this.patient = new PatientInfo();
        this.baby = new BabyInfo();
    }

    public MemberInfo getMember(){
        member.setGenInfo("000000000012", "Indigent", "Firstest", "Middletest", "Lastest", "", "1990-02-28", "female");

        String address = "169 Sauyo Road, Novaliches, Quezon City";
        member.setInfo(address, "1116", "09569123456", "", "");

        return member;
    }

}
