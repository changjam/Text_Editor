package FontStyleFlyweight;

import java.util.Map;
import java.util.HashMap;

public class FontStyleFlyweightFactory {
    private static Map<Integer,FontStyleFlyweight> fontstyles =new HashMap<Integer,FontStyleFlyweight>();

    public static FontStyleFlyweight getFontStyle(int style_no){

        if(fontstyles.get(style_no)==null){
            FontStyleFlyweight font =null;
            switch (style_no){
                case 1:font =  new FontStyleone();break;
                case 2: font =  new FontStyletwo();break;
                case 3: font =  new FontStylethree();break;
                case 4:font =  new BoldStyle();break;
                case 5:font =  new ItalicStyle();break;
                case 6: font =  new UnderlineStyle();break;

            }
            fontstyles.put(style_no,font);
        }
        fontstyles.get(style_no).setFontStyle();
        return fontstyles.get(style_no);
    }
}