package symmetric;

/* su dung khoa k la 1 ma tran 2x2 
 * a b
 * c d
 * ma hoa bang cach chia input thanh cac tap 2 phan tu
 * */
public class Hill {
	public static String randomKey() {
		char[] alphabet = Alphabet.ALPHABET;
		String result = "";
		int count = 4;
		while (count != 0) {
			int random = (int) Math.floor(Math.random() * alphabet.length);
			result += alphabet[random] + " ";
			count--;
		}
		return result.trim();
	}

	public static int[][] createKey(String key) {
		int[][] result = new int[2][2];
		String[] keys = key.trim().split(" ");
		result[0][0] = Alphabet.getIndex(keys[0].charAt(0));
		result[0][1] = Alphabet.getIndex(keys[1].charAt(0));
		result[1][0] = Alphabet.getIndex(keys[2].charAt(0));
		result[1][1] = Alphabet.getIndex(keys[3].charAt(0));
		return result;
	}

	public static String formatKey(int[][] key) {
		String result = "";
		for (int i = 0; i < key.length; i++) {
			for (int j = 0; j < key[i].length; j++) {
				result += key[i][j];
				if (j != 1) {
					result += " ";
				}
			}
			result += "\n";
		}
		return result;
	}

	public static String encrypt(String input, int[][] key) {
		String result = "";
		char[] alphabet = Alphabet.ALPHABET;
		if (input.length() % 2 != 0) {
			input += "*";
		}
		String fillerInput = Alphabet.filterInput(input);
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i += 2) {
			if (Alphabet.include(inputChars[i]) && Alphabet.include(inputChars[i + 1])) {
				int x = Alphabet.getIndex(inputChars[i]);
				int y = Alphabet.getIndex(inputChars[i + 1]);
				result += alphabet[(key[0][0] * x + key[1][0] * y) % alphabet.length];
				result += alphabet[(key[0][1] * x + key[1][1] * y) % alphabet.length];
			} else {
				int x = Alphabet.getIndex(inputChars[i]);
				int y = -1;
				int encryptY = (key[0][1] * x + key[1][1] * y);
				result += alphabet[(key[0][0] * x + key[1][0] * y) % alphabet.length];
				result += alphabet[(encryptY < 0 ? (alphabet.length + encryptY) : encryptY) % alphabet.length];
			}
		}
		return result;
	}

	public static String decrypt(String input, int[][] key) {
		String result = "";
		char[] alphabet = Alphabet.ALPHABET;
		if (input.length() % 2 != 0) {
			input += "*";
		}
		int[][] decryptKey = findDecryptKey(key);
		String fillerInput = Alphabet.filterInput(input);
		char[] inputChars = fillerInput.toCharArray();
		for (int i = 0; i < inputChars.length; i += 2) {
			if (Alphabet.include(inputChars[i]) && Alphabet.include(inputChars[i + 1])) {
				int x = Alphabet.getIndex(inputChars[i]);
				int y = Alphabet.getIndex(inputChars[i + 1]);
				result += alphabet[(decryptKey[0][0] * x + decryptKey[1][0] * y) % alphabet.length];
				result += alphabet[(decryptKey[0][1] * x + decryptKey[1][1] * y) % alphabet.length];
			} else {
				int x = Alphabet.getIndex(inputChars[i]);
				int y = -1;
				int encryptY = (decryptKey[0][1] * x + decryptKey[1][1] * y);
				result += alphabet[(decryptKey[0][0] * x + decryptKey[1][0] * y) % alphabet.length];
				result += alphabet[(encryptY < 0 ? (alphabet.length + encryptY) : encryptY) % alphabet.length];
			}
		}
		return result;
	}

	public static int inverseNum(int num) {
		for (int i = 0; i < Alphabet.ALPHABET.length; i++) {
			if ((num * i) % 26 == 1) {
				return i;
			}
		}
		return -1;
	}

	public static int[][] findDecryptKey(int[][] key) {
		int[][] result = new int[2][2];
		char[] alphabet = Alphabet.ALPHABET;
		int detMaTrix = detMatrix(key);
		int inverseDetMaTrix = inverseNum(detMaTrix);
		if (gcd(detMaTrix, alphabet.length) == 1) {
			int[][] inverseMatrix = inverseMatrix(key);
			for (int i = 0; i < inverseMatrix.length; i++) {
				for (int j = 0; j < inverseMatrix[i].length; j++) {
					inverseMatrix[i][j] = myMod(inverseMatrix[i][j], alphabet.length);
					inverseMatrix[i][j] = inverseMatrix[i][j] * inverseDetMaTrix;
					inverseMatrix[i][j] = myMod(inverseMatrix[i][j], alphabet.length);
				}
			}
			result = inverseMatrix;
			return result;
		} else {
			System.out.println("ma tran khong khong kha nghich");
		}
		return result;
	}

	public static int myMod(int a, int b) {
		return a < 0 ? a + b : a % b;
	}

	public static int[][] inverseMatrix(int[][] matrix) {
		int[][] result = new int[2][2];
		result[0][0] = matrix[1][1];
		result[0][0] = -matrix[0][1];
		result[0][0] = -matrix[1][0];
		result[0][0] = matrix[0][0];
		return result;
	}

	public static int gcd(int a, int b) {
		if (b != 0)
			return gcd(b, a % b);
		else
			return a;
	}

	public static int detMatrix(int[][] matrix) {
		return matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
	}

	public static void main(String[] args) {
		int[][] key = createKey(randomKey());
		String encrypt = encrypt("Lương Hữu Luân", key);
		System.out.println(encrypt);
		String decrypt = decrypt(encrypt, key);
		System.out.println(decrypt);
	}
}
