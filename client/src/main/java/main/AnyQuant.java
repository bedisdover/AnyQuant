package main;

import javafx.application.Application;
import javafx.stage.Stage;
import presentation.frame.MainFrame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * Created by song on 16-3-1.
 * <p>
 * 程序主方法
 */
public class AnyQuant extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JFrame mainFrame = MainFrame.getMainFrame();
        mainFrame.setVisible(true);
    }

    public static void initGlobalFontSetting(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }
}
