package dynamic_programming.gold1.drawing_trade;

/*
 *
 * 그림 교환 - https://www.acmicpc.net/problem/1029
 *
 * 그림 거래 조건
 *  1. 그림을 팔 때, 그림을 산 가격보다 크거나 같은 가격으로 팔아야 한다.
 *  2. 같은 그림을 두 번 이상 사는 것은 불가능하다.
 *
 *
 *
 * 문제
 *  1번 아티스트는 그림을 외부 상인에게 가격 0을 주고 샀다. 이제 그 그림을 친구에게 팔려고 한다.
 *  그림을 소유했던 사람의 수의 최댓값을 출력하는 프로그램을 작성하시오.(1번 아티스트와 마지막으로 그 그림을 소유한 사람도 포함)
 *
 * 입력
 *  첫째 줄에 예술가의 수 N이 주어진다.
 *   (*조건*)N은 2보다 크거나 같고, 15보다 작거나 같은 자연수이다.
 *  둘째 줄부터 N개의 줄에는 N개의 수가 주어진다.
 *   i번째 줄의 j번째 수는 j번 예술가가 i번 예술가에게 그 그림을 살 때의 가격이다.
 *   (*조건*)모든 가격은 0이 제일 낮은 가격이고, 9가 제일 높은 가격이다.
 *
 * 출력
 *  첫째 줄에 그 그림을 소유 했던 사람들 (잠시라도 소유했던 사람도 포함)의 최댓값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1029 {

    private static int N, checkVisit;
    private static int[][] ledger;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            checkVisit = (1 << N) - 1;

            ledger = new int[N + 1][N + 1];
            dp = new int[N + 1][checkVisit];
            for (int i = 1; i <= N; i++) {
                String trade = br.readLine();
                Arrays.fill(dp[i], -1);
                for (int j = 1; j <= N; j++) {
                    ledger[i][j] = trade.charAt(j - 1) - '0';
                }
            }
        }

        int result = solve(1, 0, 1,1);
        System.out.println(result);
    }

    private static int solve(int seller, int currentPrice, int count, int check) {

        // 모두 방문했을 경우
        if (check == checkVisit)
            return count;
        if (dp[seller][check] != -1)
            return dp[seller][check];

        dp[seller][check] = count;

        for (int consumer = 2; consumer <= N; consumer++) {
            // 판매자가 구매자와 다름 || 구매자가 이미 구매한 그림이 아님
            if (consumer == seller || isSold(consumer, check)) continue;

            // 판매가
            int soldPrice = ledger[seller][consumer];

            if (currentPrice <= soldPrice) {
                int newCheck = checkVisit(consumer, check);
                dp[seller][check] = Math.max(dp[seller][check], solve(consumer, soldPrice, count + 1, newCheck));
            }
        }


        return dp[seller][check];
    }

    private static boolean isSold(int consumer, int check) {
        return (check & (1 << (consumer - 1))) != 0;
    }

    private static int checkVisit(int consumer, int check) {
        return check | (1 << (consumer - 1));
    }
}