package Command;

import Memento.WindowEventListener;

import java.awt.*;

public class GreenCommand implements Command{
    @Override
    public void execute() {
        new WindowEventListener.ForegroundColor("FG-Green", Color.GREEN).actionPerformed(null);
    }
}
