package core;

import java.util.Calendar;

/**
 * Created by Group 5 on 3/1/16.
 */
public class Shift {

    private int shift_ID;
    private Calendar startTime;
    private Calendar endTime;
    private String shiftType;
    private int reqDoctors;
    private int reqNurses;

    public Shift(Calendar startTime, Calendar endTime, String shiftType, int reqDoctors, int reqNurses) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
        this.reqDoctors = reqDoctors;
        this.reqNurses = reqNurses;
    }

    public int getShift_ID() {
        return shift_ID;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
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

	@Override
	public String toString() {
		return "Shift [shift_ID=" + shift_ID + ", startTime=" + startTime + ", endTime=" + endTime + ", shiftType="
				+ shiftType + ", reqDoctors=" + reqDoctors + ", reqNurses=" + reqNurses + "]";
	}
    
    
}
