package dynamic_programming.gold2.christmas_tree;

/*
 * 문제.
 *  크리스마스 트리를 만들려고 한다.
 *  트리는 N 개의 레벨로 이뤄져 있다.
 *  또 장난감 색 빨강, 파랑, 초록이 있다.
 *  레벨 K 에는 딱 K 개의 장난감이 있어야 한다.
 *  각 레벨에 놓으려고 선택한 색이 있으면 그 색의 장난감의 수는 서로 같아야 한다.
 *  예)
 *   레벨 3
 *    (빨강: 2, 파랑 1) X => 빨강 수 != 파랑 수
 *   레벨 4
 *    (빨강: 2, 파랑 2) O => 빨강 수 == 파랑 수
 *
 */

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
    private static int solve(int lv, int r, int g, int b) {

        // LV 이 1이고
        if (lv == 1) {
            // 남은 장난감 수가 1 보다 많다면 그 수만큼 return
            return geCount(lv, r, g, b);
        }

        int result = 0;

        // a. 해당 lv 의 배치 개수 덩어리들을 구하고 다음 라운드로 보낸다.
        // b. 해당 lv 의 조합 가능한 경우의 수와 재귀 호출 결과를 곱한다.
        // c. a. 의 덩어리 개수만큼 b의 결과들을 합한다.
        for (int i = 1; i <= 3; i++) {
            int availableCount = 0;
            if (lv % i == 0) {

            }
        }

        return 0;
    }

    private static int geCount(int cnt, int r, int g, int b) {
        int result = 0;
        if (r >= cnt) result++;
        if (g >= cnt) result++;
        if (b >= cnt) result++;
        return result;
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
