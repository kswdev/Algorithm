package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942 {

    private static int[] num;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        num = new int[N+1];
        dp = new int[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= N; n++) {
            num[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >= 1; i--) {
            for (int j = i; j <= N; j++) {
                if (j == i) {
                    dp[i][j] = 1;
                } else if (j-i == 1) {
                    if (num[j-1] == num[j])
                        dp[i][j] = 1;
                    else
                        dp[i][j] = 0;
                } else {
                    if ((dp[i+1][j-1] == 1) && num[i] == num[j]) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = 0;
                    }
                }
            }
        }

        int M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(dp[s][e]).append("\n");
        }

        System.out.println(sb);
    }
}
/*

1 0 1 0 0 0 1
0 1 0 0 0 1 0
0 0 1 0 1 0 0
0 0 0 1 0 1 0
0 0 0 0 1 0 1
0 0 0 0 0 1 0
0 0 0 0 0 0 1
*/