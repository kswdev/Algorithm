package dynamic_programming.gold2.game;

/*
 *
 * 게임 - https://www.acmicpc.net/problem/1103
 *
 * 문제
 *  형택이는 1부터 9까지의 숫자와, 구멍이 있는 직사각형 보드에서 재밌는 게임을 한다.
 *  일단 보드의 가장 왼쪽 위에 동전을 하나 올려놓는다. 그다음에 다음과 같이 동전을 움직인다.
 *   1. 동전이 있는 곳에 쓰여 있는 숫자 X를 본다.
 *   2. 위, 아래, 왼쪽, 오른쪽 방향 중에 한가지를 고른다.
 *   3. 동전을 위에서 고른 방향으로 X만큼 움직인다. 이때, 중간에 있는 구멍은 무시한다.
 *
 * 입력
 *  줄에 보드의 세로 크기 N과 가로 크기 M이 주어진다.
 *   (*조건*)이 값은 모두 50보다 작거나 같은 자연수이다.
 *  둘째 줄부터 N개의 줄에 보드의 상태가 주어진다.
 *   쓰여 있는 숫자는 1부터 9까지의 자연수 또는 H이다. 가장 왼쪽 위칸은 H가 아니다. H는 구멍이다.
 *   가장 왼쪽 위칸은 H가 아니다. H는 구멍이다.
 *
 * 출력
 *  첫째 줄에 그 그림을 소유 했던 사람들 (잠시라도 소유했던 사람도 포함)의 최댓값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1103 {

    private static int INF;
    private static int N, M;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int[][] board, dp;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            INF = N * M;

            dp = new int[N][M];
            board = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                String row = br.readLine();
                Arrays.fill(dp[i], -1);
                for (int j = 0; j < M; j++) {
                    board[i][j] = row.charAt(j) - '0';
                }
            }
        }

        int result = maxTime(0, 0);

        if (result > INF)
            System.out.println("-1");
        else
            System.out.println(result);
    }

    // top down - 메모리 초과
    private static int maxTime(int x, int y) {

        if (isHole(x, y))
            return 0;

        if (visited[x][y])
            return INF;

        visited[x][y] = true;

        if (dp[x][y] != -1)
            return dp[x][y];

        int dist = board[x][y];
        int result = 0;

        for (int i = 0; i < 4; i++) {
            int dirX = dx[i];
            int dirY = dy[i];

            int nextX = (dirX * dist) + x;
            int nextY = (dirY * dist) + y;

            if (isEnd(nextX, nextY)) {
                result = Math.max(result, 1);
                continue;
            }

            result = Math.max(result, maxTime(nextX, nextY) + 1);
            visited[nextX][nextY] = false;
        }

        return dp[x][y] = result;
    }

    private static boolean isEnd(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }

    private static boolean isHole(int x, int y) {
        return board[x][y] == 24;
    }
}
