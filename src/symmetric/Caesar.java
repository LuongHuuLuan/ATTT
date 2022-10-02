package symmetric;

public class Caesar {

	public static String encrypt(String input, int key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if (inputChars[i] == alphabet[j]) {
					result += (j + key <= 28) ? alphabet[j + key] : alphabet[(j + key) % alphabet.length];
				}
			}
			if (!Alphabet.include(inputChars[i])) {
				result += inputChars[i];
			}
		}
		return result;
	}
	
	public static String decrypt(String input, int key) {
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		String result = "";
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if (inputChars[i] == alphabet[j]) {
					result += (j - key >= 0) ? alphabet[j - key] : alphabet[(j - key) + alphabet.length];
				}
			}
			if (!Alphabet.include(inputChars[i])) {
				result += inputChars[i];
			}
		}
		return result;
	}
}
