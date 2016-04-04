package presentation.UltraSwing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Created by 宋益明 on 16-3-17.
 * <p>
 * 滚动面板
 * 可调节透明度
 */
public class UltraScrollPane extends JScrollPane {

    private Component view;

    public UltraScrollPane(Component view) {
        super(view);

        this.view = view;

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setBackground(new Color(0, 0, 0, 0));
        setBorder(new BevelBorder(BevelBorder.LOWERED));
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        setSize(view.getSize());
        getHorizontalScrollBar().setUI(new UltraScrollBarUI());
        getVerticalScrollBar().setUI(new UltraScrollBarUI());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    class UltraScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

            int w = thumbBounds.width;
            int h = thumbBounds.height;
            g.translate(thumbBounds.x, thumbBounds.y);
            //绘制白色边框矩形
            // g.setColor(java.awt.Color.WHITE);
            g.drawRect(0, 0, w - 1, h - 1);
            //用黑色填充矩形
            g.setColor(java.awt.Color.BLACK);
            g.fillRect(0, 0, w - 1, h - 1);

            //绘制红色的线
            g.setColor(java.awt.Color.WHITE);
            //(1,1)到(1,h-2)的线（左边）
            g.drawLine(1, 1, 1, h - 2);
            //(2,1)到(w-3,1)的线（上边）
            g.drawLine(2, 1, w - 3, 1);
            g.setColor(java.awt.Color.WHITE);
            //(2,h-2)到(w-2,h-2)的线（下边）
            g.drawLine(2, h - 2, w - 2, h - 2);
            //(w-2,1)到(w-2, h-3)的线（右边）
            g.drawLine(w - 2, 1, w - 2, h - 3);
            g.translate(-thumbBounds.x, -thumbBounds.y);
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(java.awt.Color.BLUE);
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

            //始终没有进入这两个判断方法，通过单独调用它们中的任意一个即刻明白这两个方法的含义
            if (trackHighlight == DECREASE_HIGHLIGHT) {
                paintDecreaseHighlight(g);
            } else if (trackHighlight == INCREASE_HIGHLIGHT) {
                paintIncreaseHighlight(g);
            }
        }

        @Override
        protected void paintDecreaseHighlight(Graphics g) {
            Insets insets = scrollbar.getInsets();
            Rectangle thumbR = getThumbBounds();
            g.setColor(java.awt.Color.RED);
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                //paint the distance between the start of the track and top of the thumb
                int x = insets.left;
                int y = trackRect.y;
                int w = scrollbar.getWidth() - (insets.left + insets.right);
                int h = thumbR.y - y;
                System.out.println("垂直方向" + "x:" + x + "y:" + y + "w:" + w + "h:" + h);
                g.fillRect(x, y, w, h);
            } else {
                //if left-to-right, fill the area between the start of the track and
                //the left edge of the thumb. If right-to-left, fill the area between
                //the end of the thumb and end of the track.
                int x, w;
                if (scrollbar.getComponentOrientation().isLeftToRight()) {
                    x = trackRect.x;
                    w = thumbR.x - x;

                } else {
                    x = thumbR.x + thumbR.width;
                    w = trackRect.x + trackRect.width - x;
                }

                int y = insets.top;
                int h = scrollbar.getHeight() - (insets.top + insets.bottom);
                System.out.println("水平方向" + "x:" + x + "y:" + y + "w:" + w + "h:" + h);
                g.fillRect(x, y, w, h);
            }
        }

        @Override
        protected void paintIncreaseHighlight(Graphics g) {
            Insets insets = scrollbar.getInsets();
            Rectangle thumbR = getThumbBounds();
            g.setColor(java.awt.Color.RED);
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                //fill the area between the bottom of the thumb and the end of the track.
                int x = insets.left;
                int y = thumbR.y + thumbR.height;
                int w = scrollbar.getWidth() - (insets.left + insets.right);
                int h = trackRect.y + trackRect.height - y;
                g.fillRect(x, y, w, h);
            } else {
                //if left-to-right, fill the area between the right of the thumb and the
                //end of the track. If right-to-left, then fill the area to the left of
                //the thumb and the start of the track.
                int x, w;
                if (scrollbar.getComponentOrientation().isLeftToRight()) {
                    x = thumbR.x + thumbR.width;
                    w = trackRect.x + trackRect.width - x;
                } else {
                    x = trackRect.x;
                    w = thumbR.x - x;
                }
                int y = insets.top;
                int h = scrollbar.getHeight() - (insets.top + insets.bottom);
                g.fillRect(x, y, w, h);
            }
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new BasicArrowButton(orientation,
                    UIManager.getColor("ScrollBar.thumb"),
                    UIManager.getColor("ScrollBar.thumbShadow"),
                    UIManager.getColor("ScrollBar.thumbDarkShadow"),
                    UIManager.getColor("ScrollBar.thumbHighlight"));
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new BasicArrowButton(orientation) {
                Color darkShadow = new Color(0xFFC125);
                Color shadow = new Color(0xFFF68F);
                Color highlight = new Color(0xFF7F24);

                @Override
                public void paintTriangle(Graphics g, int x, int y, int size,
                                          int direction, boolean isEnabled) {
                    Color oldColor = g.getColor();
                    int mid, i, j;

                    j = 0;
                    size = Math.max(size, 2);
                    mid = (size / 2) - 1;

                    g.translate(x, y);
                    if (isEnabled) {
                        g.setColor(darkShadow);
                    } else {
                        g.setColor(shadow);
                    }

                    switch (direction) {
                        case NORTH:
                            for (i = 0; i < size; i++) {
                                g.drawLine(mid - i, i, mid + i, i);

                            }
                            if (!isEnabled) {
                                g.setColor(highlight);
                                g.drawLine(mid - i + 2, i, mid + i, i);

                            }
                            break;
                        case SOUTH:
                            if (!isEnabled) {
                                g.translate(1, 1);
                                g.setColor(highlight);
                                for (i = size - 1; i >= 0; i--) {
                                    g.drawLine(mid - i, j, mid + i, j);
                                    j++;
                                }
                                g.translate(-1, -1);
                                g.setColor(shadow);
                            }
                            j = 0;
                            for (i = size - 1; i >= 0; i--) {
                                g.drawLine(mid - i, j, mid + i, j);
                                j++;
                            }
                            break;
                        case WEST:
                            for (i = 0; i < size; i++) {
                                g.drawLine(i, mid - i, i, mid + i);

                            }
                            if (!isEnabled) {
                                g.setColor(highlight);
                                g.drawLine(i, mid - i + 2, i, mid + i);
                            }
                            break;
                        case EAST:
                            if (!isEnabled) {
                                g.translate(1, 1);
                                g.setColor(highlight);
                                for (i = size - 1; i >= 0; i--) {
                                    g.drawLine(j, mid - i, j, mid + i);
                                    j++;
                                }
                                g.translate(-1, -1);
                                g.setColor(shadow);
                            }

                            j = 0;
                            for (i = size - 1; i >= 0; i--) {
                                g.drawLine(j, mid - i, j, mid + i);
                                j++;
                            }
                            break;
                    }
                    g.translate(-x, -y);
                    g.setColor(oldColor);
                }
            };
        }
    }
}