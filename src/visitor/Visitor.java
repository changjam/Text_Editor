package visitor;

import CompositeAndDecorator.Body;
import CompositeAndDecorator.HTML;
import CompositeAndDecorator.Span;
import CompositeAndDecorator.Character;

public interface Visitor {
    void visit(Body body);
    void visit(CompositeAndDecorator.Paragraph paragraph);
    void visit(Span span);
    void visit(Character character);
    void visit(CompositeAndDecorator.Bold bold);
    void visit(CompositeAndDecorator.Italic italic);
    void visit(CompositeAndDecorator.Underline underline);
    void visit(CompositeAndDecorator.Font font);
    void visit(HTML glyph);
}
