package CompositeAndDecorator;

import visitor.Visitor;

public abstract class Decorator extends HTML {
    protected HTML html;  //Decoratee
    @Override
    public void insert(HTML g) {
        html.insert(g);
    }
    @Override
    public void remove(int i) {
        html.remove(i);
    }

    @Override
    public HTML getChild(int i) {
        return html.getChild(i);
    }
    @Override
    public int getChildSize() { return html.getChildSize(); }

    @Override
    public void setAttribute(String attribute) { html.setAttribute(attribute); }
    @Override
    public void setContent(String content) { html.setContent(content); }

    @Override
    public String getAttribute() { return html.getAttribute(); }
    @Override
    public String getContent() { return html.getContent(); }
    @Override
    public String getTagname() { return html.getTagname(); }

    @Override
    public boolean isSingleTag() { return html.isSingleTag(); }
    @Override
    public void accept(Visitor visitor) { html.accept(visitor); }
}
