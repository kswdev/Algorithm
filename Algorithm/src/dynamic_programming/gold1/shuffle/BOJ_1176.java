package dynamic_programming.gold1.shuffle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1176 {

    private static int N, K;
    private static long[][] dp;
    private static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[1 << N][N];
        heights = new int[N];

        for (long[] bitmask: dp) Arrays.fill(bitmask, -1);

        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(maxCount());
    }

    private static long maxCount() {
        long result = 0;

        for (int lastPerson = 0; lastPerson < N; lastPerson++) {
            int bitmask = 1 << lastPerson;
            result += solve(bitmask, lastPerson);
        }

        return result;
    }

    private static long solve(int bitmask, int lastPerson) {
        if (bitmask == (1 << N) - 1) return 1;
        if (dp[bitmask][lastPerson] != -1) return dp[bitmask][lastPerson];

        long result = 0;

        for (int candidate = 0; candidate < N; candidate++) {
            int personPosition = 1 << candidate;
            if (notVisited(bitmask, personPosition) && isAcceptable(lastPerson, candidate)) {
                int newBitMask = bitmask | personPosition;
                result += solve(newBitMask, candidate);
            }
        }

        dp[bitmask][lastPerson] = result;

        return dp[bitmask][lastPerson];
    }

    private static boolean notVisited(int bitmask, int personPosition) {
        return (bitmask & personPosition) == 0;
    }

    private static boolean isAcceptable(int lastPerson, int currentPerson) {
        return Math.abs(heights[lastPerson] - heights[currentPerson]) > K;
    }
}
