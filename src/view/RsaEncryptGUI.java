package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import file_utils.FileUtils;
import public_key.RSA;
import public_key.RSA.RSA_KEY;

public class RsaEncryptGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension dimForBtn = new Dimension(115, 40);
	private Font btnFont = new Font("Tahoma", Font.BOLD, 12);
	private Font labelFont = new Font("Tahoma", Font.BOLD, 16);
	private DefaultComboBoxModel<String> modelCombobox = new DefaultComboBoxModel<String>(
			new String[] { "1024 bit", "2048 bit", "4096 bit" });
	private JTextField textFieldPLKey;
	private JTextField textFieldPVKey;
	private JComboBox<String> comboBoxKeySize;
	private JTextArea textAreaPlainText, textAreaCipherText;
	private JPanel panelKey;
	private JLabel lblKey;
	private JRadioButton rdbtnUsePublicKey;
	private JRadioButton rdbtnUsePrivateKey;
	private JCheckBox cbEnterKeySize;

	private byte[] byteData;
	private RSA rsa;
	private JTextField textFieldKeySize;

	public RsaEncryptGUI() {
		rsa = new RSA();
		setLayout(null);

		JLabel lblTool = new JLabel("@LHL Encrypt Tool");
		lblTool.setHorizontalAlignment(SwingConstants.CENTER);
		lblTool.setForeground(Color.LIGHT_GRAY);
		lblTool.setBounds(0, 438, 744, 14);
		add(lblTool);

		JPanel panelKeySize = new JPanel();
		panelKeySize.setBounds(15, 11, 316, 69);
		add(panelKeySize);
		panelKeySize.setLayout(null);

		JLabel lblKeySize = new JLabel("Choose key size:");
		lblKeySize.setBounds(0, 6, 136, 20);
		lblKeySize.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelKeySize.add(lblKeySize);

		comboBoxKeySize = new JComboBox<String>();
		comboBoxKeySize.setModel(modelCombobox);
		comboBoxKeySize.setBounds(146, 5, 101, 23);
		comboBoxKeySize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelKeySize.add(comboBoxKeySize);

		JLabel lblEnterKeySize = new JLabel("Enter key size:");
		lblEnterKeySize.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEnterKeySize.setBounds(0, 37, 126, 20);
		panelKeySize.add(lblEnterKeySize);

		cbEnterKeySize = new JCheckBox("");
		cbEnterKeySize.setBounds(123, 38, 20, 20);
		panelKeySize.add(cbEnterKeySize);
		cbEnterKeySize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbEnterKeySize.isSelected()) {
					comboBoxKeySize.setEnabled(false);
				} else {
					comboBoxKeySize.setEnabled(true);
				}
			}
		});

		textFieldKeySize = new JTextField();
		textFieldKeySize.setBounds(146, 37, 101, 23);
		panelKeySize.add(textFieldKeySize);
		textFieldKeySize.setColumns(10);

		panelKey = new JPanel();
		panelKey.setBackground(new Color(255, 128, 128));
		panelKey.setBounds(334, 11, 407, 30);
		add(panelKey);
		panelKey.setLayout(null);

		lblKey = new JLabel("Please import or create new key");
		lblKey.setForeground(new Color(255, 255, 255));
		lblKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblKey.setBounds(0, 0, 383, 30);
		panelKey.add(lblKey);

		JPanel panelPlainText = new JPanel();
		panelPlainText.setBounds(15, 222, 234, 145);
		add(panelPlainText);
		panelPlainText.setLayout(new GridLayout(1, 1, 0, 0));

		JScrollPane scrollPanePlainText = new JScrollPane();
		panelPlainText.add(scrollPanePlainText);

		textAreaPlainText = new JTextArea();
		scrollPanePlainText.setViewportView(textAreaPlainText);

		JLabel lblPlainText = new JLabel("Plain text");
		lblPlainText.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlainText.setFont(labelFont);
		scrollPanePlainText.setColumnHeaderView(lblPlainText);

		JPanel panelCipherText = new JPanel();
		panelCipherText.setBounds(320, 222, 236, 145);
		add(panelCipherText);
		panelCipherText.setLayout(new GridLayout(1, 1, 0, 0));

		JScrollPane scrollPaneCipherText = new JScrollPane();
		panelCipherText.add(scrollPaneCipherText);

		textAreaCipherText = new JTextArea();
		scrollPaneCipherText.setViewportView(textAreaCipherText);

		JLabel lblCipherText = new JLabel("Cipher text");
		lblCipherText.setHorizontalAlignment(SwingConstants.CENTER);
		lblCipherText.setFont(labelFont);
		scrollPaneCipherText.setColumnHeaderView(lblCipherText);

		JPanel panelBtns = new JPanel();
		panelBtns.setBounds(0, 374, 744, 53);
		add(panelBtns);

		JButton btnImportKey = new JButton("Import key");
		btnImportKey.setPreferredSize(new Dimension(115, 40));
		btnImportKey.setForeground(Color.WHITE);
		btnImportKey.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnImportKey.setBackground(Color.BLUE);
		panelBtns.add(btnImportKey);
		btnImportKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onImportKey();
			}
		});

		JButton btnImportText = new JButton("Import Text");
		btnImportText.setForeground(Color.WHITE);
		btnImportText.setFont(btnFont);
		btnImportText.setBackground(Color.BLUE);
		btnImportText.setPreferredSize(dimForBtn);
		panelBtns.add(btnImportText);
		btnImportText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onImportText();
			}
		});

		JButton btnCreateKey = new JButton("Create key");
		btnCreateKey.setPreferredSize(new Dimension(115, 40));
		btnCreateKey.setForeground(Color.WHITE);
		btnCreateKey.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCreateKey.setBackground(Color.BLUE);
		panelBtns.add(btnCreateKey);
		btnCreateKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onCreateKey();
			}
		});

		JButton btnSaveKey = new JButton("Save key");
		btnSaveKey.setPreferredSize(new Dimension(115, 40));
		btnSaveKey.setForeground(Color.WHITE);
		btnSaveKey.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSaveKey.setBackground(Color.BLUE);
		panelBtns.add(btnSaveKey);
		btnSaveKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onSaveKey();
			}
		});

		JButton btnSaveText = new JButton("Save Text");
		btnSaveText.setForeground(Color.WHITE);
		btnSaveText.setFont(btnFont);
		btnSaveText.setBackground(Color.BLUE);
		btnSaveText.setPreferredSize(dimForBtn);
		panelBtns.add(btnSaveText);
		btnSaveText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onSaveText();
			}
		});

		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setForeground(Color.WHITE);
		btnEncrypt.setFont(btnFont);
		btnEncrypt.setBackground(Color.BLUE);
		btnEncrypt.setPreferredSize(dimForBtn);
		panelBtns.add(btnEncrypt);
		btnEncrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onEncrypt();
			}
		});

		JLabel lblPuclicKey = new JLabel("Puclic key");
		lblPuclicKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuclicKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPuclicKey.setBounds(0, 60, 751, 20);
		add(lblPuclicKey);

		JLabel lblPrivateKey = new JLabel("Private key");
		lblPrivateKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrivateKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrivateKey.setBounds(0, 136, 751, 20);
		add(lblPrivateKey);

		JPanel panelPLKey = new JPanel();
		panelPLKey.setBounds(0, 88, 751, 30);
		add(panelPLKey);
		panelPLKey.setLayout(null);

		textFieldPLKey = new JTextField();
		textFieldPLKey.setBounds(15, 0, 725, 30);
		panelPLKey.add(textFieldPLKey);
		textFieldPLKey.setColumns(10);

		JPanel panelPVKey = new JPanel();
		panelPVKey.setLayout(null);
		panelPVKey.setBounds(0, 167, 751, 30);
		add(panelPVKey);

		textFieldPVKey = new JTextField();
		textFieldPVKey.setColumns(10);
		textFieldPVKey.setBounds(15, 0, 725, 30);
		panelPVKey.add(textFieldPVKey);

		ButtonGroup btnGroup = new ButtonGroup();

		rdbtnUsePublicKey = new JRadioButton("Use public key");
		rdbtnUsePublicKey.setSelected(true);
		rdbtnUsePublicKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnUsePublicKey.setBounds(578, 266, 166, 23);
		add(rdbtnUsePublicKey);
		btnGroup.add(rdbtnUsePublicKey);

		rdbtnUsePrivateKey = new JRadioButton("Use private key");
		rdbtnUsePrivateKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnUsePrivateKey.setBounds(578, 306, 166, 23);
		add(rdbtnUsePrivateKey);
		btnGroup.add(rdbtnUsePrivateKey);

	}

	public void keyIsReady() {
		lblKey.setText("Key is ready");
		panelKey.setBackground(new Color(128, 255, 128));
	}

	public String getUseKey() {
		if (rdbtnUsePrivateKey.isSelected()) {
			return "private key";
		}
		return "public key";
	}

	public void getPublicKey() {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(textFieldPLKey.getText().trim());
			this.rsa.setPublicKey(keyBytes);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid key");
		}
	}

	public void getPrivateKey() {
		try {
			byte[] keyBytes = Base64.getDecoder().decode(textFieldPVKey.getText().trim());
			this.rsa.setPrivateKey(keyBytes);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid key");
		}
	}

	public void onImportKey() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			try {
				Key key = (Key) FileUtils.readObjectFile(choose.getAbsolutePath());
				if (key.getAlgorithm().equalsIgnoreCase("RSA")) {
					String baseKey = Base64.getEncoder().encodeToString(key.getEncoded());
					String classKey = key.getClass().getName();
					String keyType = classKey.substring(classKey.lastIndexOf(".") + 1);
					if(keyType.equals("RSAPublicKeyImpl")) {
						textFieldPLKey.setText(baseKey);
					} else {
						textFieldPVKey.setText(baseKey);
					}
					keyIsReady();
				} else {
					JOptionPane.showMessageDialog(null, "This is not RSA key");
				}
			} catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(null, "This is not RSA key");
			}
		}
	}

	public void onImportText() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String[] fileNameSplit = choose.getName().split("\\.");
			if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
				String fileContent = FileUtils.readTextFile(choose.getAbsolutePath());
				textAreaPlainText.setText(fileContent.trim());
			} else {
				byteData = FileUtils.readByteFile(choose.getAbsolutePath());
				textAreaPlainText.setText("Encrypt file: " + choose.getAbsolutePath());
			}
		}
	}

	public void onCreateKey() {
		try {
			int keyLenght = 0;
			if (!cbEnterKeySize.isSelected()) {
				int[] keyLenghts = { 1024, 2048, 4096 };
				keyLenght = keyLenghts[comboBoxKeySize.getSelectedIndex()];
			} else {
				keyLenght = Integer.parseInt(textFieldKeySize.getText());
			}
			if (keyLenght < 512) {
				JOptionPane.showMessageDialog(null, "Key size is a number > 512");
			} else {
				rsa.createKey(keyLenght);
				String publicKey = Base64.getEncoder().encodeToString(rsa.getPublicKey().getEncoded());
				String privateKey = Base64.getEncoder().encodeToString(rsa.getPrivateKey().getEncoded());
				textFieldPLKey.setText(publicKey);
				textFieldPVKey.setText(privateKey);
				keyIsReady();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Key size is a number > 512");
		}
	}

	public void onSaveKey() {
		Key key = null;
		if (getUseKey().equals("public key")) {
			key = rsa.getPublicKey();
		} else {
			key = rsa.getPrivateKey();
		}
		if (key == null) {
			JOptionPane.showMessageDialog(null, "Key is null, Import or create new key");
		} else {
			FileUtils.onSaveObj(key);
		}
	}

	public void onSaveText() {
		String cipherText = textAreaCipherText.getText();
		if (cipherText.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to save");
		} else {
			String saveContent = "";
			if (cipherText.equals("Encryption successful, choose save text to save the result")) {
				saveContent = Base64.getEncoder().encodeToString(byteData);
			} else {
				saveContent = cipherText;
			}
			FileUtils.onSaveText(saveContent);
		}
	}

	public void onEncrypt() {
		try {
			if (textAreaPlainText.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Nothing to encrypt");
			} else {
				if (getUseKey().equals("public key")) {
					getPublicKey();
					if (rsa.getPublicKey() == null) {
						JOptionPane.showMessageDialog(null, "Import or create new public key before encrypt");
					} else {
						String plainText = textAreaPlainText.getText();
						if (plainText.indexOf("Encrypt file: ") == 0) {
							byte[] encrypt = rsa.encrypt(byteData, RSA_KEY.PUBLIC_KEY);
							byteData = encrypt;
							textAreaCipherText.setText("Encryption successful, choose save text to save the result");
						} else {
							byte[] plainTextByte = plainText.getBytes();
							byte[] encrypt = rsa.encrypt(plainTextByte, RSA_KEY.PUBLIC_KEY);
							textAreaCipherText.setText(Base64.getEncoder().encodeToString(encrypt));
						}
					}
				} else {
					getPrivateKey();
					if (rsa.getPrivateKey() == null) {
						JOptionPane.showMessageDialog(null, "Import or create new private key before encrypt");
					} else {
						String plainText = textAreaPlainText.getText();
						if (plainText.indexOf("Encrypt file: ") == 0) {
							byte[] encrypt = rsa.encrypt(byteData, RSA_KEY.PRIVATE_KEY);
							byteData = encrypt;
							textAreaCipherText.setText("Encryption successful, choose save text to save the result");
						} else {
							byte[] plainTextByte = plainText.getBytes();
							byte[] encrypt = rsa.encrypt(plainTextByte, RSA_KEY.PRIVATE_KEY);
							textAreaCipherText.setText(Base64.getEncoder().encodeToString(encrypt));
						}
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Can't encrypt, try again");
		}
	}
}
