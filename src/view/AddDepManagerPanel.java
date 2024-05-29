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
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import Exceptions.HandleExceptions;
import Exceptions.IncorrectDateFormat;
import Exceptions.InputIsEmpty;
import Exceptions.NoDepartments;
import Exceptions.WrongImageFileException;
import model.Department;
import model.DepartmentManager;
import model.Factory;
import model.PersonAlreadyExistException;
import model.YearOfBirthNotInRange;
import utils.TypesInFactory;
import viewUtils.SerializableMethods;
import viewUtils.TextFormatterOnlyNumbers;
import viewUtils.TextFormattersClass;

/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class AddDepManagerPanel extends AddEmployeePanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField bonusField;
	private JFormattedTextField appoitmentDateField;
	private Date appDateStr;
	private double bonusStr;
	private JLabel appointmentLabel;
	private JLabel salaryBonusLabel;

	/**
	 * Create the panel.
	 */
	public AddDepManagerPanel(String str) {
		super(str);
		initialiseJlist(TypesInFactory.All_Department_Managers);
		initialseComboBox();
		initialiseAllComponentsSet();
		addToPanel();

	}

	private void initialiseAllComponentsSet() {
		updateListBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {//overRide the action preformed in father class
				initialseComboBox();
			}
		});
		// Date field label and textfield
		appointmentLabel = new JLabel("Appointment Date");
		// Bonus field
		salaryBonusLabel = new JLabel("Salary Bonus");
		textFormPanel.add(salaryBonusLabel);

		bonusField = new JTextField();
		bonusField.setName("Bonus");
		TextFormatterOnlyNumbers bonusFormat = new TextFormatterOnlyNumbers();
		bonusFormat.isDouble();// Use the double formatter(Allow '.' char)
		((AbstractDocument) bonusField.getDocument()).setDocumentFilter(bonusFormat);
		bonusField.setColumns(10);

		appoitmentDateField = new JFormattedTextField(TextFormattersClass.MyDateFormmater());
		appoitmentDateField.setValue(new Date());
		appoitmentDateField.setName("Appointment Date");

	}

	private void addToPanel() {

		textFormPanel.add(bonusField);
		addFieldToList(bonusField);
		textFormPanel.add(appointmentLabel);
		textFormPanel.add(appoitmentDateField);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "save":// Check field and add employee
			try {
				if (checkEmpty()) {
					setValues();
					bonusStr = Double.parseDouble(bonusField.getText());
					date = getDate();
					appDateStr = TextFormattersClass.getDate(appoitmentDateField.getText());
					finalImage = getprofileImage();
					DepartmentManager depMen = new DepartmentManager(idStr, firstnameStr, lastnameStr, phoneNumberStr,
							GenderStr, date, salary, dep, yearOfBirthStr, appDateStr, bonusStr, passStr, finalImage,
							userName);
					Factory.getInstance().addDepartmentManager(depMen);
					SerializableMethods.serialize();
					initialiseJlist(TypesInFactory.All_Department_Managers);
					initialseComboBox();
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

	private void initialseComboBox() {
		depComBox.removeAllItems();
		boolean isEmpty = true;
		if (!Factory.getInstance().getAllDepartments().isEmpty()) {
			depComBox.removeAllItems();
			depComBox.setEnabled(true);

			for (Department d : Factory.getInstance().getAllDepartments().values()) {
				if (d.getDepartmentManager() == null) {// Add to the ComboBox only departments without a manager to
														// avoid user error
					isEmpty = false;
					depComBox.addItem(d.getDepartmentID() + "-" + d.getSpecialization());
				}
			}
		}
		if (isEmpty) {// If the list is empty, disable it and set the text
			depComBox.addItem("Please create a new department");
			depComBox.setEnabled(false);
			depComBox.setForeground(Color.BLACK);
		}

	}
}
