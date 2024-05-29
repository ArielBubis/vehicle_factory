package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
import java.util.Date;

import javax.swing.ImageIcon;

import utils.Gender;

public class DepartmentManager extends Employee {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private Date appointmentDate;
	private double bonus;

	/**
	 * DepartmentManager Constructor
	 *
	 * @param iD
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param gender
	 * @param dateOfStartWork
	 * @param salary
	 * @param dep
	 * @param yearOfBirth
	 * @param appointmentDate
	 * @param bonus
	 */
	public DepartmentManager(String iD, String firstName, String lastName, String phoneNumber, Gender gender,
			Date dateOfStartWork, Double salary, Department dep, int yearOfBirth, Date appointmentDate, double bonus,
			String pass, ImageIcon photoPath, String userName) {
		super(iD, firstName, lastName, phoneNumber, gender, dateOfStartWork, salary, dep, yearOfBirth, pass, photoPath,
				userName);
		this.appointmentDate = appointmentDate;
		this.bonus = bonus;
	}

	/**
	 * Partial DepartmentManager constructor
	 *
	 * @param iD
	 */
	public DepartmentManager(String iD) {
		super(iD);
	}

	/**
	 *
	 * @return Date appointmentDate
	 */
	public Date getAppointmentDate() {
		return appointmentDate;
	}

	/**
	 *
	 * @param appointmentDate
	 */
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	/**
	 *
	 * @return double bonus
	 */
	public double getBonus() {
		return bonus;
	}

	/**
	 *
	 * @param bonus
	 */
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return super.toString() + " , bonus=" + bonus;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
