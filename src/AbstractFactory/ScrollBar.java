package AbstractFactory;

import javax.swing.*;

abstract public class ScrollBar extends JScrollBar {
    public void setPosition(int value){ this.setValue(value); }
    public int getPosition(){ return this.getValue(); }
    abstract public ScrollBar setOriented(int orientation);
    public void setEnable(boolean enable){this.setEnable(enable);}
}
