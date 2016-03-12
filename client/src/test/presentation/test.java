package presentation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by 宋益明 on 16-3-12.
 * <p>
 * 测试折叠效果
 */
class JGroupPanel extends JPanel {
    /* 用来管理组的三个容器 */
    private JPanel pNorth = new JPanel();

    private JPanel pCenter = new JPanel();

    private JPanel pSouth = new JPanel();

    int[] openFlag = new int[3];

    /* 当前全部组的集合 */
    private ArrayList<JGroupContainer> groupList = new ArrayList<JGroupContainer>();

    /* 是否已禁止添加组件 */
    private boolean forbidFlag = false;

    String[][] str = new String[3][5];

    /* 当前激活的组 */
    private JGroupContainer activeGroup = null;

    transient ActionListener al = e -> {
        JButton bttTitle = (JButton) e.getSource();
        expandGroup((JGroupContainer) bttTitle.getParent());
    };

    public boolean hasCreateDefaultGroup = false;

    public JGroupPanel() {
        initComponents();
        createDefaultGroup();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(pNorth, BorderLayout.NORTH);
        this.add(pCenter, BorderLayout.CENTER);
        this.add(pSouth, BorderLayout.SOUTH);
        pNorth.setLayout(new GroupLayout());
        pCenter.setLayout(new BorderLayout());
        pSouth.setLayout(new GroupLayout());
        forbidFlag = true;
        for (int i = 0; i < 3; i++) {
            openFlag[i] = 0;
        }
    }

    private void createDefaultGroup() {
        // Default Group
        for (int i = 1; i <= 3; i++) {
            insertGroup(i - 1, "Group " + i);

            for (int j = 1; j <= 5; j++) {
                str[i - 1][j - 1] = "Member " + j + "of " + i;
                JButton bttMember = new JButton(str[i - 1][j - 1]);
                bttMember.setActionCommand(str[i - 1][j - 1]);

                addMember(i - 1, bttMember);
                bttMember.setPreferredSize(new Dimension(1, 40));
                bttMember.setOpaque(false);
            }

            getGroup(i - 1).setMemberGap(20, 5);
        }
        // expandGroup(0);
        hasCreateDefaultGroup = true;
    }

    /**
     * @param groupNames String[] 预设组名
     */
    public JGroupPanel(String groupNames[]) {
        initComponents();
        addGroup(groupNames);
    }

    /**
     * 展开组
     *
     * @param name String 组名
     */
    public void expandGroup(String name) {
        for (int i = getGroupCount() - 1; i >= 0; i--) {
            if (getGroupName(i).equals(name)) {
                expandGroup(i);
            }
        }
    }

    /**
     * 展开组
     *
     * @param index int 组的顺序号
     */
    public void expandGroup(int index) {
        expandGroup(getGroup(index));
    }

    /**
     * 展开组
     *
     * @param group JGroupContainer 组
     */
    protected void expandGroup(JGroupContainer group) {
        int num = 0;
        pNorth.removeAll();
        pCenter.removeAll();
        pSouth.removeAll();
        boolean hasAddCenter = false;
        for (int i = 0; i < groupList.size(); i++) {
            Component c = groupList.get(i);
            if (openFlag[i] == 0) {
                if (hasAddCenter) {
                    pSouth.add(c);
                } else if (c == group) {
                    pCenter.add(c, BorderLayout.CENTER);
                    openFlag[i] = 1;
                    num = i + 1;
                    hasAddCenter = true;
                } else {
                    pNorth.add(c);
                }
            } else {
                if (activeGroup != null) {
                    activeGroup.collapse();
                    activeGroup = null;
                }
                if (c == group) {
                    pNorth.add(c);
                } else if (num == 0) {
                    pNorth.add(c);
                } else if (num > i + 1) {
                    pNorth.add(c);
                } else {
                    pSouth.add(c);
                }

                openFlag[i] = 0;
            }
        }
        if (activeGroup != null) {
            activeGroup.collapse();
        }
        for (int i = 0; i < groupList.size(); i++) {
            Component c = groupList.get(i);
            if (c == group && openFlag[i] == 1) {
                activeGroup = group;
                activeGroup.expand();
            }
        }
        pNorth.doLayout();
        pCenter.doLayout();
        pSouth.doLayout();
        doLayout();
    }

