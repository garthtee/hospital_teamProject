package core;

import java.util.Calendar;

/**
 * Created by Group 5 on 3/1/16.
 */
public class Shift {



    private int shift_ID;
    private String startTime;
    private String endTime;
    private String shiftType;
    private int reqDoctors;
    private int reqNurses;
    private int ward_ID;

    public Shift(){

    }

    public Shift(String startTime, String endTime, String shiftType, int reqDoctors, int reqNurses) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
        this.reqDoctors = reqDoctors;
        this.reqNurses = reqNurses;
    }

    public int getShift_ID() {
        return shift_ID;
    }

    public void setShift_ID(int shift_ID) {
        this.shift_ID = shift_ID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public int getReqDoctors() {
        return reqDoctors;
    }

    public void setReqDoctors(int reqDoctors) {
        this.reqDoctors = reqDoctors;
    }

    public int getReqNurses() {
        return reqNurses;
    }

    public void setReqNurses(int reqNurses) {
        this.reqNurses = reqNurses;
    }

    public int getWard_ID() {
        return ward_ID;
    }

    public void setWard_ID(int ward_ID) {
        this.ward_ID = ward_ID;
    }

	@Override
	public String toString() {
		return "Shift [shift_ID=" + shift_ID + ", startTime=" + startTime + ", endTime=" + endTime + ", shiftType="
				+ shiftType + ", reqDoctors=" + reqDoctors + ", reqNurses=" + reqNurses + "]";
	}
    
    
}
