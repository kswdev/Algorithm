package dynamic_programming.gold1.fences;

/*
 * 문제.
 *  민식이는 초원에 삼각형 모양의 울타리를 치려고 한다.
 *  민식이는 지하실에 N개의 울타리가 있다.
 *  민식이는 3개의 울타리를 이용해서 삼각형 모양을 만든다.
 *  삼각형의 각 변은 울타리 하나이다
 *  타리는 붙이거나 쪼갤 수 없다.
 *  민식이는 삼각형 넓이의 합을 최대로 하려고 한다.
 *
 * 입력.
 *  첫째 줄에 울타리의 개수 N이 주어진다.
 *  둘째 줄에 각 울타리의 길이가 주어진다.
 *
 * 출력.
 *  첫째 줄에 줄을 문제의 정답을 출력한다.
 *  절대/상대 오차는 10-9까지 허용한다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1139 {

    private static int N;
    private static double[] dp;
    private static Integer[] fences;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        dp = new double[1 << N];
        fences = new Integer[N];

        for (int i = 0; i < N; i++) {
            fences[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(fences, Collections.reverseOrder());
        System.out.printf("%.9f", calculateMaxArea(0));
    }

    private static double calculateMaxArea(int bitmask) {
        if (bitmask == (1 << N)) return 0;
        if (dp[bitmask] != 0) return dp[bitmask];

        double result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if ((bitmask & (1 << i)) == 0 &&
                        (bitmask & (1 << j)) == 0 &&
                        (bitmask & (1 << k)) == 0 &&
                        fences[i] < fences[j] + fences[k]
                    ) {
                        double currArea = calculateTriangleArea(fences[i], fences[j], fences[k]);
                        result = Math.max(result, currArea + calculateMaxArea(bitmask | (1 << i) | (1 << j) | (1 << k)));
                    }
                }
            }
        }

        return dp[bitmask] = result;
    }

    private static double calculateTriangleArea(int a, int b, int c) {

        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