    /**
     * 收缩组
     *
     * @param name String 组名
     */
    public void collapseGroup(String name) {
        for (int i = getGroupCount() - 1; i >= 0; i--) {
            if (getGroupName(i).equals(name)) {
                collapseGroup(i);
            }
        }
    }

    /**
     * 收缩组
     *
     * @param index int 组的顺序号
     */
    public void collapseGroup(int index) {
        collapseGroup(getGroup(index));
    }

    /**
     * 收缩组
     *
     * @param group JGroupContainer 组
     */
    protected void collapseGroup(JGroupContainer group) {
        if (group == activeGroup) {
            activeGroup.collapse();
            activeGroup = null;
        }
    }

    /**
     * 添加组
     *
     * @param name String 组名
     */
    public void addGroup(String name) {
        this.insertGroup(getGroupCount(), name);
    }

    /**
     * 添加多个组
     *
     * @param names String[] 组名
     */
    public void addGroup(String names[]) {
        for (int i = 0; i < names.length; i++) {
            addGroup(names[i]);
        }
    }

    /**
     * 插入一个组
     *
     * @param index int 顺序号
     * @param name  String 组名
     * @param bg    Color 背景色
     */
    public void insertGroup(int index, String name, Color bg) {
        if (index < 0 || index > groupList.size()) {
            throw new ArrayIndexOutOfBoundsException("index:" + index
                    + " >count:" + groupList.size());
        }
        if (hasCreateDefaultGroup) {
            while (getGroupCount() > 0) {
                removeGroup(0);
            }
            hasCreateDefaultGroup = false;
        }
        int countNorth = pNorth.getComponentCount();
        int countCenter = pCenter.getComponentCount();
        int countSouth = pSouth.getComponentCount();
        JGroupContainer group;
        if (index <= countNorth) {
            group = insertGroup(pNorth, index, name, bg);
        } else if (index <= countNorth + countCenter) {
            group = insertGroup(pCenter, index - countNorth, name, bg);
        } else if (index <= countNorth + countCenter + countSouth) {
            group = insertGroup(pSouth, index - countNorth - countCenter, name,
                    bg);
        } else {
            group = insertGroup(pSouth, countSouth, name, bg);
        }
        group.getTitleButton().addActionListener(al);
        groupList.add(index, group);
    }

    /**
     * 插入一个组
     *
     * @param index int 顺序号
     * @param name  String 组名
     */
    public void insertGroup(int index, String name) {
        insertGroup(index, name, UIManager.getColor("Desktop.background"));
    }

    /**
     * 插入一个组
     *
     * @param p     JPanel 目标面板
     * @param index int 顺序号
     * @param name  String 组名
     * @return JGroupContainer
     */
    private JGroupContainer insertGroup(JPanel p, int index, String name,
                                        Color bg) {
        JGroupContainer group = new JGroupContainer(name, bg);
        p.add(group);
        return group;
    }

    /**
     * 删除一个组
     *
     * @param index int 顺序号
     */
    public void removeGroup(int index) {
        JGroupContainer c = groupList.get(index);
        c.getParent().remove(c);
        c.getTitleButton().removeActionListener(al);
    }

    /**
     * 删除一个组
     *
     * @param name String 组名
     */
    public void removeGroup(String name) {
        for (int i = getGroupCount() - 1; i >= 0; i--) {
            if (getGroupName(i).equals(name)) {
                this.removeGroup(i);
            }
        }
    }

    /**
     * 设置组名
     *
     * @param index int 顺序号
     * @param name  String 组名
     */
    public void setGroupName(int index, String name) {
        this.getGroup(index).setName(name);
    }

    /**
     * 取得组名
     *
     * @param groupIndex int 顺序号
     * @return String 组名
     */
    public String getGroupName(int groupIndex) {
        return getGroup(groupIndex).getName();
    }

