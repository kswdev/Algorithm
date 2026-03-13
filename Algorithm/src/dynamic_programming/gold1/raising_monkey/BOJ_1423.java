package dynamic_programming.gold1.raising_monkey;

/*
    문제.
        해빈이는 가지고 있는 캐릭터를 D일동안 훈련시키려고 한다.
        캐릭터의 레벨은 1보다 크거나 같고, N보다 작거나 같은 정수이다.
        해빈이는 하루에 한 캐릭터를 고를 수 있다.
        캐릭터를 하루동안 훈련시키면, 그 캐릭터의 레벨이 1 오른다.
        꼭 캐릭터를 고를 필요는 없다. 하루는 게임을 하지 않고 그냥 쉬어도 된다.

        해빈이가 가지고 있는 캐릭터의 수가 각 레벨 별로 주어진다.
        이때, 적절히 훈련을 시켜서 캐릭터의 힘의 합을 최대로 만드는 프로그램을 작성하시오.

    입력.
        첫째 줄에 캐릭터의 최대 레벨 N(1 ≤ N ≤ 50)이 주어진다.
        둘째 줄에는 해빈이가 가지고 있는 캐릭터의 수가 각 레벨 별로 주어진다.
        셋째 줄에는 캐릭터의 힘이 각 레벨 별로 주어진다.
        각 레벨 별 캐릭터의 수와 힘은 1,000,000보다 작거나 같은 음이 아닌 정수이다.
        넷째 줄에는 훈련시킬 수 있는 기간인 D (1 ≤ D ≤ 100)가 주어진다.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1423 {

    private static int[] characterCount;
    private static int[] powerPerLevel;
    private static long[][][] dp;
    private static long initValue = 0;
    private static int N, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        characterCount = new int[N+1];
        powerPerLevel = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            characterCount[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            powerPerLevel[i] = Integer.parseInt(st.nextToken());
        }

        D = Integer.parseInt(br.readLine());
        dp = new long[D+1][N+1][101];

        for (int i = 1; i <= N; i++) {
            initValue += (long) powerPerLevel[i] * characterCount[i];
        }

        for (int i = 0; i <= D; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        System.out.println(solve(0, 1, 0));
    }

    private static long solve(int day, int level, int count) {
        if (level == N) return initValue;
        if (dp[day][level][count] != -1) {
            return dp[day][level][count];
        }

        long result = 0;

        for (int i = 0; i <= characterCount[level] + count; i++) {
            if (day + i > D) break;
            result = Math.max(result, solve(day+i, level+1, i) + ((long) powerPerLevel[level + 1] * i) - ((long) powerPerLevel[level] * i));
        }

        return dp[day][level][count] = result;
    }
}