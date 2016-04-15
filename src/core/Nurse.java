package core;


import java.util.Calendar;

public class Nurse extends Employee{
	private String nurseGrade;
	private String nurseType;

	public Nurse(String nurseGrade, String nurseType) {
		this.nurseGrade = nurseGrade;
		this.nurseType = nurseType;
	}

	public Nurse(String fName, String nurseGrade, String nurseType) {
		super(fName);
		this.nurseGrade = nurseGrade;
		this.nurseType = nurseType;
	}

	public Nurse(String fName, String sName, Calendar dOB, String contactNum, String email, Double numHolidays, Double contractHours, double salary, int ward_ID, String nurseGrade, String nurseType) {
		super(fName, sName, dOB, contactNum, email, numHolidays, contractHours, salary, ward_ID);
		this.nurseGrade = nurseGrade;
		this.nurseType = nurseType;
	}

	public String getNurseGrade() {
		return nurseGrade;
	}

	public void setNurseGrade(String nurseGrade) {
		this.nurseGrade = nurseGrade;
	}

	public String getNurseType() {
		return nurseType;
	}

	public void setNurseType(String nurseType) {
		this.nurseType = nurseType;
	}

}
