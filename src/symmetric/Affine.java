package symmetric;

/*
 * n = alphabet.lenght
 * k = (a, b) | gcd(a, n) = 1
 * e = (ax +b) % n
 * d = a^-1(x-b) % n
 */
// gap loi khi giai ma so am chia du cho so duong
public class Affine {
	public static String createKey() {
		char[] alphabet = Alphabet.ALPHABET;
		int randomA = (int) Math.floor(Math.random() * alphabet.length);
		int randomB = (int) Math.floor(Math.random() * alphabet.length);
		while (gcd(randomA, alphabet.length) != 1) {
			randomA = (int) Math.floor(Math.random() * alphabet.length);
		}
		return randomA + " " + randomB;
	}

	public static String encrypt(String input, String key) {
		String result = "";
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		char[] inputChars = fillerInput.toCharArray();
		int a = Integer.parseInt(key.split(" ")[0]);
		int b = Integer.parseInt(key.split(" ")[1]);
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i])) {
				int indexChar = Alphabet.getIndex(inputChars[i]);
				result += alphabet[(a * indexChar + b) % alphabet.length];
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}

	public static String decrypt(String input, String key) {
		String result = "";
		char[] alphabet = Alphabet.ALPHABET;
		String fillerInput = Alphabet.filterInput(input);
		char[] inputChars = fillerInput.toCharArray();
		int a = Integer.parseInt(key.split(" ")[0]);
		int b = Integer.parseInt(key.split(" ")[1]);
		int decryptA = -1;
		for (int i = 0; i < alphabet.length; i++) {
			if ((i * a) % alphabet.length == 1) {
				decryptA = i;
				break;
			}
		}
		for (int i = 0; i < inputChars.length; i++) {
			if (Alphabet.include(inputChars[i])) {
				int indexChar = Alphabet.getIndex(inputChars[i]);
				// so am chia du se lay phan bu cua no -5 phan bu trong Z28 la 28 - 5 = 23
				result += alphabet[(decryptA
						* ((indexChar - b) < 0 ? alphabet.length - Math.abs((indexChar - b)) : (indexChar - b)))
						% alphabet.length];
			} else {
				result += inputChars[i];
			}
		}
		return result;
	}

	public static int gcd(int a, int b) {
		if (b != 0)
			return gcd(b, a % b);
		else
			return a;
	}

	public static void main(String[] args) {
		System.out.println(-140 % 28);
		System.out.println(135 % 28);
	}

}
