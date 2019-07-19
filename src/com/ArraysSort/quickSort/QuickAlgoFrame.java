package com.ArraysSort.quickSort;

import com.ArraysSort.AlgoVisHelper;

import javax.swing.*;
import java.awt.*;

public class QuickAlgoFrame extends JFrame{

    private int canvasWidth;
    private int canvasHeight;

    public QuickAlgoFrame(String title, int canvasWidth, int canvasHeight){

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public QuickAlgoFrame(String title){

        this(title, 1024, 768);
    }

    public int getCanvasWidth(){return canvasWidth;}
    public int getCanvasHeight(){return canvasHeight;}

    // data
    private QuickSortData data;
    public void render(QuickSortData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

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
            int w = canvasWidth/data.N();
            for(int i = 0 ; i < data.N() ; i ++ ) {
                if ( i >= data.l && i <= data.r)
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Green);   //当前正在处理的区间
                else
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);   //不是当前正在处理的区间

                if( i == data.curPivot )
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Indigo);
                if( i == data.curElement)
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                if( data.fixedPivots[i] )
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);

                AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}