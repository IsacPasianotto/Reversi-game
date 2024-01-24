package org.example;

import javax.swing.*;
import java.awt.*;

public class MainDesktop {


    public static void main(String[] args) {

        JFrame frame = new JFrame("Reversi");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);

        DesktopBoard board = new DesktopBoard();


        JToolBar dashboard = new JToolBar(null, JToolBar.VERTICAL);
        dashboard.setFloatable(false);

        JButton button = new JButton("Undo");
        button.addActionListener(e -> {
            System.out.println("Undo");
        });

        // Create a glue component to center the button vertically
        Component verticalGlue1 = Box.createVerticalGlue();
        Component verticalGlue2 = Box.createVerticalGlue();

        dashboard.add(verticalGlue1);
        dashboard.add(button);
        dashboard.add(verticalGlue2);


        JPanel globalWiev = new JPanel();
        globalWiev.setLayout(new BorderLayout());
        globalWiev.add(board.getPanel(), BorderLayout.CENTER);
        globalWiev.add(dashboard, BorderLayout.EAST);

        frame.add(globalWiev);
        frame.setVisible(true);
    }

}
