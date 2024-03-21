
import java.util.Set;
import java.util.TreeSet;

public class Bet implements Comparable<Bet> {

	private int betId;
	private Set<Integer> betNumbers = new TreeSet<>();
	private static double value = 5;
	private static int quantity = 1;

	public Bet(Set<Integer> betNumbers, int betId) {
		this.betId = betId;
		this.betNumbers = betNumbers;
		quantity++;
	}

	//Retorna quantidade de apostas
	public static int getQuantity() {
		return quantity;
	}

	//Retorna valor da aposta 
	public static double getValue() {
		return value;
	}


	//Retorna id da aposta
	public int getbetId() {
		return this.betId;
	}

	//Retorna numeros da aposta
	public Set<Integer> getNumbers() {
		return this.betNumbers;
	}

	//Adiciona numeros a aposta
	public void setNumbers(int number) {
		for (int i = 0; i < 5; i++) {
			this.betNumbers.add(number);
		}
	}

	//ComareTo para ordenar as apostas
	@Override
	public int compareTo(Bet otherBet) {
		return Integer.compare(this.betId, otherBet.betId);
	}
}
