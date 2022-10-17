package symmetric;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncryptFile {
	private SecretKey key;
	private Cipher cipher;

	public EncryptFile() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			key = keyGenerator.generateKey();
			cipher = Cipher.getInstance("DES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public void readKey(String path) {
		try {
			ObjectInputStream OIS = new ObjectInputStream(new FileInputStream(path));
			SecretKey secretKey = (SecretKey) OIS.readObject();
			setKey(secretKey);
			System.out.println(key);
			OIS.close();
		} catch (FileNotFoundException e) {
			System.exit(0);
			throw new RuntimeException(e);
		} catch (IOException e) {
			System.exit(0);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.exit(0);
			throw new RuntimeException(e);
		}
	}

	public void setKey(SecretKey secretKey) {
		this.key = secretKey;
	}

	public void writeKey(String path) {
		try {
			ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(path));
			OOS.writeObject(key);
			OOS.flush();
			OOS.close();
		} catch (FileNotFoundException e) {
			System.exit(0);
			throw new RuntimeException(e);
		} catch (IOException e) {
			System.exit(0);
			throw new RuntimeException(e);
		}

	}

	public void encryptFile(String pathIn, String pathOut) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(pathIn));
			CipherOutputStream COS = new CipherOutputStream(new BufferedOutputStream(new FileOutputStream(pathOut)),
					cipher);
			byte[] buff = new byte[1024];
			int readByte = 0;
			while ((readByte = BIS.read(buff)) != -1) {
				COS.write(buff, 0, readByte);
			}
			COS.flush();

			BIS.close();
			COS.close();
			System.out.println("Encrypt successful");
		} catch (FileNotFoundException e) {
			System.exit(0);
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			System.out.println("Invalid key");
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
			throw new RuntimeException(e);
		}
	}

	public void decryptFile(String pathIn, String pathOut) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			CipherInputStream CIS = new CipherInputStream(new FileInputStream(pathIn), cipher);
			BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(pathOut));
			byte[] buff = new byte[1024];
			int readByte = 0;
			while ((readByte = CIS.read(buff)) != -1) {
				BOS.write(buff, 0, readByte);
			}
			BOS.flush();

			CIS.close();
			BOS.close();
			System.out.println("Decrypt successful");
		} catch (FileNotFoundException e) {
			System.exit(0);
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			System.out.println("Invalid key");
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		EncryptFile encryptFile = new EncryptFile();
//		encryptFile.writeKey("E:\\TaiLieuHocTap\\Nam4\\AnToanThongTin_ThayLong\\test\\key.txt");
//		encryptFile.readKey("E:\\TaiLieuHocTap\\Nam4\\AnToanThongTin_ThayLong\\test\\key.txt");
//		encryptFile.encryptFile("E:\\TaiLieuHocTap\\Nam4\\AnToanThongTin_ThayLong\\test\\test.png",
//				"E:\\TaiLieuHocTap\\Nam4\\AnToanThongTin_ThayLong\\test\\encryptTest.png");

		encryptFile.readKey("E:\\TaiLieuHocTap\\Nam4\\AnToanThongTin_ThayLong\\test\\key.txt");
		encryptFile.decryptFile("E:\\TaiLieuHocTap\\Nam4\\AnToanThongTin_ThayLong\\test\\encryptTest.png",
				"E:\\TaiLieuHocTap\\Nam4\\AnToanThongTin_ThayLong\\test\\decryptTest.png");
	}

}
