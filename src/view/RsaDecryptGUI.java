package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RsaDecryptGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dimension dimForBtn = new Dimension(115, 40);
	private Font btnFont = new Font("Tahoma", Font.BOLD, 12);
	private Font labelFont = new Font("Tahoma", Font.BOLD, 16);
	private DefaultComboBoxModel<String> modelCombobox = new DefaultComboBoxModel<String>(
			new String[] { "512 bit", "1024 bit", "2048 bit" });
	private JTextField textFieldPLKey;
	private JTextField textFieldPVKey;

	public RsaDecryptGUI() {
		setLayout(null);

		JLabel lblTool = new JLabel("@LHL Encrypt Tool");
		lblTool.setHorizontalAlignment(SwingConstants.CENTER);
		lblTool.setForeground(Color.LIGHT_GRAY);
		lblTool.setBounds(0, 438, 744, 14);
		add(lblTool);

		JPanel panelKeySize = new JPanel();
		panelKeySize.setBounds(15, 11, 316, 30);
		add(panelKeySize);
		panelKeySize.setLayout(null);

		JLabel lblKeySize = new JLabel("Choose key size:");
		lblKeySize.setBounds(0, 6, 136, 20);
		lblKeySize.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelKeySize.add(lblKeySize);

		JComboBox<String> comboBoxKeySize = new JComboBox<String>();
		comboBoxKeySize.setModel(modelCombobox);
		comboBoxKeySize.setBounds(146, 5, 101, 23);
		comboBoxKeySize.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelKeySize.add(comboBoxKeySize);

		JPanel panelKey = new JPanel();
		panelKey.setBackground(new Color(128, 255, 128));
		panelKey.setBounds(334, 11, 407, 30);
		add(panelKey);
		panelKey.setLayout(null);

		JLabel lblKey = new JLabel("Key is ready");
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

		JTextArea textAreaPlainText = new JTextArea();
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

		JTextArea textAreaCipherText = new JTextArea();
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

		JButton btnImportText = new JButton("Import text");
		btnImportText.setForeground(Color.WHITE);
		btnImportText.setFont(btnFont);
		btnImportText.setBackground(Color.BLUE);
		btnImportText.setPreferredSize(dimForBtn);
		panelBtns.add(btnImportText);

		JButton btnSaveKey = new JButton("Save key");
		btnSaveKey.setPreferredSize(new Dimension(115, 40));
		btnSaveKey.setForeground(Color.WHITE);
		btnSaveKey.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSaveKey.setBackground(Color.BLUE);
		panelBtns.add(btnSaveKey);

		JButton btnSaveText = new JButton("Save text");
		btnSaveText.setForeground(Color.WHITE);
		btnSaveText.setFont(btnFont);
		btnSaveText.setBackground(Color.BLUE);
		btnSaveText.setPreferredSize(dimForBtn);
		panelBtns.add(btnSaveText);

		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setForeground(Color.WHITE);
		btnDecrypt.setFont(btnFont);
		btnDecrypt.setBackground(Color.BLUE);
		btnDecrypt.setPreferredSize(dimForBtn);
		panelBtns.add(btnDecrypt);

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

		JRadioButton rdbtnUsePublicKey = new JRadioButton("Use public key");
		rdbtnUsePublicKey.setSelected(true);
		rdbtnUsePublicKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnUsePublicKey.setBounds(578, 266, 166, 23);
		add(rdbtnUsePublicKey);
		btnGroup.add(rdbtnUsePublicKey);

		JRadioButton rdbtnUsePrivateKey = new JRadioButton("Use private key");
		rdbtnUsePrivateKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnUsePrivateKey.setBounds(578, 306, 166, 23);
		add(rdbtnUsePrivateKey);
		btnGroup.add(rdbtnUsePrivateKey);

	}

}
