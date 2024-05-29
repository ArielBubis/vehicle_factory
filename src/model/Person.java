package model;

import java.io.Serializable;
/**
 * @author Ariel Bubis 205735749
 *
 */
import java.util.Objects;

import utils.Gender;

public class Person implements Comparable<Person>, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private String ID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Gender gender;
	private int yearOfBirth;

	/**
	 * Person constructor
	 *
	 * @param iD
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param gender
	 * @param yearOfBirth
	 */
	public Person(String iD, String firstName, String lastName, String phoneNumber, Gender gender, int yearOfBirth) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.yearOfBirth = yearOfBirth;
	}

	/**
	 * Partial constructor
	 *
	 * @param iD
	 */
	public Person(String iD) {
		super();
		this.ID = iD;
	}

	/**
	 * @param firstName2
	 * @param lastName2
	 */
	public Person(String firstName2, String lastName2) {
		super();
		this.firstName = firstName2;
		this.lastName = lastName2;
	}

	/**
	 *
	 * @return ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 *
	 * @return gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 *
	 * @param gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 *
	 * @return yearOfBirth
	 */
	public int getYearOfBirth() {
		return yearOfBirth;
	}

	/**
	 *
	 * @param yearOfBirth
	 */
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	/**
	 *
	 * @param iD
	 */
	public void setID(String iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 *
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 *
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 *
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Person other = (Person) obj;
		return Objects.equals(ID, other.ID);
	}

	@Override
	public String toString() {
		return "ID= " + ID + ", firstName= " + firstName + ", lastName=" + lastName + ", phoneNumber= " + phoneNumber;
	}

	/**
	 *
	 */
	@Override
	public int compareTo(Person prsn) {
		Integer prsnID1 = Integer.parseInt(this.getID());
		Integer prsnID2 = Integer.parseInt(prsn.getID());
		if (prsnID1 > prsnID2) {
			return 1;
		} else if (prsnID1 == prsnID2) {
			return 0;
		}
		return -1;

	}

}
