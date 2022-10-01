package builder;

import CompositeAndDecorator.HTML;
import utils.parseArgs;

public interface Builder {
 parseArgs parse(HTML glyph);//解析Glyph??

 //void build_font();
 //void buildBI();
 String getType();  //當前所呈現的模式

 void build_bcg();

 void build_color();

 void build_cursor();

 void build_margin();

 //void build_fontStyle();

 //void build_TextDecoration();

 Style getStyle();//!!與Style關聯
}
