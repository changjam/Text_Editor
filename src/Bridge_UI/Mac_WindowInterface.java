package  Bridge_UI;

import AbstractFactory.GadgetFactory;
import AbstractFactory.GadgetFactoryProducer;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

public class Mac_WindowInterface implements WindowInterface {
    private final String OSName = "Mac";
    String  FontName="文泉驛正黑";
    private Font useFont = new Font(FontName, Font.PLAIN, 14);

    @Override
    public String getOSName() {
        return OSName;
    }

    @Override
    public JFrame drawFrame() {

        DefaultMetalTheme linux = new DefaultMetalTheme() {
            private final Color Color_BACKGROUND = new Color(60, 59, 57, 255);
            private final Color Color_FOREGROUND = new Color(159, 148, 146, 255);
            private final Color BACKGROUND = new Color(43, 41, 42, 255);
            private final Color FOREGROUND = new Color(244, 247, 230, 255);

            //inactive title color
            public ColorUIResource getWindowTitleInactiveBackground() {
                return new ColorUIResource(Color_BACKGROUND);
            }
            public ColorUIResource getWindowTitleInactiveForeground() {
                return new ColorUIResource(Color_FOREGROUND);
            }
            //start inActiveBumps
            public ColorUIResource getControlHighlight () {
                return new ColorUIResource(Color_BACKGROUND);
            }

            public ColorUIResource getControlDarkShadow () {
                return new ColorUIResource(Color_BACKGROUND);
            }

            public ColorUIResource getControl () {
                return new ColorUIResource(Color_FOREGROUND);
            }
            //end inActiveBumps

            //active title color
            public ColorUIResource getWindowTitleBackground() {
                return new ColorUIResource(BACKGROUND);
            }
            public ColorUIResource getWindowTitleForeground() {
                return new ColorUIResource(FOREGROUND);
            }
            //start ActiveBumps
            public ColorUIResource getPrimaryControlHighlight() {
                return new ColorUIResource(BACKGROUND);
            }
            public ColorUIResource getPrimaryControlDarkShadow() {
                return new ColorUIResource(BACKGROUND);
            }

            public ColorUIResource getPrimaryControl(){
                return new ColorUIResource(FOREGROUND);
            }

            //end ActiveBumps

            //font
            public FontUIResource getWindowTitleFont(){
                return new FontUIResource(useFont);
            }
            public FontUIResource getControlTextFont(){
                return new FontUIResource(useFont);
            }
            public FontUIResource getMenuTextFont(){
                return new FontUIResource(useFont);
            }
            public FontUIResource getSubTextFont(){
                return new FontUIResource(useFont);
            }
            public FontUIResource getSystemTextFont(){
                return new FontUIResource(useFont);
            }
            public FontUIResource getUserTextFont(){
                return new FontUIResource(useFont);
            }
        };

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        Toolkit.getDefaultToolkit().setDynamicLayout(true);

        MetalLookAndFeel.setCurrentTheme(linux);

        // 設定JFrame
        try { UIManager.setLookAndFeel(new MetalLookAndFeel()); }
        catch(Exception ex) {}

        initfont();

        return new JFrame();
    }

    @Override
    public void setOSFont(Font getFont){
        this.useFont = getFont;
        initfont();
    }

    @Override
    public Font getOSFont(){
        return this.useFont;
    }

    @Override
    public GadgetFactory getWidgetFactory() {
        return GadgetFactoryProducer.getFactory(OSName);
    }

    private static WindowInterface macInterface;
    public static  WindowInterface getInstance() {
        if(macInterface == null)
        {
            // 需要時才建立物件, 稱為 lazy Initialization
            macInterface = new Mac_WindowInterface();
        }

        return macInterface;
    }

    public void initfont() {
        UIManager.put("CheckBoxMenuItem.acceleratorFont", useFont);
        UIManager.put("Button.font", useFont);
        UIManager.put("ToggleButton.font", useFont);
        UIManager.put("RadioButton.font", useFont);
        UIManager.put("CheckBox.font", useFont);
        UIManager.put("ColorChooser.font", useFont);
        UIManager.put("ComboBox.font", useFont);
        UIManager.put("Label.font", useFont);
        UIManager.put("List.font", useFont);
        UIManager.put("MenuBar.font", useFont);
        UIManager.put("Menu.acceleratorFont", useFont);
        UIManager.put("RadioButtonMenuItem.acceleratorFont", useFont);
        UIManager.put("MenuItem.acceleratorFont", useFont);
        UIManager.put("MenuItem.font", useFont);
        UIManager.put("RadioButtonMenuItem.font", useFont);
        UIManager.put("CheckBoxMenuItem.font", useFont);
        UIManager.put("OptionPane.buttonFont", useFont);
        UIManager.put("OptionPane.messageFont", useFont);
        UIManager.put("Menu.font", useFont);
        UIManager.put("PopupMenu.font", useFont);
        UIManager.put("OptionPane.font", useFont);
        UIManager.put("Panel.font", useFont);
        UIManager.put("ProgressBar.font", useFont);
        UIManager.put("ScrollPane.font", useFont);
        UIManager.put("Viewport.font", useFont);
        UIManager.put("TabbedPane.font", useFont);
        UIManager.put("Slider.font", useFont);
        UIManager.put("Table.font", useFont);
        UIManager.put("TableHeader.font", useFont);
        UIManager.put("TextField.font", useFont);
        UIManager.put("Spinner.font", useFont);
        UIManager.put("PasswordField.font", useFont);
        UIManager.put("TextArea.font", useFont);
        UIManager.put("TextPane.font", useFont);
        UIManager.put("EditorPane.font", useFont);
        UIManager.put("TabbedPane.smallFont", useFont);
        UIManager.put("TitledBorder.font", useFont);
        UIManager.put("ToolBar.font", useFont);
        UIManager.put("ToolTip.font", useFont);
        UIManager.put("Tree.font", useFont);
        UIManager.put("FormattedTextField.font", useFont);
        UIManager.put("IconButton.font", useFont);
        UIManager.put("InternalFrame.optionDialogTitleFont", useFont);
        UIManager.put("InternalFrame.paletteTitleFont", useFont);
        UIManager.put("InternalFrame.titleFont", useFont);
    }
}
