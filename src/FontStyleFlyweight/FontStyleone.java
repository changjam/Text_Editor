package FontStyleFlyweight;

public class FontStyleone extends FontStyleFlyweight{
    public void setFontStyle(){
        super.reset();//將先前設的粗體、斜體、底線全部取消
        FontStyleName ="Bradley Hand ITC";
        FontSize=24;
        align="center";
        b=1;
        i=0;
        u=0;
        FGColor="FG-Red";
        super.show();
    }
}
