package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import symmetric.Vegenere;

public class VegenereDecryptGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension dimForBtn = new Dimension(115, 40);
	private Font btnFont = new Font("Tahoma", Font.BOLD, 12);
	private Font labelFont = new Font("Tahoma", Font.BOLD, 16);
	private JTextField textFieldKey;
	private JTextField textFieldKeyLenght;
	private Vegenere vegenere;
	private JTextArea textAreaPlainText, textAreaCipherText;

	public VegenereDecryptGUI() {
		vegenere = new Vegenere();
		setLayout(null);

		JLabel lblTool = new JLabel("@LHL Encrypt Tool");
		lblTool.setHorizontalAlignment(SwingConstants.CENTER);
		lblTool.setForeground(Color.LIGHT_GRAY);
		lblTool.setBounds(0, 438, 744, 14);
		add(lblTool);

		JPanel panelKey = new JPanel();
		panelKey.setBounds(0, 11, 745, 30);
		add(panelKey);

		JLabel lblKeyLenght = new JLabel("Key lenght:");
		lblKeyLenght.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblKeyLenght.setBounds(31, 5, 100, 20);
		panelKey.add(lblKeyLenght);

		textFieldKeyLenght = new JTextField();
		textFieldKeyLenght.setColumns(10);
		textFieldKeyLenght.setBounds(128, 5, 86, 20);
		panelKey.add(textFieldKeyLenght);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setBounds(249, 5, 36, 20);
		lblKey.setFont(labelFont);
		panelKey.add(lblKey);
		panelKey.setLayout(null);

		textFieldKey = new JTextField();
		textFieldKey.setBounds(291, 5, 426, 20);
		panelKey.add(textFieldKey);
		textFieldKey.setColumns(10);

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
		textAreaPlainText.setEditable(false);
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

	public void onImportKey() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String[] fileNameSplit = choose.getName().split("\\.");
			if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
				String keyType = FileUtils.getKeyType(choose.getAbsolutePath());
				if (keyType.trim().toLowerCase().equals("vegenere")) {
					String fileContent = FileUtils.readFile(choose.getAbsolutePath());
					fileContent = fileContent.substring(fileContent.indexOf(keyType) + 9);
					textFieldKey.setText(fileContent.trim());
				} else {
					JOptionPane.showMessageDialog(null, "This is not vegenere key");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please choose file.txt");
			}
		}
	}

	public void onImportText() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String[] fileNameSplit = choose.getName().split("\\.");
			if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
				String fileContent = FileUtils.readFile(choose.getAbsolutePath());
				textAreaCipherText.setText(fileContent);
			} else {
				JOptionPane.showMessageDialog(null, "Please choose file.txt");
			}
		}
	}

	public void onSaveText() {
		if (textAreaCipherText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to save");
		} else {
			FileUtils.onSave(textAreaCipherText.getText());
		}
	}

	public void onDecrypt() {
		if (textAreaCipherText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to encrypt");
		} else {
			String key = this.textFieldKey.getText().trim();
			if (vegenere.checkKey(key)) {
				String decryptText = vegenere.decrypt(this.textAreaCipherText.getText(), key);
				this.textAreaPlainText.setText(decryptText);
			} else {
				JOptionPane.showMessageDialog(null, "Key is wrong format, try again vd: 'a c v' is key");
			}
		}
	}
}
