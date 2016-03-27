package core;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Employee {
	private int emp_ID;
	private String fName;
	private String sName;
	private Calendar DOB;
	private String contactNum;
	private String email;
	private int numHolidays;
	private int contractHours;
	private double salary;
	private Double onHoliday = 0.0;
	private Double offSick = 0.0;
	private String lastShift;
	private int ward_ID;

    public Employee(){}

	public Employee(String fName){
		this.fName = fName;
	}

	public Employee(String fName, String sName, Calendar dOB, String contactNum, String email, int numHolidays,
					int contractHours, double salary, int ward_ID) {
		this.fName = fName;
		this.sName = sName;
		this.DOB = dOB;
		this.contactNum = contactNum;
		this.email = email;
		this.numHolidays = numHolidays;
		this.contractHours = contractHours;
		this.salary = salary;
		this.onHoliday = 0.0;
		this.offSick = 0.0;
		this.ward_ID = ward_ID;
	}

    public Employee(int emp_ID, String fName, String sName, Calendar dOB, String contactNum, String email, int numHolidays,
                    int contractHours, double salary, Double onHoliday, Double offSick, int ward_ID) {
		this.emp_ID = emp_ID;
		this.fName = fName;
		this.sName = sName;
		this.DOB = dOB;
		this.contactNum = contactNum;
		this.email = email;
		this.numHolidays = numHolidays;
		this.contractHours = contractHours;
		this.salary = salary;
		this.onHoliday = onHoliday;
		this.offSick = offSick;
		this.onHoliday = 0.0;
		this.offSick = 0.0;
		this.ward_ID = ward_ID;
	}

	public int getEmp_ID() {
		return emp_ID;
	}

    public void setEmp_ID(int emp_ID) {
        this.emp_ID = emp_ID;
    }

    public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Calendar getDOB() {
		return DOB;
	}

	public void setDOB(Calendar dOB) {
		DOB = dOB;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumHolidays() {
		return numHolidays;
	}

	public void setNumHolidays(int numHolidays) {
		this.numHolidays = numHolidays;
	}

	public int getContractHours() {
		return contractHours;
	}

	public void setContractHours(int contractHours) {
		this.contractHours = contractHours;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Double isOnHoliday() {
		return onHoliday;
	}

	public void setOnHoliday(Double onHoliday) {
		this.onHoliday = onHoliday;
	}

	public Double isOffSick() {
		return offSick;
	}

	public void setOffSick(Double offSick) {
		this.offSick = offSick;
	}

	public String getLastShift() {
		return lastShift;
	}

	public void setLastShift(String lastShift) {
		this.lastShift = lastShift;
	}

    public int getWard_ID() {
        return ward_ID;
    }

    public void setWard_ID(int ward_ID) {
        this.ward_ID = ward_ID;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Employee{" +
                "emp_ID=" + emp_ID +
                ", fName='" + fName + '\'' +
                ", sName='" + sName + '\'' +
                ", DOB=" + sdf.format(DOB.getTime()) +
                ", contactNum='" + contactNum + '\'' +
                ", email='" + email + '\'' +
                ", numHolidays=" + numHolidays +
                ", contractHours=" + contractHours +
                ", salary=" + salary +
                ", onHoliday=" + onHoliday +
                ", offSick=" + offSick +
                ", lastShift='" + lastShift + '\'' +
                ", ward_ID=" + ward_ID +
                '}';
    }
}
