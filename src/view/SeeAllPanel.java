/**
 *
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;

import Exceptions.NoObjectsToRemove;
import model.Factory;
import utils.TypesInFactory;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class SeeAllPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<TypesInFactory, List<String>> factoryMap;
	private JComboBox<TypesInFactory> mapsComboBox;
	private JList<String> objectList;
	private DefaultListModel<String> listModelString;
	private TypesInFactory slct;
	private JLabel slctToRemoveLabel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel pictureLbl;
	private JTextPane profieTextPane;
	private JTextPane listTextPane;
	private JPanel panel;
	private JLabel lblNewLabel;

	/**
	 * @param admin
	 * @throws NoObjectsToRemove
	 */
	/**
	 *
	 */

	public SeeAllPanel(Boolean isAdmin) throws NoObjectsToRemove {
		isAdmin = true;
		panel = new JPanel();

		lblNewLabel = new JLabel("See All Lists Available");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		mapsComboBox = new JComboBox<>();
		InitialiseSeeAllAndRemove.initialiseJlist(listModelString,(TypesInFactory)mapsComboBox.getSelectedItem(), listTextPane);
		listModelString = new DefaultListModel<>();

		InitialiseSeeAllAndRemove.initialiseComboBox(isAdmin, mapsComboBox);

		objectList = new JList<>(listModelString);

		slctToRemoveLabel = new JLabel("Select object to see profile");

		scrollPane = new JScrollPane();

		scrollPane.setViewportView(objectList);
		objectList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		objectList.setName("Select object");

		scrollPane_1 = new JScrollPane();

		pictureLbl = new JLabel("No Image Uploaded");
		pictureLbl.setBackground(Color.DARK_GRAY);
		pictureLbl.setFont(new Font("Tahoma", Font.BOLD, 10));
		pictureLbl.setHorizontalAlignment(SwingConstants.CENTER);

		listTextPane = new JTextPane();
		listTextPane.setBackground(new Color(192, 192, 192));
		listTextPane.setEditable(false);
		scrollPane.setColumnHeaderView(listTextPane);

		profieTextPane = new JTextPane();
		profieTextPane.setForeground(Color.BLACK);
		profieTextPane.setBackground(Color.LIGHT_GRAY);
		profieTextPane.setEditable(false);
		scrollPane_1.setViewportView(profieTextPane);
		objectList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				pictureLbl.setIcon(null);
				pictureLbl.setText("No Image Uploaded");
				InitialiseSeeAllAndRemove.writeProfile(objectList.getSelectedValue(), profieTextPane, listTextPane,
						pictureLbl);

			}
		});

		mapsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateJlist();

			}
		});
		initialiseLayound();
	}

	private void initialiseLayound() {

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(12).addComponent(lblNewLabel,
								GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup().addGap(20).addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
								.addComponent(mapsComboBox, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
								.addComponent(slctToRemoveLabel, GroupLayout.PREFERRED_SIZE, 156,
										GroupLayout.PREFERRED_SIZE))
								.addGap(18)))
						.addGap(0).addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE).addGap(28)
						.addComponent(pictureLbl, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addGap(129)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(pictureLbl, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 159,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(mapsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(slctToRemoveLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGap(121)));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		setLayout(groupLayout);
		panel.setLayout(gl_panel);
	}

	private void updateJlist() {
		slct = (TypesInFactory) mapsComboBox.getSelectedItem();
		listModelString.removeAllElements();
		profieTextPane.setText("");

		if (slct == null) {
			return;
		}
		factoryMap = Factory.getInstance().mapOfAllMaps();
		try {
			listTextPane.getDocument().remove(0, listTextPane.getDocument().getLength());
			listTextPane.getDocument().insertString(0, slct.name(), null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		for (String s : factoryMap.get(slct)) {
			listModelString.addElement(s);
		}
	}
}
