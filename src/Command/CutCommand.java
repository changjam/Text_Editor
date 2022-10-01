package Command;

import javax.swing.text.DefaultEditorKit;

public class CutCommand implements Command{
    @Override
    public void execute() {
        new DefaultEditorKit.CutAction().actionPerformed(null);
    }
}
