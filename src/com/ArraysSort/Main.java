package com.ArraysSort;


import com.ArraysSort.inserSort.InsertAlgoVisualizer;
import com.ArraysSort.mergeSort.MergeAlgoVisualizer;
import com.ArraysSort.quickSort.QuickAlgoVisualizer;
import com.ArraysSort.quickSortDualPivot.DualPivotQuickAlgoVisualizer;

import java.util.Arrays;

public class Main {
    //合并排序的最大值
    private static final int MAX_RUN_COUNT = 67;

    //如果待排序数组的长度小于此常数，则优先选择采用双轴快速排序而不是归并排序
    private static final int QUICKSORT_THRESHOLD = 286;

    //如果待排序数组的长度小于此常数，那么优先选择插入排序而不是快速排序
    private static final int INSERTION_SORT_THRESHOLD = 47;

    //合并排序的最大长度
    private static final int MAX_RUN_LENGTH = 33;

    public static void main(String[] args) {
        int sceneWidth = 1100;
        int sceneHeight = 900;
        int N = 40;
        int [] numbers = new int[N];
        for( int i = 0 ; i < N ; i ++) {
            numbers[i] = (int)(Math.random()*sceneHeight) + 1;
        }
        /*Arrays.sort(numbers);
        int swapTime = (int) (0.02*N);
        for (int i = 0; i < swapTime; i++){
            int a = (int) (Math.random()*N);
            int b = (int) (Math.random()*N);
            int temp = numbers[0];
            numbers[0] = numbers[b];
            numbers[b] = temp;
        }*/
        SortStart(sceneWidth,sceneHeight,numbers,0,N);
    }

    public static void SortStart(int sceneWidth, int sceneHeight,int[] a,int left,int right){

        if (right < QUICKSORT_THRESHOLD){
            if (right < INSERTION_SORT_THRESHOLD){
                InsertAlgoVisualizer Iav = new InsertAlgoVisualizer(sceneWidth,sceneHeight,a);
            }else {
                DualPivotQuickAlgoVisualizer dp = new DualPivotQuickAlgoVisualizer(sceneWidth,sceneHeight,a);
            }
        }else{
            int[] run = new int[MAX_RUN_COUNT + 1];
            int count = 0; run[0] = left;

            // Check if the array is nearly sorted
            for (int k = left; k < right; run[count] = k) {
                if (a[k] < a[k + 1]) { // ascending
                    while (++k < right && a[k - 1] <= a[k]);
                } else if (a[k] > a[k + 1]) { // descending
                    while (++k <= right && a[k - 1] >= a[k]);
                    for (int lo = run[count] - 1, hi = k; ++lo < --hi; ) {
                        int t = a[lo]; a[lo] = a[hi]; a[hi] = t;
                    }
                } else { // equal
                    for (int m = MAX_RUN_LENGTH; ++k <= right && a[k - 1] == a[k]; ) {
                        if (--m == 0) {
                            QuickAlgoVisualizer qav = new QuickAlgoVisualizer(sceneWidth,sceneHeight,a);
                            return;
                        }
                    }
                }

                if (++count == MAX_RUN_COUNT) {
                    QuickAlgoVisualizer qav = new QuickAlgoVisualizer(sceneWidth,sceneHeight,a);
                    return;
                }
            }
            MergeAlgoVisualizer Mav = new MergeAlgoVisualizer(sceneWidth,sceneHeight,a);
        }
    }

}
