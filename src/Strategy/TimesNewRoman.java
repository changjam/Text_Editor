package Strategy;

import javax.swing.text.StyledEditorKit;

public class TimesNewRoman implements FontStrategy{
    @Override
    public void setFont(String fn){
        new StyledEditorKit.FontFamilyAction("Times New Roman","Times New Roman").actionPerformed(null);
    }
}
