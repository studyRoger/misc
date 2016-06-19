package com.leetcode;

/**
 * Created by roger on 12/31/15.
 */
public class ZigZagConversion {
    public static void main(String[] args) {

        System.out.println(convert2("PAYPALISHIRING", 3));
        System.out.println(convert2("PAYPALISHIRING", 2));
        //System.out.println(convert2("PAYPALISHIRING", 1));
        //System.out.println(convert2("P", 1));
        //System.out.println(convert2("AB", 2));
        //System.out.println(convert2("A", 2));
    }

    public static String convert(String s, int numRows) {

        if(numRows == 1) return s;
        else {
            int length = (s.length() / (numRows * 2 - 2) + 1) * (numRows - 1);
            char[][] matrix = new char[numRows][length];
            char[] str = s.toCharArray();
            int x = 0;
            int y = 0;
            boolean vertical = true;
            for(char c : str) {
                matrix[y][x] = c;
                if(vertical) {
                    y++;
                } else {
                    y--;
                    x++;
                }

                if(y == 0) {
                    vertical = true;
                }
                if(y == numRows - 1){
                    vertical = false;
                }
            }
            StringBuilder sb = new StringBuilder();
            for(char[] row : matrix) {
                for(char c : row) {
                    if(c != 0) {
                        sb.append(c);
                    }
                }
            }

            return sb.toString();
        }

    }

    public static String convert2(String s, int numRows) {
        if(numRows == 1) return s;
        else {
            char[] str = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            int span = numRows * 2 - 2;
            for(int j = 0; span - j >= j; j++) {
                for(int i = 0; i * span < s.length(); i++) {
                    if(i * span + j >= s.length()) break;
                    sb.append(str[i * span + j]);
                    if(span - j > j && j > 0 ) {
                        if(i * span + span - j >= s.length()) break;
                        else sb.append(str[i * span + span - j]);
                    }

                }
            }

            return sb.toString();
        }
    }
}
