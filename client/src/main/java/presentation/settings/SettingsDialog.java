package presentation.settings;

import presentation.frame.MainFrame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class SettingsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
    private JButton custom;
	private JSlider transparency;
	private JComboBox<String> menuBarLocation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SettingsDialog dialog = new SettingsDialog();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SettingsDialog() {
        super(MainFrame.getMainFrame(), true);

        init();
        createUIComponents();

	}

    /**
     * 初始化
     */
    private void init() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(new Dimension(400, 300));
        setLocationRelativeTo(MainFrame.getMainFrame());
        setBounds(100, 100, 450, 300);
        setResizable(false);
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

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.GRAY);
        menuPanel.setBounds(0, 0, 100, 267);
        menuPanel.setLayout(null);
        //TODO 设置边界
        menuPanel.setBorder(new BevelBorder(0));
        add(menuPanel);

        JButton button = new JButton("界 面");
        button.setBounds(10, 33, 83, 23);
        menuPanel.add(button);
        {
            JLabel labelBackground = new JLabel("背景图片");
            labelBackground.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            labelBackground.setBounds(145, 37, 72, 15);
            contentPanel.add(labelBackground);
        }
        {
            custom = new JButton("自定义");
            custom.setBounds(326, 34, 93, 23);
            contentPanel.add(custom);
        }
        {
            JLabel labelTransparency = new JLabel("背景透明度");
            labelTransparency.setFont(new Font("微软雅黑", Font.PLAIN, 14));
            labelTransparency.setBounds(145, 88, 72, 15);
            contentPanel.add(labelTransparency);
        }

        transparency = new JSlider();
        transparency.setBounds(319, 80, 100, 23);
        contentPanel.add(transparency);

        JLabel labelLocation = new JLabel("菜单栏位置");
        labelLocation.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        labelLocation.setBounds(145, 139, 72, 15);
        contentPanel.add(labelLocation);

        menuBarLocation = new JComboBox<String>();
        menuBarLocation.setModel(new DefaultComboBoxModel<String>(new String[]{"左侧", "右侧", "底部", "顶部"}));
        menuBarLocation.setBounds(319, 134, 100, 28);
        contentPanel.add(menuBarLocation);

        JLabel labelAutoHide = new JLabel("自动隐藏菜单栏");
        labelAutoHide.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        labelAutoHide.setBounds(145, 195, 114, 15);
        contentPanel.add(labelAutoHide);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonPane.setBackground(Color.lightGray);
            buttonPane.setBorder(new BevelBorder(0));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton btnOK = new JButton("确 认");
                btnOK.setActionCommand("OK");
                buttonPane.add(btnOK);
            }
            {
                JButton btnApply = new JButton("应 用");
                buttonPane.add(btnApply);
            }
            {
                JButton btnCancel = new JButton("取 消");
                btnCancel.setActionCommand("Cancel");
                buttonPane.add(btnCancel);
            }
        }
    }

    /**
     * 添加监听事件
     */
    private void addListeners() {

    }
}
