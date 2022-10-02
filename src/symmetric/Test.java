package symmetric;

public class Test {
	public static void main(String[] args) {
		char[] alphabet = Alphabet.ALPHABET;
		System.out.println("Apl: " + new String(alphabet));
		testCaesar();
		testSubstitution();
		testVegenere();
//		Vegenere.test();
	}

	public static void testSubstitution() {
		System.out.println("====================");
		System.out.println("Mã hóa thay thế\n");
		String key = Substitution.createKey();
		System.out.println("Key: " + key);
		String input = "Lương Hữu Luân ay";
		System.out.println("input: " + input);
		String encrypt = Substitution.encrypt(input, key);
		System.out.println("Mã hóa: " + encrypt + "\n");
		System.out.println("Giải mã thay thế\n");
		System.out.println("Key: " + key);
		System.out.println("input: " + encrypt);
		String decrypt = Substitution.decrypt(encrypt, key);
		System.out.println("Giải mã: " + decrypt + "\n");
		System.out.println("====================");
	}

	public static void testCaesar() {
		System.out.println("====================");
		System.out.println("Mã hóa dịch chuyển\n");
		int key = 3;
		System.out.println("Key: " + key);
		String input = "Lương Hữu Luân ay";
		System.out.println("input: " + input);
		String encrypt = Caesar.encrypt(input, key);
		System.out.println("Mã hóa: " + encrypt + "\n");
		System.out.println("Giải mã dịch chuyển\n");
		System.out.println("Key: " + key);
		System.out.println("input: " + encrypt);
		String decrypt = Caesar.decrypt(encrypt, key);
		System.out.println("Giải mã: " + decrypt + "\n");
		System.out.println("====================");
	}

	public static void testVegenere() {
		System.out.println("====================");
		System.out.println("Mã hóa vegenere\n");
		int keyLenght = 5;
		System.out.println("Key lenght: " + keyLenght);
		String key = Vegenere.createKey(keyLenght);
		System.out.println("Key: " + key);
		String input = "Lương Hữu Luân ay";
		System.out.println("input: " + input);
		String encrypt = Vegenere.encrypt(input, key);
		System.out.println("Mã hóa: " + encrypt + "\n");
		System.out.println("Giải mã vegenere\n");
		System.out.println("Key lenght: " + keyLenght);
		System.out.println("Key: " + key);
		System.out.println("input: " + encrypt);
		String decrypt = Vegenere.decrypt(encrypt, key);
		System.out.println("Giải mã: " + decrypt + "\n");
		System.out.println("====================");
	}
}
