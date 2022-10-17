package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomeGUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HomeGUI() {
		setLayout(null);
		
		JLabel lblDescription = new JLabel("SECURE YOUR DATA");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setForeground(new Color(255, 255, 255));
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblDescription.setBounds(0, 388, 769, 43);
		add(lblDescription);
		
		JLabel lblBackgound = new JLabel("");
		lblBackgound.setIcon(new ImageIcon(HomeGUI.class.getResource("/images/welcomeBg.png")));
		lblBackgound.setBounds(0, 0, 769, 480);
		add(lblBackgound);
	}
}
