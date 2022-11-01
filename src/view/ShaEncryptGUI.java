package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import hasing.Hasing;

public class ShaEncryptGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension dimForBtn = new Dimension(115, 40);
	private Font btnFont = new Font("Tahoma", Font.BOLD, 12);
	private Font labelFont = new Font("Tahoma", Font.BOLD, 16);
	private DefaultComboBoxModel<String> modelCombobox = new DefaultComboBoxModel<String>(
			new String[] { "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512" });
	private JComboBox<String> comboBoxKeySize;
	private JTextArea textAreaPlainText, textAreaCipherText;
	private Hasing hasing;

	public ShaEncryptGUI() {
		setLayout(null);

		JLabel lblTool = new JLabel("@LHL Encrypt Tool");
		lblTool.setHorizontalAlignment(SwingConstants.CENTER);
		lblTool.setForeground(Color.LIGHT_GRAY);
		lblTool.setBounds(0, 438, 744, 14);
		add(lblTool);

		JPanel panelKeySize = new JPanel();
		panelKeySize.setBounds(31, 11, 686, 30);
		add(panelKeySize);
		panelKeySize.setLayout(null);

		comboBoxKeySize = new JComboBox<String>();
		comboBoxKeySize.setModel(modelCombobox);
		comboBoxKeySize.setBounds(297, 3, 101, 23);
		comboBoxKeySize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelKeySize.add(comboBoxKeySize);

		JLabel lblChooseType = new JLabel("Choose type:");
		lblChooseType.setBounds(180, 6, 101, 17);
		lblChooseType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelKeySize.add(lblChooseType);

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

	public String getSelected() {
		String[] types = { "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512" };
		return types[comboBoxKeySize.getSelectedIndex()];
	}

	public void onImportText() {
		File choose = FileUtils.chooseFile();
		if (choose != null) {
			String[] fileNameSplit = choose.getName().split("\\.");
			if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
				String fileContent = FileUtils.readFile(choose.getAbsolutePath());
				textAreaPlainText.setText(fileContent);
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

	public void onEncrypt() {
		hasing = Hasing.getIntance(getSelected());
		System.out.println(hasing.getName());
		if (textAreaPlainText.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to encrypt");
		} else {
			String encryptText = this.hasing.getHashText(this.textAreaPlainText.getText());
			this.textAreaCipherText.setText(encryptText);
		}
	}
}
