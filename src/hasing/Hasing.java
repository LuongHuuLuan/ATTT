package hasing;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasing {

	public static final String MD5 = "MD5";
	public static final String SHA224 = "SHA-1";

	private String name;
	private MessageDigest mDigest;

	private Hasing(String name) {
		this.name = name;
		try {
			mDigest = MessageDigest.getInstance(name);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MessageDigest getmDigest() {
		return mDigest;
	}

	public void setmDigest(MessageDigest mDigest) {
		this.mDigest = mDigest;
	}

	public static String getMd5() {
		return MD5;
	}

	public static String getSha224() {
		return SHA224;
	}

	public static Hasing getIntance(String name) {
		Hasing hasing = new Hasing(name);
		return hasing;
	}

	public String getHashText(String input) {
		byte[] inputByte = input.getBytes();
		mDigest.update(inputByte);
		byte[] outputByte = mDigest.digest();
		return byteToHex(outputByte);
	}

	public String getHashFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			BufferedInputStream BIS;
			try {
				BIS = new BufferedInputStream(new FileInputStream(file));
				byte[] buff = new byte[1024];
				int byteRead = 0;
				while ((byteRead = BIS.read(buff)) != -1) {
					mDigest.update(buff, 0, byteRead);
				}
				BIS.close();
				return byteToHex(mDigest.digest());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String byteToHex(byte[] input) {
		StringBuilder result = new StringBuilder();
		for (byte b : input) {
			result.append(String.format("%02x", b));
		}
		return result.toString();
	}

	public static void main(String[] args) {
		Hasing md5 = new Hasing(Hasing.SHA224);
		System.out.println(md5.getHashText("Đại học nông lâm"));
		System.out.println(md5.getHashText("Đại học nông lâm"));
		System.out.println(md5.getHashText("Đại học nông lâm"));
		System.out.println(md5.getHashText("Đại học nông lâm"));
		System.out.println(md5.getHashFile("C:\\Users\\LuanPc\\Desktop\\dic.txt"));
	}
}
