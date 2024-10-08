package dynamic_programming.gold3.matrix;

import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/**
 * 1.방법 두 매트릭스의 합이 가장 작은 순서대로 곱하기
 *
 *
 */
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

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            matrix[n][0] = Integer.parseInt(st.nextToken());
            matrix[n][1] = Integer.parseInt(st.nextToken());
        }

        for (int n = 1; n < N; n++) {
            dp[n][n+1] = matrix[n][0] * matrix[n][1] * matrix[n+1][1];
        }

        bw.write(solve(1, N));
        bw.close();
        br.close();
    }

    private static int solve(int start, int end) {
        if (start == end)
            return 0;
        else if (dp[start][end] != 0)
            return dp[start][end];

        for (int i = 1; i < end-1; i++) {
            int result = solve(start, end - i);
            dp[start][end] = Math.min(result, dp[start][end]);
        }

        return 0;
    }
}
