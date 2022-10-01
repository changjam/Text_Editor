package visitor;

import CompositeAndDecorator.Body;
import CompositeAndDecorator.HTML;
import CompositeAndDecorator.Span;
import builder.Builder;

import CompositeAndDecorator.Character;
import utils.parseArgs;

import java.util.Stack;

public class DrawGlyphVisitor implements Visitor{
    private Stack<parseArgs> parseStack = new Stack<>();
    private Builder formatting;
    public DrawGlyphVisitor(Builder formatting){
        this.formatting = formatting;
    }
    @Override public void visit(Body body) {pushCompositeToStack(body);}
    @Override public void visit(CompositeAndDecorator.Paragraph paragraph) {pushCompositeToStack(paragraph);}
    @Override public void visit(Span span) {pushCompositeToStack(span);}
    @Override public void visit(Character character) {pushLeafToStack(character);}
    @Override public void visit(CompositeAndDecorator.Bold bold) {pushCompositeToStack(bold);}
    @Override public void visit(CompositeAndDecorator.Italic italic) {pushCompositeToStack(italic);}
    @Override public void visit(CompositeAndDecorator.Underline underline) {pushCompositeToStack(underline);}
    @Override public void visit(CompositeAndDecorator.Font font) {pushCompositeToStack(font);}
    @Override public void visit(HTML glyph) {pushCompositeToStack(glyph);}
    public void pushCompositeToStack(HTML g){
        parseArgs parseArgs = formatting.parse(g);
        if(g.getChildSize()>0 && parseStack.size() >= g.getChildSize()){
            String leafParseStrings = "";
            for(int i = 0; i < g.getChildSize(); i++){
                leafParseStrings = parseStack.pop().getFullTag() + leafParseStrings;
            }
            leafParseStrings = parseArgs.getStartingTag() + leafParseStrings + parseArgs.getClosingTag();
            parseArgs pushBackParseArgs = new parseArgs();
            pushBackParseArgs.setFullTag(leafParseStrings);
            parseStack.push(pushBackParseArgs);
        } else {
            pushLeafToStack(g);
        }
    }
    public void pushLeafToStack(HTML g){
        parseArgs parseArgs = formatting.parse(g);
        parseStack.push(parseArgs);
    }
    public String getParseString(){
        return parseStack.peek().getFullTag();
    }
}
