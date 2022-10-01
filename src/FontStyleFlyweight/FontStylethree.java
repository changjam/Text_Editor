package FontStyleFlyweight;

public class FontStylethree extends FontStyleFlyweight{
    public void setFontStyle(){
        super.reset();//將先前設的粗體、斜體、底線全部取消
        FontStyleName ="Comic Sans MS";
        FontSize=24;
        align="left";
        b=0;
        i=0;
        u=1;
        FGColor="FG-Green";
        super.show();
    }
}
