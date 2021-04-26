package com.fj.cycle.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author FJ
 * @since 2021-04-01
 */
public class TestA {
    private static List<Integer> list;
    private double addNum;
    private double tmpNum;
    private int[] flag;

    public static void main(String[] args) {
        int[][] sign = {{1,12,5}, {23,1,5},{13,7,9}};
        TestA testA = new TestA();
        testA.solution(sign);
    }

    private void solution(int[][] array){
        if ((null == array) || array.length == 0){
            return;
        }
        int n = array.length;
        flag = new int[n];
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            tmpNum = array[0][i];
            flag[i] = 1;
            temp.add(i);
            exchange(array, temp, 1);
            tmpNum = 0;
            flag[i] = 0;
        }
        System.out.println(addNum);
        for (int i = 0; i < n; i++) {
            System.out.println(i + "  " + list.get(i));
        }
    }

    private void exchange(int[][] array, ArrayList<Integer> temp, int planarArray) {
        if (array.length == planarArray){
            if (addNum < tmpNum){
                addNum = tmpNum;
                list = (ArrayList<Integer>)temp.clone();
            }
        }else {
            for (int i = 0; i < array.length; i++) {
                if (flag[i] != 1){
                    temp.add(i);
                    flag[i] = 1;
                    tmpNum += array[planarArray][i];
                    exchange(array, temp, planarArray + 1);
                    temp.remove(temp.size() - 1);
                    tmpNum -= array[planarArray][i];
                    flag[i] = 0;
                }
            }
        }
    }
}
