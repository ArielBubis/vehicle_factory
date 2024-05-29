package model;

import java.io.Serializable;
import java.util.HashSet;

import utils.Specialization;

/**
 * @author Ariel Bubis 205735749
 */
public class Department implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private int departmentID;
	private Specialization specialization;
	private DepartmentManager departmentManager;
	private HashSet<Employee> allEmployees;

	/**
	 * Department constructor
	 *
	 * @param departmentID
	 * @param specialization
	 */
	public Department(int departmentID, Specialization specialization) {
		super();
		this.specialization = specialization;
		setDepartmentManager(departmentManager);
		allEmployees = new HashSet<>();
		this.departmentID = departmentID;
	}

	/**
	 * @param departmentID
	 */
	public Department(int departmentID) {
		super();
		this.departmentID = departmentID;
	}

	/**
	 * @return the departmentID
	 */
	public int getDepartmentID() {
		return departmentID;
	}

	/**
	 * @param departmentID the departmentID to set
	 */
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	/**
	 *
	 * @return DepartmentManager departmentManager
	 */
	public DepartmentManager getDepartmentManager() {
		return departmentManager;
	}

	/**
	 *
	 * @param departmentManager
	 */
	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;

	}

	/**
	 *
	 * @return  allEmployees
	 */
	public HashSet<Employee> getAllEmployees() {
		return allEmployees;
	}

	/**
	 *
	 * @param allEmployees
	 */
	public void setAllEmployees(HashSet<Employee> allEmployees) {
		this.allEmployees = allEmployees;
	}

	/**
	 * @return Specialization specialisation
	 */
	public Specialization getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization the specialisation to set
	 */
	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Department other = (Department) obj;
		return departmentID == other.departmentID;
	}

	@Override
	public String toString() {
		if (departmentManager==null) {
			return "DepartmentID=" + departmentID + ", Specialization=" + specialization + ", No department manager";
		}else {
			return "DepartmentID=" + departmentID + ", Specialization=" + specialization + ", First name="
					+ departmentManager.getFirstName() + " Last Name= " + departmentManager.getLastName();

		}
	}

	public String toString2() {
		return "DepartmentID=" + departmentID + ", Specialization=" + specialization + ", First name="
				+ departmentManager.getFirstName() + " Last Name= " + departmentManager.getLastName();
	}

	/**
	 *
	 * @param employee
	 * @return boolean - true if removed successfully and false if not
	 * @throws PersonAlreadyExistException - The person already exist in the list
	 * @throws YearOfBirthNotInRange       - The year of birth of the person is not
	 *                                     in the right range
	 */
	public boolean addEmployee(Employee employee) throws PersonAlreadyExistException, YearOfBirthNotInRange {
		if (employee == null) {
			return false;
		}
		// Check if person already exist
		if (allEmployees.contains(employee)) {
			throw new PersonAlreadyExistException(employee.getFirstName(), employee.getLastName());
		}
		// Check if the year of birth is in the right range
		if (employee.getYearOfBirth() < 1900 || employee.getYearOfBirth() > 2022) {
			throw new YearOfBirthNotInRange(employee.getID());
		}
		allEmployees.add(employee);
		return allEmployees.contains(employee);
	}

	/**
	 *
	 * @param employee
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeEmployee(Employee employee) {
		if (allEmployees.contains(employee)) {
			return allEmployees.remove(employee);

		}
		return false;
	}
}
