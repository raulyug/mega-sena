import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Round {
	static Set<Integer> winNumbers = null;
	private static int roundId = 0;

	public Round() {
		this.winNumbers = null;
		roundId++;
	}

	// Retorna id do round
	public static int getRoundId() {
		return roundId;
	}

	// Gera numeros vencedores com o metodo numbersGenerator
	public Set<Integer> generateWinnerNumbers() {
		winNumbers = numbersGenerator();
		return winNumbers;
	}

	// Retorna numeros vencedores
	public static Set<Integer> getWinNumbers() {
		return winNumbers;
	}

	// Incrementa id do round
	public static void idIncrement() {
		roundId++;
	}

	// Gera numeros aleatorios e retorna um(a) set(lista) de 5 numeros
	public static Set<Integer> numbersGenerator() {
		Random random = new Random();
		Set<Integer> numbers = new HashSet<>();

		while (numbers.size() != 5) {
			numbers.add(random.nextInt(50) + 1);
		}

		return numbers;
	}

	@Override
	public String toString() {
		return "Round " + getRoundId() + " Winner Numbers=" + winNumbers;
	}
}