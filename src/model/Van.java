package model;

import java.io.Serializable;

import utils.Color;
import utils.MyFileLogWriter;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class Van extends VehicleTransportation implements NonHybridInterface,Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private double trunkSize;
	private double maxWeightCarrying;

	/**
	 * Van constructor
	 *
	 * @param price
	 * @param costOfManufacturing
	 * @param color
	 * @param yearOfManufacture
	 * @param engineCapacity
	 * @param pollutionLevel
	 * @param trunkSize
	 * @param maxWeightCarrying
	 */
	public Van(double price, double costOfManufacturing, Color color, int yearOfManufacture, double engineCapacity,
			int pollutionLevel, double trunkSize, double maxWeightCarrying) {
		super(price, costOfManufacturing, color, yearOfManufacture, engineCapacity, pollutionLevel);
		this.trunkSize = trunkSize;
		this.maxWeightCarrying = maxWeightCarrying;
		setLicensePlate("v" + getCounter());
	}

	/**
	 * Partial constructor
	 * @param licensePlate
	 */
	public Van(String licensePlate) {
		super(licensePlate);
	}

	/**
	 *
	 * @return trunkSize
	 */
	public double getTrunkSize() {
		return trunkSize;
	}

	/**
	 *
	 * @param trunkSize
	 */
	public void setTrunkSize(double trunkSize) {
		this.trunkSize = trunkSize;
	}

	/**
	 *
	 * @return maxWeightCarrying
	 */
	public double getMaxWeightCarrying() {
		return maxWeightCarrying;
	}

	/**
	 *
	 * @param maxWeightCarrying
	 */
	public void setMaxWeightCarrying(double maxWeightCarrying) {
		this.maxWeightCarrying = maxWeightCarrying;
	}

	@Override
	/**
	 * equals methods calls the father equals method
	 *
	 */
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	/**
	 * @return String with the description of the object
	 */
	public String toString() {
		return super.toString() + " trunkSize= " + trunkSize;
	}

	@Override
	public void describeSpecialProperties() {
		MyFileLogWriter.println("trunk size <" + trunkSize + " > max weight carrying <" + maxWeightCarrying + "> ");

	}

	/**
	 * Method to upgrade the car engine capacity
	 */
	@Override
	public void upgrade() {
		setEngineCapacity(getEngineCapacity() + getEngineCapacity() / 0.05);
	}


}
