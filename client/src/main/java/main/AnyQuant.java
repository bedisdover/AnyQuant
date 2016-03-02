package main;

import presentation.MainFrame;
import presentation.MenuPanel;

import javax.swing.*;

/**
 * Created by song on 16-3-1.
 * <p>
 * 程序主方法
 */
public class AnyQuant {

    public static void main(String[] args) {
        JFrame mainFrame = MainFrame.getMainFrame();
        mainFrame.setContentPane(new MenuPanel());
        mainFrame.setVisible(true);
    }
}
