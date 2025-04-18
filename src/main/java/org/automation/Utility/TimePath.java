package org.automation.Utility;

public class TimePath {
    private int num;

    public TimePath(int num) {
        this.num = num;
    }

    public String createArrow (int row, int column) {
        return "//div[" + num + "]/div/div[2]/div[1]/table/tbody/tr[" + row +
                "]/td[" + column +"]/a/span";
    }

    public String createMiddle (int column) {
        return "//div[" + num + "]/div/div[2]/div[1]/table/tbody/tr[2]/td[" +
                column + "]/span";
    }

    public String getHourTop() {
        return createArrow(1,1);
    }

    public String getHourMiddle() {
        return createMiddle(1);
    }

    public String getHourBottom() {
        return createArrow(3,1);
    }

    public String getMinTop() {
        return createArrow(1, 3);
    }

    public String getMinMiddle() {
        return createMiddle(3);
    }

    public String getMinBottom() {
        return createArrow(3, 3);
    }


}
