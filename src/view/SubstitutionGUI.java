package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SubstitutionGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SubstitutionGUI() {
		setLayout(new GridLayout(1, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		JPanel tabEncrypt = new SubstitutionEncryptGUI();
		tabbedPane.addTab("Encrypt", null, tabEncrypt, null);
		JPanel tabDecrypt = new SubstitutionDecryptGUI();
		tabbedPane.addTab("Decrypt", null, tabDecrypt, null);

	}
}
