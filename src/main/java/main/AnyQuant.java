//package main;
//
//import config.SystemConfig;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import presentation.frame.MainFrame;
//
//import javax.swing.*;
//import javax.swing.plaf.FontUIResource;
//import javax.swing.plaf.nimbus.NimbusLookAndFeel;
//import java.awt.*;
//import java.util.Enumeration;
//
///**
// * Created by 宋益明 on 16-3-1.
// * <p>
// * 程序主方法
// */
//public class AnyQuant extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //设置全局字体
////        initGlobalFontSetting(SystemConfig.getFontConfig().getFontInfo());
//                    if (SystemConfig.getStyle().equals("Nimbus")) {
//                        UIManager.setLookAndFeel(new NimbusLookAndFeel());
//                    } else if (SystemConfig.getStyle().equals("BeautyEye")) {
////                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                JFrame mainFrame = MainFrame.getMainFrame();
//                mainFrame.setVisible(true);
//            }
//        });
//    }
//
//    public static void initGlobalFontSetting(Font font) {
//        FontUIResource fontRes = new FontUIResource(font);
//        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
//            Object key = keys.nextElement();
//            Object value = UIManager.get(key);
//            if (value instanceof FontUIResource) {
//                UIManager.put(key, fontRes);
//            }
//        }
//    }
//}
