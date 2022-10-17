package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileUtils {
	public static File chooseFile() {
		File result = null;
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter txtExt = new FileNameExtensionFilter("text file", "txt");
		fileChooser.setFileFilter(txtExt);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setDialogTitle("Select text file");

		int x = fileChooser.showDialog(null, "Select");
		if (x == JFileChooser.APPROVE_OPTION) {
			result = fileChooser.getSelectedFile();
		}
		return result;
	}

	public static String readFile(String path) {
		String result = "";
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = buffReader.readLine()) != null) {
				result += line + "\n";
			}
			buffReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can not read this file");
		}
		return result;
	}

	public static void onSave(String input) {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter txtExt = new FileNameExtensionFilter("text file", "txt");
		fileChooser.setFileFilter(txtExt);
		fileChooser.setDialogTitle("Choose location to save");
		int x = fileChooser.showSaveDialog(null);
		if (x == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				String[] options = { "Yes", "No" };
				int y = JOptionPane.showOptionDialog(null, "File is exist do you want replace it?", "Replace",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (y == 0) {
					FileWriter fw = new FileWriter(file);
					fw.write(input);
					fw.flush();
					fw.close();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Can not save file, try again");
			}
		}
	}

	public static String getKeyType(String path) {
		String result = "";
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(path));
			String line = buffReader.readLine();
			result = line;
			buffReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can not read this file");
		}
		return result;
	}
}
