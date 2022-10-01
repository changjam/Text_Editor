package AbstractFactory;

public class WinGadgetFactory implements GadgetFactory {

    @Override
    public ScrollBar createScrollbar() {
        return new WinScrollBar();
    }

    @Override
    public MenuBar createMenuBar() {
        return new WinMenuBar();
    }

    @Override
    public Menu createMenu() {
        return new WinMenu();
    }

    @Override
    public MenuItem createMenuItem() {
        return new WinMenuItem();
    }
}
