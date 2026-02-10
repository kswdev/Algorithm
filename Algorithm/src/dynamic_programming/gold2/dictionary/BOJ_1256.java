package dynamic_programming.gold2.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1256 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int INF = 1000000001;

        char[] arr = new char[N+M+1];
        int[][] dp = new int[N+1][M+1];

        for (int i = 0; i < N; i++) {
            arr[i] = 'a';
        }

        for (int i = N; i < M; i++) {
            arr[i] = 'z';
        }

        dp[1][0] = 1;
        dp[0][1] = 1;

        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (INF < dp[i-1][j] + dp[i][j-1])
                    dp[i][j] = INF;
                else
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        int a = N;
        int z = M;

        StringBuilder result = new StringBuilder();

        if (K > dp[a][z]) {
            System.out.println(-1);
        } else {
            while(K != 0) {
                if (K == 1) {
                    for (int i = 0; i < a; i++)
                        result.append("a");
                    for (int i = 0; i < z; i++)
                        result.append("z");

                    break;
                } else if (K <= dp[a-1][z]) {
                    result.append("a");
                    a -= 1;
                } else {
                    result.append("z");
                    K -= dp[a-1][z];
                    z -= 1;
                }
            }
        }

        System.out.println(result);
    }
}
