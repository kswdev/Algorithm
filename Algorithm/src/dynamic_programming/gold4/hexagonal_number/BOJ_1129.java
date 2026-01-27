package dynamic_programming.gold4.hexagonal_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1129 {

    private static List<Integer> hexagonalNum = new ArrayList<>();
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        hexagonalNum.add(0);
        hexagonalNum.add(1);
        dp = new int[n+1];

        maxHexagonalNumber(n);

        System.out.println(minHexagonalNumberCount(n));
    }

    private static int minHexagonalNumberCount(int n) {
        if (dp[n] != 0)
            return dp[n];

        int min = Integer.MAX_VALUE;

        for (int hex : hexagonalNum) {
            if (hex > n) break;
            if (hex == 0) continue;
            min = Math.min(min, minHexagonalNumberCount(n-hex) + 1);
        }

        return dp[n] = min;
    }

    private static void maxHexagonalNumber(int n) {
        for (int i = 2; hexagonalNum.get(i-1) < n; i++) {
            hexagonalNum.add(hexagonalNum.get(i-1) + ((i-1) * 4) + 1);
            dp[hexagonalNum.get(i-1)] = 1;
        }
    }
}
