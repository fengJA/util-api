package com.fj.test.likou.caseinter;

/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */

public class RotationMatrix {
    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{15,13, 2, 5},{14, 3, 4, 1},{12, 6, 8, 9},{16, 7,10,11}};
        int[][] matrix = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        int[][] rotate = rotate(matrix);
        for (int[] data : rotate){
            for (int da : data){
                System.out.println(da);
            }
        }
    }

    public static int[][] rotate(int[][] matrix) {
        int length = matrix.length;
        // 对角线
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        int num = length >> 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < num; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][length - 1 - j];
                matrix[i][length - 1 - j] = tmp;
            }
        }
        return matrix;
    }

}
