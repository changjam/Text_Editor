package Bridge_UI;
import AbstractFactory.GadgetFactory;
import AbstractFactory.GadgetFactoryProducer;

import javax.swing.*;
import java.awt.*;

public class Win_WindowInterface implements WindowInterface {
    private final String OSName = "Win";
    String  FontName="微軟正黑體";
    private Font systemFont = new Font(FontName, Font.PLAIN, 16);

    @Override
    public String getOSName() {
        return OSName;
    }

    @Override
    public JFrame drawFrame() {
        // 設定JFrame風格
        //UIManager.getSystemLookAndFeelClassName()
        try { UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); }
        catch(Exception ex) {}

        initfont();

        return new JFrame();
    }

    @Override
    public void setOSFont(Font getFont){
        this.systemFont = getFont;
        initfont();
    }

    @Override
    public Font getOSFont(){
        return this.systemFont;
    }

    @Override
    public GadgetFactory getWidgetFactory() {
        return GadgetFactoryProducer.getFactory(OSName);
    }

    private static WindowInterface winInterface;
    public static  WindowInterface getInstance() {
        if(winInterface == null)
        {
            // 需要時才建立物件, 稱為 lazy instantiaze
            winInterface = new Win_WindowInterface();
        }

        return winInterface;
    }

    public void initfont() {
        UIManager.put("CheckBoxMenuItem.acceleratorFont", systemFont);
        UIManager.put("Button.font", systemFont);
        UIManager.put("ToggleButton.font", systemFont);
        UIManager.put("RadioButton.font", systemFont);
        UIManager.put("CheckBox.font", systemFont);
        UIManager.put("ColorChooser.font", systemFont);
        UIManager.put("ComboBox.font", systemFont);
        UIManager.put("Label.font", systemFont);
        UIManager.put("List.font", systemFont);
        UIManager.put("MenuBar.font", systemFont);
        UIManager.put("Menu.acceleratorFont", systemFont);
        UIManager.put("RadioButtonMenuItem.acceleratorFont", systemFont);
        UIManager.put("MenuItem.acceleratorFont", systemFont);
        UIManager.put("MenuItem.font", systemFont);
        UIManager.put("RadioButtonMenuItem.font", systemFont);
        UIManager.put("CheckBoxMenuItem.font", systemFont);
        UIManager.put("OptionPane.buttonFont", systemFont);
        UIManager.put("OptionPane.messageFont", systemFont);
        UIManager.put("Menu.font", systemFont);
        UIManager.put("PopupMenu.font", systemFont);
        UIManager.put("OptionPane.font", systemFont);
        UIManager.put("Panel.font", systemFont);
        UIManager.put("ProgressBar.font", systemFont);
        UIManager.put("ScrollPane.font", systemFont);
        UIManager.put("Viewport.font", systemFont);
        UIManager.put("TabbedPane.font", systemFont);
        UIManager.put("Slider.font", systemFont);
        UIManager.put("Table.font", systemFont);
        UIManager.put("TableHeader.font", systemFont);
        UIManager.put("TextField.font", systemFont);
        UIManager.put("Spinner.font", systemFont);
        UIManager.put("PasswordField.font", systemFont);
        UIManager.put("TextArea.font", systemFont);
        UIManager.put("TextPane.font", systemFont);
        UIManager.put("EditorPane.font", systemFont);
        UIManager.put("TabbedPane.smallFont", systemFont);
        UIManager.put("TitledBorder.font", systemFont);
        UIManager.put("ToolBar.font", systemFont);
        UIManager.put("ToolTip.font", systemFont);
        UIManager.put("Tree.font", systemFont);
        UIManager.put("FormattedTextField.font", systemFont);
        UIManager.put("IconButton.font", systemFont);
        UIManager.put("InternalFrame.optionDialogTitleFont", systemFont);
        UIManager.put("InternalFrame.paletteTitleFont", systemFont);
        UIManager.put("InternalFrame.titleFont", systemFont);
    }
}
