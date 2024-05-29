/**
 *
 */
package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import Exceptions.InputIsEmpty;
import utils.Color;
import viewUtils.TextFormatterOnlyNumbers;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class VehicleTransportationPanel extends JPanel implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected JTextField priceField;
	protected JTextField costOfManufacturingField;
	protected JTextField yearOfManufactureField;
	protected JTextField pollutionLevelField;
	protected JButton saveBtn;
	protected JPanel textFormPanel;
	protected JTextField engineCapacityField;
	protected JComboBox<Color> colorComboBox;


	protected Double price;
	protected double costOfManufacturing;
	protected Color color;
	protected int yearOfManufacture;
	protected double engineCapacity;
	protected int pollutionLevel;

	private final ArrayList<JTextField> textFields = new ArrayList<>();
	protected JPanel wrapperPanel;

	/**
	 * Create the frame.
	 */
	public VehicleTransportationPanel(String str) {
		wrapperPanel = new JPanel();
		wrapperPanel.setBounds(0, 0, 300, 420);
		JPanel panel = new JPanel();
		panel.setBounds(42, 40, 192, 89);
		String text = "ADD function of \n" + str;
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JTextArea textArea = new JTextArea(3, 15);
		textArea.setTabSize(15);
		textArea.setText(text);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBackground(UIManager.getColor("Label.background"));
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		textArea.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.add(textArea);

		saveBtn = new JButton("Save Vehicle");
		panel.add(saveBtn);
		saveBtn.addActionListener(this);

		textFormPanel = new JPanel();
		textFormPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel priceJLabel = new JLabel("Price");
		textFormPanel.add(priceJLabel);

		priceField = new JTextField();
		TextFormatterOnlyNumbers priceFormatter = new TextFormatterOnlyNumbers();
		priceFormatter.isDouble();
		((AbstractDocument) priceField.getDocument()).setDocumentFilter(priceFormatter);
		priceField.setName("Price");
		textFormPanel.add(priceField);
		priceField.setColumns(10);
		textFields.add(priceField);

		JLabel costLabel = new JLabel("Cost of Manufacturing");
		textFormPanel.add(costLabel);

		costOfManufacturingField = new JTextField();
		TextFormatterOnlyNumbers costFormatter = new TextFormatterOnlyNumbers();
		costFormatter.isDouble();
		((AbstractDocument) costOfManufacturingField.getDocument()).setDocumentFilter(costFormatter);
		textFormPanel.add(costOfManufacturingField);
		costOfManufacturingField.setColumns(10);
		costOfManufacturingField.setName("Cost of Manufacturing");
		textFields.add(costOfManufacturingField);

		JLabel colorLabel = new JLabel("Color");
		textFormPanel.add(colorLabel);


		colorComboBox = new JComboBox<>();
		colorComboBox.setModel(new DefaultComboBoxModel<>(Color.values()));
		textFormPanel.add(colorComboBox);


		JLabel lblNewLabel_4 = new JLabel("Year Of Manufacture");
		textFormPanel.add(lblNewLabel_4);

		yearOfManufactureField = new JTextField();
		TextFormatterOnlyNumbers yearOfManufactureFormatter = new TextFormatterOnlyNumbers();
		yearOfManufactureFormatter.setMaxLength(4);
		((AbstractDocument) yearOfManufactureField.getDocument()).setDocumentFilter(yearOfManufactureFormatter);
		textFormPanel.add(yearOfManufactureField);
		yearOfManufactureField.setName("Year Of Manufacture");
		textFields.add(yearOfManufactureField);


		JLabel lblNewLabel_5 = new JLabel("Engine Capacity");
		textFormPanel.add(lblNewLabel_5);

		engineCapacityField = new JTextField();
		TextFormatterOnlyNumbers engineCapacityFormatter = new TextFormatterOnlyNumbers();
		engineCapacityFormatter.isDouble();
		((AbstractDocument) engineCapacityField.getDocument()).setDocumentFilter(engineCapacityFormatter);
		engineCapacityField.setName("Engine Capacity");
		textFormPanel.add(engineCapacityField);
		engineCapacityField.setColumns(10);
		textFields.add(engineCapacityField);

		JLabel lblNewLabel_6 = new JLabel("Pollution level");
		textFormPanel.add(lblNewLabel_6);

		pollutionLevelField = new JTextField();
		TextFormatterOnlyNumbers pollutionLevelFormat= new TextFormatterOnlyNumbers();
		((AbstractDocument) pollutionLevelField.getDocument()).setDocumentFilter(pollutionLevelFormat);
		pollutionLevelField.setColumns(10);
		pollutionLevelField.setName("Pollution level");
		textFields.add(pollutionLevelField);
		textFormPanel.add(pollutionLevelField);

		GroupLayout gl_wrapperPanel = new GroupLayout(wrapperPanel);
		gl_wrapperPanel.setHorizontalGroup(
			gl_wrapperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_wrapperPanel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_wrapperPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_wrapperPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(textFormPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(85))
		);
		gl_wrapperPanel.setVerticalGroup(
			gl_wrapperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_wrapperPanel.createSequentialGroup()
					.addGap(7)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(textFormPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		wrapperPanel.setLayout(gl_wrapperPanel);
		Dimension dm= new Dimension(wrapperPanel.getWidth()+20, wrapperPanel.getHeight());
		wrapperPanel.setPreferredSize(dm);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(219))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(textFormPanel, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
					.addGap(215))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(wrapperPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(65))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(textFormPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(wrapperPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public void setValuesParent() {
//		price = Double.parseDouble(priceField.getText());
//		costOfManufacturing = Double.parseDouble(costOfManufacturingField.getText());
//		color = (Color) colorComboBox.getSelectedItem();
//		yearOfManufacture = Integer.parseInt(yearOfManufactureField.getText());
//		engineCapacity = Double.parseDouble(engineCapacityField.getText());
//		pollutionLevel = Integer.parseInt(pollutionLevelField.getText());
		price = getPrice();
		costOfManufacturing = getCostOfManufacturing();
		color = getColor();
		yearOfManufacture =getYearOfManufacture();
		engineCapacity = getEngineCapacity();
		pollutionLevel = getPollutionLevel();
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return Double.parseDouble(priceField.getText());
	}

	/**
	 * @return the costOfManufacturing
	 */
	public double getCostOfManufacturing() {
		return Double.parseDouble(costOfManufacturingField.getText());
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return (Color) colorComboBox.getSelectedItem();
	}

	/**
	 * @return the yearOfManufacture
	 */
	public int getYearOfManufacture() {
		return Integer.parseInt(yearOfManufactureField.getText());
	}

	/**
	 * @return the engineCapacity
	 */
	public double getEngineCapacity() {
		return Double.parseDouble(engineCapacityField.getText());
	}

	/**
	 * @return the pollution Level
	 */
	public int getPollutionLevel() {
		return Integer.parseInt(pollutionLevelField.getText());
	}


	public boolean checkEmpty() throws InputIsEmpty{
		for (JTextField field : textFields) {
			if(field instanceof JFormattedTextField) {
				if(!((JFormattedTextField) field).isEditValid()) {
					throw new InputIsEmpty(field.getName());
				}
			}
			if(field.getText().isEmpty()) {
				throw new InputIsEmpty(field.getName());
			}
		}
		return true;
	}

	public void addFieldToList(JTextField jtf) {
		textFields.add(jtf);
	}
}
