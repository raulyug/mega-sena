import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Round {
	private Set<Integer> winnerNumbers = null;
	private int roundId = 0;
	

	public Round() {
		this.winnerNumbers = null;
		roundId++;
	}

	public int getroundId() {
		return this.roundId;
	}
	
	public Set<Integer> generateWinnerNumbers() {
		winnerNumbers = numbersGenerator();
		return winnerNumbers;
	}

	public void addOneMoreWinnernumber(){
		Random random = new Random();
		winnerNumbers.add(random.nextInt(50) + 1);
	}
	public Set<Integer> getWinnerNumbers() {
		return winnerNumbers;
	}



	@Override
	public String toString() {
		return "Round " + getroundId() + " Winner Numbers=" + winnerNumbers;
	}
	
	public static Set<Integer> numbersGenerator() {
		Random random = new Random();
		Set<Integer> numbers = new HashSet<>();
		// for (int i = 0; i < 5; i++) {
		// 	numbers.add(random.nextInt(50) + 1);
		// }
		while (numbers.size()!=5){
			numbers.add(random.nextInt(50) + 1);
		}
		
		return numbers;
	}
}