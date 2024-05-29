/**
 *
 */
package view;

import java.awt.Image;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import Exceptions.HandleExceptions;
import Exceptions.NoObjectsToRemove;
import model.Customer;
import model.Deal;
import model.Department;
import model.DepartmentManager;
import model.Employee;
import model.Factory;
import model.VehicleTransportation;
import utils.TypesInFactory;

/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 */
public class InitialiseSeeAllAndRemove {
	/**
	 * Methods for SeeAll and RemoveObjects panel, as they have similar components
	 */
	private static HashMap<TypesInFactory, List<String>> factoryMap;

	protected static void paintProfilePicture(Employee emp, JLabel pictureLbl) {
		pictureLbl.setText(null);
		pictureLbl.setIcon(null);
		ImageIcon imageIcon = emp.getPhoto(); // load the image to a imageIcon
		if (imageIcon == null) {
			pictureLbl.setText("No Image Uploaded");
		} else {
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(114, 123, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			imageIcon = new ImageIcon(newimg); // transform it back

			pictureLbl.setIcon(imageIcon);

		}
	}

	protected static void writeProfile(String slcted, JTextPane profieTextPane, JTextPane listTextPane,
			JLabel pictureLbl) {
		try {
			if (slcted == null) {
				return;
			}

			String selectedType = listTextPane.getDocument().getText(0, listTextPane.getDocument().getLength());
			String selectedObjectFromList = slcted.substring(0, slcted.indexOf('|'));

			if (selectedType.equals(TypesInFactory.All_Customers.name())) {
				Customer cstmr = Factory.getInstance().getRealCustomer(selectedObjectFromList);
				profieTextPane.setText(cstmr.toString());
			}
			if (selectedType.equals(TypesInFactory.All_Employees.name())) {
				Employee emp = Factory.getInstance().getRealEmployee(selectedObjectFromList);
				profieTextPane.setText(emp.toString());
				InitialiseSeeAllAndRemove.paintProfilePicture(emp, pictureLbl);
			}
			if (selectedType.equals(TypesInFactory.All_Department_Managers.name())) {
				DepartmentManager depM = Factory.getInstance().getRealDepartmentManager(selectedObjectFromList);
				profieTextPane.setText(depM.toString());
				InitialiseSeeAllAndRemove.paintProfilePicture(depM, pictureLbl);
			}
			if (selectedType.equals(TypesInFactory.All_Deals.name())) {
				Deal d = Factory.getInstance().getRealDeal(selectedObjectFromList);
				profieTextPane.setText(d.toString());

			}
			if (selectedType.equals(TypesInFactory.All_Departments.name())) {
				Department dpr = Factory.getInstance().getRealDepartment(Integer.parseInt(selectedObjectFromList));
				profieTextPane.setText(dpr.toString());

			}
			if (selectedType.equals(TypesInFactory.All_Vehicles.name())) {
				VehicleTransportation vt = Factory.getInstance().getRealVehicleTransportation(selectedObjectFromList);
				profieTextPane.setText(vt.toString());

			}
		} catch (Exception e) {
			HandleExceptions.showException(e);
		}

	}

	/**
	 * Add all type of maps in factory to the combox as long as they are not empty
	 *
	 * @throws NoObjectsToRemove
	 */
	protected static void initialiseComboBox(Boolean admin, JComboBox<TypesInFactory> mapsComboBox)
			throws NoObjectsToRemove {

		factoryMap = Factory.getInstance().mapOfAllMaps();
		for (TypesInFactory tInF : factoryMap.keySet()) {
			if (!factoryMap.get(tInF).isEmpty()) {
				if (tInF.equals(TypesInFactory.All_Employees) || tInF.equals(TypesInFactory.All_Department_Managers)
						|| tInF.equals(TypesInFactory.All_Departments)) {
					if (admin) {
						mapsComboBox.addItem(tInF);// Only admin can remove Employees and dep managers
					}

				} else {
					mapsComboBox.addItem(tInF);
				}
			}
		}
		if (admin && factoryMap.isEmpty()) {
			throw new NoObjectsToRemove("All");
		}
		if ((!admin) && !factoryMap.containsKey(TypesInFactory.All_Customers)
				&& !factoryMap.containsKey(TypesInFactory.All_Vehicles)
				&& !factoryMap.containsKey(TypesInFactory.All_Deals)) {
			throw new NoObjectsToRemove("The lists you have premmission to edit, those");
		}
	}

	protected static void initialiseJlist(DefaultListModel<String> listModelString, TypesInFactory slct,
			JTextPane txtpnList) {
		factoryMap = Factory.getInstance().mapOfAllMaps();

		if (slct == null) {
			return;
		}

		try {
			txtpnList.getDocument().insertString(0, slct.name(), null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String s : factoryMap.get(slct)) {
			listModelString.addElement(s);
		}

	}

}
