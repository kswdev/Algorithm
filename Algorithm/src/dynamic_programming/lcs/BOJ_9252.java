package dynamic_programming.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[str2.length()+1][str1.length()+1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1))
                    dp[j][i] = dp[j-1][i-1] + 1;
                else
                    dp[j][i] = Math.max(dp[j][i-1], dp[j-1][i]);
            }
        }

        int result = dp[str2.length()][str1.length()];

        System.out.println(result);


        ToString(str1, str1.length(), str2.length(), sb, dp);

        System.out.println(sb);
    }

    static void ToString(String str1, int X, int Y, StringBuilder sb, int[][] dp) {
        Stack<Character> st = new Stack<>();
        while (Y > 0 && X > 0) {
            if (dp[Y][X] == dp[Y - 1][X]) {
                Y--;
            } else if (dp[Y][X] == dp[Y][X - 1]) {
                X--;
            } else {
                st.push(str1.charAt(X-1));
                Y--;
                X--;
            }
        }
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
    }
}
