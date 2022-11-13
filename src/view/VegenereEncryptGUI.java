package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import file_utils.FileUtils;
import symmetric.Alphabet.ALPHABET;
import symmetric.Vegenere;

public class VegenereEncryptGUI extends JPanel {
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
	private JRadioButton rdoUseEnglish, rdoUseVietNamese;

	public VegenereEncryptGUI() {
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
		panelKey.setLayout(null);

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

		textFieldKey = new JTextField();
		textFieldKey.setBounds(291, 5, 426, 20);
		panelKey.add(textFieldKey);
		textFieldKey.setColumns(10);

		JPanel panelPlainText = new JPanel();
		panelPlainText.setBounds(31, 70, 686, 140);
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
		panelCipherText.setBounds(31, 225, 686, 140);
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

		JPanel panelAlphabet = new JPanel();
		panelAlphabet.setBounds(31, 45, 686, 20);
		add(panelAlphabet);
		panelAlphabet.setLayout(null);

		ButtonGroup btnGroups = new ButtonGroup();

		rdoUseEnglish = new JRadioButton("Use English alphabet");
		btnGroups.add(rdoUseEnglish);
		rdoUseEnglish.setBounds(180, 0, 160, 20);
		panelAlphabet.add(rdoUseEnglish);
		rdoUseEnglish.setSelected(true);
		rdoUseEnglish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vegenere.setAlphabet(ALPHABET.ENGLISH);
			}
		});

		rdoUseVietNamese = new JRadioButton("Use Vietnamese alphabet");
		btnGroups.add(rdoUseVietNamese);
		rdoUseVietNamese.setBounds(340, 0, 200, 20);
		panelAlphabet.add(rdoUseVietNamese);
		rdoUseVietNamese.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vegenere.setAlphabet(ALPHABET.VIETNAMESE);
			}
		});
	}

	public void onImportKey() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String keyType = FileUtils.getKeyType(choose.getAbsolutePath());
			if (keyType.trim().toLowerCase().equals("vegenere")) {
				String alphabetType = FileUtils.getKeyAlphabet(choose.getAbsolutePath());
				if (alphabetType.equalsIgnoreCase("ENGLISH")) {
					rdoUseEnglish.setSelected(true);
					vegenere.setAlphabet(ALPHABET.ENGLISH);
				} else {
					rdoUseVietNamese.setSelected(true);
					vegenere.setAlphabet(ALPHABET.VIETNAMESE);
				}
				String fileContent = FileUtils.readContentFile(choose.getAbsolutePath());
				textFieldKey.setText(fileContent.trim());
			} else {
				JOptionPane.showMessageDialog(null, "This is not vegenere key");
			}
		}
	}

	public void onImportText() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String[] fileNameSplit = choose.getName().split("\\.");
			if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
				String fileContent = FileUtils.readTextFile(choose.getAbsolutePath());
				textAreaPlainText.setText(fileContent);
			} else {
				JOptionPane.showMessageDialog(null, "Please choose file.txt");
			}
		}
	}

	public void onCreateKey() {
		try {
			int keyLenght = Integer.parseInt(textFieldKeyLenght.getText().trim());
			String key = vegenere.createKey(keyLenght);
			textFieldKey.setText(key);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Key lenght is a number");
		}
	}

	public void onSaveKey() {
		String saveContent = "vegenere";
		if (vegenere.getUseAlphabet() == ALPHABET.ENGLISH) {
			saveContent += " ENGLISH\n";
		} else {
			saveContent += " VIETNAMESE\n";
		}
		String key = textFieldKey.getText().trim();
		if (key.length() != 0) {
			if (vegenere.checkKey(key)) {
				saveContent += key;
				FileUtils.onSaveText(saveContent);
			} else {
				JOptionPane.showMessageDialog(null, "Key is wrong format, try again vd: 'a c v' is key");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Key is null, Please create key");

		}
	}

	public void onSaveText() {
		if (textAreaCipherText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to save");
		} else {
			FileUtils.onSaveText(textAreaCipherText.getText());
		}
	}

	public void onEncrypt() {
		if (textAreaPlainText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to encrypt");
		} else {
			String key = this.textFieldKey.getText().trim();
			if (vegenere.checkKey(key)) {
				String encryptText = vegenere.encrypt(this.textAreaPlainText.getText(), key);
				this.textAreaCipherText.setText(encryptText);
			} else {
				JOptionPane.showMessageDialog(null, "Key is wrong format, try again vd: 'a c v' is key");
			}
		}
	}
}
