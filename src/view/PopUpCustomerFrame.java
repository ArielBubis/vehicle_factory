/**
 *
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class PopUpCustomerFrame extends JFrame implements ActionListener {

	/**
	 *A pop up frame with selection to add new Customer or a VIP Customer
	 */
	private static final long serialVersionUID = 1L;
	private JPanel buttonPanel;
	private JButton cstmrBtn;
	private JButton vipCstmrBnt;
	private JPanel customerFormPanel;
	private JPanel panel;
	private JPanel cstmrPanel;
	private JPanel vipCstmrPanel;

	public PopUpCustomerFrame() {
		setResizable(false);

		setBounds(0, 0, 700, 800);
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBounds(0, 0, 700, 800);
		getContentPane().add(panel);

		buttonPanel = new JPanel();
		panel.add(buttonPanel);

		cstmrBtn = new JButton("Add new customer");
		buttonPanel.add(cstmrBtn);
		cstmrBtn.setActionCommand("Customer");
		cstmrBtn.addActionListener(this);

		vipCstmrBnt = new JButton("Add new VIP Customer");
		buttonPanel.add(vipCstmrBnt);
		vipCstmrBnt.setActionCommand("VIPCustomer");
		vipCstmrBnt.addActionListener(this);

		customerFormPanel = new JPanel();
		panel.add(customerFormPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "Customer"://Get the customer panel and add it to the frame
			panel.removeAll();
			panel.add(buttonPanel);
			cstmrPanel = new AddCustomerPanel("Customer");
			cstmrPanel.setBounds(0, 0, 600, 600);
			cstmrPanel.setAlignmentX(LEFT_ALIGNMENT);
			cstmrPanel.setAlignmentY(CENTER_ALIGNMENT);
			panel.add(cstmrPanel);
			break;
		case "VIPCustomer"://Get the VIPcustomer panel and add it to the frame
			panel.removeAll();
			panel.add(buttonPanel);
			vipCstmrPanel = new AddVIPCustomerPanel("VIP Customer");
			vipCstmrPanel.setBounds(0, 0, 600, 600);
			vipCstmrPanel.setAlignmentX(LEFT_ALIGNMENT);
			vipCstmrPanel.setAlignmentY(CENTER_ALIGNMENT);
			panel.add(vipCstmrPanel);

			break;
		}

		revalidate();
		repaint();
	}
}
