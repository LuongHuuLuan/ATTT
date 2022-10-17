package symmetric;

import java.util.ArrayList;
import java.util.List;

// create 1 key by change position of alphabet abcde => baced
// after that substitute similar position a=>b, b=>a, c=>c, d=>e, e=>d
public class Substitution {

	public String createKey() {
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

	public String encrypt(String input, String key) {
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		char[] keyChars = key.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i])) {
				int indexChar = Alphabet.getIndex(inputChars[i]);
				result += keyChars[indexChar];
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
		char[] keyChars = key.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i])) {
				for (int j = 0; j < keyChars.length; j++) {
					if (inputChars[i] == keyChars[j]) {
						result += alphabet[j];
					}
				}
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}
}
