/**
 *
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;

import model.Deal;
import model.DepartmentManager;
import model.Employee;
import model.Factory;
import model.VehicleTransportation;
import viewUtils.TextFormatterOnlyNumbers;
import javax.swing.ScrollPaneConstants;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class QueriesForm extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextPane totoalProfitTextPane;
	private JComboBox<Deal> dealComboBox;
	private JTextPane pollByDealTextPane;
	private JTextField listLengthTextField;
	private JList<Employee> empByPKList;
	private JButton paintEmpByPKListButton;
	private DefaultListModel<Employee> listModelEmp;
	private DefaultListModel<DepartmentManager> listModelDep;
	private JList<DepartmentManager> bestDepManList;
	private JComboBox<VehicleTransportation> profitPerVTcomboBox;
	private JTextPane profitPerVTTextPane;
	private JLabel empListHeader;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel_2;
	private JScrollPane emplPKListscrollPane;
	private JLabel lblNewLabel_3;
	private JLabel avgPolLabel;
	private JPanel panel;
	private JLabel headeQueriesLabel;


	public QueriesForm() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);


		headeQueriesLabel = new JLabel("Queries");
		headeQueriesLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		headeQueriesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		/******** avgPollutionLevelOfDeal ***X*****/

		avgPolLabel = new JLabel("Get avarage pollution of deal");

		dealComboBox = new JComboBox<>();
		pollByDealTextPane = new JTextPane();
		pollByDealTextPane.setEditable(false);
		pollByDealTextPane.setBackground(Color.LIGHT_GRAY);

		dealComboBox.addActionListener(new ActionListener() {//When an element is selected show the average pollution output on the pollByDealTextPane

			@Override
			public void actionPerformed(ActionEvent e) {

				String pollOfDeal = Double.toString(Factory.getInstance().avgPollutionLevelOfDeal((Deal) dealComboBox.getSelectedItem()));
				pollByDealTextPane.setText("Avarage pollution of deal "+((Deal)dealComboBox.getSelectedItem()).getDealID()+" is:" + pollOfDeal);
			}
		});

		/*****profitPerVehiclesTransportation****/

		lblNewLabel_3 = new JLabel("Profit Per Vehicles");
		profitPerVTcomboBox = new JComboBox<>();

		emplPKListscrollPane = new JScrollPane();
		emplPKListscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		profitPerVTTextPane = new JTextPane();

		profitPerVTTextPane.setEditable(false);
		profitPerVTTextPane.setBackground(SystemColor.info);

		profitPerVTcomboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VehicleTransportation vt = (VehicleTransportation) profitPerVTcomboBox.getSelectedItem();
				String str = Double. toString(Factory.getInstance().profit.profitPerVehiclesTransportation(vt));
				profitPerVTTextPane.setText(str);
			}
		});
		/******************************************/
		initializeComboBox();

		/***************Totalprofit*****V*******/

		lblNewLabel_2 = new JLabel("Total Profit Of Factory:");

		totoalProfitTextPane = new JTextPane();
		totoalProfitTextPane.setText(Integer.toString((Factory.getInstance().totalProfit())));
		totoalProfitTextPane.setEditable(false);
		totoalProfitTextPane.setBackground(SystemColor.inactiveCaption);
		/***************Totalprofit*************/


		/*************************************/
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		/*************************************/
		/***************All Employees list****************/

		lblNewLabel_5 = new JLabel("List Length:");

		listLengthTextField = new JTextField();//Set the K(List length)
		TextFormatterOnlyNumbers lengthFieldFormat = new TextFormatterOnlyNumbers();
		((AbstractDocument) listLengthTextField.getDocument()).setDocumentFilter(lengthFieldFormat);
		listModelEmp = new DefaultListModel<>();
		listLengthTextField.setColumns(10);
		empByPKList = new JList<>(listModelEmp);
		empByPKList.setEnabled(false);
		emplPKListscrollPane.setViewportView(empByPKList);

		paintEmpByPKListButton = new JButton("Show List");
		paintEmpByPKListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paintEmpByPkList(Integer.parseInt(listLengthTextField.getText()));
			}
		});


		lblNewLabel_4 = new JLabel("All Best Managers");
		scrollPane_1.setColumnHeaderView(lblNewLabel_4);
		listModelDep = new DefaultListModel<>();
		bestDepManList = new JList<>(listModelDep);
		bestDepManList.setEnabled(false);
		initialiseDepList();
		scrollPane_1.setViewportView(bestDepManList);

		empListHeader = new JLabel("All Employees By PK");
		emplPKListscrollPane.setColumnHeaderView(empListHeader);

		initialiseLayout();
	}

	private void initialiseLayout() {
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(headeQueriesLabel, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(avgPolLabel, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
									.addGap(105))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(114))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(26)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(profitPerVTcomboBox, 0, 314, Short.MAX_VALUE)
								.addComponent(profitPerVTTextPane, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
								.addComponent(pollByDealTextPane, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
								.addComponent(totoalProfitTextPane, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
								.addComponent(dealComboBox, 0, 314, Short.MAX_VALUE))
							.addGap(20))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(emplPKListscrollPane, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
									.addGap(20))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(listLengthTextField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(paintEmpByPKListButton)
									.addPreferredGap(ComponentPlacement.RELATED, 416, Short.MAX_VALUE)))))
					.addGap(0))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(headeQueriesLabel, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(avgPolLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(dealComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pollByDealTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2)
						.addComponent(totoalProfitTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(profitPerVTcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(profitPerVTTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(listLengthTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(paintEmpByPKListButton))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(emplPKListscrollPane)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
					.addGap(119))
		);
		panel.setLayout(gl_panel);

	}
	/**
	 * Initialise the Deal Combo box
	 */
	private void initializeComboBox() {
		for (Deal d : Factory.getInstance().getAllDeals().values()) {
			dealComboBox.addItem(d);
		}
		for (VehicleTransportation vt : Factory.getInstance().getAllVehicleTransportation().values()) {
			profitPerVTcomboBox.addItem(vt);
		}

	}
	/**
	 * initialise the all best Dep managers list
	 */
	private void initialiseDepList() {
		listModelDep.removeAllElements();
		bestDepManList.removeAll();
		List<DepartmentManager> list= Factory.getInstance().AllBestsDepManger();
		for (DepartmentManager employee : list) {
			listModelDep.addElement(employee);
		}
	}

	/**
	 * Paint the Employee ordered by PK Jlist
	 */
	private void paintEmpByPkList(int k) {
		listModelEmp.removeAllElements();
		empByPKList.removeAll();
		List<Employee> list= Factory.getInstance().allEmployees(k);
		for (Employee employee : list) {
			listModelEmp.addElement(employee);
		}
	}
}
