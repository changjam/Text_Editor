package AbstractFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class WinScrollBar extends ScrollBar {
    private final int Thumb_size = 12;
    public WinScrollBar(){
        BasicScrollBarUI ui = new BasicScrollBarUI() {

            protected JButton createDecreaseButton(int orientation) {
                return new InvisibleScrollBarButton();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return new InvisibleScrollBarButton();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                Graphics2D g2d = (Graphics2D) g;
                g.setColor(new Color(230, 230, 230));
                g2d.fill(trackBounds);
                g2d.draw(trackBounds);
            }

            @Override
            protected void setThumbBounds(int x, int y, int width, int height) {
                super.setThumbBounds(x, y, width, height);
                scrollbar.repaint();
            }

            class InvisibleScrollBarButton extends JButton {
                private InvisibleScrollBarButton() {
                    setOpaque(false);
                    setFocusable(false);
                    setFocusPainted(false);
                    setBorderPainted(false);
                    setBorder(BorderFactory.createEmptyBorder());
                }
            }
        };

        this.setPreferredSize(new Dimension(Thumb_size, Thumb_size));
        this.setUI(ui);

    }

    public void setPosition(int value){ this.setValue(value); }
    public int getPosition(){ return this.getValue(); }

    public ScrollBar setOriented(int orientation) {
        this.setOrientation(orientation);
        return this;
    }
}
