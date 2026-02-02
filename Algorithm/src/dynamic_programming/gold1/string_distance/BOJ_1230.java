package dynamic_programming.gold1.string_distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1230 {

    private static int INF = 1000;
    private static int NOT_INSERT = 0;
    private static int INSERT = 1;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String o = br.readLine();
        String n = br.readLine();

        dp = new int[o.length()+1][n.length()+1][2];

        for (int i = 0; i <= o.length(); i++) {
            for (int j = 0; j <= n.length(); j++) {
                dp[i][j][NOT_INSERT] = INF;
                dp[i][j][INSERT] = INF;
            }
        }

        dp[0][0][NOT_INSERT] = 0;
        dp[0][0][INSERT] = INF;

        for (int i = 1; i <= n.length(); i++) {
            dp[0][i][INSERT] = 1;
        }

        for (int i = 0; i < o.length(); i++) {
            for (int j = i; j < n.length(); j++) {
                dp[i+1][j+1][NOT_INSERT] = notInsert(o, n, i, j);
                dp[i+1][j+1][INSERT] = insert(i, j);
            }
        }

        int result = Math.min(dp[o.length()][n.length()][NOT_INSERT], dp[o.length()][n.length()][INSERT]);
        System.out.println(result == INF ? -1 : result);
    }

    private static int notInsert(String o, String n, int i, int j) {
        if (o.charAt(i) == n.charAt(j))
            return Math.min(dp[i][j][NOT_INSERT], dp[i][j][INSERT]);
        else
            return INF;
    }

    private static int insert(int i, int j) {
        return Math.min(dp[i+1][j][NOT_INSERT] + 1, dp[i+1][j][INSERT]);
    }
}
