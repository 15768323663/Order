package com.ArraysSort.inserSort;

import com.ArraysSort.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;

public class InsertAlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public InsertAlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();        //创建画布，在画布中可以进行绘制
        setContentPane(canvas);                       //设置窗口的内容面板为，自己创建的画布
        pack();                                       //自动调整窗口的大小和画布的大小一致

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //让窗口点击关闭按钮，可以关闭
        setResizable(false);                            //不让调整大小

        setVisible(true);
    }

    public InsertAlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // data
    private InsertionSortData data;
    public void render(InsertionSortData data){
        this.data = data;
        repaint();            //刷新画布
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        //不用理g这个参数从哪里传进来，swing框架会智能的自动传值
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth/data.N();       //每个数字代表的柱状图的宽度 = 窗口的宽度 / 数字的数量
            for(int i = 0 ; i < data.N() ; i ++ ) {
                if (i < data.orderedIndex)
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                else
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);

                if( i == data.currentIndex )
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                //w = w -1 是为了让矩形之间有一个像素的宽度
                AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        //设置画布的大小
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}