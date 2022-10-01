package Bridge_UI;

import Memento.UiOperateListener;
import Memento.WindowEventListener;
import builder.*;
import AbstractFactory.GadgetFactory;
import CompositeAndDecorator.HTML;
import utils.HTMLParser;
import visitor.CountCharacterVisitor;
import visitor.DrawGlyphVisitor;
import AbstractFactory.Menu;
import AbstractFactory.MenuBar;
import AbstractFactory.MenuItem;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class WholeWindow extends WindowAbstract {
    // Frame
    private final JFrame frame;
    private final int width = 800;
    private final int height = 600;
    private final DialogWindow dialogWindow;

    // Listener初始化
    private final WindowEventListener windowEventListener = new WindowEventListener(this);
    private final UiOperateListener uiOperateListener = new UiOperateListener(this);
    private final GadgetFactory gadgetFactory;

    // 編輯區初始化
    public final JTextPane editorViewer = new JTextPane();
    private final JPanel statusBar = new JPanel(new BorderLayout());
    private final JLabel statusLabel = new JLabel();

    // 排版物件初始化
    private Builder formatting = new NormalBuild();

    // Glyph初始化
    private HTML root = null;

    //    變數
    private Menu fontStyleMenu;
    private Menu colorMenu;
    private Menu sizeMenu;
    private Menu fontMenu;
    private Menu alignMenu;
    DrawGlyphVisitor drawGlyphVisitor;
    //================================================== GUI ==================================================
    public WholeWindow(WindowInterface win_impl) {
        super(win_impl);                                //把Implementor存著
        gadgetFactory = super.getWidgetFactory();   //依不同的Implementor取得WidgetFactory
        //-------------------- JFrame --------------------
        // 建立JFrame
        frame = super.drawFrame();                  //依不同的Implementor建出Frame
        frame.setTitle("Group4 Document Editor");
        //設置icon
        ImageIcon img = new ImageIcon("icon.jpg");
        frame.setIconImage(img.getImage());
        frame.setBounds(60, 60, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 建立MenuBar
        frame.setJMenuBar(createMenuBar());
        // 設定Layout
        frame.setLayout(new BorderLayout());
        frame.add(createScrollPane(editorViewer), BorderLayout.CENTER);
        frame.add(statusBar, BorderLayout.SOUTH);

        // 設定 Dialog Window
        dialogWindow = new DialogWindow(win_impl);
        //設定游標
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        //-------------------- 狀態欄 --------------------
        statusLabel.setHorizontalAlignment(JLabel.RIGHT);
        statusLabel.setFont(super.getSystemFont());
        statusBar.setPreferredSize(new Dimension(statusBar.getWidth(), 30));
        statusBar.add(statusLabel);
        //-------------------- 編輯器區 --------------------
        editorViewer.setContentType("text/html");
        editorViewer.getDocument().addDocumentListener(uiOperateListener);
        editorViewer.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) { insertBlankText(); }
        });
    }

    // 執行
    public void run(){
        //打包frame並顯示
        frame.pack();
        frame.setSize(width, height);
        frame.setVisible(true);
        loadFromEditorViewer();
    }

    //================================================== 繪製 ==================================================
    // 繪製 Glyph 內容到 editorViewer 上
    public void drawIntoEditorViewer(){
        if(uiOperateListener.getActive()) {
            Runnable doAssist = () -> {
                // 暫停監聽
                uiOperateListener.setActive(false);
                // 儲存游標狀態
                int caretPosition = editorViewer.getCaretPosition();
                // 執行Visit
                drawGlyphVisitor = new DrawGlyphVisitor(formatting);
                CountCharacterVisitor countCharacterVisitor = new CountCharacterVisitor();
                root.accept(drawGlyphVisitor);      //傳入drawGlyphVisitor處理Glyph解析
                root.accept(countCharacterVisitor); //利用countCharacterVisitor計算字數
                //觀察body
                //System.out.println(drawGlyphVisitor.getParseString());
                // 畫到 editorViewer上
                editorViewer.setText("<html><head><style>*{margin: 20px;}</style></head>" + drawGlyphVisitor.getParseString() + "</html>");
                //觀察html
                System.out.println(editorViewer.getText());
                editorViewer.setCaretPosition(Math.min(caretPosition, editorViewer.getDocument().getLength()-1));
                // 顯示目前狀態
                statusLabel.setText( countCharacterVisitor.getParagraph() + " Paragraph　" + countCharacterVisitor.getCharacter() + " Character　|　Present mode：" + formatting.getType() + "　　");
                // 重新監聽
                uiOperateListener.setActive(true);
            };
            SwingUtilities.invokeLater(doAssist);
        }
    }

    // 讀取使用者輸入的字
    public void loadFromEditorViewer(){ setEditorContent(new HTMLParser().parse(editorViewer.getText())); }


    //================================================== GETTER SETTER ==================================================
    // 設定內容到 editorViewer
    public void setEditorContent(HTML root){
        this.root = root;
        drawIntoEditorViewer();
    }
    // 設定排版
    public void setFormatting(Builder formatting){
        // 設定排版模式
        this.formatting = formatting;
        drawIntoEditorViewer();
        // 選單按鈕調整
        boolean enabled = this.formatting.getType().equals("Normal");
        fontStyleMenu.setEnabled(enabled);
        colorMenu.setEnabled(enabled);
        sizeMenu.setEnabled(enabled);
        fontMenu.setEnabled(enabled);
        alignMenu.setEnabled(enabled);
        editorViewer.setEditable(true);
    }
    // 取得排版
    public Builder getFormatting(){ return this.formatting; }
    // 取得Glyph
    public HTML getRoot() { return this.root; }
    // 啟動Dialog Window
    public void showDialog(String message, String title) {
        dialogWindow.showDialog(message, title);
    }

    public DrawGlyphVisitor getDrawGlyphVisitor(){
        return this.drawGlyphVisitor;
    }
    public void undo(String s){
        editorViewer.setText(s);
    }
    //================================================== INSERT ACTION ==================================================

    // 插入空白字元
    public void insertBlankText(){
        try {
            editorViewer.getDocument().insertString(editorViewer.getCaretPosition(),"&nbsp;", null);
            editorViewer.setCaretPosition(Math.max(0, editorViewer.getCaretPosition()-6));
        }
        catch (BadLocationException e) { e.printStackTrace(); }
    }


    // ==================================================建立 ScrollPane==================================================
    public JScrollPane createScrollPane(Component component){
        JScrollPane scrollPane = new JScrollPane();
        // 建立並設定 ScrollBar
        scrollPane.setVerticalScrollBar(gadgetFactory.createScrollbar().setOriented(JScrollBar.VERTICAL));
        scrollPane.setHorizontalScrollBar(gadgetFactory.createScrollbar().setOriented(JScrollBar.HORIZONTAL));
        // 內容放入 ScrollPane
        scrollPane.setViewportView(component);
        scrollPane.setBorder(null);
        scrollPane.setEnabled(false);
        return scrollPane;
    }

    // ==================================================建立選單列==================================================
    public MenuBar createMenuBar() {
        MenuBar bar = gadgetFactory.createMenuBar();

        // MenuBar物件宣告
        Menu fileMenu;
        MenuItem newFileMenu;
        MenuItem openFileMenu;
        MenuItem saveFileMenu;
        Menu editMenu;
        MenuItem undoEditMenu;
        MenuItem cutEditMenu;
        MenuItem copyEditMenu;
        MenuItem pasteEditMenu;

        MenuItem boldFontStyleMenu;
        MenuItem italicFontStyleMenu;
        MenuItem underlineFontStyleMenu;
        MenuItem styleoneFontStyleMenu;
        MenuItem styletwoFontStyleMenu;
        MenuItem stylethreeFontStyleMenu;

        MenuItem redForegroundColorMenu;
        MenuItem greenForegroundColorMenu;
        MenuItem blueForegroundColorMenu;
        MenuItem blackForegroundColorMenu;
        Menu formatMenu;
        MenuItem fullFormatMenu;
        MenuItem plaintextFormatMenu;
        MenuItem ProtectionModeFormatMenu;
        Menu helpMenu;
        MenuItem aboutHelpMenu;

        MenuItem size1Menu;
        MenuItem size2Menu;
        MenuItem size3Menu;



        MenuItem TimesNewRomanFontMenu;
        MenuItem ComicSansMSFontMenu;
        MenuItem BradleyHandITCFontMenu;
        MenuItem sthupoFontMenu;
        MenuItem STXingkaiFontMenu;
        MenuItem leftalignMenu;
        MenuItem rightalignMenu;
        MenuItem centeralignMenu;

        // --------------------------------------------------建立選單選項 [檔案]--------------------------------------------------
        fileMenu = gadgetFactory.createMenu();
        fileMenu.setDescription("File");
        // 建立 [檔案] 選單子選項
        // --------------------
        newFileMenu =  gadgetFactory.createMenuItem();
        newFileMenu.setDescription("New");
        newFileMenu.setActionCommand("new");
        // --------------------
        openFileMenu = gadgetFactory.createMenuItem();
        openFileMenu.setDescription("Open");
        openFileMenu.setActionCommand("open");
        // --------------------
        saveFileMenu = gadgetFactory.createMenuItem();
        saveFileMenu.setDescription("Save");
        saveFileMenu.setActionCommand("save");

        // 監聽指定選項
        newFileMenu.addActionListener(windowEventListener);
        openFileMenu.addActionListener(windowEventListener);
        saveFileMenu.addActionListener(windowEventListener);

        // 按鈕加入上級選單中
        fileMenu.addMenuItem(newFileMenu);
        fileMenu.addMenuItem(openFileMenu);
        fileMenu.addMenuItem(saveFileMenu);

        // --------------------------------------------------建立選單選項 [編輯]--------------------------------------------------
        editMenu = gadgetFactory.createMenu();
        editMenu.setDescription("Edit");
        // 建立 [編輯] 選單子選項
        // --------------------
        undoEditMenu = gadgetFactory.createMenuItem();
        undoEditMenu.setDescription("Undo");
        undoEditMenu.setActionCommand("undo");
        // --------------------
        cutEditMenu = gadgetFactory.createMenuItem();
        cutEditMenu.setDescription("Cut (ctrl+x)");
        cutEditMenu.setActionCommand("cut");
        // --------------------
        copyEditMenu = gadgetFactory.createMenuItem();
        copyEditMenu.setDescription("Copy (ctrl+c)");
        copyEditMenu.setActionCommand("copy");
        // --------------------
        pasteEditMenu = gadgetFactory.createMenuItem();
        pasteEditMenu.setDescription("Paste (ctrl+v)");
        pasteEditMenu.setActionCommand("paste");
        // --------------------


        // 監聽指定選項
        undoEditMenu.addActionListener(windowEventListener);
        cutEditMenu.addActionListener(windowEventListener);
        copyEditMenu.addActionListener(windowEventListener);
        pasteEditMenu.addActionListener(windowEventListener);


        // 按鈕加入上級選單中
        editMenu.addMenuItem(undoEditMenu);
        editMenu.addMenuItem(cutEditMenu);
        editMenu.addMenuItem(copyEditMenu);
        editMenu.addMenuItem(pasteEditMenu);

        // ----------------------------------建立字型選單-------------------------

        sizeMenu = gadgetFactory.createMenu();
        sizeMenu.setDescription("Font Size");

        size1Menu = gadgetFactory.createMenuItem();
        size1Menu.setDescription("Middle");
        size1Menu.setActionCommand("size1");
        size2Menu = gadgetFactory.createMenuItem();
        size2Menu.setDescription("Large");
        size2Menu.setActionCommand("size2");
        size3Menu = gadgetFactory.createMenuItem();
        size3Menu.setDescription("Oversized");
        size3Menu.setActionCommand("size3");


        //監聽字形加入
        size1Menu.addActionListener(windowEventListener);
        size2Menu.addActionListener(windowEventListener);
        size3Menu.addActionListener(windowEventListener);

        sizeMenu.addMenuItem(size1Menu);
        sizeMenu.addMenuItem(size2Menu);
        sizeMenu.addMenuItem(size3Menu);

        // ----------------------------------建立字型選單-------------------------

        fontMenu = gadgetFactory.createMenu();
        fontMenu.setDescription("Font");

        // --------------------
        sthupoFontMenu = gadgetFactory.createMenuItem();
        sthupoFontMenu.setDescription("STHupo");
        sthupoFontMenu.setActionCommand("STHupo");
        sthupoFontMenu.setFont(new Font("STHupo", Font.PLAIN, 14));
        // --------------------
        STXingkaiFontMenu = gadgetFactory.createMenuItem();
        STXingkaiFontMenu.setDescription("STXingkai");
        STXingkaiFontMenu.setActionCommand("STXingkai");
        STXingkaiFontMenu.setFont(new Font("STXingkai", Font.PLAIN, 14));
        // --------------------
        TimesNewRomanFontMenu = gadgetFactory.createMenuItem();
        TimesNewRomanFontMenu.setDescription("Times New Roman");
        TimesNewRomanFontMenu.setActionCommand("Times New Roman");
        TimesNewRomanFontMenu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        // --------------------
        ComicSansMSFontMenu = gadgetFactory.createMenuItem();
        ComicSansMSFontMenu.setDescription("Comic Sans MS");
        ComicSansMSFontMenu.setActionCommand("Comic Sans MS");
        ComicSansMSFontMenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        // --------------------
        BradleyHandITCFontMenu = gadgetFactory.createMenuItem();
        BradleyHandITCFontMenu.setDescription("Bradley Hand ITC");
        BradleyHandITCFontMenu.setActionCommand("Bradley Hand ITC");
        BradleyHandITCFontMenu.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 14));


        // 監聽指定選項
        sthupoFontMenu.addActionListener(windowEventListener);
        STXingkaiFontMenu.addActionListener(windowEventListener);
        TimesNewRomanFontMenu.addActionListener(windowEventListener);
        ComicSansMSFontMenu.addActionListener(windowEventListener);
        BradleyHandITCFontMenu.addActionListener(windowEventListener);
        // 按鈕加入上級選單中
        fontMenu.addMenuItem(TimesNewRomanFontMenu);
        fontMenu.addMenuItem(ComicSansMSFontMenu);
        fontMenu.addMenuItem(BradleyHandITCFontMenu);
        fontMenu.addMenuItem(sthupoFontMenu);
        fontMenu.addMenuItem(STXingkaiFontMenu);
        // --------------------------------------------------建立選單選項 [樣式]--------------------------------------------------
        fontStyleMenu = gadgetFactory.createMenu();
        fontStyleMenu.setDescription("Style");
        // 建立 [樣式] 選單子選項
        // --------------------
        boldFontStyleMenu = gadgetFactory.createMenuItem();
        boldFontStyleMenu.setDescription("Bold");
        boldFontStyleMenu.setActionCommand("Bold");
        boldFontStyleMenu.setFont(new Font(boldFontStyleMenu.getFont().getFontName(), Font.BOLD, 16));
        // --------------------
        italicFontStyleMenu = gadgetFactory.createMenuItem();
        italicFontStyleMenu.setDescription("Italic");
        italicFontStyleMenu.setActionCommand("Italic");
        italicFontStyleMenu.setFont(new Font(italicFontStyleMenu.getFont().getFontName(), Font.ITALIC, 16));
        // --------------------
        underlineFontStyleMenu = gadgetFactory.createMenuItem();
        underlineFontStyleMenu.setDescription("Underline");
        underlineFontStyleMenu.setActionCommand("Underline");
        Map attributes = underlineFontStyleMenu.getFont().getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        underlineFontStyleMenu.setFont(underlineFontStyleMenu.getFont().deriveFont(attributes));
        // --------------------
        styleoneFontStyleMenu = gadgetFactory.createMenuItem();
        styleoneFontStyleMenu.setDescription("Style 1");
        styleoneFontStyleMenu.setActionCommand("styleone");
        styleoneFontStyleMenu.setFont(new Font("Bradley Hand ITC", Font.BOLD, 24));
        styleoneFontStyleMenu.setForeground(Color.RED);
        // --------------------
        styletwoFontStyleMenu = gadgetFactory.createMenuItem();
        styletwoFontStyleMenu.setDescription("Style 2");
        styletwoFontStyleMenu.setActionCommand("styletwo");
        styletwoFontStyleMenu.setFont(new Font("STXingkai", Font.ITALIC, 24));
        styletwoFontStyleMenu.setForeground(Color.BLUE);
        // --------------------
        stylethreeFontStyleMenu = gadgetFactory.createMenuItem();
        stylethreeFontStyleMenu.setDescription("Style 3");
        stylethreeFontStyleMenu.setActionCommand("stylethree");
        stylethreeFontStyleMenu.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        stylethreeFontStyleMenu.setForeground(Color.GREEN);

        // 監聽指定選項
        boldFontStyleMenu.addActionListener(windowEventListener);
        italicFontStyleMenu.addActionListener(windowEventListener);
        underlineFontStyleMenu.addActionListener(windowEventListener);
        styleoneFontStyleMenu.addActionListener(windowEventListener);
        styletwoFontStyleMenu.addActionListener(windowEventListener);
        stylethreeFontStyleMenu.addActionListener(windowEventListener);

        // 按鈕加入上級選單中
        fontStyleMenu.addMenuItem(boldFontStyleMenu);
        fontStyleMenu.addMenuItem(italicFontStyleMenu);
        fontStyleMenu.addMenuItem(underlineFontStyleMenu);
        fontStyleMenu.addMenuItem(styleoneFontStyleMenu);
        fontStyleMenu.addMenuItem(styletwoFontStyleMenu);
        fontStyleMenu.addMenuItem(stylethreeFontStyleMenu);

        // ----------------------------------建立對齊選單-------------------------

        alignMenu = gadgetFactory.createMenu();
        alignMenu.setDescription("Align");

        leftalignMenu = gadgetFactory.createMenuItem();
        leftalignMenu.setDescription("Left");
        leftalignMenu.setActionCommand("left");
        rightalignMenu = gadgetFactory.createMenuItem();
        rightalignMenu.setDescription("Right");
        rightalignMenu.setActionCommand("right");
        centeralignMenu = gadgetFactory.createMenuItem();
        centeralignMenu.setDescription("Center");
        centeralignMenu.setActionCommand("center");


        //監聽字形加入
        leftalignMenu.addActionListener(windowEventListener);
        rightalignMenu.addActionListener(windowEventListener);
        centeralignMenu.addActionListener(windowEventListener);

        alignMenu.addMenuItem(leftalignMenu);
        alignMenu.addMenuItem(centeralignMenu);
        alignMenu.addMenuItem(rightalignMenu);

        // --------------------------------------------------建立選單選項 [色彩]--------------------------------------------------
        colorMenu = gadgetFactory.createMenu();
        colorMenu.setDescription("Color");
        // 建立 [色彩] 選單子選項
        // --------------------
        redForegroundColorMenu = gadgetFactory.createMenuItem();
        redForegroundColorMenu.setDescription("Red");
        redForegroundColorMenu.setActionCommand("FG-Red");
        redForegroundColorMenu.setForeground(Color.RED);
        // --------------------
        greenForegroundColorMenu = gadgetFactory.createMenuItem();
        greenForegroundColorMenu.setDescription("Green");
        greenForegroundColorMenu.setActionCommand("FG-Green");
        greenForegroundColorMenu.setForeground(Color.GREEN);
        // --------------------
        blueForegroundColorMenu = gadgetFactory.createMenuItem();
        blueForegroundColorMenu.setDescription("Blue");
        blueForegroundColorMenu.setActionCommand("FG-Blue");
        blueForegroundColorMenu.setForeground(Color.BLUE);
        // --------------------

        // --------------------
        blackForegroundColorMenu = gadgetFactory.createMenuItem();
        blackForegroundColorMenu.setDescription("Black");
        blackForegroundColorMenu.setActionCommand("FG-Black");

        // 監聽指定選項
        redForegroundColorMenu.addActionListener(windowEventListener);
        greenForegroundColorMenu.addActionListener(windowEventListener);
        blueForegroundColorMenu.addActionListener(windowEventListener);

        blackForegroundColorMenu.addActionListener(windowEventListener);

        // 按鈕加入上級選單中
        colorMenu.addMenuItem(redForegroundColorMenu);
        colorMenu.addMenuItem(greenForegroundColorMenu);
        colorMenu.addMenuItem(blueForegroundColorMenu);
        colorMenu.addMenuItem(blackForegroundColorMenu);

        // --------------------------------------------------建立選單選項 [排版]--------------------------------------------------
        formatMenu = gadgetFactory.createMenu();
        formatMenu.setDescription("Present");
        // 建立 [排版] 選單子選項
        // --------------------
        fullFormatMenu = gadgetFactory.createMenuItem();
        fullFormatMenu.setDescription("Normal");
        fullFormatMenu.setActionCommand("Normal");
        // --------------------
        plaintextFormatMenu = gadgetFactory.createMenuItem();
        plaintextFormatMenu.setDescription("Plain text");
        plaintextFormatMenu.setActionCommand("TextOnly");
        // --------------------
        ProtectionModeFormatMenu = gadgetFactory.createMenuItem();
        ProtectionModeFormatMenu.setDescription("View");
        ProtectionModeFormatMenu.setActionCommand("ViewOnly");
        // 監聽指定選項
        fullFormatMenu.addActionListener(windowEventListener);
        plaintextFormatMenu.addActionListener(windowEventListener);
        ProtectionModeFormatMenu.addActionListener(windowEventListener);
        // 按鈕加入上級選單中
        formatMenu.addMenuItem(fullFormatMenu);
        formatMenu.addMenuItem(plaintextFormatMenu);
        formatMenu.addMenuItem(ProtectionModeFormatMenu);
        // --------------------------------------------------建立選單選項 [幫助]--------------------------------------------------
        helpMenu = gadgetFactory.createMenu();
        helpMenu.setDescription("Help");
        // 建立 [幫助] 選單子選項
        // --------------------
        aboutHelpMenu = gadgetFactory.createMenuItem();
        aboutHelpMenu.setDescription("About");
        aboutHelpMenu.setActionCommand("about");

        // 監聽指定選項
        aboutHelpMenu.addActionListener(windowEventListener);

        // 按鈕加入上級選單中
        helpMenu.addMenuItem(aboutHelpMenu);

        // --------------------------------------------------按鈕加入上級選單中--------------------------------------------------
        bar.addMenu(fileMenu);
        bar.addMenu(editMenu);
        bar.addMenu(fontMenu);
        bar.addMenu(sizeMenu);
        bar.addMenu(alignMenu);
        bar.addMenu(fontStyleMenu);
        bar.addMenu(colorMenu);
        bar.addMenu(formatMenu);
        bar.addMenu(helpMenu);
        return bar;
    }
}
