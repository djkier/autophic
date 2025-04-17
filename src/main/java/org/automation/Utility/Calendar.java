package org.automation.Utility;

public class Calendar {
    private CalendarPath admission;
    private CalendarPath discharge;

    public Calendar() {
        this.admission = setAdmission();
        this.discharge = setDischarge();
    }

    public CalendarPath setAdmission() {
        return createCalendar(2);
    }

    public CalendarPath setDischarge() {
        return createCalendar(3);
    }

    public CalendarPath createCalendar(int no){
        String calSelector = "//div[" + no + "]/div";
        String mySelector = "//div[" + no + "]/div/div[1]";
        String pickDay = "//div[" + no + "]/div/div[1]";
        return new CalendarPath(calSelector, mySelector, pickDay);
    }

    public CalendarPath getAdmission() {
        return admission;
    }

    public CalendarPath getDischarge() {
        return discharge;
    }


}
