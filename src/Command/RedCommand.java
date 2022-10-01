package Command;

import Memento.WindowEventListener;

import java.awt.*;

public class RedCommand implements Command{
    @Override
    public void execute() {
        new WindowEventListener.ForegroundColor("FG-Red", Color.RED).actionPerformed(null);
    }
}
