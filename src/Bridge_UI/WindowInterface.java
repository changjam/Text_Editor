package  Bridge_UI;

import AbstractFactory.GadgetFactory;

import javax.swing.*;
import java.awt.*;

public interface WindowInterface {
    String getOSName();
    JFrame drawFrame();
    void setOSFont(Font systemFont);
    Font getOSFont();
    GadgetFactory getWidgetFactory();
}
