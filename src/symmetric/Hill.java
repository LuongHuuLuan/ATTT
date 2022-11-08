package symmetric;

import symmetric.Alphabet.ALPHABET;

/* su dung khoa k la 1 ma tran 2x2 
 * a b
 * c d
 * ma hoa bang cach chia input thanh cac tap 2 phan tu
 * */
public class Hill {
	private ALPHABET useAlphabet = ALPHABET.ENGLISH;
	private int alphabetLength = Alphabet.ENGLISH_ALPHABET.length;

	public int[][] createKey() {
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		int[][] result = new int[2][2];
		result[0][0] = (int) Math.floor(Math.random() * alphabet.length);
		result[0][1] = (int) Math.floor(Math.random() * alphabet.length);
		result[1][0] = (int) Math.floor(Math.random() * alphabet.length);
		result[1][1] = (int) Math.floor(Math.random() * alphabet.length);
		if (detMatrix(result) == 0 || myMod(detMatrix(result), alphabet.length) == 0) {
			return createKey();
		} else {
			return result;
		}
	}

	public int[][] convertKeyToInt(String key) {
		int[][] result = new int[2][2];
		String[] keys = key.trim().split(" ");
		result[0][0] = Alphabet.getIndex(keys[0].charAt(0), useAlphabet);
		result[0][1] = Alphabet.getIndex(keys[1].charAt(0), useAlphabet);
		result[1][0] = Alphabet.getIndex(keys[2].charAt(0), useAlphabet);
		result[1][1] = Alphabet.getIndex(keys[3].charAt(0), useAlphabet);
		return result;
	}

