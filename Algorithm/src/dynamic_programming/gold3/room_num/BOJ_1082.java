package dynamic_programming.gold3.room_num;

/*
 *
 * 그림 교환 - https://www.acmicpc.net/problem/1082
 *
 * 문제
 *  숫자를 구매하기 위해 준비한 금액은 M원이다.
 *  문방구에서 파는 숫자는 0부터 N-1까지이다.
 *  각 숫자 i의 가격은 Pi이다.
 *  문방구에서는 같은 숫자를 여러 개 구매할 수 있고, 문방구는 매우 많은 재고를 보유하고 있기 때문에, 항상 원하는 만큼 숫자를 구매할 수 있다.
 *  방 번호가 0이 아니라면 0으로 시작할 수 없다.
 *
 * 입력
 *  첫째 줄에 N이 주아진다.
 *  둘째 줄에는 공백으로 구분된 P0, ..., PN-1이 주어진다.
 *  마지막 줄에는 M이 주어진다.
 *
 * 출력
 *  첫째 줄에 최대 M원을 사용해서 만들 수 있는 가장 큰 방 번호를 출력한다.
 *
 * 조건
 *  1 ≤ N ≤ 10
 *  1 ≤ Pi ≤ 50
 *  1 ≤ M ≤ 50
 *  N, Pi, M은 정수
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1082 {

    private static int N, M;
    private static int[] price;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
            price = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());
            dp = new int[M + 1];
            Arrays.fill(dp, -1);
        }
        System.out.println(maxRoomNum(M));
    }

    private static int maxRoomNum(int money) {

        if (dp[money] != -1) {
            return dp[money];
        }

        for (int room = 0; room < N; room++) {
            if (money >= price[room]) {
                int next = maxRoomNum(money - price[room]);

                if (next != -1) {
                    dp[money] = Math.max(dp[money], (room * 10) + next);
                }
            }
        }

        return dp[money];
    }
}
