package AbstractFactory;

import java.awt.*;

public class MacMenuItem extends MenuItem{
    private final Color foreground = new Color(43, 41, 42, 255);
    private final Color background = new Color(211, 206, 205, 255);

    public MacMenuItem(){
        this.setOpaque(true);
        this.setBackground(background);
        this.setForeground(foreground );
    }

    public void setDescription(String description) {
        this.setText(description);
    }
}