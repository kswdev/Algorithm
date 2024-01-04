package divide_and_rule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992 {

    private static int N;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        System.out.println(quadTree(0, 0, N));
    }

    private static String quadTree(int row, int col, int size) {
        if(check(row, col, size)) {
            if (map[row][col] == 1) return "1";
            else return "0";
        }

        int newSize = size / 2;

        return "(" + quadTree(row, col, newSize) +
                     quadTree(row, col + newSize, newSize) +
                     quadTree(row + newSize, col, newSize) +
                     quadTree(row + newSize, col + newSize, newSize) +
                ")";
    }

    private static boolean check(int row, int col, int size) {
        int flag = map[row][col];

        for (int i = row; i < row + size; i++)
            for (int j = col; j < col + size; j++)
                if (map[i][j] != flag) return false;

        return true;
    }
}
