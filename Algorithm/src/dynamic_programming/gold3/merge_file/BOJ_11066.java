package dynamic_programming.gold3.merge_file;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11066 {

    private static int[] chapter;
    private static int[][] dp;
    private static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            chapter = new int[K+1];
            dp  = new int[K+1][K+1];
            sum = new int[K+1][K+1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int k = 1; k <= K; k++) {
                chapter[k] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[k], Integer.MAX_VALUE);
            }

            for (int i = 1; i <= K; i++) {
                for (int j = i; j <= K; j++) {
                    sum[i][j] = sum[i][j-1] + chapter[j];
                }
            }

            dp[1][K] = sumPaging(1, K);
            sb.append(dp[1][K]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static int sumPaging(int start, int end) {
        if (start == end)
            return 0;
        else if (dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }

        for (int i = start; i < end; i++) {

            int result = sumPaging(start, i) + sumPaging(i+1, end) + sum[start][end];

            dp[start][end] = Math.min(dp[start][end], result);
        }

        return dp[start][end];
    }
}
