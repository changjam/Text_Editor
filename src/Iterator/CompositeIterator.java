package Iterator;

import CompositeAndDecorator.HTML;

public class CompositeIterator implements Iterator{
    private int current = 0;
    private HTML Composite;
    public CompositeIterator(HTML glyphComposite){
        this.Composite = glyphComposite;
    }
    @Override
    public void first(){
        current = 0;
    }

    @Override
    public HTML next() {
        return Composite.getChild(current++);
    }

    @Override
    public boolean isDone() {
        if(current >= Composite.getChildSize() || Composite.getChild(current) == null)
            return false;
        return true;
    }

    public HTML currentItem(){
        return Composite.getChild(current);
    }
}
