package desktop.utilities;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JGradientButton extends JButton {
    private static final Color boardColor = new Color(37, 135, 24);
    private static final Color boardColor2 = new Color(7, 177, 2);
    private static final Color mouseOverColor = new Color(15, 40, 155);
    private static final Color suggestionColor = new Color(255, 255, 0);

    JGradientButton(int x, int y){
        super("");
        setContentAreaFilled(false);
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        setMargin(buttonMargin);
        setBorderPainted(true);
        Color tileBorderColor = new Color(0, 0, 0);
        setBorder(new LineBorder(tileBorderColor));
        setBackground(boardColor);
        putClientProperty("row", x);
        putClientProperty("column", y);
        putClientProperty("toSuggest", false);
        MouseAdapter mouseBehaviour = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(mouseOverColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if ((boolean) getClientProperty("toSuggest"))
                    setBackground(suggestionColor);
                else
                    setBackground(boardColor);
            }
        };
        addMouseListener(mouseBehaviour);
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0),
                getBackground(),
                new Point(0, getHeight()/3),
                boardColor2));
        g2.fillRect(0, 0, getWidth(), getHeight()/3);
        g2.setPaint(new GradientPaint(
                new Point(0, getHeight()/3),
                boardColor2,
                new Point(0, getHeight()),
                getBackground()));
        g2.fillRect(0, getHeight()/3, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }

    protected void setToSuggestProperty(boolean toSuggest){
        putClientProperty("toSuggest", toSuggest);
    }

    protected void resetBackground() {
        setBackground((boolean) getClientProperty("toSuggest") ? suggestionColor : boardColor);
    }


}