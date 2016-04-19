package core;

/**
 * Created by Group 5 on 3/1/16.
 */
public class Ward {

    private int ward_ID;
    private String wardType;
    private int reqNurses;
    private int reqDoctors;
    private String scheduled;

    public Ward() {}

    public Ward(int ward_ID, String wardType, int reqNurses, int reqDoctors, String scheduled) {
        this.ward_ID = ward_ID;
        this.wardType = wardType;
        this.reqNurses = reqNurses;
        this.reqDoctors = reqDoctors;
        this.scheduled = scheduled;
    }

    public int getWard_ID() {
        return ward_ID;
    }

    public void setWard_ID(int ward_ID) {
        this.ward_ID = ward_ID;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }

    public int getReqNurses() {
        return reqNurses;
    }

    public void setReqNurses(int reqNurses) {
        this.reqNurses = reqNurses;
    }

    public int getReqDoctors() {
        return reqDoctors;
    }

    public void setReqDoctors(int reqDoctors) {
        this.reqDoctors = reqDoctors;
    }

    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    @Override
    public String toString() {
        return "Ward " + ward_ID +
                ": " + wardType;
    }
}
