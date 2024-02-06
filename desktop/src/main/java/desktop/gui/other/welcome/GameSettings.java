package desktop.gui.other.welcome;

import board.ColoredPawn;
import player.Player;
import player.computer.RandomPlayer;
import player.computer.SmartPlayer;
import player.human.Human;

/**
 * A record containing the settings of the game.
 */
public record GameSettings(boolean isHumanVsHuman, boolean isDifficultyEasy, boolean isHumanFirst) {
    public boolean isDefaultOnIthPanel(int i){
        return switch (i) {
            case 0 -> isHumanVsHuman();
            case 1 -> isDifficultyEasy();
            case 2 -> isHumanFirst();
            default -> false;
        };
    }

    public record Players(Player blackPlayer, Player whitePlayer) {}
    public Players getPlayers() {
        Player blackPlayer;
        Player whitePlayer;
        if (isHumanVsHuman()) {
            blackPlayer = new Human(ColoredPawn.BLACK);
            whitePlayer = new Human(ColoredPawn.WHITE);
        } else {
            if (isDifficultyEasy()) {
                blackPlayer = isHumanFirst() ? new Human(ColoredPawn.BLACK) : new RandomPlayer(ColoredPawn.BLACK);
                whitePlayer = isHumanFirst() ? new RandomPlayer(ColoredPawn.WHITE) : new Human(ColoredPawn.WHITE);
            } else {
                blackPlayer = isHumanFirst() ? new Human(ColoredPawn.BLACK) : new SmartPlayer(ColoredPawn.BLACK);
                whitePlayer = isHumanFirst() ? new SmartPlayer(ColoredPawn.WHITE) : new Human(ColoredPawn.WHITE);
            }
        }
        return new Players(blackPlayer, whitePlayer);
    }
}
