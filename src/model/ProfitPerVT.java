/**
 *
 */
package model;

import java.io.Serializable;

/**
 * @author Ariel Bubis 205735749
 */
public interface ProfitPerVT extends Serializable{
	 static final long serialVersionUID = 658069984366857587L;

	public double profitPerVehiclesTransportation(VehicleTransportation vehicleTransportation);
}

