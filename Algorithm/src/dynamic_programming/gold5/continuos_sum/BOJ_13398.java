package dynamic_programming.gold5.continuos_sum;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_13398 {

    private static int N;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][2]; // j = 0: 수를 제거 하지 않음, j = 1: 특정 수를 제거 함.
        dp[0][0] = dp[0][1] = arr[0];

        int ans = arr[0];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1] + arr[i]);

            ans = Math.max(ans, Math.max(dp[i][1], dp[i][0]));
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}