package FontStyleFlyweight;

import Strategy.*;
import Memento.WindowEventListener.ForegroundColor;
import javax.swing.text.StyledEditorKit;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public abstract class FontStyleFlyweight {
    protected static String FontStyleName;
    protected static int FontSize;
    protected static int b;
    protected static int i;
    protected static int u;
    protected static String FGColor;
    protected static String align;
    protected static Color c=null;
    protected static int a=0;
    protected FontContext context;
    public abstract void setFontStyle();
    private Map<String,Color> fgcolorlist =new HashMap<String,Color>(){{
        put("FG-Red",Color.RED);
        put("FG-Blue",Color.BLUE);
        put("FG-Green",Color.GREEN);
    }};
    private Map<String,Integer> alignlist =new HashMap<String,Integer>(){{
        put("center",1);
        put("right",2);
        put("left",0);
    }};
    private Map<String, FontStrategy> fontlist =new HashMap<String,FontStrategy>(){{
        put("Bradley Hand ITC",new BradleyHandITC());
        put("STXingkai",new STXingkai());
        put("Comic Sans MS",new ComicSansMS());
    }};

    protected void reset(){ //重設粗體、斜體、底線
        if (b==1){new StyledEditorKit.BoldAction().actionPerformed(null);}
        if (i==1){new StyledEditorKit.ItalicAction().actionPerformed(null);}
        if (u==1){new StyledEditorKit.UnderlineAction().actionPerformed(null);}
    }

    protected void show(){
        c = fgcolorlist.get(FGColor);
        a = alignlist.get(align);
        context = new FontContext(fontlist.get(FontStyleName));
        context.runStrategy(FontStyleName);
        new StyledEditorKit.FontSizeAction("font-size-"+FontSize,FontSize).actionPerformed(null);
        new StyledEditorKit.AlignmentAction(align,a).actionPerformed(null);
        new ForegroundColor(FGColor, c).actionPerformed(null);
        reset();
    }
}