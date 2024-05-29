/**
 *
 */
package view;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import Exceptions.HandleExceptions;
import Exceptions.InputIsEmpty;
import model.Factory;
import model.PoullutionLevelNotInRange;
import model.Van;
import viewUtils.SerializableMethods;
import viewUtils.TextFormatterOnlyNumbers;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class AddVanPanel extends VehicleTransportationPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField trunkSizeField;
	protected JTextField maxWeightField;

	private double trunkSize;
	private double maxWeightCarrying;
	private JLabel trunkSizeLabel;
	private JLabel maxWLabel;

	/**
	 * @param str
	 */
	public AddVanPanel(String str) {
		super(str);
		initialiseAllComponents();
		addToPanel();
	}

	private void initialiseAllComponents() {
		trunkSizeField = new JTextField();
		TextFormatterOnlyNumbers trunkSizeFormatter = new TextFormatterOnlyNumbers();
		trunkSizeFormatter.isDouble();
		((AbstractDocument) trunkSizeField.getDocument()).setDocumentFilter(trunkSizeFormatter);
		trunkSizeField.setColumns(10);
		maxWeightField = new JTextField();
		TextFormatterOnlyNumbers maxWeighTextFormatter = new TextFormatterOnlyNumbers();
		maxWeighTextFormatter.isDouble();
		maxWeightField.setColumns(10);
		maxWLabel = new JLabel("Max Weight");
		trunkSizeLabel = new JLabel("Trunk size");

	}

	private void addToPanel() {
		// Trunk Size label and formatted textfield
		textFormPanel.add(trunkSizeLabel);
		textFormPanel.add(trunkSizeField);

		// Max wieght label and formatted text field
		textFormPanel.add(maxWLabel);
		textFormPanel.add(maxWeightField);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (checkEmpty()) {
				setValues();
				Van van = new Van(price, costOfManufacturing, color, yearOfManufacture, engineCapacity, pollutionLevel,
						trunkSize, maxWeightCarrying);
				Factory.getInstance().addVan(van);
			}
			SerializableMethods.serialize();

		} catch (InputIsEmpty | PoullutionLevelNotInRange | IOException e1) {
			Factory.getInstance().subCarCounter();// If there was an issue with the add, subtract one from the counter
			HandleExceptions.showException(e1);
		}
		System.out.println(Factory.getInstance().getAllVehicleTransportation());
	}

	/**
	 * Set values
	 */
	public void setValues() {
		setValuesParent();
		trunkSize = Double.parseDouble(trunkSizeField.getText());
		maxWeightCarrying = Double.parseDouble(maxWeightField.getText());

	}

}
