package Bridge_UI;

import javax.swing.*;

public class DialogWindow extends WindowAbstract {
    private JFrame frame;
    public DialogWindow(WindowInterface impl) {
        super(impl);
        frame = super.drawFrame();
    }
    public void showDialog(String message, String title){
        JOptionPane.showMessageDialog(frame, message,title, JOptionPane.INFORMATION_MESSAGE);
    }
}