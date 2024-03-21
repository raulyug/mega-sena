import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerSorter {
    
    public static void sortByPlayerName(List<Player> players) {
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
    }
}