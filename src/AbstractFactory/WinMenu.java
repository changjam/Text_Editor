package AbstractFactory;

import java.awt.*;

public class WinMenu extends Menu {
    private final Color foreground = Color.BLACK;
    private final Color background = new Color(246, 246, 246, 255);
    private final Color Selected_foreground = Color.WHITE;
    private final Color Selected_background = new Color(43, 151, 246, 255);

    public WinMenu(){
        this.setOpaque(true);
        this.setBackground(background);
        this.setForeground(foreground);

        this.addChangeListener(e -> {
            if (e.getSource() instanceof Menu) {
                Menu item = (Menu) e.getSource();
                if (item.isSelected() || item.isArmed()) {
                    item.setBackground(Selected_background);
                    item.setForeground(Selected_foreground);
                } else {
                    item.setBackground(background);
                    item.setForeground(foreground);
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
