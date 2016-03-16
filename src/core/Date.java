//package core;
//
//import java.io.Serializable;
//import java.util.Scanner;
//
//import javax.swing.JOptionPane;
//
///**
// *
// * @author L00106273 - Garth Toland
// * @version 1.0
// * Date: 24/09/2015
// * Description: Models a date
// *
// */
//
//
//		// REFERENCING below code in enterDate() method //
//		/* http://stackoverflow.com/a/2912935/4602214 */
//
//public class Date implements Serializable {
//
//	private int day;
//	private int month;
//	private int year;
//	private static int thisYear = 2015; // Set current year here
//	transient Scanner keyboardIn = new Scanner(System.in);
//
//	/**
//	 * Default Constructor
//	 * Called when object is created like this
//	 * ==> Date pObj = new Date();
//	 */
//	public Date() {	}
//
//	/**
//	 * Initialization constructor
//	 * Called when object would have been created like this (NOT possible now)
//	 * 		==> Date todaysDate = new Date(1, 10, 2015);
//	 * @param day
//	 * @param month
//	 * @param year
//	 */
//	public Date(int day, int month, int year) throws IllegalArgumentException {
//
//		if((day <= 0 || day > 31) || (month <= 0 || month > 12) || (year <= 0))
//			throw new IllegalArgumentException("Incorrect date.");
//
//		// No exception thrown
//		this.day = day;
//		this.month = month;
//		this.year = year;
//	}
//
//	/**
//	 * toString() Method
//	 *  ==> Calls Date's toString() to display the date details
//	 */
//	public String toString(){
//		return day + "/" + month + "/" + year;
//	}
//
//	/**
//	 * equals() method
//	 * ==> Called when comparing an object with another object, e.g. - if(d1.equals(d2))
//	 * @param dateIn
//	 * @return
//	 */
//	public boolean equals(Date dateIn){
//		if(day == dateIn.getDay() && month == dateIn.getMonth() && year == dateIn.getYear())
//			return true;
//		else
//			return false;
//	}
//
//
//	/*	Getters && Setters	*/
//	//////////////////////////
//
//	/**
//	 * Gets day of date
//	 * @return day integer
//	 */
//	public int getDay() {
//		return day;
//	}
//
//	/**
//	 *
//	 * @param day takes in a integer representing the day
//	 */
//	public Date setDay(int day) throws IllegalArgumentException {
//		if((day <= 0 || (day > 31)))
//			throw new IllegalArgumentException("Day is an incorrect value.");
//
//		// No exception thrown
//		this.day = day;
//		return this;
//	}
//
//	/**
//	 * Gets month of date
//	 * @return month as an integer
//	 */
//	public int getMonth() {
//		return month;
//	}
//
//	/**
//	 * Sets the month of a date
//	 * @param month takes in an integer representing a month
//	 */
//	public Date setMonth(int month) throws IllegalArgumentException {
//		if((month <= 0) || (month > 12))
//			throw new IllegalArgumentException("Month is an incorrect value.");
//
//		// No exception thrown
//		this.month = month;
//
//		return this;
//	}
//
//	/**
//	 * Gets the year of the date
//	 * @return year as an integer
//	 */
//	public int getYear() {
//		return year;
//	}
//
//	/**
//	 * Sets the year of a date
//	 * @param year takes in a integer representing a year
//	 */
//	public Date setYear(int year) throws IllegalArgumentException {
//		if((year <= 1900 || year > thisYear))
//			throw new IllegalArgumentException("Year is an incorrect value.");
//
//		// No exception thrown
//		this.year = year;
//
//		return this;
//	}
//
//
//}