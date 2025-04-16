package org.automation.informationcontroller;

public class BabyInfo extends PatientBabyCommon{
    private int weight;
    private String nbs;

    public BabyInfo() {
        this.weight = 0;
        this.nbs = "";
        super.setCode("Z38.0", "99460");
        super.setPayment(4141.5, 978);
    }

    public void setBabyInfo(int weight, String nbs) {
        this.weight = weight;
        this.nbs = nbs;
    }

    public String addDia(){
        return "Term and live baby " + super.getGender() +
                " in Cephalic Presentation Body Weight - " +
                this.weight + " grams A/S - 9,10";
    }

    public String disDia() {
        return addDia();
    }

    public String getNbs() {
        return nbs;
    }




}
