package AbstractFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class MacScrollBar extends AbstractFactory.ScrollBar {
    private final int Thumb_size  = 8;
    public MacScrollBar(){
        BasicScrollBarUI ui = new BasicScrollBarUI() {
            private final int ScrollBar_ALPHA_dragging = 255;
            private final int SCROLL_BAR_ALPHA_ROLLOVER = 250;
            private final int SCROLL_BAR_ALPHA = 245;
            @Override
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
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                int alpha = (isDragging ? ScrollBar_ALPHA_dragging : (isThumbRollover() ? SCROLL_BAR_ALPHA_ROLLOVER : SCROLL_BAR_ALPHA));
                int orientation = scrollbar.getOrientation();
                int width = (orientation == JScrollBar.VERTICAL) ? Thumb_size : thumbBounds.width;
                width = Math.max(width, Thumb_size);
                int height = (orientation == JScrollBar.VERTICAL) ? thumbBounds.height : Thumb_size;
                height = Math.max(height, Thumb_size);

                Graphics2D graphics2D = (Graphics2D) g.create();
                graphics2D.setColor(new Color(246, 111, 53, alpha));
                graphics2D.fillRoundRect(thumbBounds.x, thumbBounds.y, width, height, Thumb_size, Thumb_size);
                graphics2D.dispose();
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

    public ScrollBar setOriented(int orientation) {
        this.setOrientation(orientation);
        return this;
    }
}
