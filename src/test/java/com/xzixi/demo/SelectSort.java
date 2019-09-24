package com.xzixi.demo;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i=0; i<len-1; i++) {
            int index = i;
            for (int j=i+1; j<len; j++) {
                if (arr[j]<arr[i]) {
                    index = j;
                }
            }
            if (index!=i) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }

}
