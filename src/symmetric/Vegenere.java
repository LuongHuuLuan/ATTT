package symmetric;

import java.util.Scanner;
import java.util.StringTokenizer;

// input includes key lenght and keys
// EX: 4 10 13 2 7: 4 is key lenght and 10 13 2 7 is key
public class Vegenere {

	public static String createKey(int keyLenght) {
		char[] alphabet = Alphabet.ALPHABET;
		String result = "";
		int count = keyLenght;
		while (count > 0) {
			int random = (int) Math.floor(Math.random() * alphabet.length);
			result += random + " ";
			count--;
		}
		return result.trim();
	}

	public static String createInputKey(String input) {
		StringTokenizer stk = new StringTokenizer(input);
		int countTokens = stk.countTokens();
		if (countTokens < 2) {
			throw new Error("Can not create key");
		} else {
			try {
				int keyLengh = Integer.parseInt(stk.nextToken());
				if (keyLengh > countTokens - 1) {
					throw new Error("A lack of some key value");
				} else if (keyLengh < countTokens - 1) {
					throw new Error("Excess of some key values");
				}
				for (int i = 0; i < keyLengh; i++) {
					if (Integer.parseInt(stk.nextToken()) > Alphabet.ALPHABET.length) {
						throw new Error("key value out of " + Alphabet.ALPHABET.length);
					}
				}
				return input.substring(input.indexOf(" ") + 1);
			} catch (NumberFormatException e) {
				throw new Error("Some value not int");
			}
		}
	}

	public static boolean checkInputKey(String input) {
		StringTokenizer stk = new StringTokenizer(input);
		int countTokens = stk.countTokens();
		if (countTokens < 2) {
			return false;
		} else {
			try {
				int keyLengh = Integer.parseInt(stk.nextToken());
				if (keyLengh > countTokens - 1) {
					return false;
				} else if (keyLengh < countTokens - 1) {
					return false;
				}
				for (int i = 0; i < keyLengh; i++) {
					if (Integer.parseInt(stk.nextToken()) > Alphabet.ALPHABET.length) {
						return false;
					}
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	public static String encrypt(String input, String key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		String[] keysString = key.split(" ");
		int[] keys = new int[keysString.length];
		for (int i = 0; i < keysString.length; i++) {
			keys[i] = Integer.parseInt(keysString[i]);
		}
		int indexKey = 0;
		for (int i = 0; i < inputChars.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if (inputChars[i] == alphabet[j]) {
					result += (j + keys[indexKey] < 29) ? alphabet[j + keys[indexKey]]
							: alphabet[(j + keys[indexKey]) % alphabet.length];
					indexKey = ++indexKey % keys.length;
				}
			}
			if (!Alphabet.include(inputChars[i])) {
				result += inputChars[i];
			}
		}
		return result;
	}

	public static String decrypt(String input, String key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		String[] keysString = key.split(" ");
		int[] keys = new int[keysString.length];
		for (int i = 0; i < keysString.length; i++) {
			keys[i] = Integer.parseInt(keysString[i]);
		}
		int indexKey = 0;
		for (int i = 0; i < inputChars.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if (inputChars[i] == alphabet[j]) {
					result += (j - keys[indexKey] >= 0) ? alphabet[j - keys[indexKey]]
							: alphabet[(j - keys[indexKey]) + alphabet.length];
					indexKey = ++indexKey % keys.length;
				}
			}
			if (!Alphabet.include(inputChars[i])) {
				result += inputChars[i];
			}
		}
		return result;
	}

	public static void test() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your key format: \"key lenght\" \"keyValue1, keyValue2,...\"");
		String key = createInputKey(sc.nextLine());
		System.out.println("Enter your plain text");
		String input = sc.nextLine();
		sc.close();
		System.out.println("Encrypt:");
		String encrypt = encrypt(input, key);
		System.out.println(encrypt);
		System.out.println("Decrypt:");
		System.out.println(decrypt(encrypt, key));
	}
}
