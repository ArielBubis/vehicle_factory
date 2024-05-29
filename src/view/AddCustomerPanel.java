/**
 *
 */
package view;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import Exceptions.HandleExceptions;
import Exceptions.IncorrectDateFormat;
import Exceptions.InputIsEmpty;
import model.Customer;
import model.Factory;
import model.PersonAlreadyExistException;
import model.YearOfBirthNotInRange;
import utils.Area;
import utils.TypesInFactory;
import viewUtils.SerializableMethods;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class AddCustomerPanel extends PersonForm {

	private static final long serialVersionUID = 1L;
	private JComboBox<Area> areaComBox;
	private JLabel areaLabel;

	/**
	 * @param str - Name of object for the jtextpane
	 */
	public AddCustomerPanel(String str) {
		super(str);

		initialiseJlist(TypesInFactory.All_Customers);
		initialiseAllComponentsSet();
		addToPanel();
	}

	private void initialiseAllComponentsSet() {
		// Area label and comboBox
		areaLabel = new JLabel("Area");
		areaComBox = new JComboBox<>();
		areaComBox.setModel(new DefaultComboBoxModel<>(Area.values()));
	}


	private void addToPanel() {
		// TODO Auto-generated method stub
		textFormPanel.add(areaLabel);
		textFormPanel.add(areaComBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (checkEmpty()) {
				setValues();
				Customer cstmr = new Customer(idStr, firstnameStr, lastnameStr, phoneNumberStr, GenderStr,
						yearOfBirthStr, areaStr, date);
				Factory.getInstance().addCustomer(cstmr);
				SerializableMethods.serialize();
				initialiseJlist(TypesInFactory.All_Customers);

			}
		} catch (PersonAlreadyExistException | YearOfBirthNotInRange | IOException | InputIsEmpty | ParseException
				| IncorrectDateFormat e1) {
			HandleExceptions.showException(e1);
		}
	}


	/**
	 * Set Values
	 *
	 * @throws ParseException
	 * @throws IncorrectDateFormat - incorrect date format
	 */
	public void setValues() throws ParseException, IncorrectDateFormat {
		setValuesOfparent();
		areaStr = getArea();

	}
	/**
	 *
	 * @return Area
	 */
	public Area getArea() {
		return (Area) areaComBox.getSelectedItem();
	}

}
