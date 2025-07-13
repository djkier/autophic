package org.automation.informationcontroller;

public class BabyInfo extends PatientBabyCommon {
    private int weight;
    private String nbs;

    public BabyInfo() {
        this.weight = 0;
        this.nbs = "";
        super.setCode("Z38.0", "99460");
        super.setPayment(4141.5, 978);
        super.setPatientType("Child");
        super.setAdmissionDiagnosis(addDia());
        super.setDischargeDiagnosis(disDia());
    }

    public void setBabyInfo(int weight, String nbs) {
        this.weight = weight;
        this.nbs = nbs;
    }

    @Override
    public String addDia() {
        String genderEquiv = super.getGender().equalsIgnoreCase("male") ? "boy" : "girl";

        return "Term and live baby " +
                genderEquiv +
                " in Cephalic Presentation Body Weight - " +
                this.weight +
                " grams A/S - 9,10";
    }

    @Override
    public String disDia() {
        return addDia();
    }

    public String getNbs() {
        return nbs;
    }




}
