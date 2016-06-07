package zac.com;

import java.util.Random;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
//        int[] weight = {4, 3, 2, 3};
//        int[] value = {3, 2, 4, 4};
//        int capa = 6;

//        int[] weight = {1, 3, 4, 5};
//        int[] value = {1, 4, 5, 7};
//        int capa = 7;

        int[] weight = {1, 4, 2, 6, 7, 4, 1, 3};
        int[] value = {4, 1, 5, 6, 7, 2, 4, 1};
        int capa = 13;

        int[][] myArr = knapsack(weight, value, capa);
        printArr(myArr);

        Stack<Integer> s = recover(myArr, weight, value, capa);

        while (!s.isEmpty()) System.out.print("item " + s.pop() + ", ");
        System.out.println(" are selected.");
    }

    /**
     * Knapsack with DP
     *
     * @param theWeight
     * @param theValue
     * @param theCapacity
     * @return int[][]
     */
    private static int[][] knapsack(int[] theWeight, int[] theValue, int theCapacity) {
        int i, j;

        /** 2D array for DP */
        int[][] dp = new int[theValue.length][theCapacity + 1];

        for (j = 0; j <= theCapacity; j++) {

            if (j >= theWeight[0]) dp[0][j] = theValue[0];

            else dp[0][j] = 0;
        }

        for (i = 1; i < theValue.length; i++) {

            for (j = 0; j <= theCapacity; j++) {

                if (j < theWeight[i]) dp[i][j] = dp[i-1][j];

                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - theWeight[i]] + theValue[i]);
            }
        }
        return dp;
    }

    /**
     * Print array
     *
     * @param theArr
     */
    private static void printArr(int[][] theArr) {
        int i, j;

        for (i = 0; i < theArr.length; i++) {

            for (j = 0; j < theArr[0].length; j++) {
                System.out.print(theArr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Recover selection
     *
     * @param theArr
     * @param theCapa
     * @return Stack<Integer>
     */
    private static Stack<Integer> recover(int[][] theArr, int[] theWeight, int[] theValue, int theCapa) {
        Stack<Integer> s = new Stack<>();
        int i, j = theCapa;
        i = theArr.length - 1;
        int max = theArr[i][j];

        while (max > 0) {

            if (i > 0) {

                if (theArr[i][j] == theArr[i - 1][j]) i--;

                else {
                    s.push(i + 1);
                    j -= theWeight[i];
                    i--;
                    max = theArr[i][j];
                }
            /** i = 0 */
            } else {

                if (max > 0) s.push(i + 1);

                max = 0;
            }
        }
        return s;
    }
}
