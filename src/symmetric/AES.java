package symmetric;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AES {
	private SecretKey secretKey;
	private Cipher cipher;

	public AES() {
		try {
			cipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}

	}

	public SecretKey getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(SecretKey secretKey) {
		this.secretKey = secretKey;
	}

	public void createKey(int keySize) {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			switch (keySize) {
			case 128: {
				keyGen.init(128);
				break;
			}
			case 192: {
				keyGen.init(192);
				break;
			}
			case 256: {
				keyGen.init(256);
				break;
			}
			default:
				throw new IllegalArgumentException("Wrong keysize: must be equal to 128, 192 or 256");
			}
			secretKey = keyGen.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String plainText) {
		try {
			byte[] input = plainText.getBytes();
			byte[] cipherByte;
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			cipherByte = cipher.doFinal(input);

			return Base64.getEncoder().encodeToString(cipherByte);
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] encrypt(byte[] plainByte) {
		try {
			byte[] cipherByte;
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			cipherByte = cipher.doFinal(plainByte);

			return cipherByte;
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String cipherText) {
		String plainText = "";
		try {
			byte[] input = Base64.getDecoder().decode(cipherText.getBytes());
			byte[] plainByte;
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			plainByte = cipher.doFinal(input);
			return new String(plainByte);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return plainText;
	}

	public byte[] decrypt(byte[] cipherByte) {
		try {
			byte[] plainByte;
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			plainByte = cipher.doFinal(cipherByte);

			return plainByte;
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		AES aes = new AES();
		aes.createKey(128);
		aes.createKey(192);
		aes.createKey(256);
	}
}
