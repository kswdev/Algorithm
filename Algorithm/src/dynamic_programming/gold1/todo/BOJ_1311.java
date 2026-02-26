package dynamic_programming.gold1.todo;

/*
    문제.
        사람: N (1 ≤ N ≤ 20)
        일 : N (1 ≤ N ≤ 20)

        각 사람은 일을 담당해야 한다.
        각 일은 한 사람이 해야 한다.
        사람은 1번부터 N번까지 번호가 매겨져 있으며,
        일도 1번부터 N번까지 번호가 매겨져 있다.

        Dij를 i번 사람이 j번 일을 할 때 필요한 비용(비용 <= 10,000)이라고 했을 때,
        모든 일을 하는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1311 {

    private static int N;
    private static int[][] work;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][(1 << N + 1)-1];
        work = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++)
                work[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(1, 0));
    }


    /*
        0 1 2 3
        1 2 3 3
        2 2 3 2
        3 3 4 1
     */

    // 파라미터 : 현재 방문한 사원, 이미 한 일 조합
    // dp : 현재 방문한 사원, 이미 한 일 조합 => 최소 비용
    private static int solve(int worker, int workHistory) {

        if (isWorkComplete(workHistory)) {
            return 0;
        }

        if (dp[worker][workHistory] != 0) {
            return dp[worker][workHistory];
        }

        int result = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            if (isWorkerAlreadyWork(i, workHistory)) continue;

            result = Math.min(result, solve(worker+1, workHistory | 1 << (i - 1)) + work[worker][i]);
        }

        return dp[worker][workHistory] = result;
    }

    private static boolean isWorkComplete(int workHistory) {
        return (workHistory) == (1 << N) - 1;
    }

    private static boolean isWorkerAlreadyWork(int worker, int workHistory) {
        return (workHistory & 1 << (worker-1)) != 0;
    }
}
