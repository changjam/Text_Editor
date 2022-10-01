package CompositeAndDecorator;

import Iterator.Iterator;
import Iterator.CompositeIterator;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends HTML {
    private List<HTML> glyphs = new ArrayList<>();
    private String tagname = "p";
    private String attribute = "";
    public Paragraph(){}

    @Override
    public void insert(HTML g) {
        glyphs.add(g);
    }
    @Override
    public void remove(int i) {
        glyphs.remove(i);
    }

    @Override
    public HTML getChild(int i) {
        return glyphs.get(i);
    }
    @Override
    public int getChildSize() {
        return glyphs.size();
    }
    @Override
    public Iterator getIterator() {
        return new CompositeIterator(this);
    }

    @Override
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getAttribute() {
        return attribute;
    }
    @Override
    public String getTagname() { return tagname; }
    @Override
    public String getContent() { return ""; }

    @Override
    public boolean isSingleTag() { return false; }
    @Override
    public void accept(Visitor visitor) {
        Iterator iterator = this.getIterator();
        while (iterator.isDone()) {
            iterator.next().accept(visitor);
        }
        visitor.visit(this);
    }
}
