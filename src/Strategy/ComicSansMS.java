package Strategy;

import javax.swing.text.StyledEditorKit;

public class ComicSansMS implements FontStrategy{
    @Override
    public void setFont(String fn){
        new StyledEditorKit.FontFamilyAction("Comic Sans MS","Comic Sans MS").actionPerformed(null);
    }
}
