package view;

import javax.swing.JFrame;

public class MainView extends JFrame{
	
	public static void main(String[] args) {
		JFrame main = new MainView();
		main.setVisible(true);
		main.setSize(1000, 600);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLocationRelativeTo(null);
	}
}
