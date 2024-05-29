package model;

import java.io.Serializable;
import java.util.Objects;

import utils.Color;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class VehicleTransportation implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private static int counter = Factory.getInstance().getCarCounter();
	private String licensePlate;
	private double price;
	private double costOfManufacturing;
	private Color color;
	private int yearOfManufacture;
	private double engineCapacity;
	private int pollutionLevel;

	/**
	 * VehicleTransportation constructor
	 *
	 * @param price
	 * @param costOfManufacturing
	 * @param color
	 * @param yearOfManufacture
	 * @param engineCapacity
	 * @param pollutionLevel
	 */
	public VehicleTransportation(double price, double costOfManufacturing, Color color, int yearOfManufacture,
			double engineCapacity, int pollutionLevel) {
		this.licensePlate = licensePlate + counter;
		this.price = price;
		this.costOfManufacturing = costOfManufacturing;
		this.color = color;
		this.yearOfManufacture = yearOfManufacture;
		this.engineCapacity = engineCapacity;
		this.pollutionLevel = pollutionLevel;
		counter = Factory.getInstance().getCarCounter();
		Factory.getInstance().addCarCounter();
	}

	/**
	 * Partial constructor
	 * @param licensePlate
	 */
	public VehicleTransportation(String licensePlate) {
		super();
		this.licensePlate = licensePlate;
	}

	/**
	 *
	 * @return int counter
	 */
	public static int getCounter() {
		return counter;
	}

	/**
	 *
	 * @param counter
	 */
	public static void setCounter(int counter) {
		VehicleTransportation.counter = counter;
	}

	/**
	 *
	 * @return String licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 *
	 * @param licensePlate
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 *
	 * @return double price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 *
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 *
	 * @return double costOfManufacturing
	 */
	public double getCostOfManufacturing() {
		return costOfManufacturing;
	}

	/**
	 *
	 * @param costOfManufacturing
	 */
	public void setCostOfManufacturing(double costOfManufacturing) {
		this.costOfManufacturing = costOfManufacturing;
	}

	/**
	 *
	 * @return Color color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 *
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 *
	 * @return int yearOfManufacture
	 */
	public int getYearOfManufacture() {
		return yearOfManufacture;
	}

	/**
	 *
	 * @param yearOfManufacture
	 */
	public void setYearOfManufacture(int yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	/**
	 *
	 * @return double engineCapacity
	 */
	public double getEngineCapacity() {
		return engineCapacity;
	}

	/**
	 *
	 * @param engineCapacity
	 */
	public void setEngineCapacity(double engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	/**
	 *
	 * @return int pollutionLevel
	 */
	public int getPollutionLevel() {
		return pollutionLevel;
	}

	/**
	 *
	 * @param pollutionLevel
	 */
	public void setPollutionLevel(int pollutionLevel) {
		this.pollutionLevel = pollutionLevel;
	}

	public void describeSpecialProperties() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		VehicleTransportation other = (VehicleTransportation) obj;
		return Objects.equals(licensePlate, other.licensePlate);
	}



	@Override
	public String toString() {
		return "licensePlate= " + licensePlate + ", price= " + price + ", yearOfManufacture= " + yearOfManufacture
				+ ", engineCapacity= " + engineCapacity;
	}


}
