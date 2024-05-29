/**
 *
 */
package view;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import Exceptions.HandleExceptions;
import Exceptions.InputIsEmpty;
import model.Factory;
import model.HybridMotorcycle;
import model.Motorcycle;
import model.PoullutionLevelHybridVehiclesMustBeOne;
import model.PoullutionLevelNotInRange;
import viewUtils.SerializableMethods;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class MotorcyclyFrame extends VehicleTransportationPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private boolean isOffRoad;
	private JComboBox<Boolean> offRoadcomboBox;
	private JCheckBox hybridCheckBox;
	private JLabel offRoadLabel;
	/**
	 * @param str
	 */
	public MotorcyclyFrame(String str) {
		super(str);
		initialiseComponents();
	}

	private void initialiseComponents(){
		offRoadLabel = new JLabel("Off road");
		offRoadcomboBox = new JComboBox<>();
		offRoadcomboBox.addItem(Boolean.TRUE);
		offRoadcomboBox.addItem(Boolean.FALSE);
		hybridCheckBox = new JCheckBox("This is a Hybrid Vehicle");
		//Add
		textFormPanel.add(offRoadLabel);
		textFormPanel.add(offRoadcomboBox);
		textFormPanel.add(hybridCheckBox);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (checkEmpty()) {
				setValues();
				if (hybridCheckBox.isSelected()) {
					HybridMotorcycle hMotorcycle = new HybridMotorcycle(price, costOfManufacturing, color, yearOfManufacture, engineCapacity,
							pollutionLevel, isOffRoad);
					Factory.getInstance().addHybridMotorcycle(hMotorcycle);
				} else {
					Motorcycle motorcycle = new Motorcycle(price, costOfManufacturing, color, yearOfManufacture, engineCapacity,
							pollutionLevel, isOffRoad);
					Factory.getInstance().addMotorcycle(motorcycle);
				}
				SerializableMethods.serialize();

			}
		} catch (InputIsEmpty | PoullutionLevelNotInRange | PoullutionLevelHybridVehiclesMustBeOne | IOException e1) {
			// TODO Auto-generated catch block
			Factory.getInstance().subCarCounter();
			HandleExceptions.showException(e1);
		}
		System.out.println(Factory.getInstance().allVehicleTransportations());
	}

	public void setValues() {
		setValuesParent();
		isOffRoad = (Boolean) offRoadcomboBox.getSelectedItem();

	}

}
