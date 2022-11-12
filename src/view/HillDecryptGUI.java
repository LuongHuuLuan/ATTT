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
import symmetric.Hill;

public class HillDecryptGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension dimForBtn = new Dimension(115, 40);
	private Font btnFont = new Font("Tahoma", Font.BOLD, 12);
	private Font labelFont = new Font("Tahoma", Font.BOLD, 16);
	private JTextField textFieldKey;
	private Hill hill;
	private JTextArea textAreaPlainText, textAreaCipherText;
	private JRadioButton rdoUseEnglish, rdoUseVietNamese;

	public HillDecryptGUI() {
		hill = new Hill();
		setLayout(null);

		JLabel lblTool = new JLabel("@LHL Encrypt Tool");
		lblTool.setHorizontalAlignment(SwingConstants.CENTER);
		lblTool.setForeground(Color.LIGHT_GRAY);
		lblTool.setBounds(0, 438, 744, 14);
		add(lblTool);

		JPanel panelKey = new JPanel();
		panelKey.setBounds(0, 11, 745, 30);
		add(panelKey);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setBounds(31, 5, 686, 20);
		lblKey.setFont(labelFont);
		panelKey.add(lblKey);
		panelKey.setLayout(null);

		textFieldKey = new JTextField();
		textFieldKey.setBounds(73, 5, 644, 20);
		panelKey.add(textFieldKey);
		textFieldKey.setColumns(10);

		JPanel panelCipherText = new JPanel();
		panelCipherText.setBounds(31, 70, 686, 140);
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
		panelPlainText.setBounds(31, 225, 686, 140);
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
				hill.setAlphabet(ALPHABET.ENGLISH);
			}
		});

		rdoUseVietNamese = new JRadioButton("Use Vietnamese alphabet");
		btnGroups.add(rdoUseVietNamese);
		rdoUseVietNamese.setBounds(340, 0, 200, 20);
		panelAlphabet.add(rdoUseVietNamese);
		rdoUseVietNamese.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hill.setAlphabet(ALPHABET.VIETNAMESE);
			}
		});
	}

	public void onImportKey() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String keyType = FileUtils.getKeyType(choose.getAbsolutePath());
			if (keyType.trim().toLowerCase().equals("hill")) {
				String alphabetType = FileUtils.getKeyAlphabet(choose.getAbsolutePath());
				if (alphabetType.equalsIgnoreCase("ENGLISH")) {
					rdoUseEnglish.setSelected(true);
					hill.setAlphabet(ALPHABET.ENGLISH);
				} else {
					rdoUseVietNamese.setSelected(true);
					hill.setAlphabet(ALPHABET.VIETNAMESE);
				}
				String fileContent = FileUtils.readContentFile(choose.getAbsolutePath());
				textFieldKey.setText(fileContent.trim());
			} else {
				JOptionPane.showMessageDialog(null, "This is not hill key");
			}
		}
	}

	public void onImportText() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String fileContent = FileUtils.readFile(choose.getAbsolutePath());
			textAreaCipherText.setText(fileContent);
		}
	}

	public void onSaveText() {
		if (textAreaCipherText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to save");
		} else {
			FileUtils.onSaveText(textAreaCipherText.getText());
		}
	}

	public void onDecrypt() {
		if (textAreaCipherText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to encrypt");
		} else {
			String key = this.textFieldKey.getText().trim();
			if (!hill.checkKey(key)) {
				JOptionPane.showMessageDialog(null, "Invalid key");
			} else {
				String encryptText = hill.decryptWithSpecialChar(this.textAreaCipherText.getText().trim(), key);
				this.textAreaPlainText.setText(encryptText);
			}
		}
	}
}
