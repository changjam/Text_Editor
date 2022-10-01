package AbstractFactory;

import java.awt.*;

public class WinMenuBar extends MenuBar {
    private final Color background= new Color(246, 246, 246, 255);
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(background);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public void addMenu(Menu m) {
        this.add(m);
    }
}