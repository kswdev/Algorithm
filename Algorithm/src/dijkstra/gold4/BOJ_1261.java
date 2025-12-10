package dijkstra.gold4;

/*
 * 알고스팟 운영진 미로 갇힌 문제
 * 미로 크기 : N x M
 *
 * 조건.
 *  1. 미로는 빈 방 또는 벽으로 이루어져 있다.
 *  2. 빈 방은 자유롭게 다닐 수 있지만, 벽은 부수지 않으면 이동할 수 없다.
 *  3. 알고스팟 운영진은 여러명이지만, 항상 모두 같은 방에 있어야 한다
 *  4. 어떤 방에서 이동할 수 있는 방은 상하좌우로 인접한 빈 방이다.
 *  5. 벽은 평소에는 이동할 수 없지만, 알고스팟의 무기 AOJ를 이용해 벽을 부수어 버릴 수 있다. 벽을 부수면, 빈 방과 동일한 방으로 변한다.
 *
 * 문제.
 *  현재 (1, 1)에 있는 알고스팟 운영진이 (N, M)으로 이동하려면 벽을 최소 몇 개 부수어야 하는지 구하는 프로그램을 작성하시오.
 *
 * 입력.
 *  1. 첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다.
 *  2. 다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다.
 *     0 -> 빈 방
 *     1 -> 벽
 *
 * 출력.
 *  첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1261 {

    private static int N, M;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();

            for (int j = 1; j <= M; j++) {
                map[i][j] = line.charAt(j-1) - '0';
            }
        }

        System.out.println(minBreakCountAt());
    }

    private static int minBreakCountAt() {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.add(new int[] {1, 1, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            visited[curr[0]][curr[1]] = true;

            if (arrive(curr)) return curr[2];

            for (int i = 0; i < 4; i++) {
                int nextN = curr[0] + dx[i];
                int nextM = curr[1] + dy[i];

                if (nextM < 1 || nextM > M || nextN < 1 || nextN > N || visited[nextN][nextM]) continue;

                int cnt = map[nextN][nextM] + curr[2];

                queue.add(new int[] {nextN, nextM, cnt});
            }
        }

        return 0;
    }

    private static boolean arrive(int[] target) {
        return target[0] == N && target[1] == M;
    }
}
