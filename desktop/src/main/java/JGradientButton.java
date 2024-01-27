import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JGradientButton extends JButton {
    static final Color boardColor = new Color(37, 135, 24);
    private static final Color tileBorderColor = new Color(0, 0, 0);
    public static final Color boardColor2 = new Color(7, 177, 2);

    public static final Color mouseOverColor = new Color(15, 40, 155);

    public static final Color suggestionColor = new Color(255, 255, 0);

    JGradientButton(String text, int i, int j){
        super(text);
        setContentAreaFilled(false);
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        setMargin(buttonMargin);
        setBorderPainted(true);
        setBorder(new LineBorder(tileBorderColor));
        setBackground(boardColor);
        putClientProperty("row", i);
        putClientProperty("column", j);
        putClientProperty("toSuggest", false);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(mouseOverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if ((boolean)getClientProperty("toSuggest"))
                    setBackground(suggestionColor);
                else
                    setBackground(boardColor);
            }
        });

    }


    public void addActionListener(ActionListener listener) {
        super.addActionListener(listener);
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


}
