package core;


import java.util.Calendar;

public class Doctor extends Employee{
	private String doctorGrade;
	private String speciality;

	public Doctor(String doctorGrade, String speciality) {
		this.doctorGrade = doctorGrade;
		this.speciality = speciality;
	}

	public Doctor(String fName, String doctorGrade, String speciality) {
		super(fName);
		this.doctorGrade = doctorGrade;
		this.speciality = speciality;
	}

	public Doctor(String fName, String sName, Calendar dOB, String contactNum, String email, int numHolidays, int contractHours, double salary, int ward_ID, String doctorGrade, String speciality) {
		super(fName, sName, dOB, contactNum, email, numHolidays, contractHours, salary, ward_ID);
		this.doctorGrade = doctorGrade;
		this.speciality = speciality;
	}

	public String getDoctorGrade() {
		return doctorGrade;
	}
	public void setDoctorGrade(String doctorGrade) {
		this.doctorGrade = doctorGrade;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

}
