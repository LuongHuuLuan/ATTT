package symmetric;

public class Test {
	public static void main(String[] args) {
		char[] alphabet = Alphabet.ALPHABET;
		System.out.println("Apl: " + new String(alphabet));
		testCaesar();
		testSubstitution();
		testVegenere();
		testAffine();
//		Vegenere.test();
	}

	public static void testSubstitution() {
		Substitution substitution = new Substitution();
		System.out.println("====================");
		System.out.println("Mã hóa thay thế\n");
		String key = substitution.createKey();
		System.out.println("Key: " + key);
		String input = "Lương Hữu Luân ay";
		System.out.println("input: " + input);
		String encrypt = substitution.encrypt(input, key);
		System.out.println("Mã hóa: " + encrypt + "\n");
		System.out.println("Giải mã thay thế\n");
		System.out.println("Key: " + key);
		System.out.println("input: " + encrypt);
		String decrypt = substitution.decrypt(encrypt, key);
		System.out.println("Giải mã: " + decrypt + "\n");
		System.out.println("====================");
	}

	public static void testCaesar() {
		Caesar ceasar = new Caesar();
		System.out.println("====================");
		System.out.println("Mã hóa dịch chuyển\n");
		int key = 3;
		System.out.println("Key: " + key);
		String input = "Lương Hữu Luân ay";
		System.out.println("input: " + input);
		String encrypt = ceasar.encrypt(input, key);
		System.out.println("Mã hóa: " + encrypt + "\n");
		System.out.println("Giải mã dịch chuyển\n");
		System.out.println("Key: " + key);
		System.out.println("input: " + encrypt);
		String decrypt = ceasar.decrypt(encrypt, key);
		System.out.println("Giải mã: " + decrypt + "\n");
		System.out.println("====================");
	}

	public static void testVegenere() {
		Vegenere vegenere = new Vegenere();
		System.out.println("====================");
		System.out.println("Mã hóa vegenere\n");
		int keyLenght = 1;
		System.out.println("Key lenght: " + keyLenght);
		String key = vegenere.createKey(keyLenght);
		System.out.println("Key: " + key);
		String input = "Lương Hữu Luân ay";
		System.out.println("input: " + input);
		String encrypt = vegenere.encrypt(input, key);
		System.out.println("Mã hóa: " + encrypt + "\n");
		System.out.println("Giải mã vegenere\n");
		System.out.println("Key lenght: " + keyLenght);
		System.out.println("Key: " + key);
		System.out.println("input: " + encrypt);
		String decrypt = vegenere.decrypt(encrypt, key);
		System.out.println("Giải mã: " + decrypt + "\n");
		System.out.println("====================");
	}

	public static void testAffine() {
		System.out.println("====================");
		System.out.println("Mã hóa affine\n");
		String key = Affine.createKey();
		System.out.println("Key: " + key);
		String input = "Lương Hữu Luân ay";
		System.out.println("input: " + input);
		String encrypt = Affine.encrypt(input, key);
		System.out.println("Mã hóa: " + encrypt + "\n");
		System.out.println("Giải mã affine\n");
		System.out.println("Key: " + key);
		System.out.println("input: " + encrypt);
		String decrypt = Affine.decrypt(encrypt, key);
		System.out.println("Giải mã: " + decrypt + "\n");
		System.out.println("====================");
	}
}
