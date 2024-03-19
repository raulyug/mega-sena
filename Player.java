import java.util.List;

public class Player {

	private String cpf;
	private String name;
	private List<Bet> bets;
	
	public Player(String cpf, String name) {
		this.cpf = cpf;
		this.name = name;
	}

	public String getCpf() {
		return this.cpf;
	}

	public String getName() {
		return this.name;
	}

	public void addNewBet(Bet bet) {
		bets.add(bet);
	}
}
