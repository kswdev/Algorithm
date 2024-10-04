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

        int N = Integer.parseInt(br.readLine());
        num = new int[N+1];
        dp = new int[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= N; n++) {
            num[n] = Integer.parseInt(st.nextToken());
            dp[n][n] = 1;
        }

        int M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            dp[s][e] = isPalindrome(s, e);
        }

    }

    private static int isPalindrome(int s, int e) {
        boolean isOdd = (e - s) % 2 == 0;

        if (isOdd) {
            int pivot = (s + e) / 2;

            for (int i = pivot; i < e; i++) {
                if (num[pivot-1] != num[pivot+1])
                    return 0;
            }
        } else {
        }

        return 1;
    }
}
/*

1 0 1 0 0 0
0 1 0 0 0 1
0 0 1 0 1 0
0 0 0 1 0 1
0 0 0 0 1 0
0 0 0 0 0 1
*/