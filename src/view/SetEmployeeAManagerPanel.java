/**
 *
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;

import Exceptions.AllDepHasManager;
import Exceptions.HandleExceptions;
import Exceptions.NoDepartments;
import Exceptions.NoEmployeesException;
import model.Department;
import model.Employee;
import model.Factory;
import viewUtils.SerializableMethods;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class SetEmployeeAManagerPanel extends JPanel implements ActionListener {
	private JPanel setNewDepPanel;
	private JComboBox<Department> depComboBox;
	private JTextPane seniorEmp;
	private JButton appntManagerBtn;

	public SetEmployeeAManagerPanel() throws NoDepartments, AllDepHasManager {

		setNewDepPanel = new JPanel();

		JLabel depSelectLabel = new JLabel("Select Department:");

		JLabel lblNewLabel = new JLabel("Set a new manager in existing department");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		depComboBox = new JComboBox<>();
		initialiseComboBox();

		JLabel empSelectLabel = new JLabel("Most Senior Employee:");

		seniorEmp = new JTextPane();
		seniorEmp.setName("Senior employee field");
		seniorEmp.setBackground(Color.LIGHT_GRAY);
		seniorEmp.setEditable(false);

		depComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showSeniorEmployee();
			}
		});

		appntManagerBtn = new JButton("Appoint a new Manager");
		appntManagerBtn.addActionListener(this);

		GroupLayout gl_panel = new GroupLayout(setNewDepPanel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(depComboBox, 0, 288, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(empSelectLabel)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(appntManagerBtn, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
										.addComponent(seniorEmp, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGap(130)
							.addComponent(depSelectLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(depSelectLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(depComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(empSelectLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(seniorEmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(appntManagerBtn)
					.addContainerGap(80, Short.MAX_VALUE))
		);
		setNewDepPanel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(setNewDepPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(79))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(setNewDepPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent e) {
		Department department = (Department) depComboBox.getSelectedItem();
		try {
			if (Factory.getInstance().findSeniorEmployee(department)
					.getID() == "temp") {
				throw new NoEmployeesException();
			}
			Factory.getInstance().appointmentOfNewDepartmentManager(department);
			SerializableMethods.serialize();
			initialiseComboBox();
			} catch (IOException | NoEmployeesException | NoDepartments | AllDepHasManager e1) {
			// TODO Auto-generated catch block
			HandleExceptions.showException(e1);
		}
	}

	private void initialiseComboBox() throws NoDepartments, AllDepHasManager {
		List<Department> noMenDepList = new ArrayList<>();
		if (Factory.getInstance().getAllDepartments().isEmpty()) {
			throw new NoDepartments();
		} else {
			for (Department d : Factory.getInstance().getAllDepartments().values()) {
				if (d.getDepartmentManager() == null) {
					noMenDepList.add(d);
					depComboBox.addItem(d);
				}
			}
		}
		if (noMenDepList.isEmpty()) {
			depComboBox.removeAllItems();
			throw new AllDepHasManager();
		}

	}

	/**
	 * Using the findSeniorEmployee function from Factore class we can find and
	 * print out the most senior employee of the deprtment if there are any
	 * employees present
	 */
	private void showSeniorEmployee() {
		if((Department) depComboBox.getSelectedItem() == null) {
			return;
		}
		Employee emp = Factory.getInstance().findSeniorEmployee((Department) depComboBox.getSelectedItem());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			seniorEmp.getDocument().remove(0, seniorEmp.getDocument().getLength());

			if (emp.getID() != "temp") {
				seniorEmp.getDocument().insertString(0, "Employee name:" + emp.getFirstName() + " " + emp.getLastName()
						+ " |Date of Start work " + sdf.format(emp.getDateOfStartWork()), null);

			} else {
				seniorEmp.getDocument().insertString(0, "No employees in this department", null);
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			HandleExceptions.showException(e);
		}
	}

}
