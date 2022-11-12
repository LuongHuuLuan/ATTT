package public_key;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
	private KeyPair keyPair;
	private PublicKey publicKey;
	private PrivateKey privateKey;

	public enum RSA_KEY {
		PUBLIC_KEY, PRIVATE_KEY
	}

	public RSA() {
		
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		X509EncodedKeySpec ks = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PublicKey pk = kf.generatePublic(ks);
		this.publicKey = pk;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey pk = kf.generatePrivate(ks);
		this.privateKey = pk;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public byte[] mergeByteArr(byte[] arr1, byte[] arr2) {
		byte[] result = new byte[arr1.length + arr2.length];
		int offset = 0;
		for (int i = 0; i < result.length; i++) {
			if (i < arr1.length) {
				result[i] = arr1[i];
			} else {
				result[i] = arr2[offset];
				offset++;
			}
		}
		return result;
	}

	public void createKey(int keySize) {
		try {
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(keySize);
			keyPair = keyGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public byte[] encrypt(byte[] plainTextByte, RSA_KEY keyType) {
		// 117, 245, 501
		byte[] result = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			if (keyType == RSA_KEY.PRIVATE_KEY) {
				cipher.init(Cipher.ENCRYPT_MODE, this.privateKey);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
			}
			int outputLenght = cipher.getOutputSize(plainTextByte.length) - 11;
			int countBlock = plainTextByte.length / outputLenght;
			int restBlock = plainTextByte.length % outputLenght;
			for (int i = 0; i < countBlock; i++) {
				int start = i * outputLenght;
				int offset = i * outputLenght + outputLenght;
				byte[] subByte = Arrays.copyOfRange(plainTextByte, start, offset);
				byte[] encrypt = cipher.doFinal(subByte);
				if (result == null) {
					result = encrypt;
				} else {
					result = mergeByteArr(result, encrypt);
				}
			}
			if (restBlock != 0) {
				byte[] subByte = Arrays.copyOfRange(plainTextByte, plainTextByte.length - restBlock,
						plainTextByte.length);
				byte[] encrypt = cipher.doFinal(subByte);
				if (result == null) {
					result = encrypt;
				} else {
					result = mergeByteArr(result, encrypt);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public byte[] decrypt(byte[] cipherTextByte, RSA_KEY keyType) {
		// 128 256 512
		byte[] result = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			if (keyType == RSA_KEY.PRIVATE_KEY) {
				cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, this.publicKey);
			}
			int outputLenght = cipher.getOutputSize(cipherTextByte.length);
			int countBlock = cipherTextByte.length / outputLenght;
			for (int i = 0; i < countBlock; i++) {
				int start = i * outputLenght;
				int offset = i * outputLenght + outputLenght;
				byte[] subByte = Arrays.copyOfRange(cipherTextByte, start, offset);
				byte[] encrypt = cipher.doFinal(subByte);
				if (result == null) {
					result = encrypt;
				} else {
					result = mergeByteArr(result, encrypt);
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
