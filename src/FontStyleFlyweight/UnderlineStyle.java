package FontStyleFlyweight;

import javax.swing.text.StyledEditorKit;

public class UnderlineStyle extends FontStyleFlyweight{
    public void setFontStyle(){
        if(u==0){
            u=1;
        }else{
            u=0;
        }
        new StyledEditorKit.UnderlineAction().actionPerformed(null);
    }
}
