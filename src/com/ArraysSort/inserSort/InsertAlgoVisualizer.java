package com.ArraysSort.inserSort;


import com.ArraysSort.AlgoVisHelper;

import java.awt.*;

public class InsertAlgoVisualizer {

    private static int DELAY = 10;

    private InsertionSortData data;
    private InsertAlgoFrame frame;

    public InsertAlgoVisualizer(int sceneWidth, int sceneHeight, int[] numbers){

        // 初始化数据
        data = new InsertionSortData(numbers);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new InsertAlgoFrame("Insertion Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    //动画效果
    public void run(){
        setData(0, -1);

        for( int i = 0 ; i < data.N() ; i ++ ){
            setData(i, i);
            for(int j = i ; j > 0 && data.get(j) < data.get(j-1) ; j --){
                data.swap(j,j-1);
                setData(i+1, j-1);
            }
        }
        setData(data.N(), -1);

    }

    private void setData(int orderedIndex, int currentIndex){
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
}