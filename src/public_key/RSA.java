package public_key;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

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

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	public void createKey(int keySize) {
		try {
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
			switch (keySize) {
			case 1024: {
				keyGenerator.initialize(1024);
				break;
			}
			case 2048: {
				keyGenerator.initialize(2048);
				break;
			}
			case 4096: {
				keyGenerator.initialize(4096);
				break;
			}
			default:
				throw new IllegalArgumentException("Wrong keysize: must be equal to 1024, 2048 or 4096");
			}
			keyPair = keyGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public int getMaxLenghtEncrypt(int byteLenght, RSA_KEY key) {
		if (key == RSA_KEY.PUBLIC_KEY) {
			if (byteLenght == 162)
				return 117;
			else if (byteLenght == 294) {
				return 245;
			} else {
				return 501;
			}
		} else {
			if (byteLenght < 700)
				return 117;
			else if (byteLenght < 1300) {
				return 245;
			} else {
				return 501;
			}
		}
	}

	public int getMaxLenghtDecrypt(int byteLenght, RSA_KEY key) {
		if (key == RSA_KEY.PUBLIC_KEY) {
			if (byteLenght == 162)
				return 128;
			else if (byteLenght == 294) {
				return 256;
			} else {
				return 512;
			}
		} else {
			// private 128 256 512
			if (byteLenght < 700)
				return 128;
			else if (byteLenght < 1300) {
				return 256;
			} else {
				return 512;
			}
		}
	}

	public byte[] encrypt(byte[] plainTextByte, RSA_KEY keyType) {
		// 117, 245, 501
		byte[] result = null;
		int maxLenght = 0;
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			if (keyType == RSA_KEY.PRIVATE_KEY) {
				cipher.init(Cipher.ENCRYPT_MODE, this.privateKey);
				maxLenght = getMaxLenghtEncrypt(this.privateKey.getEncoded().length, RSA_KEY.PRIVATE_KEY);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
				maxLenght = getMaxLenghtEncrypt(this.publicKey.getEncoded().length, RSA_KEY.PUBLIC_KEY);
			}
			int byteDiv = plainTextByte.length / maxLenght;
			int byteRest = plainTextByte.length % maxLenght;
			for (int i = 0; i < byteDiv; i++) {
				int startByte = maxLenght * i;
				int offsetByte = maxLenght * i + maxLenght;
				byte[] subByteArr = subByteArr(plainTextByte, startByte, offsetByte);
				byte[] cipherByte = cipher.doFinal(subByteArr);
				if (i == 0) {
					result = cipherByte;
				} else {
					result = mergeByteArr(result, cipherByte);
				}
			}
			if (byteRest != 0) {
				byte[] subByteArr = subByteArr(plainTextByte, plainTextByte.length - byteRest, plainTextByte.length);
				byte[] cipherByte = cipher.doFinal(subByteArr);
				if (result == null) {
					result = cipherByte;
				} else
					result = mergeByteArr(result, cipherByte);
			}
			return result;
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
		return null;
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

	public byte[] subByteArr(byte[] arr, int start, int offset) {
		byte[] result = new byte[offset - start];
		int j = 0;
		for (int i = start; i < offset; i++) {
			result[j] = arr[i];
			j++;
		}
		return result;
	}

	public byte[] decrypt(byte[] cipherTextByte, RSA_KEY keyType) {
		// 128 256 512
		byte[] result = null;
		int maxLenght = 0;
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			if (keyType == RSA_KEY.PRIVATE_KEY) {
				cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
				maxLenght = getMaxLenghtDecrypt(this.privateKey.getEncoded().length, RSA_KEY.PRIVATE_KEY);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, this.publicKey);
				maxLenght = getMaxLenghtDecrypt(this.publicKey.getEncoded().length, RSA_KEY.PUBLIC_KEY);
			}
			int byteDiv = cipherTextByte.length / maxLenght;
			int byteRest = cipherTextByte.length % maxLenght;
			for (int i = 0; i < byteDiv; i++) {
				int startByte = maxLenght * i;
				int offsetByte = maxLenght * i + maxLenght;
				byte[] subByteArr = subByteArr(cipherTextByte, startByte, offsetByte);
				byte[] cipherByte = cipher.doFinal(subByteArr);
				if (i == 0) {
					result = cipherByte;
				} else {
					result = mergeByteArr(result, cipherByte);
				}
			}
			if (byteRest != 0) {
				byte[] subByteArr = subByteArr(cipherTextByte, cipherTextByte.length - byteRest, cipherTextByte.length);
				byte[] cipherByte = cipher.doFinal(subByteArr);
				if (result == null) {
					result = cipherByte;
				} else
					result = mergeByteArr(result, cipherByte);
			}
			return result;
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
		return null;
	}
	public static void main(String[] args) {
		
	}
}
