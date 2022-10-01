package Memento;

import Bridge_UI.WholeWindow;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UiOperateListener implements DocumentListener {
    private boolean active = true;
    private WholeWindow mainWindow;

    public UiOperateListener(WholeWindow mainWindow){
        this.mainWindow = mainWindow;
    }
    @Override
    public void insertUpdate(DocumentEvent e) { if(active) mainWindow.loadFromEditorViewer(); }
    @Override
    public void removeUpdate(DocumentEvent e) { if(active) mainWindow.loadFromEditorViewer(); }
    @Override
    public void changedUpdate(DocumentEvent e) { if(active) mainWindow.loadFromEditorViewer(); }

    public void setActive(boolean active) { this.active = active; }
    public boolean getActive() { return this.active; }
}
