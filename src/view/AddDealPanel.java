/**
 *
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;

import Exceptions.HandleExceptions;
import Exceptions.IncorrectDateFormat;
import Exceptions.InputIsEmpty;
import Exceptions.NoCarsInDeal;
import Exceptions.NoCarsInStock;
import model.Customer;
import model.Deal;
import model.Factory;
import model.VIPCustomer;
import model.VehicleTransportation;
import viewUtils.SerializableMethods;
import viewUtils.TextFormatterOnlyNumbers;
import viewUtils.TextFormattersClass;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class AddDealPanel extends JPanel implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Customer> customerComBox;
	private JButton createCstmrBtn;
	private JList<VehicleTransportation> inStockCarList;
	private JList<VehicleTransportation> inDealCarList;

	private DefaultListModel<VehicleTransportation> listModelStock;
	private DefaultListModel<VehicleTransportation> listModelDeal;

	private HashMap<String, VehicleTransportation> vtInStockMap;
	private HashMap<String, VehicleTransportation> vtInDealMap;

	private JPanel dealForm;

	private JScrollPane scrollPane;
	private JFormattedTextField dateField;
	private JScrollPane scrollPaneCarsInDeal;
	private JLabel costLabel;
	private JLabel cstmrSelectLabel;
	private JLabel addDealLabel;
	private JLabel dealDateLabel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton updateCombox;
	private JButton addDealBtn;
	private JLabel lblNewLabel_2;
	private JTextField costField;
	private JButton totalPriceBtn;
	/*************************************************************/
	private Customer customer;
	private Date dealDate;
	private HashSet<VehicleTransportation> allVehicleTransportation;
	private double shippingCost;
	private JEditorPane totalPriceeditorPane;
	private JLabel labelExplanation;
	private GroupLayout gl_dealForm;

	/**
	 *
	 */
	public AddDealPanel() {
		initialiseJlists();
		initialiseAllComponentsSet();
		initialiseComboBox();

		dealForm.setLayout(layoutSet());
		add(dealForm);
	}

	private void initialiseAllComponentsSet() {
		// Main panel
		dealForm = new JPanel();
		dealForm.setBounds(10, 10, 490, 621);

		// Add Deal Header
		addDealLabel = new JLabel("Add Deal");
		addDealLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addDealLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		// Customer comboBox label and comboBox
		cstmrSelectLabel = new JLabel("Select Customer");
		cstmrSelectLabel.setHorizontalAlignment(SwingConstants.LEFT);

		customerComBox = new JComboBox<>();
		customerComBox.setName("Customer list");
		// Button that opens a popUp window with the ADD customer frame
		createCstmrBtn = new JButton("Press here to create A new Customer");
		createCstmrBtn.setActionCommand("createNewCstmr");
		createCstmrBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new PopUpCustomerFrame();
				frame.setVisible(true);
			}
		});

		// Button to update/ReInitialse the comboBox after we added a new Customer
		updateCombox = new JButton("Update List");
		updateCombox.setActionCommand("update");
		updateCombox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initialiseComboBox();
			}
		});

		// Add the date of deal label and text field
		dealDateLabel = new JLabel("Deal date");

		dateField = new JFormattedTextField(TextFormattersClass.MyDateFormmater());
		dateField.setValue(new Date());
		dateField.setName("Date");

		// Jlist of the cats in stock
		inStockCarList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		 MouseListener mouseListener = new MouseAdapter() {
		     public void mousePressed(MouseEvent e) {
					addVTToDealList();
		     }
		 };
		 inStockCarList.addMouseListener(mouseListener);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(inStockCarList);
		inStockCarList.setLayoutOrientation(JList.VERTICAL);
		lblNewLabel = new JLabel("Vehicles in stock");
		scrollPane.setColumnHeaderView(lblNewLabel);
		// Label
		labelExplanation = new JLabel("You can press a vehicle to add or remove vehicle from the deal");
		labelExplanation.setFont(new Font("Tahoma", Font.BOLD, 10));
		labelExplanation.setHorizontalAlignment(SwingConstants.CENTER);

		// Jlist of the cars in deal
		inDealCarList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		MouseListener mouseListener2 = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				removeVtFromDealList();
			}
		};
		inDealCarList.addMouseListener(mouseListener2);

		scrollPaneCarsInDeal = new JScrollPane();
		scrollPaneCarsInDeal.setViewportView(inDealCarList);
		inDealCarList.setLayoutOrientation(JList.VERTICAL);

		lblNewLabel_1 = new JLabel("Vehicles added to deal");
		scrollPaneCarsInDeal.setColumnHeaderView(lblNewLabel_1);

		// Cost of shipping label field and
		costLabel = new JLabel("Shipping cost:");

		costField = new JTextField();
		costField.setName("Cost Field");
		// Setting the formatter (Formating using documentfilter)
		TextFormatterOnlyNumbers costFormatter = new TextFormatterOnlyNumbers();
		costFormatter.setMaxLength(11);
		costFormatter.isDouble();
		((AbstractDocument) costField.getDocument()).setDocumentFilter(costFormatter);
		costField.setColumns(11);

		// Total deal price label button and TextPane
		lblNewLabel_2 = new JLabel("Total Deal price:");

		totalPriceBtn = new JButton("Show total price");
		totalPriceBtn.setActionCommand("totalPrice");
		totalPriceBtn.addActionListener(this);

		// When pressing will create a new deal(After checking) and get the total cost
		totalPriceeditorPane = new JEditorPane();
		totalPriceeditorPane.setBackground(Color.LIGHT_GRAY);
		totalPriceeditorPane.setEditable(false);

		// Add Deal Button
		addDealBtn = new JButton("Add deal");
		addDealBtn.setActionCommand("addDeal");
		addDealBtn.addActionListener(this);

	}

	/**
	 * All the layout settings
	 *
	 * @return
	 */
	private GroupLayout layoutSet() {
		gl_dealForm = new GroupLayout(dealForm);
		gl_dealForm.setHorizontalGroup(gl_dealForm.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_dealForm.createSequentialGroup().addContainerGap()
						.addComponent(scrollPaneCarsInDeal, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_dealForm.createSequentialGroup().addContainerGap().addGroup(gl_dealForm
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_dealForm.createSequentialGroup().addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(totalPriceeditorPane, GroupLayout.PREFERRED_SIZE, 79,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(totalPriceBtn))
						.addGroup(gl_dealForm.createSequentialGroup().addGap(113).addComponent(addDealBtn,
								GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_dealForm.createSequentialGroup().addComponent(costLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(costField,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(232, Short.MAX_VALUE))
				.addGroup(gl_dealForm.createSequentialGroup().addContainerGap()
						.addGroup(gl_dealForm.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_dealForm.createSequentialGroup().addGap(10).addComponent(labelExplanation,
										GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 510,
										Short.MAX_VALUE))
						.addContainerGap())
				.addGroup(gl_dealForm.createSequentialGroup().addGroup(gl_dealForm
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_dealForm.createSequentialGroup().addContainerGap().addComponent(addDealLabel,
								GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
						.addGroup(gl_dealForm.createSequentialGroup().addGap(11)
								.addGroup(gl_dealForm.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_dealForm.createSequentialGroup()
												.addComponent(dealDateLabel, GroupLayout.PREFERRED_SIZE, 65,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(dateField,
														GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_dealForm.createSequentialGroup()
												.addComponent(cstmrSelectLabel, GroupLayout.PREFERRED_SIZE, 83,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addGroup(gl_dealForm.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_dealForm.createSequentialGroup()
																.addComponent(createCstmrBtn)
																.addPreferredGap(ComponentPlacement.RELATED, 107,
																		Short.MAX_VALUE)
																.addComponent(updateCombox))
														.addComponent(customerComBox, 0, 395, Short.MAX_VALUE))))))
						.addGap(23)));
		gl_dealForm.setVerticalGroup(gl_dealForm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dealForm.createSequentialGroup().addGap(5).addComponent(addDealLabel).addGap(14)
						.addGroup(gl_dealForm.createParallelGroup(Alignment.BASELINE).addComponent(cstmrSelectLabel)
								.addComponent(customerComBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_dealForm.createParallelGroup(Alignment.BASELINE).addComponent(createCstmrBtn)
								.addComponent(updateCombox))
						.addGap(13)
						.addGroup(gl_dealForm.createParallelGroup(Alignment.BASELINE).addComponent(dealDateLabel)
								.addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(21)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(labelExplanation, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPaneCarsInDeal, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addGap(33)
						.addGroup(gl_dealForm.createParallelGroup(Alignment.BASELINE).addComponent(costLabel)
								.addComponent(costField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(12)
						.addGroup(gl_dealForm.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_2)
								.addComponent(totalPriceeditorPane, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(totalPriceBtn))
						.addGap(13).addComponent(addDealBtn).addContainerGap(74, Short.MAX_VALUE)));
		return gl_dealForm;

	}

	/**
	 * Press a button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "totalPrice":// Show total price of the deal
			try {
				if (checkEmpty()) {// If one of the fields is empty it will fail
					totalPriceeditorPane.getDocument().remove(0, totalPriceeditorPane.getDocument().getLength());
					Deal deal = setValues();
					totalPriceeditorPane.getDocument().insertString(0, deal.getTotalDealPrice() + "", null);
				}
			} catch (ParseException | IncorrectDateFormat | BadLocationException | InputIsEmpty | NoCarsInDeal
					| NoCarsInStock e2) {
				Factory.getInstance().subDealCounter();
				HandleExceptions.showException(e2);
			}
			break;

		case "addDeal":
			try {
				if (checkEmpty()) {// If one of the fields is empty it will fail
					Deal deal = setValues();// set the values
					Factory.getInstance().addDeal(deal);// add deal
					SerializableMethods.serialize();
				}
			} catch (ParseException | IncorrectDateFormat | IOException | InputIsEmpty | NoCarsInDeal
					| NoCarsInStock e1) {
				// TODO Auto-generated catch block
				HandleExceptions.showException(e1);
			}
		}
	}

	/**
	 * Get values from the form
	 *
	 * @return Deal deal
	 * @throws ParseException
	 * @throws IncorrectDateFormat
	 */
	private Deal setValues() throws ParseException, IncorrectDateFormat {

		if (customerComBox.getSelectedItem() instanceof VIPCustomer) {
			customer = (VIPCustomer) customerComBox.getSelectedItem();
		} else {
			customer = (Customer) customerComBox.getSelectedItem();
		}
		dealDate = TextFormattersClass.getDate(dateField.getText());
		allVehicleTransportation = new HashSet<>(vtInDealMap.values());
		shippingCost = Double.parseDouble(costField.getText());

		Deal deal = new Deal(customer, dealDate, allVehicleTransportation, shippingCost);
		return deal;

	}

	/**
	 * Remove from In stock list and add to the deal list
	 *
	 */
	private void addVTToDealList() {
		if (inStockCarList.getSelectedIndex() == -1) {
			return;
		}
		String str = inStockCarList.getSelectedValue().getLicensePlate();
		listModelStock.removeElement(vtInStockMap.get(str));
		vtInDealMap.put(str, vtInStockMap.get(str));
		listModelDeal.addElement(vtInDealMap.get(str));
	}

	/**
	 * Remove from the deal list and add to In stock list
	 *
	 */
	private void removeVtFromDealList() {
		if (inDealCarList.getSelectedIndex() == -1) {
			return;
		}
		String str = inDealCarList.getSelectedValue().getLicensePlate();
		listModelDeal.removeElement(vtInDealMap.get(str));
		// vtInStockMap.put(str, vtInDealMap.get(str));
		vtInDealMap.remove(str);
		listModelStock.addElement(vtInStockMap.get(str));

	}

	/**
	 * Initialise to Customer ComboBox
	 */
	private void initialiseComboBox() {
		customerComBox.removeAllItems();
		for (Customer cst : Factory.getInstance().getAllCustomers().values()) {
			customerComBox.addItem(cst);
		}

	}

	/**
	 * Initialise the Jlists
	 */
	private void initialiseJlists() {
		vtInStockMap = Factory.getInstance().getAllVehicleTransportation();
		vtInDealMap = new HashMap<>();

		listModelStock = new DefaultListModel<>();
		listModelDeal = new DefaultListModel<>();

		for (VehicleTransportation vt1 : vtInStockMap.values()) {
			listModelStock.addElement(vt1);
		}

		inStockCarList = new JList<>(listModelStock);
		inDealCarList = new JList<>(listModelDeal);
	}

	/**
	 *
	 * Check if on of the fields is empty
	 *
	 * @return boolean
	 * @throws InputIsEmpty
	 * @throws NoCarsInDeal
	 * @throws NoCarsInStock
	 */
	private boolean checkEmpty() throws InputIsEmpty, NoCarsInDeal, NoCarsInStock {
		if (customerComBox.getSelectedItem() == null) {
			throw new InputIsEmpty("Customer List");
		}
		if (Factory.getInstance().getAllVehicleTransportation().isEmpty() && vtInDealMap.isEmpty()) {
			throw new NoCarsInStock();
		}
		if (costField.getText().isEmpty()) {
			throw new InputIsEmpty(costField.getName());
		}
		if (vtInDealMap.isEmpty()) {
			throw new NoCarsInDeal();
		}
		return true;
	}
}
