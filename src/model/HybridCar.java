package model;

import java.io.Serializable;

import utils.Color;
import utils.MyFileLogWriter;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class HybridCar extends Car implements HybridInterface, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private double batteryCapacity;

	/**
	 * HybridCar constructor
	 *
	 * @param price
	 * @param costOfManufacturing
	 * @param color
	 * @param yearOfManufacture
	 * @param engineCapacity
	 * @param pollutionLevel
	 * @param numberOfSeats
	 * @param isConvertible
	 */
	public HybridCar(double price, double costOfManufacturing, Color color, int yearOfManufacture,
			double engineCapacity, int pollutionLevel, int numberOfSeats, boolean isConvertible) {
		super(price, costOfManufacturing, color, yearOfManufacture, engineCapacity, pollutionLevel, numberOfSeats,
				isConvertible);
		setHybridProperty();
	}

	/**
	 * Partial constructor
	 *
	 * @param licensePlate
	 */
	public HybridCar(String licensePlate) {
		super(licensePlate);
	}

	/**
	 *
	 * @return double batteryCapacity
	 */
	public double getBatteryCapacity() {
		return batteryCapacity;
	}

	/**
	 *
	 * @param batteryCapacity
	 */
	public void setBatteryCapacity(double batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	/**
	 * Set the hybrid property (Engine capacity * 2.5)
	 */
	@Override
	public void setHybridProperty() {
		this.batteryCapacity = getEngineCapacity() * 2.5;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + " ,battery capacity= " + batteryCapacity;
	}

	@Override
	public void describeSpecialProperties() {
		// TODO Auto-generated method stub
		MyFileLogWriter.println("battery capacity<" + batteryCapacity + ">");

	}

}
