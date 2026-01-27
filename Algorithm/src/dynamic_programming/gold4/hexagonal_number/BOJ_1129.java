package dynamic_programming.gold4.hexagonal_number;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1129 {

    private static int[] hexagonalNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        hexagonalNum = new int[n+1];
        hexagonalNum[1] = 1;

        int index = maxHexagonalNumber(n);

        System.out.println(minHexagonalNumberCount(index, n));
    }

    private static int minHexagonalNumberCount(int index, int n) {
        if (hexagonalNum[index] == n)
            return 0;

        if (hexagonalNum[index] < n)
            return minHexagonalNumberCount(index, n - hexagonalNum[index]) + 1;
        else
            return minHexagonalNumberCount(index - 1, n);
    }

    private static int maxHexagonalNumber(int n) {

        int maxIndex = 1;

        for (int i = 2; hexagonalNum[i-1] < n; i++) {
            hexagonalNum[i] = hexagonalNum[i-1] + ((i-1) * 4) + 1;
            maxIndex = i-1;
        }

        return maxIndex;
    }
}
