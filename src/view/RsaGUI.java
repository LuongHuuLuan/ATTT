package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class RsaGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RsaGUI() {
		setLayout(new GridLayout(1, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		JPanel tabEncrypt = new RsaEncryptGUI();
		tabbedPane.addTab("Encrypt", null, tabEncrypt, null);
		JPanel tabDecrypt = new RsaDecryptGUI();
		tabbedPane.addTab("Decrypt", null, tabDecrypt, null);

	}
}
