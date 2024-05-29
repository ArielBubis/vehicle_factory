package model;

/**
 * @author Ariel Bubis
 * @ID 205735749
 */

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import Exceptions.AllDepartmentsCrated;
import utils.Area;
import utils.Gender;
import utils.Specialization;
import utils.TypesInFactory;
import viewUtils.Sound;

public class Factory implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private HashMap<String, Employee> allEmployees;
	private HashMap<String, Customer> allCustomers;
	private HashMap<String, VehicleTransportation> allVehicleTransportation;
	private HashMap<String, Deal> allDeals;
	private HashMap<String, Department> allDepartments;
	private HashMap<String, Employee> usersAndPassword;// key username, value key id and value Employee
	private int carCounter;
	private int dealCounter;
	public Sound sound;
	private static Factory factory;

	private Factory() {
		allEmployees = new HashMap<>();
		allCustomers = new HashMap<>();
		allDeals = new HashMap<>();
		allDepartments = new HashMap<>();
		allVehicleTransportation = new HashMap<>();
		usersAndPassword = new HashMap<>();
		carCounter = 1;
		dealCounter = 1;
		sound = Sound.create();
	}

	/**
	 * @return the sound
	 */
	public Sound getSound() {
		return sound;
	}

	public static Factory create() {
		if (factory == null)
			factory = new Factory();
		return factory;
	}

	public static Factory getInstance() {
		factory = viewUtils.SerializableMethods.factory;
		if (factory == null)
			factory = new Factory();
		return factory;
	}

	// ---------------------------------------

	public int getCarCounter() {
		return carCounter;
	}

	public void addCarCounter() {
		carCounter++;
	}

	public void subCarCounter() {
		carCounter--;
	}

	public int getDealCounter() {
		return dealCounter;
	}

	public void addDealCounter() {
		dealCounter++;
	}

	public void subDealCounter() {
		dealCounter--;
	}
	// ---------------------------------------

	/**
	 * @return the usersAndPassword
	 */
	public HashMap<String, Employee> getUsersAndPassword() {
		return usersAndPassword;
	}

	/**
	 *
	 * @return allEmployees
	 */

	public HashMap<String, Employee> getAllEmployees() {
		return allEmployees;
	}

	/**
	 *
	 * @param allEmployees
	 */
	public void setAllEmployees(HashMap<String, Employee> allEmployees) {
		this.allEmployees = allEmployees;
	}

	/**
	 *
	 * @return allCustomers
	 */
	public HashMap<String, Customer> getAllCustomers() {
		return allCustomers;
	}

	/**
	 *
	 * @param allCustomers
	 */
	public void setAllCustomers(HashMap<String, Customer> allCustomers) {
		this.allCustomers = allCustomers;
	}

	/**
	 *
	 * @return allVehicleTransportation
	 */
	public HashMap<String, VehicleTransportation> getAllVehicleTransportation() {
		return allVehicleTransportation;
	}

	/**
	 *
	 * @param allVehicleTransportation
	 */
	public void setAllVehicleTransportation(HashMap<String, VehicleTransportation> allVehicleTransportation) {
		this.allVehicleTransportation = allVehicleTransportation;
	}

	/**
	 *
	 * @return allDeals
	 */
	public HashMap<String, Deal> getAllDeals() {
		return allDeals;
	}

	/**
	 *
	 * @param allDeals
	 */
	public void setAllDeals(HashMap<String, Deal> allDeals) {
		this.allDeals = allDeals;
	}

	/**
	 *
	 * @return allDepartments
	 */
	public HashMap<String, Department> getAllDepartments() {
		return allDepartments;
	}

	/**
	 *
	 * @param allDepartments
	 */
	public void setAllDepartments(HashMap<String, Department> allDepartments) {
		this.allDepartments = allDepartments;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------
	 * ------------------------------Add Methods------------------------------------
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------
	 */
	/**
	 * Add methods to add object to ArrayLists
	 *
	 * @param obj
	 * @return true if added successfully, false if not
	 *
	 *         The method uses the put function from hashMap Class and send a true
	 *         if succeeded or false if failed Check is the object is not empty and
	 *         if there isn't another object like this in the HashMap
	 *
	 */
	/*
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------
	 */

	/**
	 *
	 * @param car
	 * @return boolean - True if added successfully and false if not
	 * @throws PoullutionLevelNotInRange - Pollution level is not in the right
	 *                                   ranger
	 * @throws IncorrectNumberOfSeats    - Not the right number of seats in car
	 */
	public boolean addCar(Car car) throws PoullutionLevelNotInRange, IncorrectNumberOfSeats {
		if (car == null || allVehicleTransportation.containsKey(car.getLicensePlate())) {
			return false;
		}
		// Check if pollution level is not correct
		if (car.getPollutionLevel() < 1 || car.getPollutionLevel() > 15) {
			throw new PoullutionLevelNotInRange(car.getLicensePlate());
		}
		// Check correct number of seats
		if (car.getNumberOfSeats() > 7) {
			throw new IncorrectNumberOfSeats(car.getLicensePlate());
		}
		allVehicleTransportation.put(car.getLicensePlate(), car);
		return allVehicleTransportation.containsKey(car.getLicensePlate());
	}

	/**
	 *
	 * @param hybridCar
	 * @return boolean - True if added successfully and false if not
	 * @throws PoullutionLevelHybridVehiclesMustBeOne - The pollution level for
	 *                                                hybrid Vehicle is different
	 *                                                then 1
	 * @throws IncorrectNumberOfSeats                 - Not the right number of
	 *                                                seats in car
	 */
	public boolean addHybridCar(HybridCar hybridCar)
			throws IncorrectNumberOfSeats, PoullutionLevelHybridVehiclesMustBeOne {
		if (hybridCar == null || allVehicleTransportation.containsKey(hybridCar.getLicensePlate())) {
			return false;
		}
		// Check correct number of seats
		if (hybridCar.getNumberOfSeats() > 7) {
			throw new IncorrectNumberOfSeats(hybridCar.getLicensePlate());
		}
		// Check if pollution level is not correct
		if (hybridCar.getPollutionLevel() != 1) {
			throw new PoullutionLevelHybridVehiclesMustBeOne(hybridCar.getLicensePlate());
		}
		allVehicleTransportation.put(hybridCar.getLicensePlate(), hybridCar);
		return allVehicleTransportation.containsKey(hybridCar.getLicensePlate());
	}

	/**
	 *
	 * @param van
	 * @return boolean - True if added successfully and false if not
	 * @throws PoullutionLevelNotInRange - Pollution level is not in the right
	 *                                   ranger
	 */
	public boolean addVan(Van van) throws PoullutionLevelNotInRange {
		if (van == null || allVehicleTransportation.containsKey(van.getLicensePlate())) {
			return false;
		}
		// Check if pollution level is not correct
		if (van.getPollutionLevel() < 1 || van.getPollutionLevel() > 15) {
			throw new PoullutionLevelNotInRange(van.getLicensePlate());
		}
		allVehicleTransportation.put(van.getLicensePlate(), van);
		return allVehicleTransportation.containsKey(van.getLicensePlate());
	}

	/**
	 *
	 * @param motorcycle
	 * @return boolean - True if added successfully and false if not
	 * @throws PoullutionLevelNotInRange - Pollution level is not in the right
	 *                                   ranger
	 */
	public boolean addMotorcycle(Motorcycle motorcycle) throws PoullutionLevelNotInRange {
		if (motorcycle == null || allVehicleTransportation.containsKey(motorcycle.getLicensePlate())) {
			return false;
		}
		// Check if pollution level is not correct
		if (motorcycle.getPollutionLevel() < 1 || motorcycle.getPollutionLevel() > 15) {
			throw new PoullutionLevelNotInRange(motorcycle.getLicensePlate());
		}
		allVehicleTransportation.put(motorcycle.getLicensePlate(), motorcycle);
		return allVehicleTransportation.containsKey(motorcycle.getLicensePlate());
	}

	/**
	 *
	 * @param hybridMotorcycle
	 * @return boolean - True if added successfully and false if not
	 * @throws PoullutionLevelHybridVehiclesMustBeOne - The pollution level for
	 *                                                hybrid Vehicle is different
	 *                                                then 1
	 */
	public boolean addHybridMotorcycle(HybridMotorcycle hybridMotorcycle)
			throws PoullutionLevelHybridVehiclesMustBeOne {
		if (hybridMotorcycle == null || allVehicleTransportation.containsKey(hybridMotorcycle.getLicensePlate())) {
			return false;
		}
		// Check if pollution level is not correct
		if (hybridMotorcycle.getPollutionLevel() != 1) {
			throw new PoullutionLevelHybridVehiclesMustBeOne(hybridMotorcycle.getLicensePlate());
		}
		allVehicleTransportation.put(hybridMotorcycle.getLicensePlate(), hybridMotorcycle);
		return allVehicleTransportation.containsKey(hybridMotorcycle.getLicensePlate());
	}

	/**
	 *
	 * @param customer
	 * @return boolean - True if added successfully and false if not
	 * @throws PersonAlreadyExistException - The person already exist in the list
	 * @throws YearOfBirthNotInRange       - The year of birth of the person is not
	 *                                     in the right range
	 */
	public boolean addCustomer(Customer customer) throws PersonAlreadyExistException, YearOfBirthNotInRange {
		if (customer == null) {
			return false;
		}
		// Check if person already exist
		if (allCustomers.containsKey(customer.getID())) {
			throw new PersonAlreadyExistException(customer.getFirstName(), customer.getLastName());
		}
		// Check if the year of birth is in the right range
		if (customer.getYearOfBirth() < 1900 || customer.getYearOfBirth() > 2022) {
			throw new YearOfBirthNotInRange(customer.getID());
		}
		allCustomers.put(customer.getID(), customer);
		return allCustomers.containsKey(customer.getID());
	}

	/**
	 *
	 * @param vipCustomer
	 * @return boolean - True if added successfully and false if not
	 * @throws PersonAlreadyExistException - The person already exist in the list
	 * @throws YearOfBirthNotInRange       - The year of birth of the person is not
	 *                                     in the right range
	 */
	public boolean addVIPCustomer(VIPCustomer vipCustomer) throws PersonAlreadyExistException, YearOfBirthNotInRange {
		if (vipCustomer == null) {
			return false;
		}
		// Check if person already exist
		if (allCustomers.containsKey(vipCustomer.getID())) {
			throw new PersonAlreadyExistException(vipCustomer.getFirstName(), vipCustomer.getLastName());
		}
		// Check if the year of birth is in the right range
		if (vipCustomer.getYearOfBirth() < 1900 || vipCustomer.getYearOfBirth() > 2022) {
			throw new YearOfBirthNotInRange(vipCustomer.getID());
		}
		allCustomers.put(vipCustomer.getID(), vipCustomer);
		return allCustomers.containsKey(vipCustomer.getID());
	}

	/**
	 *
	 * @param employee
	 * @return boolean - True if added successfully and false if not also adds the
	 *         employee id and password to the usersAndPassword arraylist
	 * @throws PersonAlreadyExistException - The person already exist in the list
	 * @throws YearOfBirthNotInRange       - The year of birth of the person is not
	 *                                     in the right range
	 *
	 */
	public boolean addEmployee(Employee employee) throws PersonAlreadyExistException, YearOfBirthNotInRange {
		if (employee == null) {
			return false;
		}
		// Check if person already exist
		if (allEmployees.containsKey(employee.getID())) {
			throw new PersonAlreadyExistException(employee.getFirstName(), employee.getLastName());
		}
		// Check if the year of birth is in the right range
		if (employee.getYearOfBirth() < 1900 || employee.getYearOfBirth() > 2022) {
			throw new YearOfBirthNotInRange(employee.getID());
		}
		allEmployees.put(employee.getID(), employee);

		if (allEmployees.containsKey(employee.getID()) && employee.getDep().addEmployee(employee)) {
			usersAndPassword.put(employee.getFirstName() + employee.getID(), employee);
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param departmentManager
	 * @return boolean - True if added successfully and false if not
	 * @throws PersonAlreadyExistException - The person already exist in the list
	 * @throws YearOfBirthNotInRange       - The year of birth of the person is not
	 *                                     in the right range
	 */
	public boolean addDepartmentManager(DepartmentManager departmentManager)
			throws PersonAlreadyExistException, YearOfBirthNotInRange {
		if ((departmentManager == null) || (departmentManager.getDep().getDepartmentManager() != null)) {
			return false;
		}

		// Check if person already exist
		if (allEmployees.containsKey(departmentManager.getID())) {
			throw new PersonAlreadyExistException(departmentManager.getFirstName(), departmentManager.getLastName());
		}
		// Check if the year of birth is in the right range
		if (departmentManager.getYearOfBirth() < 1900 || departmentManager.getYearOfBirth() > 2022) {
			throw new YearOfBirthNotInRange(departmentManager.getID());
		}

		allEmployees.put(departmentManager.getID(), departmentManager);
		// Add the department manager to the department he manages
		departmentManager.getDep().setDepartmentManager(departmentManager);

		if (allEmployees.containsKey(departmentManager.getID())) {//if added add to users list
			usersAndPassword.put(departmentManager.getFirstName() + departmentManager.getID(), departmentManager);
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param deal
	 * @return boolean - True if added successfully and false if not
	 */
	public boolean addDeal(Deal deal) {
		if (deal == null || allDeals.containsKey(deal.getDealID())) {
			return false;
		}
		allDeals.put(deal.getDealID(), deal);
		allCustomers.get(deal.getCustomer().getID()).addDeal(deal);
		return allDeals.containsKey(deal.getDealID())
				&& allCustomers.get(deal.getCustomer().getID()).getAllDeals().contains(deal);
	}

	/**
	 *
	 * @param department
	 * @return boolean - True if added successfully and false if not
	 * @throws DepartmentAlredyExsist
	 */
	public boolean addDepartment(Department department) throws DepartmentAlredyExsist {
		if (department == null)
			return false;
		if (allDepartments.containsKey(Integer.toString(department.getDepartmentID()))) {
			throw new DepartmentAlredyExsist(department);
		}
		allDepartments.put(Integer.toString(department.getDepartmentID()), department);
		return allDepartments.containsKey(Integer.toString(department.getDepartmentID()));
	}

	/*
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------
	 * -----------------------------Remove Methods----------------------------------
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------
	 */
	/**
	 * Remove methods to remove object from ArrayList
	 *
	 * The methods gets an object, using contains method it check if the hashMap
	 * contains the object if it contains the object it will remove it and return
	 * true if fails to remove returns false Throws exception if the object does not
	 * exist
	 *
	 */

	/**
	 *
	 * @param car
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeCar(Car car) {
		if (car != null && allVehicleTransportation.containsKey(car.getLicensePlate())) {
			allVehicleTransportation.remove(car.getLicensePlate());
			return !allVehicleTransportation.containsKey(car.getLicensePlate());
		}
		return false;

	}

	/**
	 *
	 * @param hybridCar
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeHybridCar(HybridCar hybridCar) {
		if (hybridCar != null && allVehicleTransportation.containsKey(hybridCar.getLicensePlate())) {
			allVehicleTransportation.remove(hybridCar.getLicensePlate());
			return !allVehicleTransportation.containsKey(hybridCar.getLicensePlate());
		}
		return false;

	}

	/**
	 *
	 * @param van
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeVan(Van van) {
		if (van != null && allVehicleTransportation.containsKey(van.getLicensePlate())) {
			allVehicleTransportation.remove(van.getLicensePlate());
			return !allVehicleTransportation.containsKey(van.getLicensePlate());
		}
		return false;

	}

	/**
	 *
	 * @param hybridMotorcycle
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeHybridMotorcycle(HybridMotorcycle hybridMotorcycle) {
		if (hybridMotorcycle != null && allVehicleTransportation.containsKey(hybridMotorcycle.getLicensePlate())) {
			allVehicleTransportation.remove(hybridMotorcycle.getLicensePlate());
			return !allVehicleTransportation.containsKey(hybridMotorcycle.getLicensePlate());
		}
		return false;

	}

	/**
	 *
	 * @param motorocycle
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeMotorcycle(Motorcycle motorocycle) {
		if (motorocycle != null && allVehicleTransportation.containsKey(motorocycle.getLicensePlate())) {
			allVehicleTransportation.remove(motorocycle.getLicensePlate());
			return !allVehicleTransportation.containsKey(motorocycle.getLicensePlate());

		}
		return false;
	}

	/**
	 *
	 * @param customer
	 * @return boolean - true if removed successfully and false if not
	 * @throws PersonNotExistException - The object does not exist in our list
	 */
	public boolean removeCustomer(Customer customer) throws PersonNotExistException {
		// check if person exists
		if ((customer == null) || !allCustomers.containsKey(customer.getID())) {
			throw new PersonNotExistException();
		}
		allCustomers.remove(customer.getID());
		return !allCustomers.containsKey(customer.getID());

	}

	/**
	 *
	 * @param vipCustomer
	 * @return boolean - true if removed successfully and false if not
	 * @throws PersonNotExistException - The object does not exist in our list
	 */
	public boolean removeVIPCustomer(VIPCustomer vipCustomer) throws PersonNotExistException {
		if (vipCustomer == null) {
			throw new PersonNotExistException();
		}
		// check if person exists
		if (!allCustomers.containsKey(vipCustomer.getID())) {
			System.out.println(vipCustomer);
			throw new PersonNotExistException();

		}
		allCustomers.remove(vipCustomer.getID());
		return !allCustomers.containsKey(vipCustomer.getID());
	}

	/**
	 *
	 * @param employee
	 * @return boolean - true if removed successfully and false if not
	 * @throws PersonNotExistException - The object does not exist in our list
	 */
	public boolean removeEmployee(Employee employee) throws PersonNotExistException {
		// check if person exists
		if ((employee == null) || !allEmployees.containsKey(employee.getID())
				|| (employee instanceof DepartmentManager)) {
			throw new PersonNotExistException();
		}
		allDepartments.get(Integer.toString(employee.getDep().getDepartmentID())).removeEmployee(employee);
		allEmployees.remove(employee.getID());
		return !allEmployees.containsKey(employee.getID()) && !allDepartments
				.get(Integer.toString(employee.getDep().getDepartmentID())).getAllEmployees().contains(employee);
	}

	/**
	 *
	 * @param departmentManager
	 * @return boolean - true if removed successfully and false if not
	 * @throws PersonNotExistException - The object does not exist in our list
	 */
	public boolean removeDepartmentManager(DepartmentManager departmentManager) throws PersonNotExistException {
		if ((departmentManager == null) || !allEmployees.containsKey(departmentManager.getID())) {
			throw new PersonNotExistException();

		}
		// Set the department manager to NULL in department
		allDepartments.get(Integer.toString(departmentManager.getDep().getDepartmentID())).setDepartmentManager(null);
		// Remove Department manager from the AllEmployee in the department
		allDepartments.get(Integer.toString(departmentManager.getDep().getDepartmentID()))
				.removeEmployee(departmentManager);
		// Remove the Department manager from allEmployee in the factory
		allEmployees.remove(departmentManager.getID());

		return !allEmployees.containsKey(departmentManager.getID())
				&& (allDepartments.get(Integer.toString(departmentManager.getDep().getDepartmentID()))
						.getDepartmentManager() == null)
				&& !(allDepartments.get(Integer.toString(departmentManager.getDep().getDepartmentID()))
						.getAllEmployees().contains(departmentManager));
	}

	/**
	 *
	 * @param deal
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeDeal(Deal deal) {
		if (deal != null && allDeals.containsKey(deal.getDealID())) {
			if (allCustomers.get(deal.getCustomer().getID()) != null) {
				allCustomers.get(deal.getCustomer().getID()).removeDeal(deal);// Remove the deal from the customer deal
																				// list
			}
			allDeals.remove(deal.getDealID());

			return !allDeals.containsKey(deal.getDealID())
					&& !allCustomers.get(deal.getCustomer().getID()).getAllDeals().contains(deal);
		}
		return false;
	}

	/**
	 *
	 * @param department
	 * @return boolean - true if removed successfully and false if not
	 *
	 */

	public boolean removeDepartment(Department department) {
		HashMap<String, Employee> temp = new HashMap<>();
		if (department != null && allDepartments.containsKey(Integer.toString(department.getDepartmentID()))) {
			for (HashMap.Entry<String, Employee> emp : allEmployees.entrySet()) {
				Employee val = emp.getValue();
				if (!(val.getDep()).equals(department)) {
					temp.put(val.getID(), val);// Fill the temporary HashMap with all the employees that are not from //
												// the department
				}
			}
			allEmployees = temp;
			allDepartments.remove(Integer.toString(department.getDepartmentID()));
			return checkRemovedEmployees(temp, department)
					&& !allDepartments.containsKey(Integer.toString(department.getDepartmentID()));
		}
		return false;
	}

	/**
	 *
	 * @param vt
	 * @return boolean - true if removed successfully and false if not
	 */
	public boolean removeVehicleTeansportation(VehicleTransportation vt) {
		if (vt != null && allVehicleTransportation.containsKey(vt.getLicensePlate())) {
			allVehicleTransportation.remove(vt.getLicensePlate());
			return !allVehicleTransportation.containsKey(vt.getLicensePlate());
		}
		return false;

	}

	/**
	 * Check that there are no employees from the removed department left in the
	 * HashMap
	 *
	 * @param newEmployeeList
	 *
	 * @param department
	 *
	 * @return True if there are no employees from the removed department, false if
	 *         there are
	 */
	private boolean checkRemovedEmployees(HashMap<String, Employee> newEmployeeList, Department department) {
		boolean flag = false;
		for (HashMap.Entry<String, Employee> emp : allEmployees.entrySet()) {
			Employee val1 = emp.getValue();
			if (val1.getDep().equals(department)) {
				flag = false;
			} else {
				flag = true;
			}
		}
		return flag;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------
	 * ---------------------------GetReal methods-----------------------------------
	 * -----------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------
	 */
	/**
	 * Get real methods, the methods gets a String ID and return the Object
	 * according to the ID
	 */

	/**
	 *
	 * @param licensePlate
	 * @return object type Car
	 */
	public Car getRealCar(String licensePlate) {
		if (allVehicleTransportation.containsKey(licensePlate)
				&& (allVehicleTransportation.get(licensePlate) instanceof Car)) {
			return (Car) allVehicleTransportation.get(licensePlate);
		}
		return null;
	}

	/**
	 *
	 * @param licensePlate
	 * @return object type HybridCar
	 */
	public HybridCar getRealHybridCar(String licensePlate) {
		if (allVehicleTransportation.containsKey(licensePlate)
				&& (allVehicleTransportation.get(licensePlate) instanceof HybridCar)) {
			return (HybridCar) allVehicleTransportation.get(licensePlate);
		}
		return null;
	}

	/**
	 *
	 * @param licensePlate
	 * @return object type Van
	 */
	public Van getRealVan(String licensePlate) {
		if (allVehicleTransportation.containsKey(licensePlate)
				&& (allVehicleTransportation.get(licensePlate) instanceof Van)) {
			return (Van) allVehicleTransportation.get(licensePlate);
		}
		return null;
	}

	/**
	 *
	 * @param licensePlate
	 * @return object type HybridMotorcycle
	 */
	public HybridMotorcycle getRealHybridMotorcycle(String licensePlate) {
		if (allVehicleTransportation.containsKey(licensePlate)
				&& (allVehicleTransportation.get(licensePlate) instanceof HybridMotorcycle)) {
			return (HybridMotorcycle) allVehicleTransportation.get(licensePlate);
		}
		return null;
	}

	/**
	 *
	 * @param licensePlate
	 * @return object type Motorcycle
	 */
	public Motorcycle getRealMotorcycle(String licensePlate) {
		if (allVehicleTransportation.containsKey(licensePlate)
				&& (allVehicleTransportation.get(licensePlate) instanceof Motorcycle)) {
			return (Motorcycle) allVehicleTransportation.get(licensePlate);
		}
		return null;
	}

	/**
	 *
	 * @param licensePlate
	 * @return object type VehicleTransportation
	 */
	public VehicleTransportation getRealVehicleTransportation(String licensePlate) {
		if (allVehicleTransportation.containsKey(licensePlate)) {
			return allVehicleTransportation.get(licensePlate);
		}
		return null;
	}

	/**
	 *
	 * @param ID
	 * @return object type Customer
	 */
	public Customer getRealCustomer(String ID) {
		if (allCustomers.containsKey(ID) && (allCustomers.get(ID) instanceof Customer)) {
			return allCustomers.get(ID);
		}
		return null;
	}

	/**
	 *
	 * @param ID
	 * @return object type VIPCustomer
	 */
	public VIPCustomer getRealVIPCustomer(String ID) {
		if (allCustomers.containsKey(ID) && (allCustomers.get(ID) instanceof VIPCustomer)) {
			return (VIPCustomer) allCustomers.get(ID);
		}
		return null;
	}

	/**
	 *
	 * @param ID
	 * @return object type Employee
	 */
	public Employee getRealEmployee(String ID) {
		if (allEmployees.containsKey(ID) && (allEmployees.get(ID) instanceof Employee)) {
			return allEmployees.get(ID);
		}
		return null;
	}

	/**
	 *
	 * @param ID
	 * @return object type DepartmentManager
	 */
	public DepartmentManager getRealDepartmentManager(String ID) {
		if (allEmployees.containsKey(ID) && (allEmployees.get(ID) instanceof DepartmentManager)) {
			return (DepartmentManager) allEmployees.get(ID);
		}
		return null;
	}

	/**
	 *
	 * @param ID
	 * @return object type Deal
	 */
	public Deal getRealDeal(String ID) {
		if (allDeals.containsKey(ID) && (allDeals.get(ID) instanceof Deal)) {
			return allDeals.get(ID);
		}
		return null;
	}

	/**
	 *
	 * @param ID
	 * @return object type Department
	 */
	public Department getRealDepartment(int ID) {
		String strID = Integer.toString(ID);
		if (allDepartments.containsKey(strID)) {
			return allDepartments.get(strID);
		}
		return null;
	}

	/*
	 * A-------A-------A-------A-------A-------A-------A-------A-------A-------A----
	 * -\-----/-\-----/-\-----/-\-----/-\-----/-\-----/-\-----/-\-----/-\-----/-\---
	 * --\---/---\---/---\---/---\--Query Methods\---/---\---/---\---/---\---/---\--
	 * ---\-/-----\-/-----\-/-----\-/-----\-/-----\-/-----\-/-----\-/-----\-/-----\-
	 * ----V-------V-------V-------V-------V-------V-------V-------V-------V-------V
	 */

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ---------------------------customersByArea-----------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */

	/**
	 * Method that returns HashMap The key is an area from utils.Area and the value
	 * is an ArrayList of all the customers who live on that area
	 *
	 * @return customerByAreaHashMap
	 */
	public HashMap<Area, ArrayList<Customer>> customersByArea() {
		HashMap<Area, ArrayList<Customer>> customerByAreaHashMap = new HashMap<>();
		for (utils.Area area : utils.Area.values()) {
			// iterate on all the Area enums
			ArrayList<Customer> customersByAreaArrayList = new ArrayList<>();
			// Array list for the customer from the same area
			for (HashMap.Entry<String, Customer> cstmr : allCustomers.entrySet()) {
				// iterate on all the Customers on allCustomers hashmap
				Customer val = cstmr.getValue();
				// Get the value of the customer
				if (val.getArea() == area) {
					// Check if the costumer is from the Area
					customersByAreaArrayList.add(val);// add to the ArrayList
				}
			}
			customerByAreaHashMap.put(area, customersByAreaArrayList);
			// Add to the hashmap
		}
		return customerByAreaHashMap;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ---------------------personsByGenderAndYearOfBirth---------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */

	/**
	 * Method that returns HashMap The key is a gender from utils.Gender and the
	 * value is an HashMap where the key is the year of birth of a person and the
	 * value is all the people from the same gender as the person and the same year
	 * of birth
	 *
	 * @return personsByGenderAndYearOfBirthHashMap
	 */
	public HashMap<Gender, HashMap<Integer, ArrayList<Person>>> personsByGenderAndYearOfBirth() {
		HashMap<String, Person> allPerson = new HashMap<>();
		allPerson.putAll(allCustomers);
		allPerson.putAll(allEmployees);
		// Add all the people to one hashMap
		HashMap<Gender, HashMap<Integer, ArrayList<Person>>> personsByGenderAndYearOfBirthHashMap = new HashMap<>();

		for (utils.Gender gender : utils.Gender.values()) {
			// iterate on all the Gender enums
			HashMap<Integer, ArrayList<Person>> allSameGender = new HashMap<>();
			// Hashmap list for the person with the key is his year of birth and
			// the value is array of all the people with the gender and the same year of
			// birth

			for (HashMap.Entry<String, Person> prsn : allPerson.entrySet()) {
				// iterate on all the people on allPerson hashmap
				Person val = prsn.getValue();
				// Array list of the people with the same gender made with a helping method
				// buildArrayListByGender
				if (val.getGender() == gender) {
					ArrayList<Person> personsByBirthYearArrayList = buildArrayListByGenderAndYearOfBirth(
							val.getGender(), val.getYearOfBirth(), allPerson);
					// If the same gender we are iterating on
					allSameGender.put(val.getYearOfBirth(), personsByBirthYearArrayList);
					// add to the hashMap with the key is the year of birth
					// and the value is the arrlist of the people with the same gender
				}
			}
			personsByGenderAndYearOfBirthHashMap.put(gender, allSameGender);
		}

		return personsByGenderAndYearOfBirthHashMap;
	}

	/**
	 * The method returns arrayList with all the persons of the same gender by
	 * iterating on allPerson and checking if it equals to the gender and year of
	 * birth
	 *
	 * @param gender
	 * @param year
	 * @param allPerson
	 * @return prsnByGndrAL - ArrayList with all the persons of the same gender and
	 *         year of birth
	 *
	 */
	public ArrayList<Person> buildArrayListByGenderAndYearOfBirth(Gender gender, int year,
			HashMap<String, Person> allPerson) {
		ArrayList<Person> prsnByyearAL = new ArrayList<>();
		for (HashMap.Entry<String, Person> prsn : allPerson.entrySet()) {
			Person val = prsn.getValue();
			if (val.getYearOfBirth() == year && val.getGender() == gender) {
				prsnByyearAL.add(val);
			}
		}
		return prsnByyearAL;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * -------------------countOfVehiclesTransportationByColor----------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */

	/**
	 * Method that creates a HashMap where the key is a util.color and the value is
	 * number of the vehicles by the same colour that were sold
	 *
	 * @return vTByColorhHashMap - hashMap where the key is a util.color and the
	 *         value is number of the vehicles by the same colour that were sold
	 */
	public HashMap<utils.Color, Integer> countOfVehiclesTransportationByColor() {
		HashMap<utils.Color, Integer> vTByColorhHashMap = new HashMap<>();
		int count = 0;
		for (utils.Color color : utils.Color.values()) {
			// iterate on all the Color enums
			count = 0;// reset counter
			for (HashMap.Entry<String, Deal> deal : allDeals.entrySet()) {
				// iterate on all the deal on allDeals HashMap
				Deal val = deal.getValue();
				HashSet<VehicleTransportation> allCarsOfDeal = val.getAllVehicleTransportation();
				// HashSet of all the cars from a specific deal from all deals
				for (VehicleTransportation car : allCarsOfDeal) {
					// iterate on the cars from the deal and check if equals to the color
					if (car.getColor() == color) {
						count++;
					}
				}
				vTByColorhHashMap.put(color, count);
			}
		}
		return vTByColorhHashMap;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ------------------------avgPollutionLevelOfDeal------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * Methods that gets a Deal object and checks the average pollution level of the
	 * cars in the deal.allCars list
	 *
	 * @param deal
	 *
	 * @return sumOfPullution - Average pollution level of a deal
	 */
	public double avgPollutionLevelOfDeal(Deal deal) {
		double sumOfPollution = 0;
		if (deal != null) {
			sumOfPollution = deal.getAllVehicleTransportation().stream().mapToDouble(c -> c.getPollutionLevel()).sum();
		}
		return (sumOfPollution / deal.getAllVehicleTransportation().size());
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ---------------------idOfDealWithMinAvgPollutionLevel------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * Method to find the deal with the highest deal id, with the lowest average
	 * pollution
	 *
	 * @return String maxDeal.getDealID() - The ID of a deal with the highest deal
	 *         id, with the lowest average pollution
	 */
	public String idOfDealWithMinAvgPollutionLevel() {
		Deal maxDeal = new Deal("0");
		// Create temporary deal with id set to 0 using the Partial constructor
		double minPollution = 16;
		// The max level of pollution set to every Vehicle is 15 so
		// choosing a number higher then 15 should be a good starting point
		for (HashMap.Entry<String, Deal> deal : allDeals.entrySet()) {
			Deal val = deal.getValue();
			if (avgPollutionLevelOfDeal(val) <= minPollution) {
				// Check if the pollution is lower or equals then the minimum pollution
				if (avgPollutionLevelOfDeal(val) == minPollution) {
					if (Integer.parseInt(val.getDealID()) > Integer.parseInt(maxDeal.getDealID())) {
						// If it equals then set the higher ID number is the deal with the minimum
						// pollution level
						minPollution = avgPollutionLevelOfDeal(val);
						maxDeal = val;
					}
				} else {
					minPollution = avgPollutionLevelOfDeal(val);
					maxDeal = val;
				}
			}
		}

		return maxDeal.getDealID();
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ---------------------profitPerVehiclesTransportation-------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * Method to calculate price of car for customer(Depends if VIP or not) minus
	 * the cost of manufacturing using lambda and the functional interface
	 * ProfitPerVTCalc
	 */

	public ProfitPerVT profit = vt -> (vt.getPrice() - vt.getCostOfManufacturing());

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * -------------------------------totalProfit-----------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 *
	 * Method to calculate the total profit of the factory it gets the info of all
	 * the deals made and subtracts the cost of manufacture of every car produced
	 * and sole
	 *
	 * @return int totalProfit - Total profit of factory
	 */
	public int totalProfit() {
		double totalProfit = 0;
		totalProfit = allDeals.values().stream().mapToDouble(d -> (d.getTotalDealPrice()
				- d.getAllVehicleTransportation().stream().mapToDouble(vt -> vt.getCostOfManufacturing()).sum())).sum();
		return (int) totalProfit;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ----------------------relativePrecentageOfHybrid-----------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * Method to calculate the relative percentage of Hybrid vehicle by dividing the
	 * number of number of hybridVehicles with the size of the entire cars list
	 *
	 * @return double counter - relative percentage of Hybrid vehicle
	 */
	public double relativePrecentageOfHybrid() {
		return (countHybridVehicles() / (allVehicleTransportation.size()));
	}

	/**
	 *
	 * @return number of hybrid Vehicles in the allVehicleTransportation list
	 */
	private double countHybridVehicles() {
		int counterOfHybridCars = 0;
		for (HashMap.Entry<String, VehicleTransportation> vt : allVehicleTransportation.entrySet()) {
			VehicleTransportation val = vt.getValue();
			if (val instanceof HybridCar || val instanceof HybridMotorcycle) {
				counterOfHybridCars++;
			}
		}
		return counterOfHybridCars;

	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * -------------avgPollutionLevelOfAllVehiclesTransportation--------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * Method to calculate the average pollution level of the vehicles manufactured
	 * in the factory
	 *
	 * @return double avgPollution - Average pollution level
	 *
	 */
	public double avgPollutionLevelOfAllVehiclesTransportation() {
		double avgPollution = 0;
		for (HashMap.Entry<String, VehicleTransportation> vt : allVehicleTransportation.entrySet()) {
			VehicleTransportation val = vt.getValue();
			avgPollution += val.getPollutionLevel();
		}

		return avgPollution / allVehicleTransportation.size();
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ----------------------------isGlobalStandard---------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	final double globalPollution = 5;
	final double globalHybrid = 40;

	/**
	 *
	 * method that check if the factory holds the global standard The average of
	 * hybrid vehicles is at least 40% The average pollution level of all the cars
	 * is equals or less then 5
	 *
	 * @return boolean True if holds the global standard false if not
	 */
	public boolean isGlobalStandard() {
		return relativePrecentageOfHybrid() > globalHybrid
				&& avgPollutionLevelOfAllVehiclesTransportation() <= globalPollution;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ---------------howManyHybridVehiclesTransportationNeeded---------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 *
	 * The method runs a While loop as long as we don't have the global standard, it
	 * keeps adding 1 hybrid vehicle to the count
	 *
	 * every iteration we check the average pollution level by adding another value
	 * to the average using the formula:
	 *
	 * [((Old average value)*(number of values)) + (new value)]/[(number of
	 * values)+1]
	 *
	 * and calculate the relative percentage of Hybrid vehicle by dividing the new
	 * number of hybrid vehicles with the size of the new new number of manufactured
	 * vehicles
	 *
	 * @return int numofHybridVehiclesNeeded - Number of Hybrid Vehicles Needed to
	 *         get the global standard
	 */
	public int howManyHybridVehiclesTransportationNeeded() {
		int numofHybridVehiclesNeeded = 0;// Number of hybrid vehicles needed
		if (isGlobalStandard()) {
			return numofHybridVehiclesNeeded;
		}
		double pollutionLevel = avgPollutionLevelOfAllVehiclesTransportation();
		double prcntOfHybrid = relativePrecentageOfHybrid();
		double numOfHybridVehiclesNow = countHybridVehicles(); // Number of hybrid Vehicles now
		int allVehicle = allVehicleTransportation.size();

		while (prcntOfHybrid <= globalHybrid && pollutionLevel > globalPollution) {
			numOfHybridVehiclesNow++;
			allVehicle++;
			numofHybridVehiclesNeeded++;
			pollutionLevel = (((pollutionLevel * (allVehicle - 1)) + 1) / allVehicle);
			prcntOfHybrid = numOfHybridVehiclesNow / allVehicle;
		}
		return numofHybridVehiclesNeeded;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * -------------------appointmentOfNewDepartmentManager-------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * Method to add a appoint a new Department manager from the employee list first
	 * we check if the department already has a manager, then we check who is the
	 * employee with the most seniority and appoint him as the new manager
	 *
	 *
	 * @param department
	 * @return boolean if the Department manager was added successfully
	 */
	public boolean appointmentOfNewDepartmentManager(Department department) {

		if (department.getDepartmentManager() != null) {
			// Check if the department has a manager, return false for failed appointment of
			// new manager if found a manager
			return false;
		}

		// Find the most senior employee in the Department
		Employee employeeToManager = findSeniorEmployee(department);

		// Create date object
		java.util.Date dateOfStartWork = getDateOfStartWork();

		return addNewDepManager(employeeToManager, department, dateOfStartWork);
	}

	/*

	 */
	/**
	 * iterate through the list of all employees on this department and find the
	 * employee with the most seniority using Date.Before
	 *
	 * @param department
	 * @return Employee seniorEmployee - Senior Employee
	 */
	public Employee findSeniorEmployee(Department department) {
		Employee seniorEmployee = new Employee("temp");
		for (HashMap.Entry<String, Employee> employee : allEmployees.entrySet()) {
			Employee val = employee.getValue();
			if (val.getDep().getDepartmentID() == department.getDepartmentID()) {
				if (!(val instanceof DepartmentManager)) {
					if (seniorEmployee.getID() == "temp") {
						// Set employeeToManager as the first Employee we get in the list
						seniorEmployee = val;
					} else {
						if (val.getDateOfStartWork().before(seniorEmployee.getDateOfStartWork())) {
							// check which employee has the most seniority
							seniorEmployee = val;
						}
					}
				}
			}

		}
		return seniorEmployee;
	}

	/**
	 * Create appointment date
	 *
	 * @return dateOfStartWork
	 */
	private java.util.Date getDateOfStartWork() {
		java.util.Date dateOfStartWork = null;
		try {
			dateOfStartWork = new SimpleDateFormat("dd/MM/yyyy").parse("1/6/2022");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateOfStartWork;
	}

	/**
	 * Remove employee from allEmployees, create new DepartmentManager object with
	 * his details and add to the department add the new DepartmentManager back to
	 * allEmployees but as a DepartmentManager
	 *
	 * @param employeeToManager
	 * @param department
	 * @param dateOfStartWork
	 * @return boolean - true is successful false if failed
	 */
	private boolean addNewDepManager(Employee employeeToManager, Department department, Date dateOfStartWork) {

		allEmployees.remove(employeeToManager.getID());

		// Create Department manger object with the info from the Senior Employee
		DepartmentManager newManager = new DepartmentManager(employeeToManager.getID(),
				employeeToManager.getFirstName(), employeeToManager.getLastName(), employeeToManager.getPhoneNumber(),
				employeeToManager.getGender(), employeeToManager.getDateOfStartWork(),
				(employeeToManager.getSalary() * 1.5), department, employeeToManager.getYearOfBirth(), dateOfStartWork,
				1500, employeeToManager.getPass(), employeeToManager.getPhoto(), employeeToManager.getUserName());

		department.setDepartmentManager(newManager);

		allEmployees.put(employeeToManager.getID(), newManager);

		if (department.getDepartmentManager() != null
				&& allEmployees.get(newManager.getID()) instanceof DepartmentManager) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * -------------------------------allEmployees----------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * Method that gets a number (k), build an Arraylist of k employees and using
	 * the native compare (By ID) the arraylist is sorted them from lowest id to
	 * highest
	 *
	 * @param k - Size of list to return
	 * @return employeesArrayList - Sorted ArrayList of Employee
	 */
	public List<Employee> allEmployees(int k) {
		List<Employee> employeesArrayList = allEmployees.values().stream().sorted().collect(Collectors.toList());

		if (k < employeesArrayList.size()) {
			return employeesArrayList.subList(0, k);
		}
		return employeesArrayList;
	}
	/*
	 * Collections.sort(employeesArrayList); if (k < employeesArrayList.size()) {
	 * return employeesArrayList.subList(0, k); }
	 */

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * -------------------------------allCustomers----------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * Method builds an Arraylist of all customers and using compare it sorts them
	 * from lowest id to highest
	 *
	 * @return customerArrayList - Sorted ArrayList of Customers
	 */
	public ArrayList<Customer> allCustomers() {
		ArrayList<Customer> customerArrayList = new ArrayList<>(allCustomers.values());
		Collections.sort(customerArrayList);
		return customerArrayList;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * -------------------------allVehicleTransportations---------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * This method return a sorted TreeList of VehicleTransportation using
	 * VehicleTranspotPoulutionCompare class for sorting, the treeSet is sorted by
	 * the pollution level of a Vehicle from the highest pollution level to the
	 * lowest, the secondary sort is the price of the car (The last sort level is by
	 * the natural sort,the license plate)
	 *
	 * @return allVehicleTransportationsSorted - Sorted TreeSet of vehicles
	 */
	public TreeSet<VehicleTransportation> allVehicleTransportations() {
		TreeSet<VehicleTransportation> allVehicleTransportationsSorted = VehicleTranspotPoulutionCompare
				.buildTreeSet(allVehicleTransportation);
		return allVehicleTransportationsSorted;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ------------------------------allCustomersCmp--------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * This method returns a sorted TreeList of Customer using CustomerDealCompare
	 * class for sorting, the treeSet is sorted by the number of deals of customer
	 * from the lowest deal count to the highest, the secondary sort is the number
	 * of cars the customer is buying (The last sort level is by the natural
	 * sort,the ID)
	 *
	 *
	 * @return allCustomersSorted - Sorted TreeSet of Customers
	 */
	public TreeSet<Customer> allCustomersCmp() {
		TreeSet<Customer> allCustomersSorted = CustomerDealCompare.buildTreeSet(allCustomers);
		return allCustomersSorted;
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * -----------------------------AllBestsDepManger-------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */
	/**
	 * This method returns a sorted ArrayList of DepartmentManager using
	 * DepManagerCompare class for sorting, the ArrayList is sorted by the salary of
	 * manager from the lowest salary to the highest, the secondary sort is the
	 * appointment date (The last sort level is by the natural sort,the ID)
	 *
	 * @return allBestsDepMangerSorted - Sorted ArrayList of Department managers
	 */

	public ArrayList<DepartmentManager> AllBestsDepManger() {
		Comparator<DepartmentManager> depComparator = new DepManagerCompare();
		ArrayList<DepartmentManager> allBestsDepMangerSorted = allEmployees.values().stream()
				.filter(person -> person instanceof DepartmentManager).map(person -> (DepartmentManager) person)
				.sorted(depComparator).collect(Collectors.toCollection(ArrayList::new));

		return allBestsDepMangerSorted;
	}

	/**
	 * This method returns a sorted List of k deals using BestDealCompare class for
	 * sorting, the List is sorted by the lowest pollution average,the average is
	 * calculated using ScoreCalulator object
	 *
	 * @param k - Size of list to return
	 * @param s - ScoreCalculator type object
	 * @return bestDealListSorted - Sorted list of deals
	 */
	public List<Deal> getBestsDeal(int k, ScoreCalculator s) {
		ArrayList<Deal> bestDealListSorted = BestDealCompare.buildBestDealList(allDeals, s);
		if (k < bestDealListSorted.size()) {
			return bestDealListSorted.subList(0, k);
		}
		return bestDealListSorted;

	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ---------------------------------For GUI use---------------------------------
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */ /**
		 * Create list of the specialisations(From departments), for gui use
		 *
		 * @return specializationsList (specialisations that don't exist yet)
		 * @throws AllDepartmentsCrated
		 */
	public List<Specialization> specializationsList(Boolean checkCreated) throws AllDepartmentsCrated {
		// List of departments saved
		List<Specialization> allSpecsInFactory = Factory.getInstance().getAllDepartments().values().stream()
				.map(dep -> dep.getSpecialization()).toList();
		// list to Send
		ArrayList<Specialization> sendList = new ArrayList<>();

		// Add elements to list
		for (Specialization s : Specialization.values()) {
			if (!allSpecsInFactory.contains(s)) {// Add only specialisations that doesn't exist in the factory yet
				sendList.add(s);
			}
		}
		if (sendList.isEmpty() && checkCreated) {// If the list is empty, the boolean is used in case we need to check
			throw new AllDepartmentsCrated();
		} else {
			return sendList;
		}

	}

	/**
	 * Create HashMap that holds all the types of map in the factory and the keys of
	 * the type
	 *
	 * @return mapOfAllMaps
	 */
	public HashMap<TypesInFactory, List<String>> mapOfAllMaps() {
		HashMap<TypesInFactory, List<String>> allMaptsToMap = new HashMap<>();

		List<String> arrOfEmployeekeys = getAllEmployees().values().stream()
				.filter(Predicate.not(e -> e instanceof DepartmentManager))
				.map(e -> (e.getID() + "| " + e.getClass().getSimpleName() + ": " + e.toString())).sorted().toList();

		List<String> arrOfDepMankeys = getAllEmployees().values().stream().filter(dm -> dm instanceof DepartmentManager)
				.map(dm -> (dm.getID() + "| " + dm.getClass().getSimpleName() + ": " + dm.toString())).sorted()
				.toList();

		List<String> arrOfCustomerskeys = getAllCustomers().values().stream()
				.map(c -> c.getID() + "| " + c.getClass().getSimpleName() + "| " + c.toString()).sorted().toList();

		List<String> arrOfVTkeys = getAllVehicleTransportation().values().stream().map(
				vt -> vt.getLicensePlate() + "|Vehicle Type:" + vt.getClass().getSimpleName() + " " + vt.toString())
				.sorted().toList();

		List<String> arrOfDealkeys = getAllDeals().values().stream().map(d -> d.getDealID() + "| customerID= "
				+ d.getCustomer() + ", dealDate= " + d.getDealDate() + " , totalDealPrice= " + d.getTotalDealPrice())
				.sorted().toList();

		List<String> arrOfDepkeys = getAllDepartments().values().stream()
				.map(dep -> dep.getDepartmentID() + "| " + dep.toString()).sorted().toList();

		if (!arrOfEmployeekeys.isEmpty())
			allMaptsToMap.put(TypesInFactory.All_Employees, arrOfEmployeekeys);

		if (!arrOfDepMankeys.isEmpty())
			allMaptsToMap.put(TypesInFactory.All_Department_Managers, arrOfDepMankeys);

		if (!arrOfCustomerskeys.isEmpty())
			allMaptsToMap.put(TypesInFactory.All_Customers, arrOfCustomerskeys);

		if (!arrOfVTkeys.isEmpty())
			allMaptsToMap.put(TypesInFactory.All_Vehicles, arrOfVTkeys);

		if (!arrOfDealkeys.isEmpty())
			allMaptsToMap.put(TypesInFactory.All_Deals, arrOfDealkeys);

		if (!arrOfDepkeys.isEmpty())
			allMaptsToMap.put(TypesInFactory.All_Departments, arrOfDepkeys);

		return allMaptsToMap;

	}

}
