/**
 *
 */
package view;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import Exceptions.HandleExceptions;
import Exceptions.IncorrectDateFormat;
import Exceptions.InputIsEmpty;
import model.Factory;
import model.PersonAlreadyExistException;
import model.VIPCustomer;
import model.YearOfBirthNotInRange;
import utils.TypesInFactory;
import viewUtils.SerializableMethods;
import viewUtils.TextFormatterOnlyNumbers;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class AddVIPCustomerPanel extends AddCustomerPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	// private JFormattedTextField discountPerField;
	private JTextField discountPerField;
	protected double discountSTR;
	private JLabel discLabel;

	/**
	 * Create the frame.
	 */
	public AddVIPCustomerPanel(String str) {
		super(str);
		initialiseAllComponentsSet();
		addToPanel();
	}

	/**
	 *
	 */
	private void initialiseAllComponentsSet() {
		//Discount percentage label and text field
		discLabel = new JLabel("Discout percantage");
		discountPerField = new JTextField();
		discountPerField.setName("Discount");
		TextFormatterOnlyNumbers descField = new TextFormatterOnlyNumbers();
		descField.isPrecent();//Set formatter to percent
		descField.setMaxLength(6);//Force max length to be 6
		((AbstractDocument) discountPerField.getDocument()).setDocumentFilter(descField);
		discountPerField.setColumns(6);

	}


	/**
	 * Add components to panel And if its a textfield add to arraylist in
	 * PersonFrame to check if empty
	 */
	private void addToPanel() {
		textFormPanel.add(discLabel);
		textFormPanel.add(discountPerField);
		addFieldToList(discountPerField);

	}

	/**
	 * Create new customer, if fields are correct
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (checkEmpty()) {
				setValues();
				discountSTR = Double.parseDouble(discountPerField.getText());
				VIPCustomer vipcstmr = new VIPCustomer(idStr, firstnameStr, lastnameStr, phoneNumberStr, GenderStr,
						yearOfBirthStr, areaStr, date, discountSTR);
				Factory.getInstance().addVIPCustomer(vipcstmr);
				SerializableMethods.serialize();
				initialiseJlist(TypesInFactory.All_Customers);

			}

		} catch (PersonAlreadyExistException | YearOfBirthNotInRange | ParseException | IncorrectDateFormat
				| IOException | NumberFormatException | InputIsEmpty e1) {
			// TODO Auto-generated catch block
			HandleExceptions.showException(e1);
		}
	}
}
