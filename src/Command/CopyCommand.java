package Command;

import javax.swing.text.DefaultEditorKit;

public class CopyCommand implements Command{
    @Override
    public void execute() {
        new DefaultEditorKit.CopyAction().actionPerformed(null);
    }
}
