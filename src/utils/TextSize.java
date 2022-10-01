package utils;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.event.ActionEvent;

import javax.swing.text.MutableAttributeSet;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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