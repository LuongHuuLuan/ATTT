package view;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ShaGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShaGUI() {
		setLayout(new GridLayout(1, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		JPanel tabEncrypt = new ShaEncryptGUI();
		tabbedPane.addTab("Encrypt", null, tabEncrypt, null);

	}
}
