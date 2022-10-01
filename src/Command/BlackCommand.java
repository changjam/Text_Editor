package Command;

import Memento.WindowEventListener;

import java.awt.*;

public class BlackCommand implements Command{
    @Override
    public void execute() {
        new WindowEventListener.ForegroundColor("FG-Black", Color.BLACK).actionPerformed(null);
    }
}
