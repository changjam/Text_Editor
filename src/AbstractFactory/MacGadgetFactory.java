package AbstractFactory;

public class MacGadgetFactory implements GadgetFactory {

    @Override
    public ScrollBar createScrollbar() {
        return new MacScrollBar();
    }

    @Override
    public MenuBar createMenuBar() {
        return new MacMenuBar();
    }

    @Override
    public Menu createMenu() {
        return new MacMenu();
    }

    @Override
    public MenuItem createMenuItem() {
        return new MacMenuItem();
    }
}
