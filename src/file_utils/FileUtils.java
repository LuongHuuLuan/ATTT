package file_utils;

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

import view.mainGUI;

public class FileUtils {

	public static File chooseFile() {
		File result = null;
		File curDir = new File(mainGUI.WORKSPACE_PATH);
		JFileChooser fileChooser = new JFileChooser(curDir);
		FileNameExtensionFilter filterExt = new FileNameExtensionFilter("Ecr, Txt Files", "ecr", "txt");
		fileChooser.setFileFilter(filterExt);
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
			e.printStackTrace();
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

	private static void saveByte(File file, byte[] bytes) {
		try {
			ByteArrayInputStream BAIS = new ByteArrayInputStream(bytes);
			byte[] buff = new byte[1024];
			int byteRead = 0;
			BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(file));
			while ((byteRead = BAIS.read(buff)) != -1) {
				BOS.write(buff, 0, byteRead);
			}
			BOS.flush();
			BAIS.close();
			BOS.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can not save file, try again");
		}
	}

	public static void onByteSave(byte[] bytes) {
		JFileChooser fileChooser = new JFileChooser(mainGUI.WORKSPACE_PATH);
		FileNameExtensionFilter filterExt = new FileNameExtensionFilter("Ecr, Txt Files", "ecr", "txt");
		fileChooser.setFileFilter(filterExt);
		File file = new File("fileName.ecr");
		fileChooser.setSelectedFile(file);
		fileChooser.setDialogTitle("Choose location to save");
		int x = fileChooser.showSaveDialog(null);
		if (x == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			if (file.exists()) {
				if (file.exists()) {
					String[] options = { "Yes", "No" };
					int y = JOptionPane.showOptionDialog(null, "File is exist do you want replace it?", "Replace",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
					if (y == 0) {
						saveByte(file, bytes);
					}

				} else {
					saveByte(file, bytes);
				}
			}
		}
	}

	public static void saveText(File file, String input) {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(input);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Can not save file, try again");
		}
	}

	public static void onSaveText(String input) {
		JFileChooser fileChooser = new JFileChooser(mainGUI.WORKSPACE_PATH);
		FileNameExtensionFilter filterExt = new FileNameExtensionFilter("Ecr, Txt Files", "ecr", "txt");
		fileChooser.setFileFilter(filterExt);
		File file = new File("fileName.ecr");
		fileChooser.setSelectedFile(file);
		fileChooser.setDialogTitle("Choose location to save");
		int x = fileChooser.showSaveDialog(null);
		if (x == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			if (file.exists()) {
				String[] options = { "Yes", "No" };
				int y = JOptionPane.showOptionDialog(null, "File is exist do you want replace it?", "Replace",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (y == 0) {
					saveText(file, input);
				}

			} else {
				saveText(file, input);
			}
		}
	}

	private static void saveObj(File file, Object obj) {
		try {
			ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(file));
			OOS.writeObject(obj);
			OOS.flush();
			OOS.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Can not save file, try again");
		}
	}

	public static void onSaveObj(Object obj) {
		JFileChooser fileChooser = new JFileChooser(mainGUI.WORKSPACE_PATH);
		FileNameExtensionFilter filterExt = new FileNameExtensionFilter("Ecr, Txt Files", "ecr", "txt");
		fileChooser.setFileFilter(filterExt);
		File file = new File("fileName.ecr");
		fileChooser.setSelectedFile(file);
		fileChooser.setDialogTitle("Choose location to save");
		int x = fileChooser.showSaveDialog(null);
		if (x == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			if (file.exists()) {
				String[] options = { "Yes", "No" };
				int y = JOptionPane.showOptionDialog(null, "File is exist do you want replace it?", "Replace",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (y == 0) {
					saveObj(file, obj);
				}

			} else {
				saveObj(file, obj);
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
