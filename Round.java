import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Round {
	private Set<Integer> winNumbers = null;
	private int roundId = 0;

	public Round() {
		this.winNumbers = null;
		roundId++;
	}

	public int getroundId() {
		return this.roundId;
	}

	public Set<Integer> generateWinnerNumbers() {
		winNumbers = numbersGenerator();
		return winNumbers;
	}

	public void addOneMoreWinnerNumber() {
		Random random = new Random();
		winNumbers.add(random.nextInt(50) + 1);

	}

	public Set<Integer> getWinNumbers() {
		return winNumbers;
	}

	public void checkWinners(List<Player> players) {
		while (true) {
			boolean hasWinner = false;

			for (Player player : players) {
				if (player != null) {
					for (Bet bet : player.getBets()) {
						if (bet.getNumbers().equals(winNumbers)) {
							player.setIsWinner(true);
							hasWinner = true;
						}
					}
				}
				if (!hasWinner) {
					addOneMoreWinnerNumber();
				}
			}
		}
		// Game.endMatch(players);
	}

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
		return "Round " + getroundId() + " Winner Numbers=" + winNumbers;
	}
}