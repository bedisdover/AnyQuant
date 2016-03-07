package presentation.settings;

/**
 * Created by 宋益明 on 16-3-7.
 * <p>
 * 界面设置面板
 */

import presentation.util.ExampleFileFilter;
import presentation.util.ImageLoader;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static presentation.frame.MainFrame.getMainFrame;

public class UISettings extends JPanel {
    private JButton btnCustom;
    private JSlider transparency;
    private JComboBox<String> menuBarLocation;
    private boolean autoHide;

    UISettings() {
        init();
        createUIComponents();
        addListener();
    }

    private void init() {
        setLayout(null);
        setBorder(new BevelBorder(0));

        autoHide = false;
    }

    private void createUIComponents() {
        JLabel labelBackground = new JLabel("背景图片");
        labelBackground.setFont(Settings.font);
        labelBackground.setBounds(45, 37, 72, 15);
        add(labelBackground);

        btnCustom = new JButton("自定义");
        btnCustom.setBounds(226, 34, 93, 23);
        add(btnCustom);

        JLabel labelTransparency = new JLabel("背景透明度");
        labelTransparency.setFont(Settings.font);
        labelTransparency.setBounds(45, 88, 72, 15);
        add(labelTransparency);

        transparency = new JSlider();
        transparency.setBounds(219, 80, 100, 23);
        add(transparency);

        JLabel labelLocation = new JLabel("菜单栏位置");
        labelLocation.setFont(Settings.font);
        labelLocation.setBounds(45, 139, 72, 15);
        add(labelLocation);

        menuBarLocation = new JComboBox<String>();
        menuBarLocation.setModel(new DefaultComboBoxModel<String>(new String[]{"左侧", "右侧", "底部", "顶部"}));
        menuBarLocation.setBounds(219, 134, 100, 28);
        add(menuBarLocation);

        JLabel labelAutoHide = new JLabel("自动隐藏菜单栏");
        labelAutoHide.setFont(Settings.font);
        labelAutoHide.setBounds(45, 195, 114, 15);
        add(labelAutoHide);
    }

    private void addListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > 240 && e.getX() < 320 && e.getY() > 190 && e.getY() < 218) {
                    autoHide = !autoHide;
                    repaint();
                }
            }
        });

        btnCustom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chooseImage();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (autoHide) {
            g.drawImage(ImageLoader.on, 240, 190, 80, 28, null);
        } else {
            g.drawImage(ImageLoader.off, 240, 190, 80, 28, null);
        }
    }

    /**
     * 选择原始图片
     */
    private void chooseImage() {
        try {
            //修改文件选择器风格
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //文件选择器
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("请选择图像文件...");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        ExampleFileFilter filter = new ExampleFileFilter();
        filter.addExtension("jpg");
        filter.addExtension("gif");
        filter.addExtension("png");
        filter.setDescription("JPG & GIF & PNG Images");
        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(getMainFrame());

        if (result == JFileChooser.APPROVE_OPTION) {

        }
    }
}

