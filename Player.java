import java.util.List;

public class Player {

	private String cpf;
	private String name;
	private List<Bet> bets;
	private boolean isWinner;
	
	public Player(String cpf, String name) {
		this.cpf = cpf;
		this.name = name;
		this.isWinner = false;
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
		return this.bets;
	}

	public void addNewBet(Bet bet) {
		bets.add(bet);
	}
}
