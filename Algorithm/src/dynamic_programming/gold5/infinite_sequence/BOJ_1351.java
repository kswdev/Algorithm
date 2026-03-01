package dynamic_programming.gold5.infinite_sequence;

/*
    A[0] = 1
    A[i] = A[i/P] + A[i/Q] (i ≥ 1)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1351 {

    private static int P, Q;
    private static long N;
    private static Map<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        dp.put(0L, 1L);

        System.out.println(solve(N));
    }

    private static long solve (long n) {
        if (dp.containsKey(n)) return dp.get(n);

        dp.put(n, solve(n/P) + solve(n/Q));

        return dp.get(n);
    }
}
