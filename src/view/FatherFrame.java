/**
 *
 */
package view;

import java.awt.BorderLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Exceptions.AllDepHasManager;
import Exceptions.AllDepartmentsCrated;
import Exceptions.HandleExceptions;
import Exceptions.NoDepartments;
import Exceptions.NoObjectsToRemove;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
import model.Employee;
import model.Factory;
import utils.TypesInFactory;
import viewUtils.SerializableMethods;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;


/**
 * @author Ariel Bubis 205735749, Narkis Rosen 208019729
 *
 */
public class FatherFrame extends JFrame implements ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// public JFXPanel contentPane;
	public JPanel contentPane;// Main panel

	private JMenuBar menuBar;
	private JMenu addFuncMenu;
	private JMenu addPersonMenuItem;
	private JMenu addVehicleMenuItem;
	private JMenu queryMenu;
	private JMenu removeMenu;

	private JMenuItem choiceCustomer;
	private JMenuItem choiceVIPCustomer;
	private JMenuItem choiceEmployee;
	private JMenuItem choiceDepMan;
	private JMenuItem choiceCar;
	private JMenuItem choiceVan;
	private JMenuItem choiceMotorcycle;
	private JMenuItem choiceDeal;
	private JMenuItem choiceDepartment;
	private JInternalFrame internalFrame;
	private JMenuItem empToManagerMenuItem;
	private JMenuItem removeMemuItem;
	private Boolean isAdmin;
	private JMenuItem querySelect;
	private JMenuItem seeAllMenuItem;
	private JLabel imageLabel;
	private JInternalFrame profilePicFrame;
	private JMenu settingsMenu;
	private JCheckBoxMenuItem soundMenuCheckbox;
	private JMenuItem logOffMenuItem;
	private JScrollPane scrollPane;
	private JPanel ChangingPanel;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	protected FatherFrame(Boolean isAdmin, Employee emp) {
		// setBackground(Color.BLACK);
		initialiseMenu(isAdmin);
		isnitialiseComponentsandAddtoPanel(isAdmin, emp);
		initialiseLayout();
	}

	/**
	 *
	 */
	private void initialiseLayout() {
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(internalFrame, GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(profilePicFrame, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(internalFrame, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
						.addComponent(profilePicFrame, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}

	/**
	 * @param isAdmin
	 *
	 */
	private void initialiseMenu(Boolean isAdmin) {
		this.isAdmin = isAdmin;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// setBounds(0, 0, 641, 549);
		Factory.getInstance().sound.playSound("welcome");

		try {// Add icon to the jframe
			setIconImage(ImageIO.read(getClass().getClassLoader().getResource("images/icon1.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		addFuncMenu = new JMenu("Add Functions");
		menuBar.add(addFuncMenu);
		addPersonMenuItem = new JMenu("Person");
		addFuncMenu.add(addPersonMenuItem);
		choiceCustomer = new JMenuItem("Customer");
		choiceVIPCustomer = new JMenuItem("VIP Customer");
		choiceEmployee = new JMenuItem("Employee");
		choiceDepMan = new JMenuItem("Dep Manager");
		addVehicleMenuItem = new JMenu("Vehicle");
		addFuncMenu.add(addVehicleMenuItem);
		empToManagerMenuItem = new JMenuItem("Set employee to manager");
		choiceCar = new JMenuItem("Car/HybridCar");
		choiceVan = new JMenuItem("Van");
		choiceMotorcycle = new JMenuItem("Motorcycle/HybridMotorcycle");
		choiceDeal = new JMenuItem("Deal");
		choiceDepartment = new JMenuItem("Department");
		removeMenu = new JMenu("Remove");
		menuBar.add(removeMenu);
		removeMemuItem = new JMenuItem("Remove Functions");
		queryMenu = new JMenu("Query");
		queryMenu.setActionCommand("Query");
		queryMenu.addActionListener(this);
		menuBar.add(queryMenu);
		seeAllMenuItem = new JMenuItem("See all lists");
		querySelect = new JMenuItem("Query methods");

		settingsMenu = new JMenu("Settings");
		menuBar.add(settingsMenu);

		soundMenuCheckbox = new JCheckBoxMenuItem("Sound ON");
		soundMenuCheckbox.setSelected(Factory.getInstance().getSound().getSoundBoolean());

		soundMenuCheckbox.addActionListener(new ActionListener() {// Sound settings

			@Override
			public void actionPerformed(ActionEvent e) {
				if (soundMenuCheckbox.isSelected()) {
					Factory.getInstance().getSound().soundCheck(true);
				} else {
					Factory.getInstance().getSound().soundCheck(false);
				}
			}
		});
		settingsMenu.add(soundMenuCheckbox);

		logOffMenuItem = new JMenuItem("LogOff");
		logOffMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				LoginFrame loginFrame = new LoginFrame();
//				loginFrame.setVisible(true);
//				dispose();
				int result = JOptionPane.showConfirmDialog(null, "Do you want to save before exiting?");
				switch (result) {
				case JOptionPane.YES_OPTION:
					try {
						SerializableMethods.serialize();
						LoginFrame loginFrame = new LoginFrame();
						loginFrame.setVisible(true);
						dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				case JOptionPane.NO_OPTION:
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
					dispose();
					break;
				}

			}
		});
		settingsMenu.add(logOffMenuItem);
		showMenuByType(isAdmin);// Check the type of user and show the menu accordingly

	}

	private void isnitialiseComponentsandAddtoPanel(Boolean isAdmin, Employee emp) {
		// This function is for the video background, disabled beacuse coldn't make it
		// work on jar

		// contentPane = new JFXPanel();
		// videoPanel = new JFXPanel();
		// getVideo();

//https://stackoverflow.com/a/14364439 (JPanel gradient background)
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				Graphics2D g2d = (Graphics2D) grphcs;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(),
						getBackground().darker().darker());
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};

		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// Main internal frame
		internalFrame = new JInternalFrame("");
		internalFrame.getContentPane().setBackground(SystemColor.activeCaption);
		internalFrame.setMaximizable(true);
		internalFrame.setResizable(true);
		internalFrame.setVisible(true);
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.80),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.95));

		// Internal frame for the profile picture
		profilePicFrame = new JInternalFrame("");
		profilePicFrame.setVisible(true);

		imageLabel = new JLabel("");
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profilePicFrame.getContentPane().add(imageLabel, BorderLayout.CENTER);
		paintProfilePic(isAdmin, emp);
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(internalFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
		);
		
		ChangingPanel = new JPanel();
		ChangingPanel.setBackground(new Color(70, 130, 180));
		scrollPane.setViewportView(ChangingPanel);
		
		textArea = new JTextArea(1, 20);
		textArea.setForeground(new Color(204, 255, 255));
		scrollPane.setColumnHeaderView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setText("Hello "+emp.getFirstName() +" Choose your action using the menu bar above ");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 15));
		textArea.setFocusable(false);
		textArea.setEditable(false);
		textArea.setBorder(UIManager.getBorder("Label.border"));
		textArea.setBackground(new Color(0, 0, 102));
		internalFrame.getContentPane().setLayout(groupLayout);

	}

	/**
	 *
	 * @param isAdmin
	 *
	 *             This function gets a boolean isAdmin, if the user is admin or
	 *             Employee, adds the needed menu items according to user type
	 */
	private void showMenuByType(Boolean isAdmin) {

		if (isAdmin) {
			addPersonMenuItem.add(choiceEmployee);
			addPersonMenuItem.add(choiceDepMan);
			addFuncMenu.add(empToManagerMenuItem);
			addFuncMenu.add(choiceDepartment);
		}
		addPersonMenuItem.add(choiceCustomer);
		addPersonMenuItem.add(choiceVIPCustomer);
		addVehicleMenuItem.add(choiceCar);
		addVehicleMenuItem.add(choiceVan);
		addVehicleMenuItem.add(choiceMotorcycle);
		addFuncMenu.add(choiceDeal);
		removeMenu.add(removeMemuItem);
		queryMenu.add(querySelect);
		queryMenu.add(seeAllMenuItem);

		// Set the name of Action commands to use in the actionPerformed
		choiceCustomer.setActionCommand("AddCostumer");
		choiceVIPCustomer.setActionCommand("AddVIPCostumer");
		choiceEmployee.setActionCommand("AddEmployee");
		choiceDepMan.setActionCommand("AddDepartmentManager");
		choiceCar.setActionCommand("AddCar");
		choiceVan.setActionCommand("AddVan");
		choiceMotorcycle.setActionCommand("AddMotorcycle");
		choiceDeal.setActionCommand("AddDeal");
		choiceDepartment.setActionCommand("AddDepartment");
		empToManagerMenuItem.setActionCommand("EmployeeToManagerPanel");
		removeMemuItem.setActionCommand("Remove");
		querySelect.setActionCommand("query");
		seeAllMenuItem.setActionCommand("SeeAll");

		choiceCustomer.addActionListener(this);
		choiceVIPCustomer.addActionListener(this);
		choiceEmployee.addActionListener(this);
		choiceDepMan.addActionListener(this);
		choiceCar.addActionListener(this);
		choiceVan.addActionListener(this);
		choiceMotorcycle.addActionListener(this);
		choiceDeal.addActionListener(this);
		choiceDepartment.addActionListener(this);
		empToManagerMenuItem.addActionListener(this);
		removeMemuItem.addActionListener(this);
		querySelect.addActionListener(this);
		seeAllMenuItem.addActionListener(this);

	}

	/**
	 * Set the profile picture using the information saved it the Employee class
	 *
	 * @param isAdmin
	 * @param employee
	 */
	private void paintProfilePic(Boolean isAdmin, Employee employee) {
		if (!isAdmin) {
			Employee emp = employee;
			profilePicFrame.setTitle(emp.getID() + " | " + emp.getFirstName() + " " + emp.getLastName());
			imageLabel.setText("No Image Uploaded");
			imageLabel.setText(null);
			imageLabel.setIcon(null);
			ImageIcon imageIcon = emp.getPhoto(); // load the image to a imageIcon
			if (imageIcon == null) {
				imageLabel.setText("No Image Uploaded");
			} else {
				Image image = imageIcon.getImage(); // transform it
				Image newimg = image.getScaledInstance((int) getPreferredSize().getWidth(),
						(int) getPreferredSize().getWidth(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth
				// way
				imageIcon = new ImageIcon(newimg); // transform it back

				imageLabel.setIcon(imageIcon);

			}
		} else {// If admin, set this
			profilePicFrame.setTitle("ADMIN ADMIN");
			imageLabel.setIcon(null);
			imageLabel.setText("No Image Uploaded");
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// Doesn't work in the jar file
//		if (event.getSource() == stopVideoButton) {
//			if (playing) {
//				player.pause();
//				playing = false;
//				return;
//			} else {
//				player.play();
//				player.setCycleCount(MediaPlayer.INDEFINITE);
//				playing = true;
//				return;
//			}
//		} else {
		JPanel newPanel = null;
		switch (event.getActionCommand()) {

		case "AddCostumer":
			newPanel = new AddCustomerPanel("Customer");

			break;
		case "AddVIPCostumer":
			newPanel = new AddVIPCustomerPanel("VIPCustomer");

			break;
		case "AddEmployee":
			newPanel = new AddEmployeePanel("Employee");

			break;
		case "AddDepartmentManager":
			newPanel = new AddDepManagerPanel("Department Manager");

			break;
		case "AddCar":
			newPanel = new AddCarPanel("Car/HybridCar");

			break;
		case "AddVan":
			newPanel = new AddVanPanel("Van");

			break;
		case "AddMotorcycle":
			newPanel = new MotorcyclyFrame("Motorcycle/HybridMotorcycle");

			break;
		case "AddDeal":
			newPanel = new AddDealPanel();

			break;
		case "AddDepartment": {
			try {
				if (!Factory.getInstance().specializationsList(true).isEmpty()) {
					JFrame depFrame = new PopUpAddDepartmentForm();
					depFrame.setVisible(true);
					return;
				}
			} catch (AllDepartmentsCrated e) {
				// TODO Auto-generated catch block
				HandleExceptions.showException(e);
				return;
			}
			break;
		}
		case "EmployeeToManagerPanel":
			try {
				newPanel = new SetEmployeeAManagerPanel();
			} catch (NoDepartments | AllDepHasManager e) {
				HandleExceptions.showException(e);
				return;
			}
			break;

		case "Remove":
			try {
				newPanel = new RemoveObjectsPanel(isAdmin);
			} catch (NoObjectsToRemove e) {
				HandleExceptions.showException(e);
				return;
			}
			break;
		case "query":
			try {
				if (!(Factory.getInstance().mapOfAllMaps().size() < TypesInFactory.values().length)) {
					newPanel = new QueriesForm();
				} else {
					throw new NoObjectsToRemove("Can't use Query, some");
				}
			} catch (NoObjectsToRemove e1) {
				HandleExceptions.showException(e1);
				return;
			}
			break;
		case "SeeAll":
			try {
				newPanel = new SeeAllPanel(isAdmin);
			} catch (NoObjectsToRemove e) {
				HandleExceptions.showException(e);
				return;
			}
			break;

		}
		scrollPane.setViewportView(newPanel);

		internalFrame.setTitle(event.getActionCommand());

		revalidate();
		repaint();
	}
//	}
}

//	private void getVideo() {
//	/**
//	 *Doesn't work properly, it plays the window on eclipse not on jar, also When logging off and logging in it fails to load
//	 */
//
//		String path = this.getClass().getClassLoader().getResource("video/gears1.mp4").toExternalForm();
//		media = new Media(path);
//		player = new MediaPlayer(media);
//		StackPane root = new StackPane();
//		Scene scene = new Scene(root, contentPane.getWidth(), contentPane.getHeight());
//		MediaView mediaView = new MediaView(player);
//		player.setOnError(() -> System.out.println("media error" + player.getError().toString()));
//		mediaView.setFitWidth(contentPane.getWidth());
//		mediaView.setFitHeight(contentPane.getHeight());
//		player.setCycleCount(MediaPlayer.INDEFINITE);
//		player.setAutoPlay(true);
//		playing = true;
//
//		root.getChildren().addAll(mediaView);
//
//		contentPane.setScene(scene);
//
//	}
//
//    public byte[] streamToBytes(String streamPath) {
//        InputStream inputStream = null;//You can get an inputStream using any IO API
//        try {
//            inputStream = new FileInputStream(streamPath);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        byte[] buffer = new byte[8192];
//        int bytesRead;
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        try {
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                output.write(buffer, 0, bytesRead);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes = output.toByteArray();
//        return bytes;
//    }
