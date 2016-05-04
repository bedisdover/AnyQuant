package presentation;

import presentation.frame.MainFrame;
import presentation.util.DateChooser;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 * Created by pc on 2016/4/14.
 */
public class DateChooserTest extends JPanel {
    private DateChooser dc;
    private JButton button;

    public DateChooserTest() {
        dc = new DateChooser(this, 100, 200);
        button = new JButton("生成");
        button.setBounds(0,0,30,30);
        this.add(button);
        dc.setTime("2016-02-04");



    }


    private void addListener() {

        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

            }
        });

    }
    public static void main(String[] args){
        JFrame jFrame=new JFrame();
        DateChooserTest panel=new DateChooserTest();
        jFrame.add(panel);
        jFrame.setBounds(50, 50, 1024, 768);
        jFrame.setVisible(true);
    }


}