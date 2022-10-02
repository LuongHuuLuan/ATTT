package symmetric;

import java.util.ArrayList;
import java.util.List;

// create 1 key by change position of alphabet abcde => baced
// after that substitute similar position a=>b, b=>a, c=>c, d=>e, e=>d
public class Substitution {

	public static String createKey() {
		char[] alphabet = Alphabet.ALPHABET;
		String result = "";
		List<Character> charsList = new ArrayList<Character>();
		for (int i = 0; i < alphabet.length; i++) {
			charsList.add(alphabet[i]);
		}
		while (!charsList.isEmpty()) {
			int randomNum = (int) Math.floor(Math.random() * charsList.size());
			char c = charsList.remove(randomNum);
			result += c;
		}
		return result;
	}

	public static String encrypt(String input, String key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		char[] keyChars = key.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if (inputChars[i] == alphabet[j]) {
					result += keyChars[j];
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
		char[] keyChars = key.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			for (int j = 0; j < keyChars.length; j++) {
				if (inputChars[i] == keyChars[j]) {
					result += alphabet[j];
				}
			}
			if (!Alphabet.include(inputChars[i])) {
				result += inputChars[i];
			}
		}
		return result;
	}
}
