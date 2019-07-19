package com.ArraysSort.quickSortDualPivot;

public class QuickSortDualPivotData {

    private int[] numbers;
    public int l, r;
    public boolean[] fixedPivots;
    public int curPivot1;             //当前的标定1轴元素
    public int curPivot2;             //当前的标定2轴元素
    public int curElement;           //当前处理的元素
    public int curL, curR;

    public QuickSortDualPivotData(int[] numbers){
        this.numbers = numbers;
        fixedPivots = new boolean[numbers.length];
        for (int i = 0; i < numbers.length; i++ ){
            fixedPivots[i] = false;
        }
    }

    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if( index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        return numbers[index];
    }

    public void swap(int i, int j) {

        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}