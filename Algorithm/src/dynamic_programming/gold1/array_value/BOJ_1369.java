package dynamic_programming.gold1.array_value;

/*
 * 문제.
 *  가로 N칸, 세로 N칸으로 되어 있는 정사각형 모양의 배열이 있고, (2 ≤ N ≤ 1,000)
 *  배열의 각 칸에는 음이 아닌 정수가 하나씩 적혀져 있다. (정수 <= 1,000,000)
 *
 *  우리는 이 배열의 배열값을 다음과 같이 찾고자 한다.
 *  배열의 왼쪽 위 칸인 (1, 1)에서 오른쪽 아래 칸인 (N, N)으로의 이동을 생각할 수 있다.
 *  현재 위치의 아래 칸이나, 현재 위치의 오른쪽 칸으로만 이동할 수 있다.
 *  그 칸에 적혀져 있는 정수가 0인 경우에는 그 칸으로 이동할 수 없다.
 *
 *  이렇게 (1, 1)에서 (N, N)까지 이동하는 경로는 여러 가지가 있을 수 있다.
 *  우리는 이러한 각각의 경로에 대해서 경로값을 정의할 수 있는데,
 *  이동하면서 거쳐 온 2N-1개의 칸에 적혀 있는 모든 정수의 곱을 이 경로의 경로값으로 정의한다.
 *
 *  주어진 배열에서 (1, 1)부터 (N, N)까지 이동하는 경로는 매우 많을 수 있으므로, 경로값 또한 다양하게 나타날 수 있다.
 *  이러한 모든 경로값 중에서, 경로값을 나타내는 자연수의 끝자리 0의 개수가 가장 적을 때, 그 0의 개수를 이 배열의 배열값으로 정의한다.
 *
 *  배열이 주어지면 그 배열의 배열값을 계산하는 프로그램을 작성하시오.
 *  항상 (1, 1)에서 (N, N)으로 이동하는 경로가 존재하는 데이터만이 입력으로 들어온다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1369 {

    private static final int INF = 2000000000;
    private static int N;
    private static int[][] arr;
    private static int[][][] dp;
    private static int[][] measureDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][N+1][2];
        arr = new int[N+1][N+1];
        measureDp = new int[1000000+1][2];

        for (int i = 0; i <= 1000000; i++) {
            for (int j = 0; j < 2; j++) {
                measureDp[i][j] = -1;
            }
        }

        measureDp[2][0] = 1;
        measureDp[5][1] = 1;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(Math.min(solve(N, N)[0], solve(N, N)[1]));
    }

    // 파라미터: 좌표 x, y
    private static int[] solve(int i, int j) {

        if (arr[i][j] == 0)
            return new int[] {INF, INF};

        int twoMeasure = countNumInMeasure(arr[i][j], 0);
        int fiveMeasure = countNumInMeasure(arr[i][j], 1);

        if (i == 1 && j == 1) {
            dp[i][j][0] = twoMeasure;
            dp[i][j][1] = fiveMeasure;
            return dp[i][j];
        }

        int[] prevUp = new int[] {INF, INF};
        int[] prevLeft = new int[] {INF, INF};
        int[] result;

        if (i > 1)
            prevUp = solve(i-1, j);

        if (j > 1)
            prevLeft = solve(i, j-1);

        if (Math.min(prevUp[0] + twoMeasure, prevUp[1] + fiveMeasure) < Math.min(prevLeft[0] + twoMeasure, prevLeft[1] + fiveMeasure)) {
            result = new int[] {prevUp[0] + twoMeasure, prevUp[1] + fiveMeasure};
        } else {
            result = new int[] {prevLeft[0] + twoMeasure, prevLeft[1] + fiveMeasure};
        }

        return dp[i][j] = result;
    }

    // 파라미터: 정수, 2 || 5
    private static int countNumInMeasure(int num, int opt) {

        int measure = opt == 0 ? 2 : 5;

        if (measureDp[num][opt] != -1) return measureDp[num][opt];

        int count = 0;

        if (num % measure == 0) {
            count = 1 + countNumInMeasure(num/measure, opt);
        }

        return measureDp[num][opt] = count;
    }
}
