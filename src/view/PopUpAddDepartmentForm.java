/**
 *
 */
package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;

import Exceptions.AllDepartmentsCrated;
import Exceptions.HandleExceptions;
import Exceptions.InputIsEmpty;
import model.Department;
import model.DepartmentAlredyExsist;
import model.Factory;
import utils.Specialization;
import viewUtils.SerializableMethods;
import viewUtils.TextFormatterOnlyNumbers;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class PopUpAddDepartmentForm extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField depIDField;
	private JComboBox<Specialization> specComboBox;
	private JLabel depIDLabel;
	private JComponent addDepLabel;
	private JPanel panel;
	private JLabel specLabel;
	private JButton addDepBtn;

	private int depID;
	private Specialization spec;

	public PopUpAddDepartmentForm() {

		panel = new JPanel();
		setSize(300, 300);
		setResizable(false);

		setLocationRelativeTo(null);
		initialseComponenets();


		/******************************************************/

		initialseLayout();
	}

	private void initialseComponenets() {
		//Header
		addDepLabel = new JLabel("Add Department",SwingConstants.CENTER);
		addDepLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		//Dep ID field
		depIDLabel = new JLabel("Department ID");
		depIDField = new JTextField();
		depIDField.setName("Department ID");
		TextFormatterOnlyNumbers depIDFormatter = new TextFormatterOnlyNumbers();
		depIDFormatter.setMaxLength(11);
		depIDFormatter.isDouble();
		((AbstractDocument) depIDField.getDocument()).setDocumentFilter(depIDFormatter);
		depIDField.setColumns(10);

		//Specialisations combobox
		specLabel = new JLabel("Specializtion");
		specComboBox = new JComboBox<>();
		initialiseCombox();
		//Button to add deprtment
		addDepBtn = new JButton("Add New Department");
		addDepBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!depIDField.getText().isEmpty()) {
						depID = Integer.parseInt(depIDField.getText());
						spec = (Specialization) specComboBox.getSelectedItem();
						Department dep = new Department(depID, spec);
						Factory.getInstance().addDepartment(dep);
						SerializableMethods.serialize();
						initialiseCombox();

					} else {
						throw new InputIsEmpty(depIDField.getName());
					}
				} catch (InputIsEmpty | IOException | DepartmentAlredyExsist e2) {
					HandleExceptions.showException(e2);
				}

			}
		});

	}


	private void initialseLayout() {
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(addDepLabel, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
							.addGap(42))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(specLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(depIDLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(depIDField)
								.addComponent(specComboBox, 0, 122, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(addDepBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(61)))
					.addGap(84))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(addDepLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(depIDLabel)
						.addComponent(depIDField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(specLabel)
						.addComponent(specComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(addDepBtn)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);

		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}


	private void initialiseCombox() {
		specComboBox.setEnabled(true);
		try {
			specComboBox.removeAllItems();
			for(Specialization s: Factory.getInstance().specializationsList(true).toArray(new Specialization[0])){
				specComboBox.addItem(s);
			}
		} catch (AllDepartmentsCrated e1) {
			specComboBox.setEnabled(false);
			HandleExceptions.showException(e1);
		}
	}

}
