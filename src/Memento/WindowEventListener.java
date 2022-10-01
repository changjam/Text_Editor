package Memento;

import Command.*;
import builder.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import CompositeAndDecorator.HTML;
import CompositeAndDecorator.Paragraph;
import CompositeAndDecorator.Body;
import Bridge_UI.WholeWindow;
import utils.OOSEFILEParser;
import visitor.DrawGlyphVisitor;
import visitor.GlyphToOOSEFILEVisitor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import Strategy.*;
import FontStyleFlyweight.*;

public class WindowEventListener implements ActionListener{
    WholeWindow mainWindow;
    DrawGlyphVisitor drawGlyphVisitor;
    HTML root;
    Memento state;
    Command command;
    public WindowEventListener(WholeWindow mainWindow){
        this.mainWindow = mainWindow;
        this.state = new Memento();
    }

    public void undo(){
        String previous = state.getState();
        mainWindow.undo(previous);
    }
    public void cut(){
        state.StoreState(drawGlyphVisitor.getParseString());
        command = new CutCommand();
        command.execute();
    }
    public void copy(){
        command = new CopyCommand();
        command.execute();
    }
    public void paste(){
        state.StoreState(drawGlyphVisitor.getParseString());
        command = new PasteCommand();
        command.execute();
    }
    public void RedColor(){
        state.StoreState(drawGlyphVisitor.getParseString());
        command = new RedCommand();
        command.execute();
    }
    public void GreenColor(){
        state.StoreState(drawGlyphVisitor.getParseString());
        command = new GreenCommand();
        command.execute();
    }
    public void BlueColor(){
        state.StoreState(drawGlyphVisitor.getParseString());
        command = new BlueCommand();
        command.execute();
    }
    public void BlackColor(){
        state.StoreState(drawGlyphVisitor.getParseString());
        command = new BlackCommand();
        command.execute();
    }
    public void smallSize(){
        state.StoreState(drawGlyphVisitor.getParseString());
        new TextSize(16).actionPerformed(null);
    }
    public void midSize(){
        state.StoreState(drawGlyphVisitor.getParseString());
        new TextSize(24).actionPerformed(null);
    }
    public void bigSize(){
        state.StoreState(drawGlyphVisitor.getParseString());
        new TextSize(30).actionPerformed(null);
    }
    public void Style(int s){ //樣式(樣式1、樣式2、樣式3、粗體、斜體、底線)
        state.StoreState(drawGlyphVisitor.getParseString());
        FontStyleFlyweightFactory.getFontStyle(s);
    }

    private static Builder norBuilder = new NormalBuild();

    public void FontFamily(String X){
        FontContext context=null;
        switch (X){
            case "Times New Roman":
                context = new FontContext(new TimesNewRoman());
                break;
            case "STHupo":
                context = new FontContext(new STHupo());
                break;
            case "Comic Sans MS":
                context = new FontContext(new ComicSansMS());
                break;
            case "Bradley Hand ITC":
                context = new FontContext(new BradleyHandITC());
                break;
            case "STXingkai":
                context = new FontContext(new STXingkai());
                break;
        }
        context.runStrategy(X);
    }

    public void LeftAlign(){
        state.StoreState(drawGlyphVisitor.getParseString());
        new StyledEditorKit.AlignmentAction("left",StyleConstants.ALIGN_LEFT).actionPerformed(null);
    }
    public void RightAlign(){
        state.StoreState(drawGlyphVisitor.getParseString());
        new StyledEditorKit.AlignmentAction("right",StyleConstants.ALIGN_RIGHT).actionPerformed(null);
    }
    public void CenterAlign(){
        state.StoreState(drawGlyphVisitor.getParseString());
        new StyledEditorKit.AlignmentAction("center",StyleConstants.ALIGN_CENTER).actionPerformed(null);
    }
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        this.root = mainWindow.getRoot();
        this.drawGlyphVisitor = mainWindow.getDrawGlyphVisitor();

