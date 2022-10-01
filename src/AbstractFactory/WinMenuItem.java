package AbstractFactory;

import java.awt.*;

public class WinMenuItem extends MenuItem {
    private final Color foreground = Color.BLACK;
    private final Color background = new Color(246, 246, 246, 255);

    public WinMenuItem(){
        this.setOpaque(true);
        this.setBackground(background);
        this.setForeground(foreground);
    }

    public void setDescription(String description) {
        this.setText(description);
    }
}
