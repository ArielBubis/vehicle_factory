package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
import java.util.Date;

import utils.Area;
import utils.Gender;

public class VIPCustomer extends Customer {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private double discountPercentage;

	/**
	 * VIPCustomer constructer
	 *
	 * @param iD
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param gender
	 * @param yearOfBirth
	 * @param area
	 * @param dateOfJoining
	 * @param discountPercentage
	 * @param yearOfBirth
	 */
	public VIPCustomer(String iD, String firstName, String lastName, String phoneNumber, Gender gender, int yearOfBirth,
			Area area, Date dateOfJoining, double discountPercentage) {
		super(iD, firstName, lastName, phoneNumber, gender, yearOfBirth, area, dateOfJoining);
		this.discountPercentage = discountPercentage;
	}

	/**
	 * Partial constructor
	 * @param iD
	 */
	public VIPCustomer(String iD) {
		super(iD);
	}

	/**
	 *
	 * @return discountPercentage
	 */
	public double getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 *
	 * @param discountPercentage
	 */
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + " discountPercentage= " + discountPercentage;
	}

	@Override
	public int compareTo(Person o) {
		return super.compareTo(o);
	}
}
