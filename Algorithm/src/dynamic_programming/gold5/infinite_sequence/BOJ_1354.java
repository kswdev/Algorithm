package dynamic_programming.gold5.infinite_sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1354 {

    private static Map<Long, Long> dp = new HashMap<>();
    private static long N;
    private static int P, Q, X, Y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        dp.put(0L, 1L);

        System.out.println(solve(N));
    }

    private static long solve(long n) {
        if (dp.containsKey(n)) return dp.get(n);

        long i = (n/P) - X > 0 ? (n/P) - X : 0;
        long j = (n/Q) - Y > 0 ? (n/Q) - Y : 0;

        dp.put(n, solve(i) + solve(j));

        return dp.get(n);
    }
}
