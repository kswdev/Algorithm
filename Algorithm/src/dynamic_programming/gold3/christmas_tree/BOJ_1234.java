package dynamic_programming.gold3.christmas_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1234 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

    }

    // 구상:
    // dp에 레벨, 각 RGB 사용 갯수 표현
    // 탑 다운 방식으로 각 레벨을 거슬러 올라가며 경우의 수 곱셈
    private static void solve(int lv, int r, int g, int b) {

        for (int cnt : commonDivisor(lv)) {

        }
    }


    // 최대 숫자가 10개 밖에 안돼 공약수 찾는 로직은 생략
    private static int[] commonDivisor(int num) {
        switch (num) {
            case 1: return new int[] {1};
            case 2: return new int[] {1, 2};
            case 3: return new int[] {1, 3};
            case 4: return new int[] {1, 2, 4};
            case 5: return new int[] {1, 5};
            case 6: return new int[] {1, 2, 3, 6};
            case 7: return new int[] {1, 7};
            case 8: return new int[] {1, 2, 4, 8};
            case 9: return new int[] {1, 3, 9};
            case 10: return new int[] {1, 2, 5, 10};
            default: return new int[] {};
        }
    }


    /**
     * dp[color][lv][count]
     *
     *   1 2 3
     * 1 1 1 1
     * 2 2 2 2
     *
     *   1 2 3
     * 1 1 1 0
     * 2 1 2 0
     */
}
