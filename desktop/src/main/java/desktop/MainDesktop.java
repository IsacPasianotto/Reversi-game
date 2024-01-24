package desktop;

import board.Board;
import player.human.Human;

import javax.swing.*;
import java.awt.*;

public class MainDesktop {


    public static void main(String[] args) {
        // TODO: add a panel to select difficulty, bots, etc.

        GameDesktop game = new GameDesktop(new Board(),new Human(), new Human());
        game.play();
    }


}
