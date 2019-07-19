package com.ArraysSort.inserSort;

public class InsertionSortData {

    private int[] numbers;
    public int orderedIndex = -1;   // [0...orderedIndex) 是有序的
    public int currentIndex = -1;

    public InsertionSortData(int[] numbers){
        this.numbers = numbers;
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
