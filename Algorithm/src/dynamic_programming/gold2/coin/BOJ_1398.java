package dynamic_programming.gold2.coin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1398 {

    private static Map<Long, Long> dp = new HashMap<>();
    private static long[] testCases;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());
        testCases = new long[testCaseNum];

        for (int i = 0; i < testCaseNum; i++) {
            testCases[i] = Long.parseLong(br.readLine());
        }

        for (long testCase : testCases) {
            System.out.println(solve(testCase));
        }
    }

    private static long solve(long n) {
        if (dp.containsKey(n)) return dp.get(n);

        if (n == 0) return 0;

        long result = Long.MAX_VALUE;

        for (long candidate : getCandidate(n)) {
            if (candidate > n) continue;
            result = Math.min(result, solve(n - candidate) + 1);
        }

        dp.put(n, result);

        return dp.get(n);
    }

    private static long[] getCandidate(long number) {
        String convertString = Long.toString(number);
        int digits = convertString.length();

        double candidateOne = Math.pow(10, digits - 1);
        double candidateTwo = number + 1;
        if (digits % 2 == 0)
            candidateTwo = Math.pow(100, (digits/2) - 1) * 25;

        return new long[] { (long) candidateOne, (long) candidateTwo };
    }
}
