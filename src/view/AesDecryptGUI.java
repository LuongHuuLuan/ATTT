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
import symmetric.AES;

public class AesDecryptGUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension dimForBtn = new Dimension(115, 40);
	private Font btnFont = new Font("Tahoma", Font.BOLD, 12);
	private Font labelFont = new Font("Tahoma", Font.BOLD, 16);
	private JTextArea textAreaPlainText, textAreaCipherText;
	private JPanel panelKey;
	private JLabel lblKey;

	private byte[] byteData;
	private AES aes;

	public AesDecryptGUI() {
		aes = new AES();
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

		JPanel panelCipherText = new JPanel();
		panelCipherText.setBounds(31, 62, 686, 145);
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

		JPanel panelPlainText = new JPanel();
		panelPlainText.setBounds(31, 218, 686, 145);
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

		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setForeground(Color.WHITE);
		btnDecrypt.setFont(btnFont);
		btnDecrypt.setBackground(Color.BLUE);
		btnDecrypt.setPreferredSize(dimForBtn);
		panelBtns.add(btnDecrypt);

		btnDecrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onDecrypt();
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
				if (key.getAlgorithm().equalsIgnoreCase("AES")) {
					aes.setSecretKey(key);
					keyIsReady();
				} else {
					JOptionPane.showMessageDialog(null, "This is not AES key");
				}
			} catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(null, "This is not AES key");
			}
		}
	}

	public void onImportText() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String[] fileNameSplit = choose.getName().split("\\.");
			if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
				String fileContent = FileUtils.readTextFile(choose.getAbsolutePath());
				textAreaCipherText.setText(fileContent.trim());
			} else {
				String fileContent = FileUtils.readTextFile(choose.getAbsolutePath());
				byteData = Base64.getDecoder().decode(fileContent.trim());
				textAreaCipherText.setText("Decrypt file: " + choose.getAbsolutePath());
			}
		}
	}

	public void onSaveText() {
		String plainText = textAreaPlainText.getText();
		if (plainText.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to save");
		} else {
			FileUtils.onByteSave(byteData);
		}
	}

	public void onDecrypt() {
		try {
			if (textAreaCipherText.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "Nothing to decrypt");
			} else {
				if (aes.getSecretKey() == null) {
					JOptionPane.showMessageDialog(null, "Import key before decrypt");
				} else {
					String cipherText = textAreaCipherText.getText();
					if (cipherText.indexOf("Decrypt file: ") == 0) {
						byte[] decrypt = aes.decrypt(byteData);
						byteData = decrypt;
						textAreaPlainText.setText("Decryption successful, choose save text to save the result");
					} else {
						byteData = aes.decrypt(Base64.getDecoder().decode(cipherText));
						String decrypt = aes.decrypt(cipherText);
						textAreaPlainText.setText(decrypt);
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Can't decrypt, try again");
		}
	}
}
