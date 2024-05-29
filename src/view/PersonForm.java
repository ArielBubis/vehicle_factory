/**
 *
 */
package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import Exceptions.IncorrectDateFormat;
import Exceptions.InputIsEmpty;
import model.Factory;
import utils.Area;
import utils.Gender;
import utils.TypesInFactory;
import viewUtils.TextFormatterOnlyLetters;
import viewUtils.TextFormatterOnlyNumbers;
import viewUtils.TextFormattersClass;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class PersonForm extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	protected JPanel textFormPanel;
	protected JPanel contentPane;
	private JTextField id;
	private JTextField firstName;
	private JTextField lastName;
	private JFormattedTextField phoneNumber;
	private JFormattedTextField yearOfBirth;
	private JComboBox<Gender> genderComBox;
	private JButton saveBtn;
	private JFormattedTextField dateField;
	protected JPanel wrapperPanel;
	protected JPanel textJPanel;

	protected String idStr;
	protected String firstnameStr;
	protected String lastnameStr;
	protected String phoneNumberStr;
	protected Gender GenderStr;
	protected Area areaStr;
	protected int yearOfBirthStr;
	protected Date date;

	protected DefaultListModel<String> listModelString;

	private final ArrayList<JTextField> textFieldsArrList = new ArrayList<>();// List that gets all the different fields
																				// from each son class

	protected JPanel emplPicPanel;
	private JScrollPane scrollPane;
	private JTextPane listNameTextPane;
	private JList<String> changingList;

	private String text;

	/**
	 *
	 * @param str - name for the add function text
	 */
	public PersonForm(String str) {

		text = "ADD function of \n" + str;

		wrapperPanel = new JPanel();
		wrapperPanel.setBounds(0, 0, 600, 420);
		textJPanel = new JPanel();
		textJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JTextArea textArea = new JTextArea(3, 15);
		textArea.setTabSize(15);
		textArea.setText(text);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBackground(UIManager.getColor("Label.background"));
		textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		textArea.setBorder(new EmptyBorder(0, 0, 0, 0));
		textJPanel.add(textArea);

		saveBtn = new JButton("Save Person");
		saveBtn.setActionCommand("save");
		textJPanel.add(saveBtn);

		textFormPanel = new JPanel();
		textFormPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("ID");
		textFormPanel.add(lblNewLabel_1);

		TextFormatterOnlyNumbers idFormat = new TextFormatterOnlyNumbers();
		idFormat.setMaxLength(9);
		id = new JTextField();
		id.setName("ID");
		((AbstractDocument) id.getDocument()).setDocumentFilter(idFormat);
		textFormPanel.add(id);
		id.setColumns(10);
		textFieldsArrList.add(id);

		JLabel fNameLabel = new JLabel("First Name");
		textFormPanel.add(fNameLabel);

		firstName = new JTextField();
		((AbstractDocument) firstName.getDocument()).setDocumentFilter(new TextFormatterOnlyLetters());
		textFormPanel.add(firstName);
		firstName.setColumns(10);
		firstName.setName("First Name");
		textFieldsArrList.add(firstName);

		JLabel lNameLabel = new JLabel("Last Name");
		textFormPanel.add(lNameLabel);

		lastName = new JTextField();
		textFormPanel.add(lastName);
		((AbstractDocument) lastName.getDocument()).setDocumentFilter(new TextFormatterOnlyLetters());
		lastName.setColumns(10);
		lastName.setName("Last Name");
		textFieldsArrList.add(lastName);

		JLabel pNumberLabel = new JLabel("Phone Number");
		textFormPanel.add(pNumberLabel);

		phoneNumber = new JFormattedTextField(TextFormattersClass.getPhoneFormatter());
		textFormPanel.add(phoneNumber);
		phoneNumber.setValue("054-1234-567");
		phoneNumber.setColumns(12);
		phoneNumber.setName("Phone Number");
		textFieldsArrList.add(phoneNumber);

		JLabel genderLabel = new JLabel("Gender");
		textFormPanel.add(genderLabel);

		genderComBox = new JComboBox<>();
		genderComBox.setModel(new DefaultComboBoxModel<>(Gender.values()));
		textFormPanel.add(genderComBox);

		JLabel bYearLabel = new JLabel("Birth Year");
		textFormPanel.add(bYearLabel);
		TextFormatterOnlyNumbers yearFormat = new TextFormatterOnlyNumbers();
		yearFormat.setMaxLength(4);
		yearOfBirth = new JFormattedTextField();
		((AbstractDocument) yearOfBirth.getDocument()).setDocumentFilter(yearFormat);
		yearOfBirth.setColumns(10);
		yearOfBirth.setName("Birth Year");
		textFieldsArrList.add(yearOfBirth);
		textFormPanel.add(yearOfBirth);

		JLabel date1Label = new JLabel("Date");
		textFormPanel.add(date1Label);

		dateField = new JFormattedTextField(TextFormattersClass.MyDateFormmater());
		dateField.setValue(new Date());
		dateField.setName("Date");
		textFormPanel.add(dateField);
		textFieldsArrList.add(dateField);
		saveBtn.addActionListener(this);

		emplPicPanel = new JPanel();

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		listNameTextPane = new JTextPane();
		listNameTextPane.setBackground(UIManager.getColor("Button.background"));
		scrollPane.setColumnHeaderView(listNameTextPane);
		listNameTextPane.setText("All " + str + " List");

		listModelString = new DefaultListModel<>();
		listModelString = InitialseListsCalss.initialiseJlist(null, listModelString);
		changingList = new JList<>(listModelString);
		changingList.setEnabled(false);

		scrollPane.setViewportView(changingList);
		initialiseLayount();

	}

	private void initialiseLayount() {

		GroupLayout gl_wrapperPanel = new GroupLayout(wrapperPanel);
		gl_wrapperPanel.setHorizontalGroup(gl_wrapperPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_wrapperPanel.createSequentialGroup().addGroup(gl_wrapperPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_wrapperPanel.createSequentialGroup().addGap(7)
								.addGroup(gl_wrapperPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_wrapperPanel.createSequentialGroup()
												.addComponent(textJPanel, GroupLayout.DEFAULT_SIZE, 450,
														Short.MAX_VALUE)
												.addGap(4))
										.addComponent(textFormPanel, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)))
						.addGroup(gl_wrapperPanel.createSequentialGroup().addGap(27).addComponent(scrollPane,
								GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)))
						.addGap(18)
						.addComponent(emplPicPanel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addGap(19)));
		gl_wrapperPanel
				.setVerticalGroup(gl_wrapperPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_wrapperPanel.createSequentialGroup().addGroup(gl_wrapperPanel
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(gl_wrapperPanel.createSequentialGroup().addGap(7)
										.addComponent(textJPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(textFormPanel, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 164,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_wrapperPanel.createSequentialGroup().addContainerGap()
										.addComponent(emplPicPanel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)))
								.addContainerGap()));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(wrapperPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(34)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(23)
						.addComponent(wrapperPanel, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE).addGap(77)));
		setLayout(groupLayout);
		wrapperPanel.setLayout(gl_wrapperPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	protected void setValuesOfparent() throws ParseException, IncorrectDateFormat {
		idStr = getIDStr();
		firstnameStr = getFirstnameStr();
		lastnameStr = getLastnameStr();
		phoneNumberStr = getPhoneNumberStr();
		GenderStr = getGenderStr();
		yearOfBirthStr = getYearOfBirthStr();
		date = getDate();
	}

	protected String getIDStr() {
		return id.getText();
	}

	protected String getFirstnameStr() {
		return firstName.getText();
	}

	protected String getLastnameStr() {
		return lastName.getText();
	}

	protected String getPhoneNumberStr() {
		return phoneNumber.getText();
	}

	protected Gender getGenderStr() {
		return (Gender) genderComBox.getSelectedItem();
	}

	protected int getYearOfBirthStr() {
		return Integer.parseInt(yearOfBirth.getText());
	}

	protected Date getDate() throws ParseException, IncorrectDateFormat {
		return TextFormattersClass.getDate(dateField.getText());
	}

	/**
	 * Using the ArrList of the jtextFields we can easily check if fields are empty
	 *
	 * @return boolean
	 * @throws InputIsEmpty - input is empty
	 */
	protected boolean checkEmpty() throws InputIsEmpty {
		for (JTextField field : textFieldsArrList) {// iterate on the list
			if (field instanceof JFormattedTextField) {// check value for JFormattedTextField
				if (!((JFormattedTextField) field).isEditValid()) {
					throw new InputIsEmpty(field.getName());
				}
			}
			if (field.getText().isEmpty()) {// Check text for textfield
				throw new InputIsEmpty(field.getName());
			}
		}
		return true;
	}

	/**
	 * Add to the textfield components list to late check if empty
	 *
	 * @param jtf - the component to add to the arraylist
	 */
	protected void addFieldToList(JTextField jtf) {
		textFieldsArrList.add(jtf);
	}

	protected void initialiseJlist(TypesInFactory type) {
		listModelString = InitialseListsCalss.initialiseJlist(type, listModelString);
		changingList = new JList<>(listModelString);
		if (type.equals(TypesInFactory.All_Employees)) {// The lists of employees and managers is not combined in the
														// MapOfAllMap, so for easier use we combined them them here
			if (Factory.getInstance().mapOfAllMaps().get(TypesInFactory.All_Department_Managers) != null) {
				for (String s : Factory.getInstance().mapOfAllMaps().get(TypesInFactory.All_Department_Managers)) {
					listModelString.addElement(s);
				}
			}
		}
		if (type.equals(TypesInFactory.All_Department_Managers)) {
			if (Factory.getInstance().mapOfAllMaps().get(TypesInFactory.All_Employees) != null) {
				for (String s : Factory.getInstance().mapOfAllMaps().get(TypesInFactory.All_Employees)) {
					listModelString.addElement(s);
				}
			}
		}
	}
}
