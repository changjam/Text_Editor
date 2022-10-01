package Command;

import Memento.WindowEventListener;

import java.awt.*;

public class BlueCommand implements Command{
    @Override
    public void execute() {
        new WindowEventListener.ForegroundColor("FG-Blue", Color.BLUE).actionPerformed(null);
    }
}
