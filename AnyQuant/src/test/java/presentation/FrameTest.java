package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 宋益明 on 16-3-12.
 */
public class FrameTest extends JFrame {
    private JButton btn1, btn2, btn3, btn4, btn5;
    private JPanel pNorth, pSouth, subMenuContainer;
    private JScrollPane pCenter;

    private JButton[] btn = null;
    private static boolean expand = false;

    public FrameTest() {
        btn1 = new JButton("Grade1 menu1");
        btn1.setBackground(Color.CYAN);
        btn2 = new JButton("Grade1 menu2");
        btn2.setBackground(Color.CYAN);
        btn3 = new JButton("Grade1 menu3");
        btn3.setBackground(Color.CYAN);
        btn3.addActionListener(new ActionHandler());

        btn4 = new JButton("Grade1 menu4");
        btn4.setBackground(Color.CYAN);
        btn5 = new JButton("Grade1 menu5");
        btn5.setBackground(Color.CYAN);
        pNorth = new JPanel();
        pNorth.setLayout(new GridLayout(3, 1));
        pSouth = new JPanel();
        pSouth.setLayout(new GridLayout(2, 1));
        subMenuContainer = new JPanel();
        subMenuContainer.setLayout(new GridLayout(25, 1));

        btn = new JButton[25];
        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton("[菜单" + i + "]");
            btn[i].setBackground(Color.WHITE);
        }

        this.setLayout(new BorderLayout());

        pNorth.add(btn1);
        pNorth.add(btn2);
        pNorth.add(btn3);
        for (int i = 0; i < btn.length; i++) {
            subMenuContainer.add(btn[i]);
        }
        pCenter = new JScrollPane(subMenuContainer);

        pSouth.add(btn4);
        pSouth.add(btn5);
        this.add(pNorth, "North");
        this.add(pCenter, "Center");
        this.add(pSouth, "South");

        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (btn3 == e.getSource()) {
                if (expand) {//折叠
                    pNorth.setLayout(new GridLayout(3, 1));
                    pNorth.remove(btn4);
                    pNorth.remove(btn5);
                    pSouth.add(btn4);
                    pSouth.add(btn5);
                    for (int i = 0; i < btn.length; i++) {
                        subMenuContainer.add(btn[i]);
                    }
                    validate();
                    getContentPane().repaint();
                    expand = false;
                } else {//展开
                    for (int i = 0; i < btn.length; i++) {
                        subMenuContainer.remove(btn[i]);
                    }
                    pSouth.removeAll();
                    pNorth.setLayout(new GridLayout(5, 1));
                    pNorth.add(btn4);
                    pNorth.add(btn5);
                    pNorth.repaint();
                    pCenter.repaint();
                    pSouth.repaint();
                    validate();
                    getContentPane().repaint();
                    expand = true;
                }
            }
        }

    }

    public static void main(String[] args) {
        new FrameTest();
    }
}