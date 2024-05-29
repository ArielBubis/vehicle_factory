package model;

import java.io.Serializable;
/**
 * @author Ariel Bubis 205735749
 *
 */
import java.util.Date;

import javax.swing.ImageIcon;

import utils.Gender;

public class Employee extends Person implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private Date dateOfStartWork;
	private Double salary;
	private Department dep;
	private String pass;
	private ImageIcon photo;
	private String userName;

	/**
	 *
	 * Employee Constructor
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
	 * @param pass
	 * @param photoPath
	 * @param userName
	 */
	public Employee(String iD, String firstName, String lastName, String phoneNumber, Gender gender,
			Date dateOfStartWork, Double salary, Department dep, int yearOfBirth, String pass, ImageIcon photoPath,
			String userName) {
		super(iD, firstName, lastName, phoneNumber, gender, yearOfBirth);
		this.dateOfStartWork = dateOfStartWork;
		this.salary = salary;
		this.dep = dep;
		this.pass = pass;
		this.photo = photoPath;
		this.userName = userName;
	}

	/**
	 * Partial constructor
	 *
	 * @param iD
	 */
	public Employee(String iD) {
		super(iD);
	}

	/**
	 *
	 * Partial constructor
	 *
	 * @param firstName
	 * @param lastName
	 */
	public Employee(String firstName, String lastName) {
		super(firstName, lastName);
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(ImageIcon photo) {
		this.photo = photo;
	}

	/**
	 * @return the photoPath
	 */
	public ImageIcon getPhoto() {
		return photo;
	}

	/**
	 * @param photoPath the photoPath to set
	 */
	public void setPhotoPath(ImageIcon photoPath) {
		this.photo = photoPath;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 *
	 * @return Date dateOfStartWork
	 */
	public Date getDateOfStartWork() {
		return dateOfStartWork;
	}

	/**
	 *
	 * @param dateOfStartWork
	 */
	public void setDateOfStartWork(Date dateOfStartWork) {
		this.dateOfStartWork = dateOfStartWork;
	}

	/**
	 *
	 * @return double salary
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 *
	 * @param salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 *
	 * @return Department dep
	 */
	public Department getDep() {
		return dep;
	}

	/**
	 *
	 * @param dep
	 */
	public void setDep(Department dep) {
		this.dep = dep;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + ", salary= " + salary + ", Department= " + getDep().getDepartmentID()
				+ ", User name= " + userName + ", Password= " + pass;
	}

	@Override
	public int compareTo(Person o) {
		return super.compareTo(o);
	}

}
