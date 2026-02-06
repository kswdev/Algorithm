package dynamic_programming.gold2.christmas_tree;

/*
 * 문제.
 *  크리스마스 트리를 만들려고 한다.
 *  트리는 N 개의 레벨로 이뤄져 있다.
 *  또 장난감 색 빨강, 파랑, 초록이 있다.
 *  레벨 K 에는 딱 K 개의 장난감이 있어야 한다.
 *  각 레벨에 놓으려고 선택한 색이 있으면 그 색의 장난감의 수는 서로 같아야 한다.
 *  예)
 *   레벨 3
 *    (빨강: 2, 파랑 1) X => 빨강 수 != 파랑 수
 *   레벨 4
 *    (빨강: 2, 파랑 2) O => 빨강 수 == 파랑 수
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1234 {

    private static long[][][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        dp = new long[n+1][r+1][g+1][b+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= r; j++) {
                for (int k = 0; k <= g; k++) {
                    for (int l = 0; l <= b; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }

        System.out.println(solve(n, r, g, b));
    }

    private static long solve(int lv, int r, int g, int b) {

        if (dp[lv][r][g][b] != -1) {
            return dp[lv][r][g][b];
        }

        if (lv == 1) {
            return dp[lv][r][g][b] = getCount(lv, r, g, b);
        }

        long result = 0;

        for (int i = 1; i <= 3; i++) {
            if (lv % i != 0) continue;

            long count = 0;
            int sizeOfSet = lv / i;

            if (i == 1) {
                if (sizeOfSet <= r) count += getNumOfPossible(sizeOfSet, i) * solve(lv - 1, r - sizeOfSet, g, b);
                if (sizeOfSet <= g) count += getNumOfPossible(sizeOfSet, i) * solve(lv - 1, r, g - sizeOfSet, b);
                if (sizeOfSet <= b) count += getNumOfPossible(sizeOfSet, i) * solve(lv - 1, r, g, b - sizeOfSet);
            } else if (i == 2) {
                if (sizeOfSet <= r && sizeOfSet <= g) count += getNumOfPossible(sizeOfSet, i) * solve(lv - 1, r - sizeOfSet, g - sizeOfSet, b);
                if (sizeOfSet <= r && sizeOfSet <= b) count += getNumOfPossible(sizeOfSet, i) * solve(lv - 1, r - sizeOfSet, g, b - sizeOfSet);
                if (sizeOfSet <= g && sizeOfSet <= b) count += getNumOfPossible(sizeOfSet, i) * solve(lv - 1, r, g - sizeOfSet, b - sizeOfSet);
            } else {
                if (sizeOfSet <= r && sizeOfSet <= g && sizeOfSet <= b)
                    count += getNumOfPossible(sizeOfSet, i) * solve(lv - 1, r - sizeOfSet, g - sizeOfSet, b - sizeOfSet);
            }

            result += count;
        }

        return dp[lv][r][g][b] = result;
    }

    private static long getNumOfPossible(int sizeOfSet, int countOfSets) {
        long factor = factorial(sizeOfSet * countOfSets);
        for (int i = 0; i < countOfSets; i++) {
            factor /= factorial(sizeOfSet);
        }
        return factor;
    }

    private static int getCount(int cnt, int r, int g, int b) {
        int result = 0;
        if (r >= cnt) result++;
        if (g >= cnt) result++;
        if (b >= cnt) result++;
        return result;
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
