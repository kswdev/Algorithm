package dynamic_programming.gold3.matrix;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11049 {
    private static int[][] matrix;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        matrix = new int[N+1][2];
        dp     = new int[N+1][N+1];

        StringTokenizer st;

        for(int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            matrix[n][0] = Integer.parseInt(st.nextToken());
            matrix[n][1] = Integer.parseInt(st.nextToken());
        }

        bw.write(solve(1, N) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solve(int start, int end) {
        if (start == end)
            return 0;
        else if (dp[start][end] != Integer.MAX_VALUE)
            return dp[start][end];

        for (int i = start; i < end; i++) {
            int result = solve(start, i) + solve(i+1, end) + (matrix[start][0] * matrix[i][1] * matrix[end][1]);
            dp[start][end] = Math.min(result, dp[start][end]);
        }

        return dp[start][end];
    }
}
