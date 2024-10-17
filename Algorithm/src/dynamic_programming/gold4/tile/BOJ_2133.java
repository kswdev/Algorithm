package dynamic_programming.gold4.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];

        if (N > 1)
            dp[2] = 3;

        for (int i = 4; i <= N; i+=2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = 2; j <= i-4; j+=2) {
                //여기서 2의 의미는 j에 대해서 i의 보수인 특별 타일의 개수
                dp[i] += 2 * dp[j];
            }
            dp[i] += 2;
        }

        System.out.println(dp[N]);
    }
}
