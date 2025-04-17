package org.automation.informationcontroller;

import java.time.LocalDate;
import java.util.ArrayList;

public class PatientInfo extends PatientBabyCommon implements  PatientBabyInterface{
    private int g;
    private int p;
    private String tpal;
    private String puWeeks;
    private String babyGender;
    private ArrayList<LocalDate> checkUp;

    public PatientInfo(){
        this.g = 0;
        this.p = 0;
        this.tpal = "";
        this.puWeeks = "";
        this.babyGender = "";
        this.checkUp = new ArrayList<>();
        super.setCode("O80.0, Z37.0", "MCP01");
        super.setPayment(9360, 6240);

    }

    public void setPatientInfo(int gr, int par, String tpal, String puW, String gender, ArrayList<LocalDate> cD) {
        this.g = gr;
        this.p = par;
        this.tpal = tpal;
        this.puWeeks = puW;
        this.babyGender = gender;
        this.checkUp = cD;
    }

    public String addDia() {
        return "Normal Pregnancy " + gp(0) + " " + this.tpal +
                " Pregnancy Uterine " + weeksAog() +
                " weeks AOG, Cephalic in Labor";
    }

    public String disDia() {
        return gp(1) + "NSD to a live and term baby " +
                this.babyGender + " in Cephalic Presentation";
    }

    public String gp(int add) {
        return "G" + this.g + "P" + (this.p + add);
    }

    public String weeksAog(){
        String[] parts = this.puWeeks.split(".");
        return parts[0] + " " + parts[1] + "/7";
    }

    public ArrayList<LocalDate> getCheckUpDates() {
        return checkUp;
    }

}
