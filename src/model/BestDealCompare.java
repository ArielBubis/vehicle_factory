/**
 *
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author Ariel Bubis 205735749
 *
 */
public class BestDealCompare implements Comparator<Deal> {

	ScoreCalculator score;

	/**
	 * Constructor to implement and use ScoreCalculator object
	 *
	 * @param score
	 */
	public BestDealCompare(ScoreCalculator score) {
		super();
		this.score = score;
	}

	/**
	 * Compare using The Score we get from ScoreCalculator object
	 */
	@Override
	public int compare(Deal deal1, Deal deal2) {
		if (deal1.GetScore(score) > deal2.GetScore(score)) {
			return 1;
		}
		if (deal1.GetScore(score) == deal2.GetScore(score)) {
			return 0;
		}
		return -1;
	}

	/**
	 *
	 * @param map   - HashMap of all deals
	 * @param score
	 * @return bestDealList - Sorted Arraylist of deals
	 */
	static ArrayList<Deal> buildBestDealList(HashMap<String, Deal> map, ScoreCalculator score) {
		ArrayList<Deal> bestDealList = new ArrayList<>(map.values());
		Collections.sort(bestDealList, new BestDealCompare(score));

		return bestDealList;
	}
}
