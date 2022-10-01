package FontStyleFlyweight;

public class FontStyletwo extends FontStyleFlyweight{
    public void setFontStyle(){
        super.reset();//將先前設的粗體、斜體、底線全部取消
        FontStyleName ="STXingkai";
        FontSize=24;
        align="right";
        b=0;
        i=1;
        u=0;
        FGColor="FG-Blue";
        super.show();
    }
}
