/**
 *
 */
package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.AbstractDocument;

import Exceptions.HandleExceptions;
import Exceptions.IncorrectDateFormat;
import Exceptions.InputIsEmpty;
import Exceptions.NoDepartments;
import Exceptions.WrongImageFileException;
import model.Department;
import model.Employee;
import model.Factory;
import model.PersonAlreadyExistException;
import model.YearOfBirthNotInRange;
import utils.TypesInFactory;
import viewUtils.FileTypeFilter;
import viewUtils.SerializableMethods;
import viewUtils.TextFormatterOnlyNumbers;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class AddEmployeePanel extends PersonForm {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField salaryField;
	private JTextField passField;
	private JButton addDepartmentBtn;
	protected JLabel picLabel;
	private JButton uploadPicBtn;
	protected FileFilter pngFilter = new FileTypeFilter(".png", "PNG File");
	protected FileFilter jpgFilter = new FileTypeFilter(".jpg", "JPG File");

	protected Department dep;
	protected ImageIcon finalImage;
	protected JComboBox<String> depComBox;
	protected double salary;
	protected String filename;
	protected String passStr;
	protected String userName;

	private JLabel salaryLabel;
	private JLabel depLabel;
	private JLabel passLabel;
	protected JButton updateListBtn;

	/**
	 * Create the panel.
	 */
	public AddEmployeePanel(String str) {
		super(str);
		initialiseJlist(TypesInFactory.All_Employees);
		initialiseAllComponentsSet();
		initialseComboBox();
		addToPanel();
	}

	private void initialiseAllComponentsSet() {
		// Salary field label textField and formatter
		salaryLabel = new JLabel("Salary");

		textFormPanel.add(salaryLabel);
		salaryField = new JTextField();
		TextFormatterOnlyNumbers salaryFormatter = new TextFormatterOnlyNumbers();
		salaryFormatter.setMaxLength(11);
		salaryFormatter.isDouble();
		((AbstractDocument) salaryField.getDocument()).setDocumentFilter(salaryFormatter);
		salaryField.setName("Salary");
		salaryField.setColumns(20);
		// Department label and ComboBox
		depLabel = new JLabel("Department");
		depComBox = new JComboBox<>();

		// Password label and field
		passLabel = new JLabel("Password");
		passField = new JTextField();
		passField.setName("Password");
		passField.setColumns(10);

		// Button to open the add department pop-up window

		addDepartmentBtn = new JButton("Add Departmnet");
		addDepartmentBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame depFrame = new PopUpAddDepartmentForm();
				depFrame.setVisible(true);
			}
		});
		addDepartmentBtn.setBounds(0, 246, 124, 19);
		// Update the list button, I coudn't figure out a better way to update the
		// comboBox, so I chose to add the update button
		updateListBtn = new JButton("Update Dep List");
		updateListBtn.addActionListener(this);
		updateListBtn.setActionCommand("update");
		updateListBtn.setBounds(0, 270, 124, 21);

		// Image label and upload button

		picLabel = new JLabel();
		picLabel.setHorizontalAlignment(SwingConstants.CENTER);
		picLabel.setBounds(10, 10, 114, 123);
		picLabel.setText("No Image Uploaded");

		uploadPicBtn = new JButton("Upload Picture");
		uploadPicBtn.setActionCommand("upload");
		uploadPicBtn.addActionListener(this);
		uploadPicBtn.setBounds(0, 143, 124, 21);
	}

	private void addToPanel() {
		textFormPanel.add(salaryField);
		addFieldToList(salaryField);
		textFormPanel.add(depLabel);
		textFormPanel.add(depComBox);

		textFormPanel.add(passLabel);
		textFormPanel.add(passField);
		addFieldToList(passField);
		emplPicPanel.setLayout(null);
		emplPicPanel.add(addDepartmentBtn);
		emplPicPanel.add(updateListBtn);
		emplPicPanel.add(picLabel);
		emplPicPanel.add(uploadPicBtn);

	}

	/**
	 *
	 */
	private void initialseComboBox() {
		if (Factory.getInstance().getAllDepartments().isEmpty()) {
			depComBox.addItem("Please create a new department");
			depComBox.setEnabled(false);
			depComBox.setForeground(Color.BLACK);
			return;
		} else {
			depComBox.removeAllItems();
			depComBox.setEnabled(true);

			for (Department d : Factory.getInstance().getAllDepartments().values()) {
				depComBox.addItem(d.getDepartmentID() + "-" + d.getSpecialization());
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "update":
			initialseComboBox();
			break;
		case "save":// Check field and add employee
			try {
				if (checkEmpty()) {
					setValues();
					Employee emp = new Employee(idStr, firstnameStr, lastnameStr, phoneNumberStr, GenderStr, date,
							salary, dep, yearOfBirthStr, passStr, finalImage,userName);
					Factory.getInstance().addEmployee(emp);
					SerializableMethods.serialize();
					initialiseJlist(TypesInFactory.All_Employees);

				}

			} catch (PersonAlreadyExistException | YearOfBirthNotInRange | ParseException | IncorrectDateFormat
					| IOException | NumberFormatException | InputIsEmpty | NoDepartments e2) {
				// TODO Auto-generated catch block
				HandleExceptions.showException(e2);
			}
			break;

		case "upload":// Upload image
			JFileChooser chooser = new JFileChooser();// create a File Chooser object
			chooser.addChoosableFileFilter(pngFilter);// Set file filters
			chooser.addChoosableFileFilter(jpgFilter);
			chooser.showOpenDialog(null);
			File f = chooser.getSelectedFile();

			filename = f.getAbsolutePath();
			// below code selects the file
			BufferedImage bi;
			try {
				// display the image in a Jlabel
				bi = ImageIO.read(f);
				if (bi == null) {
					throw new WrongImageFileException();
				}
				ImageIcon imageIcon = new ImageIcon(bi); // load the image to a imageIcon
				Image image = imageIcon.getImage(); // transform it
				Image newimg = image.getScaledInstance(114, 123, java.awt.Image.SCALE_SMOOTH); // scale it the
																								// smooth way
				finalImage = new ImageIcon(newimg); // transform it back
				picLabel.setText("");

				picLabel.setIcon(finalImage);// Set image

			} catch (IOException | WrongImageFileException e1) {
				HandleExceptions.showException(e1);
			}
			break;
		}

	}

	/**
	 * setValues
	 *
	 * @throws ParseException
	 * @throws IncorrectDateFormat
	 * @throws NoDepartments
	 */
	protected void setValues() throws ParseException, IncorrectDateFormat, NoDepartments {
		setValuesOfparent();
		salary = getSalary();
		dep = getDepartment();
		passStr = getPasswordString();
		userName = getUserName();
	}

	/**
	 * @return
	 */
	private String getUserName() {
		return getFirstnameStr()+getIDStr();
	}

	/**
	 * I used the string which looks like this:(DepId - Specialisation) from the
	 * comboBox, subString the dep number only and use getReal to get the department
	 *
	 * @return Department from the comboBox,
	 * @throws NoDepartments
	 */
	protected Department getDepartment() throws NoDepartments {
		String depChosen = depComBox.getSelectedItem().toString();
		try {
			depChosen = depChosen.substring(0, depChosen.indexOf('-'));

		} catch (StringIndexOutOfBoundsException e) {
			throw new NoDepartments();
		}
		return Factory.getInstance().getRealDepartment(Integer.parseInt(depChosen));
	}

	/**
	 *
	 * @return Salary
	 */
	protected double getSalary() {
		return Double.parseDouble(salaryField.getText());
	}

	/**
	 *
	 * @return Password
	 */
	protected String getPasswordString() {
		return passField.getText();
	}

	/**
	 *
	 * @return Profile Image
	 */
	protected ImageIcon getprofileImage() {
		return finalImage;
	}

	/**
	 * @return the filename
	 */
	protected String getFilename() {
		return filename;
	}

	/**
	 * @return the passStr
	 */
	protected String getPassStr() {
		return passStr;
	}

}