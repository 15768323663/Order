package com.ArraysSort.quickSortDualPivot;

import com.ArraysSort.AlgoVisHelper;

import java.awt.*;

public class DualPivotQuickAlgoVisualizer {
    private static int DELAY = 10;

    private QuickSortDualPivotData data;
    private DualPivotQuickAlgoFrame frame;

    public DualPivotQuickAlgoVisualizer(int sceneWidth, int sceneHeight, int[] numbers){
        // 初始化数据
        data = new QuickSortDualPivotData(numbers);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new DualPivotQuickAlgoFrame("Dual Pivot Quick Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){
        setData(-1, -1, -1, -1, -1, -1, -1);

        dualPivotQuickSort(0, data.N() - 1);

        setData(-1, -1, -1, -1, -1, -1, -1);
    }

    private void dualPivotQuickSort(int l, int r){

        if (l < r) {
            setData(l, r, -1, -1, -1, -1,-1);
            if (data.get(l) > data.get(r)) {
                data.swap(l,r);
                setData(l, r, -1, l, r, -1,-1);
            }
            int pivot1 = data.get(l);
            int pivot2 = data.get(r);
            setData(l, r, -1, pivot1, pivot2, -1,-1);
            int k = l + 1;
            int j = r;
            int i = l;
            setData(l, r, -1, pivot1, pivot2, i,j);
            while (k < j) {
                if (data.get(k) < pivot1) {
                    data.swap(++i, k++);
                    setData(l, r, k, -1, -1, -1, -1);
                } else if (data.get(k) <= pivot2) {
                    setData(l, r, k, -1, -1, -1, -1);
                    k++;
                } else {
                    while (data.get(--j) > pivot2) {
                        setData(l, r, j, -1, -1, -1, -1);
                        if (j <= k) {
                            // 扫描终止
                            break;
                        }
                    }

                    if (data.get(j) < pivot1) {
                        data.swap(j, k);
                        data.swap(++i, k);
                       // setData(l, r, k, -1, -1, -1, -1);
                    } else {
                        data.swap(j, k);
                       // setData(l, r, k, -1, -1, -1, -1);
                    }
                    k++;
                    setData(l, r, -1, pivot1, pivot2, k, j);
                }
            }
            data.swap(l, i);
            setData(l, r, i, -1, -1, -1, -1);
            data.swap(r, j);
            setData(l, r, j, -1, -1, -1, -1);

            dualPivotQuickSort( l, i - 1);
            dualPivotQuickSort( i + 1, j - 1);
            dualPivotQuickSort(j + 1, r);
        }
    }

    private void setData(int l, int r, int fixedPivot, int curPivot1,int curPivot2 ,int curL, int curR){
        data.l = l;
        data.r = r;
        if(fixedPivot != -1){
            data.fixedPivots[fixedPivot] = true;
            int i = fixedPivot;
            while(i < data.N() && data.get(i) == data.get(fixedPivot)){
                data.fixedPivots[i] = true;
                i ++;
            }
        }
        data.curPivot1 = curPivot1;
        data.curPivot2 = curPivot2;
        data.curL = curL;
        data.curR = curR;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
}
