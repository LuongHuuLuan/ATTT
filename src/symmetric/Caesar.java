package symmetric;

import symmetric.Alphabet.ALPHABET;

public class Caesar {
	private ALPHABET useAlphabet = ALPHABET.ENGLISH;
	private int alphabetLength = Alphabet.ENGLISH_ALPHABET.length;

	public String encrypt(String input, int key) {
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i], useAlphabet)) {
				int indexChar = Alphabet.getIndex(inputChars[i], useAlphabet);
				result += (indexChar + key <= alphabet.length - 1) ? alphabet[indexChar + key]
						: alphabet[(indexChar + key) % alphabet.length];
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}

	public String decrypt(String input, int key) {
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i], useAlphabet)) {
				int indexChar = Alphabet.getIndex(inputChars[i], useAlphabet);
				result += (indexChar - key >= 0) ? alphabet[indexChar - key]
						: alphabet[(indexChar - key) + alphabet.length];
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

	public int getAlphabetLength() {
		return alphabetLength;
	}

}
