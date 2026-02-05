package com.yuqiqi.algorithm.backtracking;

/**
 * 解数独   力扣37  ⭐回溯思想
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class Sudoku {
    /*
        思路：遍历每一个没有填入数字的格子，逐一尝试1-9，若行、列、九宫格内没有冲突 则填入
            一旦1-9都尝试失败 就回溯到上一个格子，切换数字填入
            ⭐关键是记录冲突状态
     */
    public void solveSudoku(char[][] board) {
        //行冲突状态   line[i]代表行  哪一列为true就表示是哪个数已经被占用了
        boolean[][] line = new boolean[9][9];   //九个空格 每一个空格九个状态
        //列冲突状态   colum[i]代表列
        boolean[][] colum = new boolean[9][9];
        //九宫格冲突状态
        // ⭐⭐九宫格对应关系：i / 3 * 3 + j / 3（int类型计算  自动舍去了小数部分）
        boolean[][] grid = new boolean[9][9];

        //记录已有的冲突状态
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];  //字符
                if (ch != '.'){  //初始化冲突状态
                    line[i][ch - '1'] = true;   //⭐ch - ‘1’代表了当前的索引
                    colum[j][ch - '1'] = true;
                    grid[i / 3 * 3 + j / 3][ch - '1'] = true;
                }
            }
        }
        //调用递归
        rec(0 , 0 , board , line , colum , grid);
    }

    //递归方法  ⭐i和j都需要维护   返回一个布尔值来判断是不是填满了
    public boolean rec(int i , int j , char[][] board ,
                    boolean[][] line , boolean[][] colum , boolean[][] grid){
        //⭐查找空格  挨个遍历
        while (board[i][j] != '.'){
            if (++j >= 9){
                j = 0;
                i ++;
            }
            if (i >= 9){
                return true;  //⭐都填满了
            }
        }
        //填空
        for (int k = 1; k <= 9; k++) {  //尝试往里面填数字
            //剪枝
            if (line[i][k - 1] || colum[j][k - 1] || grid[i / 3 * 3 + j / 3][k - 1]){
                continue;   //已经填了的话直接跳过
            }

            board[i][j] = (char) (k + '0');  //这里得加一个字符0
            line[i][k - 1] = colum[j][k - 1] = grid[i / 3 * 3 + j / 3][k - 1] = true;  //占位
            if (rec(i , j , board , line , colum , grid)){  //⭐递归
                return true;   //填满了  直接返回
            }
            line[i][k - 1] = colum[j][k - 1] = grid[i / 3 * 3 + j / 3][k - 1] = false;  //⭐回溯
            board[i][j] = '.';  //⭐回溯
        }
        return false;  //没填满就返回false
    }
}
