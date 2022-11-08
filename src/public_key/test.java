package public_key;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import public_key.RSA.RSA_KEY;

public class test {
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		RSA rsa = new RSA();
		rsa.createKey(2048);
		String publicKey = Base64.getEncoder().encodeToString(rsa.getPublicKey().getEncoded());
		
		System.out.println("Public key: "+ publicKey);
		
		String input = "Lương Hữu Luân";
		System.out.println("========================");
		System.out.println("input: " + input);
		System.out.println("Encrypt with public key");
		byte[] cipher = rsa.encrypt(input.getBytes(), RSA_KEY.PRIVATE_KEY);
		System.out.println(new String(cipher, StandardCharsets.UTF_8));
		System.out.println("Decrypt with private key");
		System.out.println(new String(rsa.decrypt(cipher, RSA_KEY.PUBLIC_KEY), StandardCharsets.UTF_8));
		System.out.println("========================");
		
		System.out.println();
		
		System.out.println("========================");
		System.out.println("input: " + input);
		System.out.println("Encrypt with private key");
		cipher = rsa.encrypt(input.getBytes(), RSA_KEY.PUBLIC_KEY);
		System.out.println(new String(cipher, StandardCharsets.UTF_8));
		System.out.println("Decrypt with public key");
		System.out.println(new String(rsa.decrypt(cipher, RSA_KEY.PRIVATE_KEY), StandardCharsets.UTF_8));
		System.out.println("========================");
	}
}
