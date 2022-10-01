package visitor;

import CompositeAndDecorator.Body;
import CompositeAndDecorator.Span;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import CompositeAndDecorator.Character;

import CompositeAndDecorator.HTML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GlyphToOOSEFILEVisitor implements Visitor{
    private Stack<Map<String, Object>> parseStack = new Stack<>();
    private ArrayList<Map<String, Object>> childList;
    @Override public void visit(Body body) {pushCompositeToStack(body);}
    @Override public void visit(CompositeAndDecorator.Paragraph paragraph) {pushCompositeToStack(paragraph);}
    @Override public void visit(Span span) {pushCompositeToStack(span);}
    @Override public void visit(Character character) {
        pushChildToStack(character);}
    @Override public void visit(CompositeAndDecorator.Bold bold) {pushDecorateToStack(bold);}
    @Override public void visit(CompositeAndDecorator.Italic italic) {pushDecorateToStack(italic);}
    @Override public void visit(CompositeAndDecorator.Underline underline) {pushDecorateToStack(underline);}
    @Override public void visit(CompositeAndDecorator.Font font) {pushDecorateToStack(font);}
    @Override public void visit(HTML glyph) {pushCompositeToStack(glyph);}
    public Map<String, Object> generateGlyphElem(HTML g) {
        childList = new ArrayList<>();
        for(int i = 0; i < g.getChildSize(); i++){
            childList.add(0, parseStack.pop());
        }
        Map<String, Object> elem = new HashMap<>();
        elem.put("tag", g.getTagname());
        elem.put("attribute", g.getAttribute().replace("\\\"", "\""));
        elem.put("content", g.getContent());
        return elem;
    }
    public void pushDecorateToStack(HTML g){
        if(g.getChildSize()>0 && parseStack.size() >= g.getChildSize()){
            Map<String, Object> elem = generateGlyphElem(g);
            elem.put("decorate", childList.get(0));
            elem.put("child", new ArrayList<>());
            parseStack.push(elem);
        } else {
            pushChildToStack(g);
        }
    }
    public void pushCompositeToStack(HTML g){
        if(g.getChildSize()>0 && parseStack.size() >= g.getChildSize()){
            Map<String, Object> elem = generateGlyphElem(g);
            elem.put("decorate", null);
            elem.put("child", childList);
            parseStack.push(elem);
        } else {
            pushChildToStack(g);
        }
    }
    public void pushChildToStack(HTML g){
        Map<String, Object> elem = new HashMap<>();
        elem.put("tag", g.getTagname());
        elem.put("attribute", g.getAttribute().replace("\\\"", "\""));
        elem.put("content", g.getContent());
        elem.put("decorate", null);
        elem.put("child", new ArrayList<>());
        parseStack.push(elem);
    }
    public String getParseString(){
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(parseStack.peek());
    }
}
