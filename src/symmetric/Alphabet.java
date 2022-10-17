package symmetric;

public class Alphabet {
	public static char[] ALPHABET = { 'a', 'ă', 'â', 'b', 'c', 'd', 'đ', 'e', 'ê', 'g', 'h', 'i', 'k', 'l', 'm', 'n',
			'o', 'ô', 'ơ', 'p', 'q', 'r', 's', 't', 'u', 'ư', 'v', 'x', 'y' };
	public static char[] UNIKEY_A1 = { 'á', 'à', 'ã', 'ạ', 'ả' };
	public static char[] UNIKEY_A2 = { 'ắ', 'ằ', 'ẳ', 'ẵ', 'ặ' };
	public static char[] UNIKEY_A3 = { 'ấ', 'ầ', 'ẩ', 'ẫ', 'ậ' };
	public static char[] UNIKEY_E1 = { 'é', 'è', 'ẻ', 'ẽ', 'ẹ' };
	public static char[] UNIKEY_E2 = { 'ề', 'ế', 'ể', 'ễ', 'ệ' };
	public static char[] UNIKEY_I = { 'í', 'ì', 'ỉ', 'ĩ', 'ị' };
	public static char[] UNIKEY_O1 = { 'ò', 'ó', 'ỏ', 'õ', 'ọ' };
	public static char[] UNIKEY_O2 = { 'ồ', 'ố', 'ổ', 'ỗ', 'ộ' };
	public static char[] UNIKEY_O3 = { 'ờ', 'ớ', 'ở', 'ỡ', 'ợ' };
	public static char[] UNIKEY_U1 = { 'ù', 'ú', 'ủ', 'ũ', 'ụ' };
	public static char[] UNIKEY_U2 = { 'ừ', 'ứ', 'ử', 'ữ', 'ự' };

	public static String filterInput(String input) {
		String result = input;
		result = result.toLowerCase();
		// a
		for (int i = 0; i < UNIKEY_A1.length; i++) {
			result = result.replaceAll(UNIKEY_A1[i] + "", "a");
		}
		for (int i = 0; i < UNIKEY_A2.length; i++) {
			result = result.replaceAll(UNIKEY_A2[i] + "", "ă");
		}
		for (int i = 0; i < UNIKEY_A3.length; i++) {
			result = result.replaceAll(UNIKEY_A3[i] + "", "â");
		}
		// e
		for (int i = 0; i < UNIKEY_E1.length; i++) {
			result = result.replaceAll(UNIKEY_E1[i] + "", "e");
		}
		for (int i = 0; i < UNIKEY_E2.length; i++) {
			result = result.replaceAll(UNIKEY_E2[i] + "", "ê");
		}
		// i
		for (int i = 0; i < UNIKEY_I.length; i++) {
			result = result.replaceAll(UNIKEY_I[i] + "", "i");
		}
		// o
		for (int i = 0; i < UNIKEY_O1.length; i++) {
			result = result.replaceAll(UNIKEY_O1[i] + "", "o");
		}
		for (int i = 0; i < UNIKEY_O2.length; i++) {
			result = result.replaceAll(UNIKEY_O2[i] + "", "ô");
		}
		for (int i = 0; i < UNIKEY_O3.length; i++) {
			result = result.replaceAll(UNIKEY_O3[i] + "", "ơ");
		}
		// u
		for (int i = 0; i < UNIKEY_U1.length; i++) {
			result = result.replaceAll(UNIKEY_U1[i] + "", "u");
		}
		for (int i = 0; i < UNIKEY_U2.length; i++) {

			result = result.replaceAll(UNIKEY_U2[i] + "", "ư");
		}
		return result;
	}

	public static boolean include(char c) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				return true;
			}
		}
		return false;
	}

	public static int getIndex(char c) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				return i;
			}
		}
		return -1;
	}
}
