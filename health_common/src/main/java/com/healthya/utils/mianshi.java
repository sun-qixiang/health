package com.healthya.utils;

/**
 * @Author SunqiXiang
 * @Date 2023/2/7 9:31
 * @Version 1.0
 *
 * 编写一段Java代码，判断棋盘上是否存在5颗相同颜色的棋子排列成1条连续的直线。
 *
 *  已知棋盘大小为 32 x 32.
 *
 *  函数原型：
 *  boolean checkWin(int[][] map);
 *
 *  输入：
 *  map  一个二维数组,每个元素代表棋盘上的一个格子。
 *   如果格子中的棋子为白色，则值为1；
 *   如果格子中的棋子为黑色，则值为2；
 *   如果格子中没有棋子，则值为0;
 *
 *  输出：
 *   如果存在符合要求的5个棋子，则返回true;
 *   否则返回 false;
 */
public class mianshi {
    int count=0;
    boolean checkWin(int [][]map){
        boolean result = false;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                count=map[i][j]++;
                if (count==5||count==10){
                    result=true;
                }
                else {
                    result=false;
                }
            }
        }
        return result;
    }
}
