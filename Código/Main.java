public class Main {
	public static void main(String[] args) {
		try {
			for (int j = 1; j < 31; j++) {
				System.out.println("Ejecución n°: " + j);
				StdRandom.newSeed();
				(new Flock()).execute();
			}

		} catch (Exception e) {
			StdOut.printf("%s\n%s\n", e.getMessage(), e.getCause());
		}
	}
}