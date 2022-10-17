package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Md5GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Md5GUI() {
		setLayout(new GridLayout(1, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		JPanel tabEncrypt = new Md5EncryptGUI();
		tabbedPane.addTab("Encrypt", null, tabEncrypt, null);
		JPanel tabDecrypt = new Md5DecryptGUI();
		tabbedPane.addTab("Decrypt", null, tabDecrypt, null);

	}
}
