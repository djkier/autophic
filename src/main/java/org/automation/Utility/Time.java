package org.automation.Utility;

public class Time {
    private TimePath admission;
    private TimePath discharge;

    public Time() {
        this.admission = new TimePath(2);
        this.discharge = new TimePath(3);
    }

    public TimePath getAdmission() {
        return admission;
    }

    public TimePath getDischarge() {
        return discharge;
    }
}
