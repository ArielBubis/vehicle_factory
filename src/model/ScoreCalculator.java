/**
 *
 */
package model;

import java.util.HashSet;

/**
 * @author Ariel Bubis 205735749
 *
 */
public interface ScoreCalculator {
	float calculate(HashSet<VehicleTransportation> allVehicleTransportation);
}
