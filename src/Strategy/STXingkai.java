package Strategy;

import javax.swing.text.StyledEditorKit;

public class STXingkai implements FontStrategy{
    @Override
    public void setFont(String fn){
        new StyledEditorKit.FontFamilyAction("STXingkai","STXingkai").actionPerformed(null);
    }
}
