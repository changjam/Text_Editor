package Iterator;

import CompositeAndDecorator.HTML;

public interface Iterator {

    void first();
    HTML next();
    boolean isDone();
    HTML currentItem();
}
