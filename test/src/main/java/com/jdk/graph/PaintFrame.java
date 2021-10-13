package com.jdk.graph;

import javax.swing.*;
import java.awt.*;

/**
 * @author rensong.pu
 * @date 2019/11/19 17:51 星期二
 **/
public class PaintFrame extends JFrame {

    class Plot extends JPanel {
        @Override
        public void paint(Graphics g) {
            //笔画属性，

            BasicStroke bs = new BasicStroke(10.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
            super.paint(g);
            Graphics2D gp2d = (Graphics2D) g;
            gp2d.setColor(Color.blue);
            //第一个点的x坐标，第一个点的y坐标，第二个点的x坐标，第二点的坐标
            gp2d.drawLine(30, 30, 80, 30);
            //绘制矩形的x坐标，绘制矩形的y坐标，矩形的宽度，矩形的高度
            gp2d.drawRect(10, 50, 100, 50);
            //绘制矩形的x坐标，绘制矩形的y坐标，矩形的宽度，矩形的高度,4个角弧度的水平直径，4个角弧度的垂直直径
            gp2d.drawRoundRect(10, 120, 100, 70, 12, 12);



            gp2d.setColor(Color.black);
            gp2d.fillOval(20, 130, 30, 20);
            gp2d.fillOval(70, 130, 30, 20);


            gp2d.setColor(Color.gray);
            gp2d.fillArc(35, 160, 40, 25, 100, 270);
            Color c1 = new Color(22, 147, 140);//创建红绿蓝不


            gp2d.setColor(c1);
            gp2d.drawOval(10, 200, 100, 60);//绘制椭圆的边框。
            gp2d.fill3DRect(150, 30, 100, 80, false);

            gp2d.fillOval(150, 130, 100, 80);

            gp2d.setStroke(bs);//设置笔画属性
            gp2d.drawLine(300, 80, 300, 200);
        }
    }

    private void initialize() {
        this.setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭模式
        setContentPane(new Plot());
        this.setTitle("绘图小Demo");
        this.setLocationRelativeTo(null);//窗体居中
        this.setVisible(true);//设置窗体的可见性
    }

    public static void main(String[] args) {
        new PaintFrame().initialize();
    }
}
