package AbstractFactory;

public interface GadgetFactory {
    ScrollBar createScrollbar();
    MenuBar createMenuBar();
    Menu createMenu();
    MenuItem createMenuItem();
}
