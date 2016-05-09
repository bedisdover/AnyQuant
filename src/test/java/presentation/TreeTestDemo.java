package presentation;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-12.
 *
 * 测试JTree实现折叠效果
 */
public class TreeTestDemo extends JPanel {

    public static void main(String[] args) {
        MutableTreeNode root = new DefaultMutableTreeNode("QQ好友");
        MutableTreeNode aNode = new DefaultMutableTreeNode("我的好友");
        MutableTreeNode bNode = new DefaultMutableTreeNode("陌生人");
        MutableTreeNode cNode = new DefaultMutableTreeNode("黑名单");

        root.insert(aNode, 0);
        root.insert(bNode, 1);
        root.insert(cNode, 2);
        aNode.insert(new DefaultMutableTreeNode("friend_a"), 0);
        aNode.insert(new DefaultMutableTreeNode("friend_b"), 1);
        bNode.insert(new DefaultMutableTreeNode("stranger_a"), 0);
        bNode.insert(new DefaultMutableTreeNode("stranger_b"), 1);
        cNode.insert(new DefaultMutableTreeNode("black_a"), 0);
        cNode.insert(new DefaultMutableTreeNode("black_b"), 1);

        DefaultTreeModel model = new DefaultTreeModel(root);
        JTree tree = new JTree(model);

        JFrame frame = new JFrame("QQ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(tree), BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);

    }
}