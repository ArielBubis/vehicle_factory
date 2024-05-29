package model;

import java.io.Serializable;

import utils.Color;
import utils.MyFileLogWriter;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class Motorcycle extends VehicleTransportation implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private boolean isOffRoad;

	/**
	 * Motorcycle constructor
	 *
	 * @param price
	 * @param costOfManufacturing
	 * @param color
	 * @param yearOfManufacture
	 * @param engineCapacity
	 * @param pollutionLevel
	 * @param isOffRoad
	 */
	public Motorcycle(double price, double costOfManufacturing, Color color, int yearOfManufacture,
			double engineCapacity, int pollutionLevel, boolean isOffRoad) {
		super(price, costOfManufacturing, color, yearOfManufacture, engineCapacity, pollutionLevel);
		this.isOffRoad = isOffRoad;
		setLicensePlate("m" + getCounter());
	}

	/**
	 * Partial constructor
	 *
	 * @param licensePlate
	 */
	public Motorcycle(String licensePlate) {
		super(licensePlate);
	}

	/**
	 *
	 * @return boolean - True is off road false if not
	 */
	public boolean isOffRoad() {
		return isOffRoad;
	}

	/**
	 *
	 * @param isOffRoad
	 */
	public void setOffRoad(boolean isOffRoad) {
		this.isOffRoad = isOffRoad;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + " isOffRoad= " + isOffRoad;
	}

	@Override
	public void describeSpecialProperties() {
		MyFileLogWriter.println("is off road <" + isOffRoad + " >");
	}

}