	public String convertKeyToString(int[][] key) {
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		String result = "";
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key[i].length; j++) {
				result += alphabet[key[i][j]] + " ";
			}
		}
		return result;
	}

	public String encrypt(String input, int[][] key) {
		String result = "";
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		if (input.length() % 2 != 0) {
			input += "*";
		}
		String fillerInput = Alphabet.filterInput(input);
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i += 2) {
			int x = -1;
			int y = -1;
			if (Alphabet.include(inputChars[i], useAlphabet)) {
				x = Alphabet.getIndex(inputChars[i], useAlphabet);
			}
			if (Alphabet.include(inputChars[i + 1], useAlphabet)) {
				y = Alphabet.getIndex(inputChars[i + 1], useAlphabet);
			}
			result += alphabet[myMod(((key[0][0] * x) + (key[0][1] * y)), alphabet.length)];
			result += alphabet[myMod(((key[1][0] * x) + (key[1][1] * y)), alphabet.length)];
		}
		return result;
	}

	public String encryptWithSpecialChar(String input, String key) {
		int[][] convertKey = convertKeyToInt(key);

		String fillerInput = Alphabet.filterInput(input);
		char[] inputChars = fillerInput.toCharArray();

		char[] simpleChar = new char[inputChars.length];
		char[] specialChar = new char[inputChars.length];

		String newInput = "";
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i], useAlphabet)) {
				simpleChar[i] = inputChars[i];
				newInput += inputChars[i];
			} else {
				specialChar[i] += inputChars[i];
			}
		}
		String encrypt = encrypt(newInput, convertKey);
		char[] encryptChar = encrypt.toCharArray();
		String output = "";
		int j = 0;
		for (int i = 0; i < simpleChar.length; i++) {
			if (simpleChar[i] + "".length() == 0) {
				output += specialChar[i];
			} else {
				output += encryptChar[j];
				j++;
			}
		}
		if (encryptChar.length > newInput.length()) {
			output += encryptChar[encryptChar.length - 1];
		}
		if (inputChars.length % 2 == 0) {
			output += 0;
		} else {
			output += 1;
		}
		return output;
	}

	public String decrypt(String input, int[][] key) {
		String result = "";
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		int[][] inverseMatrix = inverseMatrix(key);
		String fillerInput = Alphabet.filterInput(input);
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i += 2) {
			int x = -1;
			int y = -1;
			if (Alphabet.include(inputChars[i], useAlphabet)) {
				x = Alphabet.getIndex(inputChars[i], useAlphabet);
			}
			if (Alphabet.include(inputChars[i + 1], useAlphabet)) {
				y = Alphabet.getIndex(inputChars[i + 1], useAlphabet);
			}
			result += alphabet[myMod((inverseMatrix[0][0] * x + inverseMatrix[0][1] * y), alphabet.length)];
			result += alphabet[myMod((inverseMatrix[1][0] * x + inverseMatrix[1][1] * y), alphabet.length)];
		}
		return result;
	}

	public String decryptWithSpecialChar(String input, String key) {
		int[][] convertKey = convertKeyToInt(key);

		int isOdd = Integer.parseInt(input.substring(input.length() - 1));
		input = input.substring(0, input.length() - 1);
		String fillerInput = Alphabet.filterInput(input);
		char[] inputChars = fillerInput.toCharArray();

		char[] simpleChar = new char[inputChars.length];
		char[] specialChar = new char[inputChars.length];

		String newInput = "";
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i], useAlphabet)) {
				simpleChar[i] = inputChars[i];
				newInput += inputChars[i];
			} else {
				specialChar[i] += inputChars[i];
			}
		}
		String decrypt = decrypt(newInput, convertKey);
		char[] decryptChar = decrypt.toCharArray();
		String output = "";
		int j = 0;
		for (int i = 0; i < simpleChar.length; i++) {
			if (simpleChar[i] + "".length() == 0) {
				output += specialChar[i];
			} else {
				output += decryptChar[j];
				j++;
			}
		}
		if (isOdd == 1) {
			output = output.substring(0, output.length() - 1);
		}
		return output;
	}

	public int inverseNum(int num) {
		for (int i = 0; i < alphabetLength; i++) {
			if (myMod((num * i), alphabetLength) == 1) {
				return i;
			}
		}
		return -1;
	}

	public int[][] inverseMatrix(int[][] key) {
		int[][] result = new int[2][2];
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		int detMaTrix = detMatrix(key);
		int inverseDetMaTrix = inverseNum(detMaTrix);
		int[][] adjunctMatrix = adjunctMatrix(key);
		for (int i = 0; i < adjunctMatrix.length; i++) {
			for (int j = 0; j < adjunctMatrix[i].length; j++) {
				result[i][j] = myMod((adjunctMatrix[i][j] * inverseDetMaTrix), alphabet.length);
			}
		}
		return result;
	}

	public int myMod(int a, int b) {
		int result = 0;
		if (a < 0) {
			if (Math.abs(a) % b != 0) {
				result = b - Math.abs(a) % b;
			}
		} else {
			result = a % b;
		}
		return result;
	}

	public int[][] adjunctMatrix(int[][] matrix) {
		char[] alphabet = null;
		if (useAlphabet == ALPHABET.ENGLISH) {
			alphabet = Alphabet.ENGLISH_ALPHABET;
		} else {
			alphabet = Alphabet.VIETNAMESE_ALPHABET;
		}
		int[][] result = new int[2][2];
		result[0][0] = myMod(matrix[1][1], alphabet.length);
		result[0][1] = myMod(-matrix[0][1], alphabet.length);
		result[1][0] = myMod(-matrix[1][0], alphabet.length);
		result[1][1] = myMod(matrix[0][0], alphabet.length);
		return result;
	}

	public int gcd(int a, int b) {
		if (b != 0)
			return gcd(b, a % b);
		else
			return a;
	}

	public int detMatrix(int[][] matrix) {
		return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
	}

	public void printKey(int[][] key) {
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key[i].length; j++) {
				System.out.print(key[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean checkKey(String input) {
		if (input.length() == 0)
			return false;
		try {
			convertKeyToInt(input);
			return true;
		} catch (Exception e) {
			e.fillInStackTrace();
			return false;
		}
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

	public static void main(String[] args) {
		Hill hill = new Hill();
		int[][] key = hill.convertKeyToInt(Alphabet.ENGLISH_ALPHABET[11] + " " + Alphabet.ENGLISH_ALPHABET[8] + " "
				+ Alphabet.ENGLISH_ALPHABET[3] + " " + Alphabet.ENGLISH_ALPHABET[7]);
		hill.printKey(key);
		String encrypt = hill.encryptWithSpecialChar("Lương Hữu Luânn", hill.convertKeyToString(key));
		System.out.println(encrypt);
		String decrypt = hill.decryptWithSpecialChar(encrypt, hill.convertKeyToString(key));
		System.out.println(decrypt);

	}
}
