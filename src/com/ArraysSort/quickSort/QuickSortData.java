package com.ArraysSort.quickSort;

public class QuickSortData {

    private int[] numbers;
    public int l, r;
    public boolean[] fixedPivots;
    public int curPivot;             //当前的标定点元素
    public int curElement;           //当前处理的元素
    public int curL, curR;

    public QuickSortData(int[] numbers){
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