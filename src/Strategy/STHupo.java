package Strategy;

import javax.swing.text.StyledEditorKit;

public class STHupo implements FontStrategy{
    @Override
    public void setFont(String fn){
        new StyledEditorKit.FontFamilyAction("STHupo","STHupo").actionPerformed(null);
    }
}
