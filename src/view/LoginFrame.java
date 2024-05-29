/**
 *
 */
package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Exceptions.HandleExceptions;
import Exceptions.InputIsEmpty;
import Exceptions.NoSuchUserException;
import Exceptions.WrongPassException;
import model.Employee;
import model.Factory;
import viewUtils.SerializableMethods;

/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 8714127335463046014L;
	protected JPanel contentPane;
	private JTextField userName;
	private JPasswordField passwordField;
	private JLabel logoLabel;
	// private JButton quickEnterAdminButton;
	private JPanel loginFormPanel;
	private JLabel userNameLabel;
	private JLabel passLabel;
	private JLabel lblNewLabel_2;
	private JButton loginButton;
	private static LoginFrame frame;
	private JCheckBox soundCheckBox;

	public static void main(String[] args) throws IOException {
		try {
			SerializableMethods.createFactoryLib();
		} catch (UnsupportedAudioFileException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 330);
		setLocationRelativeTo(null);

		initialiseComponents();
		initialiselogo();
		initialseLayout();

		// Add icon image to frame
		try {
			setIconImage(ImageIO.read(getClass().getClassLoader().getResource("images/icon1.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initialiseComponents() {

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		loginFormPanel = new JPanel();
		loginFormPanel.setBackground(Color.WHITE);
		userNameLabel = new JLabel("User Name");
		userName = new JTextField();
		userName.setBackground(Color.LIGHT_GRAY);
		userName.setColumns(10);
		passLabel = new JLabel("Password");
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		loginButton = new JButton("login");
		lblNewLabel_2 = new JLabel("(Username: Employee First name+ID; To Enter as Admin User: Admin Pass: Admin)");
		logoLabel = new JLabel("");

		loginButton.addActionListener(new ActionListener() {
			private Employee emp;

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FatherFrame mainFrame;
					if (!userName.getText().isEmpty() && !passwordField.getText().isEmpty()) {// Check if not empty
						if (userName.getText().equalsIgnoreCase("Admin")
								&& passwordField.getText().equalsIgnoreCase("Admin")) {// Check if it's admin
							getContentPane().setVisible(false);
							// frame.setVisible(false);
							dispose();

							mainFrame = new FatherFrame(true, new Employee("Admin", "Admin"));
							mainFrame.setVisible(true);
						} else {
							if (Factory.getInstance().getUsersAndPassword().containsKey(userName.getText())) {
								emp = Factory.getInstance().getUsersAndPassword().get(userName.getText());
								if (Factory.getInstance().getUsersAndPassword().get(userName.getText()).getPass()
										.equals(passwordField.getText())) {
									// frame.setVisible(false);
									dispose();
									mainFrame = new FatherFrame(false, emp);
									mainFrame.setVisible(true);
								} else {
									throw new WrongPassException();
								}
							} else {
								throw new NoSuchUserException();
							}
						}
					} else {
						throw new InputIsEmpty("At least one");
					}
				} catch (InputIsEmpty | WrongPassException | NoSuchUserException e1) {
					HandleExceptions.showException(e1);
				}
			}
		});
		soundCheckBox = new JCheckBox("Sound On");
		soundCheckBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		soundCheckBox.setBackground(Color.WHITE);
		// if(Factory.getInstance().sound != null &&
		// !Factory.getInstance().sound.getSoundBoolean()) {
		soundCheckBox.setSelected(Factory.getInstance().sound.getSoundBoolean());
		// }
		// else {
		// soundCheckBox.setSelected(true);
		// }
		soundCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (soundCheckBox.isSelected()) {
					Factory.getInstance().getSound().soundCheck(true);
				} else {
					Factory.getInstance().getSound().soundCheck(false);
				}
			}
		});

		// Testing purposes
		// quickEnterAdminButton = new JButton("Quick enter");
		// quickEnterAdminButton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// getContentPane().setVisible(false);
		// frame.setVisible(false);
		// FatherFrame mainFrame = new FatherFrame(true, "test");
		// mainFrame.setVisible(true);
		// }
		// });
	}

	private void initialseLayout() {

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(16)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(soundCheckBox, GroupLayout.PREFERRED_SIZE, 127,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(loginFormPanel, GroupLayout.PREFERRED_SIZE, 425,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
														.addGroup(Alignment.LEADING,
																gl_contentPane.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(lblNewLabel_2,
																				GroupLayout.DEFAULT_SIZE, 390,
																				Short.MAX_VALUE))
														.addGroup(gl_contentPane.createSequentialGroup()
																.addGap(70)
																.addComponent(logoLabel, GroupLayout.DEFAULT_SIZE, 330,
																		Short.MAX_VALUE)))
												.addGap(60)))
								.addContainerGap()));
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(logoLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(loginFormPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(soundCheckBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(91, Short.MAX_VALUE)));
		loginFormPanel.setLayout(new GridLayout(0, 2, 0, 0));
		loginFormPanel.add(userNameLabel);
		loginFormPanel.add(userName);
		loginFormPanel.add(passLabel);
		loginFormPanel.add(passwordField);
		loginFormPanel.add(loginButton);
		// loginFormPanel.add(quickEnterAdminButton);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Add logo to panel
	 */
	private void initialiselogo() {
		try {
			BufferedImage bi = ImageIO.read(getClass().getClassLoader().getResource("images/logo.jpg"));
			ImageIcon imageIcon = new ImageIcon(bi); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(330, 100, java.awt.Image.SCALE_SMOOTH); // scale it the
			ImageIcon finalImage = new ImageIcon(newimg); // transform it back
			logoLabel.setIcon(finalImage);// Set image

		} catch (Exception e) {
			HandleExceptions.showException(e);
		}

	}
}
