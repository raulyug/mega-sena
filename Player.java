
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

	// Retorna cpf do jogador
	public String getCpf() {
		return this.cpf;
	}

	// Seta se o jogador é vencedor
	public void setIsWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	// Retorna se o jogador é vencedor
	public boolean getIsWinner() {
		return this.isWinner;
	}

	// Retorna nome do jogador
	public String getName() {
		return this.name;
	}

	// Retorna apostas do jogador
	public List<Bet> getBets() {
		return this.playerBets;
	}

	// Adiciona nova aposta
	public void addNewBet(Bet bet) {
		System.out.println("Adicionou!");

		playerBets.add(bet);

	}

}
