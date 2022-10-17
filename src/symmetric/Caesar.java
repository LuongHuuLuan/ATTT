package symmetric;

public class Caesar {

	public String encrypt(String input, int key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i])) {
				int indexChar = Alphabet.getIndex(inputChars[i]);
				result += (indexChar + key <= 28) ? alphabet[indexChar + key]
						: alphabet[(indexChar + key) % alphabet.length];
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}

	public String decrypt(String input, int key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i])) {
				int indexChar = Alphabet.getIndex(inputChars[i]);
				result += (indexChar - key >= 0) ? alphabet[indexChar - key]
						: alphabet[(indexChar - key) + alphabet.length];
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}

}
