package CompositeAndDecorator;

import visitor.Visitor;

public class Leftalign extends Decorator {
    private String tagname = "left";
    private String attribute = "";
    public Leftalign(HTML g){
        super.html = g;
    }
    @Override
    public int getChildSize() {
        return 1;
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
        super.accept(visitor);
        visitor.visit(this);
    }
}