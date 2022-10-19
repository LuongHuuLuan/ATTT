package symmetric;

import java.util.Scanner;
import java.util.StringTokenizer;

// input includes key lenght and keys
// EX: 4 10 13 2 7: 4 is key lenght and 10 13 2 7 is key
public class Vegenere {

	public String createKey(int keyLenght) {
		char[] alphabet = Alphabet.ALPHABET;
		String result = "";
		int count = keyLenght;
		while (count > 0) {
			int random = (int) Math.floor(Math.random() * alphabet.length);
			result += alphabet[random] + " ";
			count--;
		}
		return result.trim();
	}

	public String createInputKey(String input) {
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

	public boolean checkInputKey(String input) {
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

	public boolean checkKey(String input) {
		String[] inputSplit = input.split(" ");
		for (String s : inputSplit) {
			if (s.length() != 1 && !Alphabet.include(s.charAt(0))) {
				return false;
			}
		}
		return true;
	}

	public String encrypt(String input, String key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		String[] keysString = key.split(" ");
		int[] keys = new int[keysString.length];
		for (int i = 0; i < keysString.length; i++) {
			keys[i] = Alphabet.getIndex(keysString[i].charAt(0));
		}
		int indexKey = 0;
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i])) {
				int indexChar = Alphabet.getIndex(inputChars[i]);
				result += (indexChar + keys[indexKey] < 29) ? alphabet[indexChar + keys[indexKey]]
						: alphabet[(indexChar + keys[indexKey]) % alphabet.length];
				indexKey = ++indexKey % keys.length;
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}

	public String decrypt(String input, String key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		String[] keysString = key.split(" ");
		int[] keys = new int[keysString.length];
		for (int i = 0; i < keysString.length; i++) {
			keys[i] = Alphabet.getIndex(keysString[i].charAt(0));
		}
		int indexKey = 0;
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i])) {
				int indexChar = Alphabet.getIndex(inputChars[i]);
				result += (indexChar - keys[indexKey] >= 0) ? alphabet[indexChar - keys[indexKey]]
						: alphabet[(indexChar - keys[indexKey]) + alphabet.length];
				indexKey = ++indexKey % keys.length;
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}

	public static void test() {
		Vegenere vegenere = new Vegenere();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your key format: \"key lenght\" \"keyValue1, keyValue2,...\"");
		String key = vegenere.createInputKey(sc.nextLine());
		System.out.println("Enter your plain text");
		String input = sc.nextLine();
		sc.close();
		System.out.println("Encrypt:");
		String encrypt = vegenere.encrypt(input, key);
		System.out.println(encrypt);
		System.out.println("Decrypt:");
		System.out.println(vegenere.decrypt(encrypt, key));
	}
}
