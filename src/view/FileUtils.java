package view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

	public static Object readObjectFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		Object result = null;
		ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(path));
		result = OIS.readObject();
		OIS.close();
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

	public static byte[] readByteFile(String path) {
		byte[] byteFile = null;
		ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int byteRead = 0;
		try {
			BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(path));
			while ((byteRead = BIS.read(buff)) != -1) {
				BAOS.write(buff, 0, byteRead);
			}
			byteFile = BAOS.toByteArray();
			BIS.close();
			BAOS.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can not read this file");
		}
		return byteFile;
	}

	public static void onByteSave(byte[] input) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose location to save");
		int x = fileChooser.showSaveDialog(null);
		if (x == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				String[] options = { "Yes", "No" };
				int y = JOptionPane.showOptionDialog(null, "File is exist do you want replace it?", "Replace",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (y == 0) {
					ByteArrayInputStream BAIS = new ByteArrayInputStream(input);
					byte[] buff = new byte[1024];
					int byteRead = 0;
					BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(file));
					while ((byteRead = BAIS.read(buff)) != -1) {
						BOS.write(buff, 0, byteRead);
					}
					BOS.flush();
					BAIS.close();
					BOS.close();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Can not save file, try again");
			}
		}
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

	public static void onSaveObj(Object obj) {
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
					ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(file));
					OOS.writeObject(obj);
					OOS.flush();
					OOS.close();
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
		return result.split(" ")[0];
	}

	public static String getKeyAlphabet(String path) {
		String result = "";
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(path));
			String line = buffReader.readLine();
			result = line;
			buffReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can not read this file");
		}
		return result.split(" ")[1];
	}

	public static String readContentFile(String path) {
		String result = "";
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(path));
			String line = buffReader.readLine();
			while ((line = buffReader.readLine()) != null) {
				result += line + "\n";
			}
			buffReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can not read this file");
		}
		return result;
	}
}
