
import java.util.Set;
import java.util.TreeSet;

public class Bet implements Comparable<Bet> {

	private int betId;
	private Set<Integer> betNumbers = new TreeSet<>();
	

	public Bet(Set<Integer> betNumbers) {
		this.betId = (betId == 0) ? this.betId = 1000 : betId + 1;
		this.betNumbers = betNumbers;
	}

	public int getbetId() {
		return this.betId;
	}

	public Set<Integer> getNumbers() {
		return this.betNumbers;
	}

	public void setNumbers(int number) {
		for (int i = 0; i < 5; i++) {
			this.betNumbers.add(number);
		}
	}

	@Override
	public int compareTo(Bet otherBet) {
		return Integer.compare(this.betId, otherBet.betId);
	}
}
