package model;

import java.io.Serializable;

import utils.Color;
import utils.MyFileLogWriter;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class Car extends VehicleTransportation implements NonHybridInterface, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private int numberOfSeats;
	private boolean isConvertible;

	/**
	 * Car Constructor
	 *
	 * @param price               price
	 * @param costOfManufacturing cost of manufacturing
	 * @param color               color
	 * @param yearOfManufacture   year of manufacture
	 * @param engineCapacity      Engine Capacity
	 * @param pollutionLevel      Pollution level
	 * @param numberOfSeats       Number of seats
	 * @param isConvertible       is convertible
	 */
	public Car(double price, double costOfManufacturing, Color color, int yearOfManufacture, double engineCapacity,
			int pollutionLevel, int numberOfSeats, boolean isConvertible) {
		super(price, costOfManufacturing, color, yearOfManufacture, engineCapacity, pollutionLevel);
		setLicensePlate("c" + getCounter());
		this.numberOfSeats = numberOfSeats;
		this.isConvertible = isConvertible;
	}

	/**
	 *
	 * @param licensePlate
	 */
	public Car(String licensePlate) {
		super(licensePlate);
	}

	/**
	 *
	 * @return int numberOfSeats
	 */
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	/**
	 *
	 * @param numberOfSeats
	 */
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	/**
	 *
	 * @return boolean - true if convertible false if not
	 */
	public boolean isConvertible() {
		return isConvertible;
	}

	/**
	 *
	 * @param isConvertible
	 */
	public void setConvertible(boolean isConvertible) {
		this.isConvertible = isConvertible;
	}

	@Override
	public String toString() {
		return super.toString() + " numberOfSeats= " + numberOfSeats;
	}

	@Override
	public void describeSpecialProperties() {
		MyFileLogWriter.println("number of seats <" + numberOfSeats + ">" + "is convertible <" + isConvertible + ">");
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * Method to upgrade the car engine capacity
	 */
	@Override
	public void upgrade() {
		setEngineCapacity(getEngineCapacity() + getEngineCapacity() / 10);
	}



}
