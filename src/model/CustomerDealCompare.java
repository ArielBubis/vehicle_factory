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
public class CustomerDealCompare implements Comparator<Customer> {



	@Override
	public int compare(Customer cstmr1, Customer cstmr2) {
		if (cstmr1.getAllDeals().size() > cstmr2.getAllDeals().size()) {
			return 1;
		} else if (cstmr1.getAllDeals().size() == cstmr2.getAllDeals().size()) {
				if(numOfVheicles(cstmr1)> numOfVheicles(cstmr2)) {
					return -1;
				}else
				if(numOfVheicles(cstmr1) == numOfVheicles(cstmr2)) {
					return cstmr1.compareTo(cstmr2);
				}else {
					return 1;
				}
		}
		return -1;
	}

	/**
	 *
	 * @param cstmr
	 * @return counter - Count of all vehicles bought by costumer
	 */
	public int numOfVheicles(Customer cstmr) {
		if(cstmr == null) {
			return 0;
		}
		int counter= 0 ;
		for(Deal deal: cstmr.getAllDeals()) {
			counter += deal.getAllVehicleTransportation().size();
		}
		return counter;
	}

	/**
	 *
	 * @param  map - HashMap of AllCustomers
	 * @return allCustomersSorted - SortedTreeSet
	 */
	 static TreeSet<Customer> buildTreeSet(HashMap<String, Customer> map) {
		CustomerDealCompare compare = new CustomerDealCompare();
		TreeSet<Customer> allCustomersSorted = new TreeSet<>(compare);

		for (HashMap.Entry<String, Customer> vt : map.entrySet()) {
			allCustomersSorted.add(vt.getValue());
		}
		return allCustomersSorted;
	}

}
