package dynamic_programming.gold4.lcs;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_14002 {

    public static void main(String[] args) throws IOException {
        int N;
        int[] dp;
        int[] sequence;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        dp = new int[N+1];
        sequence = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        Arrays.fill(dp, 1);
        int result = 1;
        for (int n = 1; n <= N; n++) {
            sequence[n] = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= n; i++) {
                if (sequence[n] > sequence[i]) {
                    dp[n] = Math.max(dp[i] + 1, dp[n]);
                    result = Math.max(dp[n], result);
                }
            }
        }

        sb.append(result).append("\n");

        int value = result;

        Stack<Integer> stack = new Stack<>();

        for(int i = N; i >= 1; i--){
            if(value == dp[i]) {
                stack.push(sequence[i]);
                value--;
            }
        }

        while (!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}