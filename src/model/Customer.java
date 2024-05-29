package model;

/**
 * @author Ariel Bubis 205735749
 *
 */
import java.util.Date;
import java.util.HashSet;

import utils.Area;
import utils.Gender;

public class Customer extends Person {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private Area area;
	private HashSet<Deal> allDeals;
	private Date dateOfJoining;

	/**
	 * Customer constructor
	 *
	 * @param iD
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param gender
	 * @param yearOfBirth
	 * @param area
	 * @param dateOfJoining
	 */
	public Customer(String iD, String firstName,
			String lastName, String phoneNumber,
			Gender gender, int yearOfBirth,
			Area area, Date dateOfJoining) {
		super(iD, firstName, lastName, phoneNumber, gender, yearOfBirth);
		this.area = area;
		this.allDeals = new HashSet<>();
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * Partial constructor
	 *
	 * @param iD
	 */
	public Customer(String iD) {
		super(iD);
	}

	/**
	 *
	 * @return allDeals - all deals by Customer
	 */
	public HashSet<Deal> getAllDeals() {
		return allDeals;
	}

	/**
	 *
	 * @param allDeals
	 */
	public void setAllDeals(HashSet<Deal> allDeals) {
		this.allDeals = allDeals;
	}

	/**
	 *
	 * @return Area area
	 */
	public Area getArea() {
		return area;
	}

	/**
	 *
	 * @param area
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 *
	 * @return Date dateOfJoining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 *
	 * @param dateOfJoining
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	@Override
	public String toString() {
		return super.toString() + ", dateOfJoining= " + dateOfJoining;
	}

	/**
	 * Method to add deals manually to the ArrList allDeals
	 *
	 * @param deal
	 * @return boolean - true if added successfully and false if not
	 */
	public boolean addDeal(Deal deal) {
		if (deal == null) {
			return false;
		}
		return allDeals.add(deal);

	}

	/**
	 * Method to remove deals manually to the ArrList allDeals
	 *
	 * @param deal
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeDeal(Deal deal) {
		if (deal != null) {
			if (allDeals.contains(deal)) {
				return allDeals.remove(deal);
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int compareTo(Person o) {
		return super.compareTo(o);
	}

}
