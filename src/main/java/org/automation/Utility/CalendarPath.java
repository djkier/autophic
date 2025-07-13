package org.automation.Utility;

public class CalendarPath {
    public String calSelector;
    public String mySelector;
    public String pickDay;

    public CalendarPath(String calSelector, String mySelector, String pickDay) {
        this.calSelector = calSelector;
        this.mySelector = mySelector;
        this.pickDay = pickDay;
    }

    private String getHeaderSelector() {
        return this.mySelector + "/div[1]/table/thead/tr[1]";
    }

    //Calendar ui path
    public String getCalSelector() {
        return this.calSelector + "/div/span";
    }

    //Month and Year path
    public String getMYSelector() {
        return getHeaderSelector() + "/th[2]";
    }

    public String getLeftArrow() {
        return getHeaderSelector() + "/th[1]";
    }

    public String getDays() {
        return this.pickDay + "/div[1]/table/tbody//td[@class='day']";
    }

}
