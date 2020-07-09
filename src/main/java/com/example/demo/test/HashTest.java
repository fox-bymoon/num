package com.example.demo.test;

import java.util.Arrays;

public class HashTest {
    public static void main(String[] args) {
        int arr[] = {3,1,5,51,8,4,2,46,6,7,15,16,10,13,14,12,11,17,9};
        //insertSort(arr);
        shellSort(arr);
    }
    //直接插入排序
    public static void insertSort(int[] array){
        for (int i=1;i<array.length;i++){
            int tem = array[i];
            int j = i-1;
            for (;j>=0 && array[j] > tem;j--){
                array[j+1] = array[j];
            }
            array[j+1] = tem;
        }
        System.out.println(Arrays.toString(array));
    }
    //希尔排序
    public static void shellSort(int[] array){
        int i,j;
        int tem;
        int gap = 1;
        int len = array.length;
        while (gap < len/3){
            gap = gap *3 + 1;
        }
        for (;gap >0 ;gap = gap/3){
            for (i=gap;i<len;i++){
                tem = array[i];
                for (j = i - gap;j>= 0 && array[j] > tem;j = j-gap){
                    array[j+gap] = array[j];
                }
                array[j+gap] = tem;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
