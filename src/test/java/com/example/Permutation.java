package com.example;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static List<String> permutation(String str) {
        List<String> list = new ArrayList<>();
        if (str!=null && str.length()>0) {
            help(0, str.toCharArray(), list);
        }
        return list;
    }
    public static void help(int i, char[] chs, List<String> list) {
        if (i==chs.length-1) {
            String val = String.valueOf(chs);
            if (!list.contains(val)) {
                list.add(val);
            }
        } else {
            for (int j=i; j<chs.length; j++) {
                swap(i, j, chs);
                help(i+1, chs, list);
                swap(i, j, chs);
            }
        }
    }
    public static void swap(int i, int j, char[] chs) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }
}
