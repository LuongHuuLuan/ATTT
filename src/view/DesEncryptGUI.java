package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import file_utils.FileUtils;
import symmetric.DES;

public class DesEncryptGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension dimForBtn = new Dimension(115, 40);
	private Font btnFont = new Font("Tahoma", Font.BOLD, 12);
	private Font labelFont = new Font("Tahoma", Font.BOLD, 16);
	private DES des;
	private JTextArea textAreaPlainText, textAreaCipherText;
	private JPanel panelKey;
	private JLabel lblKey;
	private byte[] byteData;

	public DesEncryptGUI() {
		des = new DES();
		setLayout(null);

		JLabel lblTool = new JLabel("@LHL Encrypt Tool");
		lblTool.setHorizontalAlignment(SwingConstants.CENTER);
		lblTool.setForeground(Color.LIGHT_GRAY);
		lblTool.setBounds(0, 438, 744, 14);
		add(lblTool);

		panelKey = new JPanel();
		panelKey.setBackground(new Color(255, 128, 128));
		panelKey.setBounds(31, 11, 686, 30);
		add(panelKey);
		panelKey.setLayout(null);

		lblKey = new JLabel("Please import or create new key");
		lblKey.setForeground(new Color(255, 255, 255));
		lblKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKey.setHorizontalAlignment(SwingConstants.CENTER);
		lblKey.setBounds(0, 0, 686, 30);
		panelKey.add(lblKey);

		JPanel panelPlainText = new JPanel();
		panelPlainText.setBounds(31, 62, 686, 145);
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
		panelCipherText.setBounds(31, 218, 686, 145);
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

		JButton btnImportText = new JButton("Import text");
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

		JButton btnSaveText = new JButton("Save text");
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
	}

	public void keyIsReady() {
		lblKey.setText("Key is ready");
		panelKey.setBackground(new Color(128, 255, 128));
	}

	public void onImportKey() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {

			try {
				SecretKey key = (SecretKey) FileUtils.readObjectFile(choose.getAbsolutePath());
				if (key.getAlgorithm().equalsIgnoreCase("DES")) {
					des.setSecretKey(key);
					keyIsReady();
				} else {
					JOptionPane.showMessageDialog(null, "This is not DES key");
				}
			} catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(null, "This is not DES key");
			}
		}
	}

	public void onImportText() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String[] fileNameSplit = choose.getName().split("\\.");
			if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
				String fileContent = FileUtils.readFile(choose.getAbsolutePath());
				textAreaPlainText.setText(fileContent.trim());
			} else {
				byteData = FileUtils.readByteFile(choose.getAbsolutePath());
				textAreaPlainText.setText("Encrypt file: " + choose.getAbsolutePath());
			}
		}
	}

	public void onCreateKey() {
		des.createKey();
		keyIsReady();
	}

	public void onSaveKey() {
		if (des.getSecretKey() == null) {
			JOptionPane.showMessageDialog(null, "Key is null, Import or create new key");
		} else {
			FileUtils.onSaveObj(des.getSecretKey());
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
				if (des.getSecretKey() == null) {
					JOptionPane.showMessageDialog(null, "Import or create new key before encrypt");
				} else {
					String plainText = textAreaPlainText.getText();
					if (plainText.indexOf("Encrypt file: ") == 0) {
						byte[] encrypt = des.encrypt(byteData);
						byteData = encrypt;
						textAreaCipherText.setText("Encryption successful, choose save text to save the result");
					} else {
						String encrypt = des.encrypt(plainText);
						textAreaCipherText.setText(encrypt);
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Can't encrypt, try again");
		}
	}
}
