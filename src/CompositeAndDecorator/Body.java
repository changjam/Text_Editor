package CompositeAndDecorator;

import Iterator.Iterator;
import Iterator.CompositeIterator;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;


public class Body extends HTML {
    private List<HTML> htmls = new ArrayList<>();
    private String tagname = "body";
    private String attribute = "";
    public Body(){}

    @Override
    public void insert(HTML g) {
        htmls.add(g);
    }
    @Override
    public void remove(int i) {
        htmls.remove(i);
    }

    @Override
    public HTML getChild(int i) {
        return htmls.get(i);
    }
    @Override
    public int getChildSize() {
        return htmls.size();
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
