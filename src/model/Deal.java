package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class Deal implements Comparable<Deal>,Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 658069984366857587L;
	private static int dealPK = Factory.getInstance().getDealCounter();
	private String dealID;
	private Customer customer;
	private Date dealDate;
	private HashSet<VehicleTransportation> allVehicleTransportation;
	private double shippingCost;
	private double totalDealPrice;


/**
 *
 * @param customer
 * @param dealDate
 * @param allVehicleTransportation
 * @param shippingCost
 */
	public Deal(Customer customer, Date dealDate, HashSet<VehicleTransportation> allVehicleTransportation,
			double shippingCost) {
		super();
		this.dealID = "" + dealPK;
		this.customer = customer;
		this.dealDate = dealDate;
		this.allVehicleTransportation = allVehicleTransportation;
		this.shippingCost = shippingCost;
		setTotalDealPrice();
		dealPK = Factory.getInstance().getDealCounter();
		Factory.getInstance().addDealCounter();
	}

	/**
	 * Partial constructor
	 *
	 * @param dealID
	 */

	public Deal(String dealID) {
		super();
		this.dealID = dealID;
	}

	/**
	 * Method to calculate the total deal price for the Customer The method sums all
	 * the prices of the car he bought, then the method check if the customer is a
	 * VIP, if he is it subtracts the discount he deserve
	 *
	 * totalDealPrice - Total deal price
	 */
	public void setTotalDealPrice() {
		double sumOfDeals = shippingCost;
		for (VehicleTransportation vt : allVehicleTransportation)
			// Sum up all the cars cost
			sumOfDeals += vt.getPrice();

		if (customer instanceof VIPCustomer)
			// Check if the costumer object is VIP customer
			sumOfDeals -= (sumOfDeals * (((VIPCustomer) customer).getDiscountPercentage()));
		this.totalDealPrice = sumOfDeals;
	}

	public static void setDealPK(int dealPK) {
		Deal.dealPK = dealPK;
	}

	/**
	 *
	 * @return the dealPK
	 */
	public static int getDealPK() {
		return dealPK;
	}

	/**
	 *
	 * @return String dealID
	 */
	public String getDealID() {
		return dealID;
	}

	/**
	 *
	 * @param dealID
	 */
	public void setDealID(String dealID) {
		this.dealID = dealID;
	}

	/**
	 *
	 * @return Customer customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 *
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 *
	 * @return allVehicleTransportation
	 */
	public HashSet<VehicleTransportation> getAllVehicleTransportation() {
		return allVehicleTransportation;
	}

	/**
	 *
	 * @param allVehicleTransportation
	 */
	public void setAllVehicleTransportation(HashSet<VehicleTransportation> allVehicleTransportation) {
		this.allVehicleTransportation = allVehicleTransportation;
	}

	/**
	 * @return the dealDate
	 */
	public Date getDealDate() {
		return dealDate;
	}

	/**
	 * @param dealDate the dealDate to set
	 */
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	/**
	 * @return the shippingCost
	 */
	public double getShippingCost() {
		return shippingCost;
	}

	/**
	 * @param shippingCost the shippingCost to set
	 */
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	/**
	 * @return the totalDealPrice
	 */
	public double getTotalDealPrice() {
		return totalDealPrice;
	}

	/**
	 *
	 * @param s - ScoreCalculator Object
	 * @return Score of the pollution level by the ScoreCalculator type
	 */
	public double GetScore(ScoreCalculator s) {
		return s.calculate(this.allVehicleTransportation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Deal other = (Deal) obj;
		return Objects.equals(dealID, other.dealID);
	}

	@Override
	public String toString() {
		return "dealID= " + dealID + ", customerID= " + customer.getID() + ", dealDate= " + dealDate
				+ " , totalDealPrice= " + totalDealPrice  + stringAllVTList();
	}

	@Override
	public int compareTo(Deal deal) {
		if (deal == null) {
			return 0;
		}
		int dealID1 = Integer.parseInt(this.dealID);
		int dealID2 = Integer.parseInt(deal.getDealID());
		if (dealID1 > dealID2) {
			return 1;
		} else if (dealID1 == dealID2) {
			return 0;
		}
		return -1;
		// return this.dealID.compareTo(deal.getDealID());
	}

	private String stringAllVTList() {
		String finalString = "\n----------\nVehicles in deal:\n";
		for(VehicleTransportation vt: allVehicleTransportation) {
			finalString = finalString +vt.getClass().getSimpleName()+": "+ vt.toString()+"\n\n";
		}
		return finalString;

	}

}
