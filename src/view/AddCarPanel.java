/**
 *
 */
package view;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import Exceptions.HandleExceptions;
import Exceptions.InputIsEmpty;
import model.Car;
import model.Factory;
import model.HybridCar;
import model.IncorrectNumberOfSeats;
import model.PoullutionLevelHybridVehiclesMustBeOne;
import model.PoullutionLevelNotInRange;
import viewUtils.SerializableMethods;
import viewUtils.TextFormatterOnlyNumbers;

/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class AddCarPanel extends VehicleTransportationPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField numberOfSeatsField;
	private JComboBox<Boolean> isConvertiblecomboBox;
	protected Boolean isConvertible;
	private int numOfSeats;
	private JCheckBox hybridCheckBox;
	private JLabel numOfSeatsJLabel;
	private JLabel convertbLabel;

	public AddCarPanel(String str) {
		super(str);
		initialseAllComponents();
		addToPanel();
	}

	private void initialseAllComponents() {
		// Number of seats label and textField
		numOfSeatsJLabel = new JLabel("Number of seats");
		numberOfSeatsField = new JTextField();
		numberOfSeatsField.setName("Number Of Seats");

		// Formatter using DocumetFilter
		TextFormatterOnlyNumbers numberOfSeatsFormatter = new TextFormatterOnlyNumbers();
		numberOfSeatsFormatter.setMaxLength(2);
		((AbstractDocument) numberOfSeatsField.getDocument()).setDocumentFilter(numberOfSeatsFormatter);
		numberOfSeatsField.setColumns(10);

		// IsConvertable label and ComboBox
		convertbLabel = new JLabel("Convertible");
		isConvertiblecomboBox = new JComboBox<>();
		isConvertiblecomboBox.addItem(Boolean.TRUE);
		isConvertiblecomboBox.addItem(Boolean.FALSE);
		// Is the car hybrid CheckBox
		hybridCheckBox = new JCheckBox("This is a Hybrid Vehicle");

	}

	/**
	 * Add components to panel And if its a textfield add to arraylist in
	 * PersonFrame to check if empty
	 */
	private void addToPanel() {
		textFormPanel.add(numOfSeatsJLabel);
		textFormPanel.add(numberOfSeatsField);
		addFieldToList(numberOfSeatsField);
		textFormPanel.add(convertbLabel);
		textFormPanel.add(isConvertiblecomboBox);
		textFormPanel.add(hybridCheckBox);
	}

	/**
	 * Check field and add new car
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (checkEmpty()) {
				setValues();
				if (hybridCheckBox.isSelected()) {
					HybridCar hCar = new HybridCar(price, costOfManufacturing, color, yearOfManufacture, engineCapacity,
							pollutionLevel, numOfSeats, isConvertible);
					Factory.getInstance().addHybridCar(hCar);
				} else {
					Car car = new Car(price, costOfManufacturing, color, yearOfManufacture, engineCapacity,
							pollutionLevel, numOfSeats, isConvertible);
					Factory.getInstance().addCar(car);
				}
				SerializableMethods.serialize();
			}
		} catch (InputIsEmpty | PoullutionLevelNotInRange | IncorrectNumberOfSeats | IOException
				| PoullutionLevelHybridVehiclesMustBeOne e1) {
			Factory.getInstance().subCarCounter();
			HandleExceptions.showException(e1);
		}
	}

	/**
	 * Set all values from the form to the fields
	 */
	private void setValues() {
		setValuesParent();
		numOfSeats = Integer.parseInt(numberOfSeatsField.getText());
		isConvertible = (Boolean) isConvertiblecomboBox.getSelectedItem();

	}
}
