package service;
import model.Player;

public class GameService {
    public void attack(Player attacker, Player defender) {
        int damage = attacker.getAttackPower();

        if (defender.isDefending()) {
            damage = damage / 2;
            defender.setDefending(false);
        }

        defender.setHealth(defender.getHealth() - damage);
    }
    public void heal(Player player) {
        player.setHealth(player.getHealth() + 10);
    }
    public void defend(Player player) {
        player.setDefending(true);
    }

    public String checkWinner(Player player1, Player player2) {

        if (player1.getHealth() <= 0) {
            return player2.getName();
        }

        if (player2.getHealth() <= 0) {
            return player1.getName();
        }

        return null;
    }
}

