package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import file_utils.FileUtils;

public class mainGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel currentSideBarActive;
	private int currentIndex = 0;
	private JTabbedPane tabbedPane;
	private JLabel lblTitle;
	private JPanel panelTitle;
	public static String WORKSPACE_PATH = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String workspacePath = FileUtils.readFile("workspace_path.txt").trim();
					if (workspacePath.length() == 0) {
						File result = new File("D:\\EcrTool");
						if (!result.exists()) {
							result.mkdir();
						}
						JFileChooser fileChooser = new JFileChooser(result);
						fileChooser.setMultiSelectionEnabled(false);
						fileChooser.setDialogTitle("Choose Workspace");
						fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int x = fileChooser.showDialog(null, "Select");
						if (x == JFileChooser.APPROVE_OPTION) {
							String defaultDir = result.getAbsolutePath();
							result = fileChooser.getSelectedFile();
							String chooseDir = result.getAbsolutePath();
							if (!defaultDir.equals(chooseDir)) {
								new File("D:\\EcrTool").delete();

							}
							FileUtils.saveText(new File("workspace_path.txt"), result.getAbsolutePath());
							WORKSPACE_PATH = result.getAbsolutePath();
							mainGUI frame = new mainGUI();
							frame.setVisible(true);
							frame.setLocationRelativeTo(null);
						}
					} else {
						WORKSPACE_PATH = workspacePath;
						mainGUI frame = new mainGUI();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public mainGUI() {
		setTitle("LHL Encrypt Tool");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 600);
		getContentPane().setLayout(null);

		panelTitle = new JPanel();
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTitle.setBackground(new Color(0, 0, 0));
		panelTitle.setBounds(0, 30, 757, 48);
		getContentPane().add(panelTitle);

		lblTitle = new JLabel("LHL ENCRYPT TOOL");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		panelTitle.add(lblTitle);

		// menuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(0, 0, 984, 30);
		getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mnItSwitchWorkspace = new JMenuItem("Switch Workspace");
		mnFile.add(mnItSwitchWorkspace);
		mnItSwitchWorkspace.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File result = null;
				JFileChooser fileChooser = new JFileChooser(WORKSPACE_PATH);
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setDialogTitle("Choose Workspace");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int x = fileChooser.showDialog(null, "Select");
				if (x == JFileChooser.APPROVE_OPTION) {
					result = fileChooser.getSelectedFile();
					FileUtils.saveText(new File("workspace_path.txt"), result.getAbsolutePath());
					WORKSPACE_PATH = result.getAbsolutePath();
				}
			}
		});

		JMenuItem mnImExit = new JMenuItem("Exit");
		mnImExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Yes", "No" };
				int x = JOptionPane.showOptionDialog(null, "Do you want exit?", "Exit", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (x == 0) {
					System.exit(0);
				}
			}
		});
		mnFile.add(mnImExit);
		// end menubar

		// Side bar
		JPanel panelSidebar = new JPanel();
		panelSidebar.setBackground(new Color(48, 7, 88));
		panelSidebar.setBounds(757, 30, 227, 531);
		getContentPane().add(panelSidebar);
		panelSidebar.setLayout(null);

		JPanel panelBgWelcome = new JPanel();
		panelBgWelcome.setLayout(null);
		panelBgWelcome.setBackground(new Color(48, 7, 88));
		panelBgWelcome.setBounds(0, 5, 227, 40);
		panelSidebar.add(panelBgWelcome);

		JLabel btnWelcome = new JLabel("");
		btnWelcome.setBounds(0, 0, 227, 40);
		currentSideBarActive = btnWelcome;
		btnWelcome.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBgWelcome.add(btnWelcome);
		btnWelcome.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnWelcomeActive.png")));

		JPanel panelBgCeasar = new JPanel();
		panelBgCeasar.setBackground(new Color(48, 7, 88));
		panelBgCeasar.setBounds(0, 45, 227, 40);
		panelSidebar.add(panelBgCeasar);
		panelBgCeasar.setLayout(null);

		JLabel btnCeasar = new JLabel("");
		btnCeasar.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnCeasar.png")));
		btnCeasar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCeasar.setBounds(0, 0, 227, 40);
		panelBgCeasar.add(btnCeasar);

		JPanel panelBgBtnSubtitution = new JPanel();
		panelBgBtnSubtitution.setBackground(new Color(48, 7, 88));
		panelBgBtnSubtitution.setLayout(null);
		panelBgBtnSubtitution.setBounds(0, 85, 227, 40);
		panelSidebar.add(panelBgBtnSubtitution);

		JLabel btnSubstitution = new JLabel("");
		btnSubstitution.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnSubstitution.png")));
		btnSubstitution.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSubstitution.setBounds(0, 0, 227, 40);
		panelBgBtnSubtitution.add(btnSubstitution);

		JPanel panelBgBtnVegenere = new JPanel();
		panelBgBtnVegenere.setLayout(null);
		panelBgBtnVegenere.setBackground(new Color(48, 7, 88));
		panelBgBtnVegenere.setBounds(0, 125, 227, 40);
		panelSidebar.add(panelBgBtnVegenere);

		JLabel btnVegenere = new JLabel("");
		btnVegenere.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnVegenere.png")));
		btnVegenere.setBounds(0, 0, 227, 40);
		btnVegenere.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBgBtnVegenere.add(btnVegenere);

		JPanel panelBgBtnHill = new JPanel();
		panelBgBtnHill.setLayout(null);
		panelBgBtnHill.setBackground(new Color(48, 7, 88));
		panelBgBtnHill.setBounds(0, 165, 227, 40);
		panelSidebar.add(panelBgBtnHill);

		JLabel btnHill = new JLabel("");
		btnHill.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnHill.png")));
		btnHill.setBounds(0, 0, 227, 40);
		btnHill.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBgBtnHill.add(btnHill);

		JPanel panelBgBtnDes = new JPanel();
		panelBgBtnDes.setLayout(null);
		panelBgBtnDes.setBackground(new Color(48, 7, 88));
		panelBgBtnDes.setBounds(0, 205, 227, 40);
		panelSidebar.add(panelBgBtnDes);

		JLabel btnDes = new JLabel("");
		btnDes.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnDes.png")));
		btnDes.setBounds(0, 0, 227, 40);
		btnDes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBgBtnDes.add(btnDes);

		JPanel panelBgBtnAes = new JPanel();
		panelBgBtnAes.setLayout(null);
		panelBgBtnAes.setBackground(new Color(48, 7, 88));
		panelBgBtnAes.setBounds(0, 245, 227, 40);
		panelSidebar.add(panelBgBtnAes);

		JLabel btnAes = new JLabel("");
		btnAes.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnAes.png")));
		btnAes.setBounds(0, 0, 227, 40);
		btnAes.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBgBtnAes.add(btnAes);

		JPanel panelBgBtnRsa = new JPanel();
		panelBgBtnRsa.setLayout(null);
		panelBgBtnRsa.setBackground(new Color(48, 7, 88));
		panelBgBtnRsa.setBounds(0, 285, 227, 40);
		panelSidebar.add(panelBgBtnRsa);

		JLabel btnRsa = new JLabel("");
		btnRsa.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnRsa.png")));
		btnRsa.setBounds(0, 0, 227, 40);
		btnRsa.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBgBtnRsa.add(btnRsa);

		JPanel panelBgBtnMd5 = new JPanel();
		panelBgBtnMd5.setLayout(null);
		panelBgBtnMd5.setBackground(new Color(48, 7, 88));
		panelBgBtnMd5.setBounds(0, 325, 227, 40);
		panelSidebar.add(panelBgBtnMd5);

		JLabel btnMd5 = new JLabel("");
		btnMd5.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnMd5.png")));
		btnMd5.setBounds(0, 0, 227, 40);
		btnMd5.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBgBtnMd5.add(btnMd5);

		JPanel panelBgBtnSha = new JPanel();
		panelBgBtnSha.setLayout(null);
		panelBgBtnSha.setBackground(new Color(48, 7, 88));
		panelBgBtnSha.setBounds(0, 365, 227, 40);
		panelSidebar.add(panelBgBtnSha);

		JLabel btnSha = new JLabel("");
		btnSha.setIcon(new ImageIcon(mainGUI.class.getResource("/images/btnSha.png")));
		btnSha.setBounds(0, 0, 227, 40);
		btnSha.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBgBtnSha.add(btnSha);

		// end side bar

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 55, 757, 506);
		getContentPane().add(tabbedPane);

		// tab home
		JPanel tabHome = new HomeGUI();
		tabbedPane.addTab("Welcome", null, tabHome, null);
		btnWelcome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabHome, "Welcome", "LHL ENCRYPT TOOL", btnWelcome, "/images/btnWelcomeActive.png", 0);
			}
		});
		// end tab home

		// Ceasar
		JPanel tabCeasar = new CeasarGUI();
		btnCeasar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabCeasar, "Ceasar", "CEASAR", btnCeasar, "/images/btnCeasarActive.png", 1);
			}
		});
		// end tab ceasar

		// substitution
		JPanel tabSubstitution = new SubstitutionGUI();
		btnSubstitution.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabSubstitution, "Substitution", "SUBSTITUTION", btnSubstitution,
						"/images/btnSubstitutionActive.png", 2);
			}
		});

		// end tab substitution

		// tab Vegenere
		JPanel tabVegenere = new VegenereGUI();
		btnVegenere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabVegenere, "Vegenere", "VEGENERE", btnVegenere, "/images/btnVegenereActive.png", 3);
			}
		});
		// end tab Vegenere

		// tab Hill
		JPanel tabHill = new HillGUI();
		btnHill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabHill, "Hill", "HILL", btnHill, "/images/btnHillActive.png", 4);
			}
		});
		// end tab Hill

		// tab DES
		JPanel tabDes = new DesGUI();
		btnDes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabDes, "DES", "DES", btnDes, "/images/btnDesActive.png", 5);
			}
		});
		// end tab DES

		// tab AES
		JPanel tabAes = new AesGUI();
		btnAes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabAes, "AES", "AES", btnAes, "/images/btnAesActive.png", 6);
			}
		});
		// end tab AES

		// tab RSA
		JPanel tabRsa = new RsaGUI();
		btnRsa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabRsa, "RSA", "RSA", btnRsa, "/images/btnRsaActive.png", 7);
			}
		});
		// end tab RSA

		// tab MD5
		JPanel tabMd5 = new Md5GUI();
		btnMd5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabMd5, "MD5", "MD5", btnMd5, "/images/btnMd5Active.png", 8);
			}
		});
		// end tab MD5

		// tab SHA
		JPanel tabSha = new ShaGUI();
		btnSha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				changeTab(tabSha, "SHA", "SHA", btnSha, "/images/btnShaActive.png", 9);
			}
		});
		// end tab SHA
	}

	public String getIconSource(int index) {
		String[] imgPaths = { "/images/btnWelcome.png", "/images/btnCeasar.png", "/images/btnSubstitution.png",
				"/images/btnVegenere.png", "/images/btnHill.png", "/images/btnDes.png", "/images/btnAes.png",
				"/images/btnRsa.png", "/images/btnMd5.png", "/images/btnSha.png" };
		return imgPaths[index];
	}

	public void changeTab(JPanel tab, String tabName, String lblName, JLabel currentSidebarActive, String pathImgActive,
			int currentIndex) {
		if (this.tabbedPane.getSelectedComponent() != tab) {
			this.tabbedPane.removeAll();
			this.tabbedPane.addTab(tabName, null, tab, null);
			this.lblTitle.setText(lblName);
			this.panelTitle.setBackground(currentIndex == 0 ? new Color(0, 0, 0) : new Color(0, 0, 255));
			this.currentSideBarActive
					.setIcon(new ImageIcon(mainGUI.class.getResource(getIconSource(this.currentIndex))));
			this.currentSideBarActive = currentSidebarActive;
			this.currentSideBarActive.setIcon(new ImageIcon(mainGUI.class.getResource(pathImgActive)));
			this.currentIndex = currentIndex;
		}
	}
}
