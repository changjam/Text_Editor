package builder;

import CompositeAndDecorator.Character;
import CompositeAndDecorator.HTML;
import utils.parseArgs;

public class NormalBuild implements Builder {
    private final String _type = "Normal";
    private static Style style = new Style();

    @Override
    public void build_bcg() {
        this.style.setBackgroundColor("#FFFFFF;");
    }

    @Override
    public void build_color() {
    }

    @Override
    public void build_cursor() {
        this.style.setCursor("auto");
    }


    @Override
    public void build_margin() {
        this.style.setMargin("5px");
    }

    @Override
    public parseArgs parse(HTML glyph) {
        parseArgs parseArgs = new parseArgs();
        String startringTag = "";
        String closingTag = "";
        if(glyph instanceof Character) {
            startringTag = glyph.getContent();
            parseArgs.setFullTag(startringTag);
        } else {
            startringTag = "<" + glyph.getTagname() + " " + glyph.getAttribute().replace("\\\"", "\"") + " " + style + " >";
            closingTag = "</" + glyph.getTagname() + ">";

            if(glyph.isSingleTag()) closingTag = "";
            parseArgs.setStartingTag(startringTag);
            parseArgs.setClosingTag(closingTag);
        }
        return parseArgs;
    }

    @Override
    public String getType() {
        return _type;
    }

    @Override
    public Style getStyle(){
        return style;
    }
}
