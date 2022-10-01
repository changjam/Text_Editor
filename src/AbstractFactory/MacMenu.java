package AbstractFactory;

import java.awt.*;

public class MacMenu extends Menu {
    private final Color foreground = new Color(43, 41, 42, 255);
    private final Color background = new Color(211, 206, 205, 255);
    private final Color Selected_background = new Color(0, 188, 212, 255);

    public MacMenu(){
        this.setOpaque(true);
        this.setBackground(background);
        this.setForeground(foreground);

        this.addChangeListener(e -> {
            if (e.getSource() instanceof Menu) {
                Menu item = (Menu) e.getSource();
                if (item.isSelected() || item.isArmed()) {
                    item.setBackground(Selected_background);
                } else {
                    item.setBackground(background);
                }
            }
        });
    }

    @Override
    public void setDescription(String description) {
        super.setText(description);
    }

    @Override
    public void addMenuItem(MenuItem m) {
        this.add(m);
    }
}
