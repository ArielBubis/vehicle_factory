/**
 *
 */
package view;

import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;

import model.Factory;
import utils.TypesInFactory;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class InitialseListsCalss {
	/**
	 * This class creats a DefaultListModel for some of the jlists used in the
	 * gui, like seeAllPanel and ReomoveObjectPanel, and gets the selecte
	 * TypesInFactory enum from the ComboBox in the panel and a DefaultListModel and
	 * initialises adds the values from the hasmap created in the Factory
	 * (Factory.getInstance().mapOfAllMaps())
	 *
	 */
	private static HashMap<TypesInFactory, List<String>> factoryMap;

	public static DefaultListModel<String> initialiseJlist(TypesInFactory slct,
			DefaultListModel<String> listModelString) {
		factoryMap = Factory.getInstance().mapOfAllMaps();
		listModelString.removeAllElements();

		if (slct == null) {
			return listModelString;
		}
		if (factoryMap.get(slct) != null) {
			for (String s : factoryMap.get(slct)) {
				listModelString.addElement(s);
			}
			return listModelString;
		}
		return listModelString;
	}

}