        switch (s) {
            case "undo":
                undo();
                break;
            case "cut":
                cut();
                break;
            case "copy":
                copy();
                break;
            case "paste":
                paste();
                break;
            case "STHupo":
                FontFamily("STHupo");
                break;
            case "Times New Roman":
                FontFamily("Times New Roman");
                break;
            case "Comic Sans MS":
                FontFamily("Comic Sans MS");
                break;
            case "Bradley Hand ITC":
                FontFamily("Bradley Hand ITC");
                break;
            case "STXingkai":
                FontFamily("STXingkai");
                break;
            case "styleone":
                Style(1);
                break;
            case "styletwo":
                Style(2);
                break;
            case "stylethree":
                Style(3);
                break;
            case "Bold":
                Style(4);
                break;
            case "Italic":
                Style(5);
                break;
            case "Underline":
                Style(6);
                break;
            case "left":
                LeftAlign();
                break;
            case "right":
                RightAlign();
                break;
            case "center":
                CenterAlign();
                break;
            case "FG-Red":
                RedColor();
                break;
            case "FG-Green":
                GreenColor();
                break;
            case "FG-Blue":
                BlueColor();
                break;
            case "FG-Black":
                BlackColor();
                break;
            case "size1":
                smallSize();
                break;
            case "size2":
                midSize();
                break;
            case "size3":
                bigSize();
                break;
            case "new":
                root = new Body();
                root.insert(new Paragraph());
                mainWindow.setEditorContent(root);
                break;
            case "open": {
                // 建立檔案選擇器
                JFileChooser jFileChooser = new JFileChooser("f:");
                jFileChooser.setFileFilter(new FileNameExtensionFilter("text", new String[] {"text"}));
                setFileChooserText("Open", "Open");
                SwingUtilities.updateComponentTreeUI(jFileChooser);
                // 若使用者選擇檔案
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    // 將絕對路徑設到已選的目錄
                    File file = new File(jFileChooser.getSelectedFile().getAbsolutePath().replaceAll("\\.oosefile", "") + ".text");

                    try {
                        // 建立 file reader 及 buffered reader
                        InputStreamReader fileReader = new InputStreamReader (new FileInputStream(file), StandardCharsets.UTF_8);    //FileReader fileReader = new FileReader(file);
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        // 一行一行讀入
                        String opening_oosefile = "";
                        String readline = "";
                        opening_oosefile = bufferedReader.readLine();
                        while ((readline = bufferedReader.readLine()) != null)
                            opening_oosefile = opening_oosefile + "\n" + readline;

                        // 設定到editorViewer
                        root = new OOSEFILEParser().parse(opening_oosefile);
                        mainWindow.setEditorContent(root);
                        // 設定排版
                        Map<String, Object> document = new Gson().fromJson(opening_oosefile, new TypeToken<Map<String, Object>>(){}.getType());
                        if(document.get("format").equals("Normal")) mainWindow.setFormatting(new NormalBuild());
                        else  mainWindow.setFormatting(new TextOnlyBuild());
                    } catch (Exception evt) {
                        System.out.println(evt.getMessage());
                        mainWindow.showDialog(evt.getMessage(), "Error");
                    }

                }
                break;

            }
            case "save": {
                // 建立檔案選擇器
                JFileChooser jFileChooser = new JFileChooser("f:");
                jFileChooser.setFileFilter(new FileNameExtensionFilter("text", new String[] {"text"}));
                setFileChooserText("Save", "Save");
                SwingUtilities.updateComponentTreeUI(jFileChooser);
                // 若使用者選擇檔案
                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    // 將絕對路徑設到已選的目錄
                    File file = new File(jFileChooser.getSelectedFile().getAbsolutePath().replaceAll("\\.oosefile", "") + ".text");

                    try {
                        // 建立 file writer 及 buffered writer
                        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);    //FileWriter fileWriter = new FileWriter(file, false);
                        BufferedWriter writer = new BufferedWriter(fileWriter);
                        // 寫入資料
                        GlyphToOOSEFILEVisitor glyphToOOSEFILEVisitor = new GlyphToOOSEFILEVisitor();
                        root.accept(glyphToOOSEFILEVisitor);
                        String saving_oosefile = "{\"format\": \"" + mainWindow.getFormatting().getType() + "\"," +
                                "\"content\": " + glyphToOOSEFILEVisitor.getParseString() + "}";
                        writer.write(saving_oosefile);
                        writer.flush();
                        writer.close();
                    } catch (Exception evt) {
                        evt.printStackTrace();
                        mainWindow.showDialog(evt.getMessage(), "Error");
                    }
                }
                break;
            }

            //----------------------------------------排版----------------------------------------
            //按下的按鈕 為 "Normal"
            case "Normal":
                StyleDirector director = new StyleDirector(norBuilder);
                director.build();
                mainWindow.setFormatting(new NormalBuild());
                break;
            //按下的按鈕 為 "TextOnly"
            case "TextOnly":
                Builder textBuilder = new TextOnlyBuild();
                StyleDirector director1 = new StyleDirector(textBuilder);
                director1.build();
                mainWindow.setFormatting(new TextOnlyBuild());
                break;
            case "ViewOnly":
                Builder ProtectBuilder = new ProtectionBuild();
                StyleDirector director2 = new StyleDirector(ProtectBuilder);
                director2.build();
                mainWindow.setFormatting(new ProtectionBuild());
                mainWindow.editorViewer.setEditable(false);
                break;

            //----------------------------------------幫助----------------------------------------
            case "about":
                mainWindow.showDialog("Hello!! We are group 4. This is OO TW1.", "About");
                break;
        }
    }

    public void setFileChooserText(String openDialogTitleText, String openButton){
        UIManager.put("FileChooser.openDialogTitleText", openDialogTitleText);
        UIManager.put("FileChooser.lookInLabelText", "Current");
        UIManager.put("FileChooser.openButtonText", openButton);
        UIManager.put("FileChooser.cancelButtonText", "Cancle");
        UIManager.put("FileChooser.fileNameLabelText", "File Name");
        UIManager.put("FileChooser.filesOfTypeLabelText", "File Type");
        UIManager.put("FileChooser.openButtonToolTipText", openButton+"Specified file");
        UIManager.put("FileChooser.cancelButtonToolTipText","Cancle");
    }


    public static class ForegroundColor extends StyledEditorKit.StyledTextAction {
        private Color fg;

        public ForegroundColor(String nm, Color fg) {
            super(nm);
            this.fg = fg;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            JEditorPane editor = getEditor(ae);
            if (editor == null) return;
            // 設定空的attribute
            MutableAttributeSet attr = new SimpleAttributeSet();
            // 傳送變換背景色的指令給editor
            if (this.fg != null) StyleConstants.setForeground(attr, this.fg);
            setCharacterAttributes(editor, attr, false);
        }
    }

    public class TextSize extends StyledEditorKit.StyledTextAction {

        private int size;

        public TextSize(int get_size) {
            super("size");
            size = get_size;
        }

        public void actionPerformed(ActionEvent ae) {
            MutableAttributeSet attr = new SimpleAttributeSet();
            JEditorPane editor = getEditor(ae);
            StyleConstants.setFontSize(attr, size);
            setCharacterAttributes(editor, attr, false);
        }
    }
}