    /**
     * 取得全部组名
     *
     * @return String[]
     */
    public String[] getGroupNames() {
        String sResult[] = new String[getGroupCount()];
        for (int i = 0; i < getGroupCount(); i++) {
            sResult[i] = getGroupName(i);
        }
        return sResult;
    }

    /**
     * 取得当前组的总数
     *
     * @return int
     */
    public int getGroupCount() {
        return groupList.size();
    }

    /**
     * 往组中添加成员组件
     *
     * @param groupIndex int 组的顺序号
     * @param member     Component 成员组件
     */
    public void addMember(int groupIndex, Component member) {
        getGroup(groupIndex).addMember(getGroup(groupIndex).getMemberCount(),
                member);
    }

    /**
     * 往组中插入成员组件
     *
     * @param groupIndex  int 组的顺序号
     * @param memberIndex int 插入的顺序号
     * @param member      Component 成员组件
     */
    public void insertMember(int groupIndex, int memberIndex, Component member) {
        getGroup(groupIndex).addMember(memberIndex, member);
    }

    /**
     * 从组中移除成员组件
     *
     * @param groupIndex  int
     * @param memberIndex int
     */
    public void removeMember(int groupIndex, int memberIndex) {
        getGroup(groupIndex).removeMember(memberIndex);
    }

    /**
     * 取得成员组件
     *
     * @param groupIndex  int 组的顺序号
     * @param memberIndex int 成员组件的顺序号
     * @return Component 成员组件
     */
    public Component getMember(int groupIndex, int memberIndex) {
        return getGroup(groupIndex).getMember(memberIndex);
    }

    /**
     * 取得全部成员组件
     *
     * @param groupIndex int 组的顺序号
     * @return Component[] 全部成员组件
     */
    public Component[] getMembers(int groupIndex) {
        return getGroup(groupIndex).getMembers();
    }

    /**
     * 取得成员组件的总数
     *
     * @param groupIndex int 组的顺序号
     * @return int 总数
     */
    public int getMemberCount(int groupIndex) {
        return getGroup(groupIndex).getMemberCount();
    }

    /**
     * 取得组
     *
     * @param index int 组的顺序号
     * @return JGroupContainer 组
     */
    protected JGroupContainer getGroup(int index) {
        return groupList.get(index);
    }

    /**
     * 覆写的addImpl方法,禁止再向JGroupPane中添加组件
     *
     * @param comp        Component
     * @param constraints Object
     * @param index       int
     */
    protected void addImpl(Component comp, Object constraints, int index) {
        if (forbidFlag) {
            if (!(comp instanceof JGroupContainer)) {
                throw new UnsupportedOperationException(
                        "JGroupPane can't add component!");
            }
        } else {
            super.addImpl(comp, constraints, index);
        }
    }

    class GroupLayout implements LayoutManager, java.io.Serializable {
        int vgap = 0;

        int hgap = 0;

        public GroupLayout() {
        }

        public GroupLayout(int hg, int vg) {
            this.hgap = hg;
            this.vgap = vg;
        }

        public void addLayoutComponent(String name, Component comp) {
        }

