package symmetric;

import java.util.ArrayList;
import java.util.List;

import symmetric.Alphabet.ALPHABET;

// create 1 key by change position of alphabet abcde => baced
// after that substitute similar position a=>b, b=>a, c=>c, d=>e, e=>d
public class Substitution {
	private ALPHABET useAlphabet = ALPHABET.ENGLISH;
	private int alphabetLength = Alphabet.ENGLISH_ALPHABET.length;

	public String createKey() {
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
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
		String fillerInput = Alphabet.filterInput(input, useAlphabet);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		char[] keyChars = key.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i], useAlphabet)) {
				int indexChar = Alphabet.getIndex(inputChars[i], useAlphabet);
				result += keyChars[indexChar];
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}

	public String decrypt(String input, String key) {
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		String fillerInput = Alphabet.filterInput(input, useAlphabet);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		char[] keyChars = key.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i], useAlphabet)) {
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

	public void setAlphabet(ALPHABET useAlphabet) {
		this.useAlphabet = useAlphabet;
		if (useAlphabet == ALPHABET.ENGLISH) {
			this.alphabetLength = Alphabet.ENGLISH_ALPHABET.length;
		} else {
			this.alphabetLength = Alphabet.VIETNAMESE_ALPHABET.length;
		}
	}

	public ALPHABET getUseAlphabet() {
		return useAlphabet;
	}

	public int getAlphabetLength() {
		return alphabetLength;
	}
}
