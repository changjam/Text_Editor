package Command;

import javax.swing.text.DefaultEditorKit;

public class PasteCommand implements Command{
    @Override
    public void execute() {
        new DefaultEditorKit.PasteAction().actionPerformed(null);
    }
}
