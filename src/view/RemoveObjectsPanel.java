/**
 *
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;

import Exceptions.HandleExceptions;
import Exceptions.InputIsEmpty;
import Exceptions.NoObjectsToRemove;
import model.Customer;
import model.Deal;
import model.Department;
import model.DepartmentManager;
import model.Employee;
import model.Factory;
import model.PersonNotExistException;
import model.VehicleTransportation;
import utils.TypesInFactory;
import viewUtils.SerializableMethods;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class RemoveObjectsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JList<String> objectList;
	private JComboBox<TypesInFactory> mapsComboBox;
	private JLabel slctListLabel;
	private JLabel lblNewLabel;
	private JLabel slctToRemoveLabel;
	private JButton removeSlctdBtn;
	private DefaultListModel<String> listModelString;
	private JTextPane txtpnList;
	private TypesInFactory slct;
	private HashMap<TypesInFactory, List<String>> factoryMap;
	private JPanel panel;
	private GroupLayout groupLayout;
	private JScrollPane scrollPane;
	private JTextPane profieTextPane;
	private JLabel pictureLbl;
	private JScrollPane scrollPane_1;

	public RemoveObjectsPanel(Boolean admin) throws NoObjectsToRemove {

		panel = new JPanel();


		lblNewLabel = new JLabel("Remove Methods");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		slctListLabel = new JLabel("Select List:");
		/************************************************************/

		mapsComboBox = new JComboBox<>();

		slctToRemoveLabel = new JLabel("Select object to remove:");

		/************************************************************/

		removeSlctdBtn = new JButton("Remove Selected");
		removeSlctdBtn.setActionCommand("Remove");
		removeSlctdBtn.addActionListener(this);

		/************************************************************/

		scrollPane = new JScrollPane();

		scrollPane_1 = new JScrollPane();

		pictureLbl = new JLabel("No Image Uploaded");
		pictureLbl.setHorizontalAlignment(SwingConstants.CENTER);
		pictureLbl.setFont(new Font("Tahoma", Font.BOLD, 10));
		pictureLbl.setBackground(Color.DARK_GRAY);



		profieTextPane = new JTextPane();
		profieTextPane.setForeground(Color.BLACK);
		profieTextPane.setEditable(false);
		profieTextPane.setBackground(Color.LIGHT_GRAY);
		scrollPane_1.setViewportView(profieTextPane);

		txtpnList = new JTextPane();
		scrollPane.setColumnHeaderView(txtpnList);
		txtpnList.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnList.setBackground(SystemColor.menu);
		txtpnList.setEditable(false);

		listModelString = new DefaultListModel<>();
		InitialiseSeeAllAndRemove.initialiseJlist(listModelString, slct, txtpnList);
		updateJlist();
		InitialiseSeeAllAndRemove.initialiseComboBox(admin,mapsComboBox);

		objectList = new JList<>(listModelString);
		objectList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		objectList.setSize(20, 20);
		objectList.setName("Select object");
		scrollPane.setViewportView(objectList);




		mapsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateJlist();

			}
		});

		objectList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				pictureLbl.setIcon(null);
				pictureLbl.setText("No Image Uploaded");
				//paintProfile();
				InitialiseSeeAllAndRemove.writeProfile(objectList.getSelectedValue(), profieTextPane,txtpnList ,pictureLbl);


			}
		});
		 initialiseLayoust();
	}


	private void initialiseLayoust() {
		groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
					.addGap(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addGap(12))
		);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(74)
							.addComponent(removeSlctdBtn))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(slctListLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(mapsComboBox, 0, 155, Short.MAX_VALUE)
											.addGap(477)))
									.addGap(23))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
										.addComponent(slctToRemoveLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 267, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
									.addGap(28)
									.addComponent(pictureLbl, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addGap(130)))))
					.addGap(0))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mapsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(slctListLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(slctToRemoveLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(removeSlctdBtn))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(pictureLbl, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()) {
		case "paintList":
			updateJlist();
			break;
		case "Remove":
			removeFromFactory();
			break;
		}
	}

	/**
	 *if the an object correctly selected using the selected type it will get the right object with the getReal function and remove it
	 */
	private void removeFromFactory() {
		try {
			if (objectList.getSelectedValue() == null) {
				throw new InputIsEmpty(objectList.getName());
			}
			String selectedType = txtpnList.getDocument().getText(0, txtpnList.getDocument().getLength());
			String selectedObjectFromList = objectList.getSelectedValue().substring(0,
					objectList.getSelectedValue().indexOf('|'));

			if (selectedType.equals(TypesInFactory.All_Customers.name())) {
				Customer cstmr = Factory.getInstance().getRealCustomer(selectedObjectFromList);
				if (Factory.getInstance().removeCustomer(cstmr)) {
					JOptionPane.showMessageDialog(null, "Removed Succesfuly", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (selectedType.equals(TypesInFactory.All_Employees.name())) {
				Employee emp = Factory.getInstance().getRealEmployee(selectedObjectFromList);
				if (Factory.getInstance().removeEmployee(emp)) {
					JOptionPane.showMessageDialog(null, "Removed Succesfuly", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
			if (selectedType.equals(TypesInFactory.All_Department_Managers.name())) {
				DepartmentManager depM = Factory.getInstance().getRealDepartmentManager(selectedObjectFromList);
				if (Factory.getInstance().removeDepartmentManager(depM)) {
					JOptionPane.showMessageDialog(null, "Removed Succesfuly", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (selectedType.equals(TypesInFactory.All_Deals.name())) {
				Deal d = Factory.getInstance().getRealDeal(selectedObjectFromList);
				if (Factory.getInstance().removeDeal(d)) {
					JOptionPane.showMessageDialog(null, "Removed Succesfuly", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (selectedType.equals(TypesInFactory.All_Departments.name())) {
				Department dpr = Factory.getInstance().getRealDepartment(Integer.parseInt(selectedObjectFromList));
				if (Factory.getInstance().removeDepartment(dpr)) {
					JOptionPane.showMessageDialog(null, "Removed Succesfuly", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if (selectedType.equals(TypesInFactory.All_Vehicles.name())) {
				VehicleTransportation vt = Factory.getInstance().getRealVehicleTransportation(selectedObjectFromList);
				if (Factory.getInstance().removeVehicleTeansportation(vt)) {
					JOptionPane.showMessageDialog(null, "Removed Succesfuly", "Success",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			SerializableMethods.serialize();
			updateJlist();

		} catch (InputIsEmpty | BadLocationException | PersonNotExistException | IOException e) {
			HandleExceptions.showException(e);
		}
	}


	/**
	 * updateJlist
	 */
	private void updateJlist() {
		slct = (TypesInFactory) mapsComboBox.getSelectedItem();
		listModelString.removeAllElements();
		profieTextPane.setText("");

		if (slct == null) {
			return;
		}
		factoryMap = Factory.getInstance().mapOfAllMaps();
		try {
			txtpnList.getDocument().remove(0, txtpnList.getDocument().getLength());
			txtpnList.getDocument().insertString(0, slct.name(), null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		if(factoryMap.get(slct) == null) {
			listModelString.removeAllElements();
			return;
		}
		for (String s : factoryMap.get(slct)) {
			listModelString.addElement(s);
		}
	}
}
