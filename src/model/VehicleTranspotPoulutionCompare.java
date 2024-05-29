/**
 *
 */
package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class VehicleTranspotPoulutionCompare implements Comparator<VehicleTransportation> {

	@Override
	public int compare(VehicleTransportation vt1, VehicleTransportation vt2) {
		if (vt1.getPollutionLevel() > vt2.getPollutionLevel()) {
			return -1;
		} else if (vt1.getPollutionLevel() == vt2.getPollutionLevel()) {
			if (vt1.getPrice() > vt2.getPrice()) {
				return 1;
			} else if (vt1.getPrice() == vt2.getPrice()) {
				return vt1.getLicensePlate().compareTo(vt2.getLicensePlate());
			}
			return -1;
		}
		return 1;
	}

	/**
	 * Create TreeSet, add values from Factory.allvehicles and sort using the compare
	 * @param map of all vehicles
	 * @return allVehicleTransportations sorted by pollution level, then by price lastly by license plate
	 */
	static TreeSet<VehicleTransportation> buildTreeSet(HashMap<String, VehicleTransportation> map){
		VehicleTranspotPoulutionCompare compare = new VehicleTranspotPoulutionCompare();
		TreeSet<VehicleTransportation> allVehicleTransportations = new TreeSet<>(compare);
		for(HashMap.Entry<String, VehicleTransportation> vt: map.entrySet()) {
			allVehicleTransportations.add(vt.getValue());
		}
		return allVehicleTransportations;
	}

}
