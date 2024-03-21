
import java.util.List;
import java.util.ArrayList;

public class Player {

	private String cpf;
	private String name;
	private boolean isWinner;
	private ArrayList playerBets;

	public Player(String cpf, String name) {
		this.cpf = cpf;
		this.name = name;
		this.isWinner = false;
		this.playerBets = new ArrayList<>();
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setIsWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public boolean getIsWinner() {
		return this.isWinner;
	}

	public String getName() {
		return this.name;
	}

	public List<Bet> getBets() {
		return this.playerBets;
	}

	public void addNewBet(Bet bet) {
		System.out.println("Adicionou!");

		playerBets.add(bet);

	}

}
