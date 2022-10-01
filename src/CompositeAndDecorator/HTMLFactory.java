package CompositeAndDecorator;


public class HTMLFactory {
    public HTMLFactory(){}
    public CompositeAndDecorator.HTML getGlyphInstance(String type, CompositeAndDecorator.HTML decoratee){
        switch(type){
            case "b": return new Bold(decoratee);
            case "i": return new Italic(decoratee);
            case "u": return new Underline(decoratee);
            case "font": return new Font(decoratee);

            case "left": return new Leftalign(decoratee);
            case "right": return new Rightalign(decoratee);
            case "center":return new Centeralign(decoratee);

            case "body": return new Body();
            case "p": return new Paragraph();
            case "span": return new Span();
            default: return new Character();
        }
    }
}