        public void removeLayoutComponent(Component comp) {
        }

        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int ncomponents = parent.getComponentCount();
                int w = 0;
                int h = 0;
                for (int i = 0; i < ncomponents; i++) {
                    Component comp = parent.getComponent(i);
                    Dimension d = comp.getPreferredSize();
                    if (w < d.width) {
                        w = d.width;
                    }
                    h += d.height + vgap;
                }
                return new Dimension(insets.left + insets.right + w + 2 * hgap,
                        insets.top + insets.bottom + h + 2 * vgap);
            }
        }

        public Dimension minimumLayoutSize(Container parent) {
            return preferredLayoutSize(parent);
        }

        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int ncomponents = parent.getComponentCount();
                if (ncomponents == 0) {
                    return;
                }
                int y = insets.top + vgap;
                for (int c = 0; c < ncomponents; c++) {
                    int h = parent.getComponent(c).getPreferredSize().height;
                    parent.getComponent(c).setBounds(
                            insets.left + hgap,
                            y,
                            parent.getWidth() - insets.left - insets.right - 2
                                    * hgap, h);
                    y += h + vgap;
                }
            }
        }

        public String toString() {
            return getClass().getName();
        }
    }

    class JGroupContainer extends JPanel {
        private JButton bttGroupTitle = new JButton();

        private JPanel pMembers = new JPanel();

        // private JScrollPane sp;
        public JGroupContainer() {
            this("");
        }

        public JGroupContainer(String name) {
            this(name, UIManager.getColor("Desktop.background"));
        }

        /**
         * @param name       String 组名
         * @param background Color 成员组件所在面板背景色
         */
        public JGroupContainer(String name, Color background) {
            bttGroupTitle.setText(name);
            bttGroupTitle.setFocusable(false);
            pMembers.setLayout(new GroupLayout(5, 5));
            this.setLayout(new BorderLayout());
            this.add(bttGroupTitle, BorderLayout.NORTH);

            // pMembers.setBackground(background);

            Color thumbColor = UIManager.getColor("ScrollBar.thumb");
            Color trackColor = UIManager.getColor("ScrollBar.track");
            Color trackHighlightColor = UIManager
                    .getColor("ScrollBar.trackHighlight");

            UIManager.put("ScrollBar.thumb", background);
            UIManager.put("ScrollBar.track", background);
            UIManager.put("ScrollBar.trackHighlight", background);
            /*
             * sp = new JScrollPane(pMembers); sp.setHorizontalScrollBarPolicy(
             * JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
             */
            this.add(pMembers, BorderLayout.CENTER);
            collapse();
            UIManager.put("ScrollBar.thumb", thumbColor);
            UIManager.put("ScrollBar.track", trackColor);
            UIManager.put("ScrollBar.trackHighlight", trackHighlightColor);
        }

        /**
         * 设置间距
         *
         * @param hgap int 横间距
         * @param vgap int 竖间距
         */
        public void setMemberGap(int hgap, int vgap) {
            pMembers.setLayout(new GroupLayout(hgap, vgap));
        }

        /**
         * 取得组的标题按钮
         *
         * @return JButton
         */
        public JButton getTitleButton() {
            return bttGroupTitle;
        }

        /**
         * 取得组的成员组件面板
         *
         * @return JPanel
         */
        public JPanel getMembersContainer() {
            return pMembers;
        }

        /**
         * 收缩组
         */
        public void collapse() {
            pMembers.setVisible(false);
            this.revalidate();
        }

        /**
         * 展开组
         */
        public void expand() {
            pMembers.setVisible(true);
            this.revalidate();
        }

        /**
         * 设置组名
         *
         * @param name String 组名
         */
        public void setName(String name) {
            bttGroupTitle.setText(name);
        }

        /**
         * 取得组名
         *
         * @return String
         */
        public String getName() {
            return bttGroupTitle.getText();
        }

        /**
         * 添加一个成员组件
         *
         * @param index int 顺序号
         * @param c     Component 成员组件
         */
        public void addMember(int index, Component c) {
            pMembers.add(c, index);
            pMembers.doLayout();
        }

        /**
         * 删除一个成员组件
         *
         * @param index int 顺序号
         */
        public void removeMember(int index) {
            pMembers.remove(index);
            pMembers.doLayout();
        }

        /**
         * 取得一个成员组件
         *
         * @param index int 顺序号
         * @return Component 成员组件
         */
        public Component getMember(int index) {
            return pMembers.getComponent(index);
        }

        /**
         * 取得全部成员组件
         *
         * @return Component[] 成员组件
         */
        public Component[] getMembers() {
            Component coms[] = new Component[getMemberCount()];
            for (int i = 0; i < coms.length; i++) {
                coms[i] = pMembers.getComponent(i);
            }
            return coms;
        }

        /**
         * 取得成员组件总数
         *
         * @return int 总数
         */
        public int getMemberCount() {
            return pMembers.getComponentCount();
        }

        /**
         * 重写的toString方法
         *
         * @return String
         */
        public String toString() {
            return getName();
        }
    }

    /**
     * 测试程序
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("JGroupPanel Demo");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JGroupPanel(), BorderLayout.CENTER);
        frame.setSize(150, 600);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width - frame.getSize().width - 10, 10);
        frame.setVisible(true);
    }
}