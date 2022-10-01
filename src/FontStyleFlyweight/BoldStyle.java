package FontStyleFlyweight;

import javax.swing.text.StyledEditorKit;

public class BoldStyle extends FontStyleFlyweight{
    public void setFontStyle(){
        if(b==0){
            b=1;
        }else{
            b=0;
        }
        new StyledEditorKit.BoldAction().actionPerformed(null);
    }
}
