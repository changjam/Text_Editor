package builder;

import CompositeAndDecorator.Character;
import CompositeAndDecorator.HTML;
import utils.parseArgs;

public class TextOnlyBuild implements Builder{
    private final String _type = "TextOnly";
    private static Style style = new Style();

    public TextOnlyBuild(){
        this.style.setTextEffect("text-decoration: none;font-style:none;font-family:none;font-size:12px;font-weight:normal;");
    }

    @Override
    public void build_bcg(){

        this.style.setBackgroundColor("#000000");
    }

    @Override
    public void build_color(){
        this.style.setColor("; color: #FFFFFF");
    }

    @Override
    public void build_cursor(){
        this.style.setCursor("auto");
    }

    @Override
    public void build_margin(){
        this.style.setMargin("0px");
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
