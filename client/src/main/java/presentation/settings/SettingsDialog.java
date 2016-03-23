package presentation.settings;

import config.SystemConfig;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.*;
import java.awt.event.*;

import static presentation.frame.MainFrame.getMainFrame;

/**
 * Created by 宋益明 on 16-3-7.
 * <p>
 * 设置窗体,拥有者为MainFrame
 */
public class SettingsDialog extends JDialog {

    /**
     * Create the dialog.
     */
    public SettingsDialog() {
        super(getMainFrame(), true);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(450, 340);
        setLocationRelativeTo(getMainFrame());
        setResizable(false);

        SettingsPanel panel = new SettingsPanel();
        setContentPane(panel);
    }

    /**
     * 设置窗体主面板
     */
    class SettingsPanel extends JPanel {
        private final int width;
        private final int height;

        /**
         * 界面设置按钮
         */
        private JButton btnUI;

        /**
         * 字体设置按钮
         */
        private JButton btnFont;

        /**
         * 确认按钮
         */
        private JButton btnOK;

        /**
         * 应用按钮
         */
        private JButton btnApply;

        /**
         * 取消按钮
         */
        private JButton btnCancel;

        /**
         * 具体的设置面板
         */
        private JPanel settings;

        SettingsPanel() {
            width = 450;
            height = 340;

            init();
            createUIComponents();
            addListeners();
        }

        /**
         * 初始化
         */
        private void init() {
            setLayout(null);
        }

        /**
         * 创建组件
         */
        private void createUIComponents() {
            try {
                UIManager.setLookAndFeel(new NimbusLookAndFeel());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            settings = new UISettings();

            addMenuPanel();
            addButtonPanel();
        }

        /**
         * 添加菜单面板
         */
        private void addMenuPanel() {
            JPanel menuPanel = new JPanel();
            menuPanel.setBackground(Color.GRAY);
            menuPanel.setBounds(0, 0, width / 5, height);
            //设置盒式布局,每个按钮一行
            menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
            //设置边界
            menuPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            add(menuPanel);

            btnUI = new JButton("界面设置");
            menuPanel.add(btnUI);

            btnFont = new JButton("字体设置");
            menuPanel.add(btnFont);
        }

        /**
         * 添加具体的设置面板
         */
        private void addSettingsPanel(JPanel panel) {
            remove(settings);
            settings = panel;
            settings.setBounds(width / 5, 0, width - getX(), height * 4 / 5);

            add(settings);
            repaint();
        }

        /**
         * 添加容纳确认,应用和取消按钮的面板
         */
        private void addButtonPanel() {
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.setBackground(Color.lightGray);
            buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            buttonPanel.setBounds(width / 5, height * 4 / 5, width * 4 / 5, height / 5);

            add(buttonPanel);

            btnOK = new JButton("确 认");
            btnApply = new JButton("应 用");
            btnCancel = new JButton("取 消");

            buttonPanel.add(btnOK);
            buttonPanel.add(btnApply);
            buttonPanel.add(btnCancel);
        }

        /**
         * 添加监听事件
         */
        private void addListeners() {
            btnOK.addActionListener(e -> onOk());

            btnApply.addActionListener(e -> onApply());

            btnCancel.addActionListener(e -> onCancel());

            btnUI.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addSettingsPanel(new UISettings());
                }
            });

            btnFont.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addSettingsPanel(new FontSettings());
                }
            });

            registerKeyboardAction(e -> onCancel(),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                    JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        }

        /**
         * 确认按钮按下的操作
         */
        private void onOk() {
            onApply();

            dispose();
        }

        /**
         * 应用按钮按下的操作
         */
        private void onApply() {
            SystemConfig.storeXML();
        }

        /**
         * 取消按钮按下的操作
         */
        private void onCancel() {
            dispose();
        }
    }
}
