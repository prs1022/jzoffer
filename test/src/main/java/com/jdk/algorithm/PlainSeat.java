package com.jdk.algorithm;

import java.io.IOException;
import java.util.*;

/**
 * 飞机排座
 *
 * 思路：
 * 给座位赋值一个数字得分，最后按得分降序
 * 先对整个座位矩阵填初始分数，保证自左到右，自上到下都是降序分数
 * 再对过道、靠窗的座位单独加分
 * 最后对所有座位排序，输出
 *
 * @author rensong.pu
 * @date 2019/11/14 9:53 星期四
 **/
public class PlainSeat {

    /**
     * key: region  val: 1 为第一区
     * key: x  val: 1 为该区域的第几排
     * key: y val: 1 为该区域的第几列
     */
    private static List<Map<String, Integer>> cellList = new ArrayList<>();

    /**
     * 排序
     * @param N 总人数
     */
    public static void sortSeat(int N) {
        if (N > cellList.size()) {
            throw new RuntimeException("安排不下" + N + "个人！");
        }
        Collections.sort(cellList, (o1, o2) -> o1.get("score").compareTo(o2.get("score")) * -1);
        for (int i = 0; i < N; i++) {
            Map<String, Integer> cell = cellList.get(i);
            System.out.printf("第%d个人坐（%d区,%d排,%d列)", (i + 1), cell.get("region"), cell.get("x"), cell.get("y"));
            System.out.println();
        }
    }

    /**
     * 初始化
     *
     * @param seats 输入矩阵
     */
    public static void init(int[][] seats) {
        int sumRow = 0;
        int sumCol = 0;
        for (int i = 0; i < seats.length; i++) {
            if (sumRow < seats[i][1]) {
                sumRow = seats[i][1];
            }
            sumCol += seats[i][0];
        }
//        System.out.println("大矩阵行:" + sumRow + ",列:" + sumCol);
        //初始化大矩阵
        int count = 1;
        int[][] matrix = new int[sumRow][sumCol];
        for (int i = sumRow - 1; i >= 0; i--) {
            for (int j = sumCol - 1; j >= 0; j--) {
                matrix[i][j] = count++;
            }
        }
        //过道得分 为靠窗的最大值+中间的最大值
        //靠窗得分 为中间的最大值
        //中间不得分
        //这样保证过道>靠窗>中间 恒成立
        int score_side = matrix[0][0] + matrix[0][1];
        int score_window = matrix[0][1];
        //给小矩阵赋值
        int colCut = 0;
        for (int i = 0; i < seats.length; i++) {
            int col = seats[i][0];
            int row = seats[i][1];
            colCut += col;
            //子矩阵初始化
            int[][] subMatrix = new int[row][col];
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    subMatrix[j][k] = matrix[j][k + colCut - col];
                }
            }
            littleMatrix(subMatrix, score_side, score_window, i == 0 ? true : false, i == seats.length - 1 ? true : false);
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    Map<String, Integer> map = new HashMap<>();
                    map.put("region", i + 1);
                    map.put("x", j + 1);
                    map.put("y", k + 1);
                    map.put("score", subMatrix[j][k]);
                    cellList.add(map);
                }
            }
//            System.out.println("第" + (i + 1) + "区座位得分:");
//            print(subMatrix);
        }
    }

    /**
     * 处理小矩阵内部元素顺序
     *  ====================================
     *  scene1 |2|1| -> |2|1|
     *  scene2 |3|2|1| - > |3|1|2|
     *  scene3 |N|...|3|2|1| -> |N|N-2|..|2|1|N-1|
     *  ===================================
     * @param subMatrix 子矩阵
     * @param score_s  过道得分
     * @param score_w  靠窗得分
     * @param left  是否是左靠窗
     * @param right 是否是右靠窗
     */
    public static void littleMatrix(int[][] subMatrix, int score_s, int score_w, boolean left, boolean right) {
        if (subMatrix.length == 0 || subMatrix[0].length <= 1) {
            return;
        }
        int col = subMatrix[0].length;
        for (int k = 0; k < subMatrix.length; k++) {
            int max = subMatrix[k][0];
            int min = subMatrix[k][col - 1];
            for (int i = col - 2; i > 0; i--) {
                subMatrix[k][i] = min++;
            }
            if (left) {
                //靠窗得分
                subMatrix[k][0] = subMatrix[k][0] + score_w;
            } else {
                //过道加分
                subMatrix[k][0] = max + score_s;
            }
            if (right) {
                subMatrix[k][col - 1] = subMatrix[k][col - 1] + score_w;
            } else {
                subMatrix[k][col - 1] = max - 1 + score_s;
            }
        }
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("输入座位数组和人数,以空格隔开\n示例:[[3,2],[4,3],[2,3],[3,4]] 30\n输入exit退出\n************************");
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            String[] s = line.split(" ");
            if (s.length != 2) {
                System.err.println("输入不合法");
                continue;
            }
            String[] split = s[0].replace("[", "").replace("]", "").split(",");
            int[][] input = new int[split.length / 2][2];
            for (int i = 0; i < split.length / 2; i++) {
                input[i][0] = Integer.parseInt(split[i]);
                input[i][1] = Integer.parseInt(split[i + 1]);
            }
            init(input);
            sortSeat(Integer.parseInt(s[1]));
        }
    }
}
