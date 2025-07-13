package org.automation.Utility;

public class CalendarPath {
    private int num;

    public CalendarPath(int num) {
        this.num = num;
    }

    private String getHeaderSelector() {
        return "//div[" + num + "]/div/div[1]/div[1]/table/thead/tr[1]";
    }

    //Calendar ui path
    public String getCalSelector() {
        return "//div[" + num + "]/div/div/span";
    }

    //Month and Year path
    public String getMYSelector() {
        return getHeaderSelector() + "/th[2]";
    }

    public String getLeftArrow() {
        return getHeaderSelector() + "/th[1]";
    }

    public String getDays() {
        return "//div[" + num + "]/div/div[1]/div[1]/table/tbody//td[@class='day']";
    }

}
