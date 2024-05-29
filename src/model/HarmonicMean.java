/**
 *
 */
package model;

import java.util.HashSet;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class HarmonicMean implements ScoreCalculator{

	@Override
	public float calculate(HashSet<VehicleTransportation> allVehicleTransportation) {
		float harmonicMean = 0;
		float setSize = allVehicleTransportation.size();

		for (VehicleTransportation vt : allVehicleTransportation) {
			harmonicMean += (float) ((1.0)/vt.getPollutionLevel());
		}
		return setSize/harmonicMean;
	}

}
