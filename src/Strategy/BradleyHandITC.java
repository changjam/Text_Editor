package Strategy;

import javax.swing.text.StyledEditorKit;

public class BradleyHandITC implements FontStrategy{
    @Override
    public void setFont(String fn){
        new StyledEditorKit.FontFamilyAction("Bradley Hand ITC","Bradley Hand ITC").actionPerformed(null);
    }
}
