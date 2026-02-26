package dynamic_programming.gold2.beads_craft;

/*
 *
 * 문제.
 *  다솜이는 구슬을 N종류 가지고 있다.
 *  서로 다른 종류의 구슬은 색이 다르다.
 *  다솜이는 구슬을 실에 껴서 목걸이를 만들려고 한다.
 *  다솜이는 임의의 연속된 3개의 구슬의 색을 모두 다르게 하려고 한다.
 *  반드시 가지고 있는 모든 구슬을 이용해야 하며,
 *  목걸이는 원형이 아니라 직선이다.
 *  다시말하면, 처음 구슬과 마지막 구슬은 이어져있는 것이 아니다.

 * ex)
 *  예를 들어, 다솜이가 1번 구슬을 2개, 2번 구슬을 1개, 3번 구슬을 1개 가지고 있다고 하자.
 *  1번 구슬이 초록, 2번 구슬이 파랑, 3번 구슬이 빨강이라고 하면,
 *  연속된 3개의 구슬이 같은 색이면 안되기 때문에, 초록구슬을 반드시 목걸이의 끝에 있어야 한다.
 *  경우의 수는 (초록-빨강-파랑-초록) 또는 (초록-파랑-빨강-초록) 총 2가지이다
 *
 * 입력.
 *  첫째 줄에 다솜이가 가지고 있는 구슬의 종류 N이 주어진다.
 *   (3 <= N <= 5)
 *  둘째 줄부터 N개의 줄에 각각의 구슬이 총 몇 개 있는 지주어진다.
 *   (각각의 구슬은 10개보다 작거나 같은 자연수)
 *   (구슬의 총 개수의 합은 35를 넘지 않는다.)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1301 {

    private static int N;
    private static int[] beadsCount;
    private static long[][][][][][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        beadsCount = new int[5];

        for (int i = 0; i < N; i++) {
            beadsCount[i] = Integer.parseInt(br.readLine());
        }

        int a = beadsCount[0];
        int b = beadsCount[1];
        int c = beadsCount[2];
        int d = beadsCount[3];
        int e = beadsCount[4];

        dp = new long[N+1][N+1][a+1][b+1][c+1][d+1][e+1];

        for (int i = 0; i < N + 1; i++)
            for (int j = 0; j < N + 1; j++)
                for (int k = 0; k <= a; k++)
                    for (int l = 0; l <= b; l++)
                        for (int m = 0; m <= c; m++)
                            for (int n = 0; n <= d; n++)
                                for (int o = 0; o <= e; o++)
                                    dp[i][j][k][l][m][n][o] = -1;


        System.out.println(solve(N, N, beadsCount[0], beadsCount[1], beadsCount[2], beadsCount[3], beadsCount[4]));
    }

    // 구슬은 끝에서부터 채운다.
    // 파라미터: depth, 이전 구슬, 이전 이전 구슬, 남은 구슬 수
    // dp[][][]: 이전 구슬, 이전 이전 구슬, 남은 구슬 수
    //  => 이전 구슬, 이전 이전 구슬을 끝으로 남은 구슬 수로 가능한 최대 경우의 수
    private static long solve(int prev, int prevPrev, int a, int b, int c, int d, int e) {

        if (dp[prev][prevPrev][a][b][c][d][e] != -1) {
            return dp[prev][prevPrev][a][b][c][d][e];
        }

        if (a + b + c + d + e == 0) return 1;

        long result = 0;

        for (int i = 0; i < N; i++) {
            if (i == prev || i == prevPrev || beadsCount[i] == 0) continue;
            beadsCount[i]--;
            result += solve(i, prev, beadsCount[0], beadsCount[1], beadsCount[2], beadsCount[3], beadsCount[4]);
            beadsCount[i]++;
        }

        return dp[prev][prevPrev][a][b][c][d][e] = result;
    }
}
