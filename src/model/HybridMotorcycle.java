package model;

import utils.Color;
import utils.MyFileLogWriter;

/**
 * @author Ariel Bubis 205735749
 *
 */

public class HybridMotorcycle extends Motorcycle implements HybridInterface {

	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private double numOfKMElectricBasis;

	/**
	 * HybridMotorcycle constructor
	 *
	 * @param price
	 * @param costOfManufacturing
	 * @param color
	 * @param yearOfManufacture
	 * @param engineCapacity
	 * @param pollutionLevel
	 * @param isOffRoad
	 */
	public HybridMotorcycle(double price, double costOfManufacturing, Color color, int yearOfManufacture,
			double engineCapacity, int pollutionLevel, boolean isOffRoad) {
		super(price, costOfManufacturing, color, yearOfManufacture, engineCapacity, pollutionLevel, isOffRoad);
		setHybridProperty();
	}

	/**
	 * Partial constructor
	 *
	 * @param licensePlate
	 */
	public HybridMotorcycle(String licensePlate) {
		super(licensePlate);
	}

	/**
	 *
	 * @return double numOfKMElectricBasis
	 */
	public double getNumOfKMElectricBasis() {
		return numOfKMElectricBasis;
	}

	/**
	 *
	 * @param numOfKMElectricBasis
	 */
	public void setNumOfKMElectricBasis(double numOfKMElectricBasis) {
		this.numOfKMElectricBasis = numOfKMElectricBasis;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/**
	 * Set the hybrid property (Engine capacity * 2.5)
	 */
	@Override
	public void setHybridProperty() {
		this.numOfKMElectricBasis = getEngineCapacity() * 1.5;
	}

	@Override
	public String toString() {
		return super.toString() + " ,numOfKMElectricBasis= " + numOfKMElectricBasis;
	}

	@Override
	public void describeSpecialProperties() {
		// TODO Auto-generated method stub
		MyFileLogWriter.println("number of km electric basis <" + numOfKMElectricBasis + ">");

	}

}
