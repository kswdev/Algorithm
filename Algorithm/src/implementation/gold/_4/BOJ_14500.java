package implementation.gold._4;

/*
 * 폴리오미노 : 1x1 크기 정사각형을 여러 개 이어서 붙인 도형
 * 조건
 *  가. 정사각형은 겹치면 안된다.
 *  나. 도형은 모두 연결되어있어야 한다.
 *  다. 정사각형의 변끼리 연결되어 있어야 한다.
 *
 * 테트로미노 : 정사각형 4개를 이어 붙인 폴리오미노
 * 문제
 *  N * M 종이 위에 숫자가 쓰여있고
 *  테트로미노를 놓아 놓인 칸에 수들의 최대합
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int N, M;
    private static int max = 0;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            StringTokenizer row = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(row.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, 4);
                other(i, j);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void other(int x, int y) {
        int x1 = x - 1;
        int y1 = y - 1;
        int x2 = x + 1;
        int y2 = y + 1;

        int result = 0;

        if (x1 >= 0 && y1 >= 0 && y2 < M)
            result = Math.max(map[x][y1] + map[x][y] + map[x][y2] + map[x1][y], result);

        if (x2 < N && y1 >= 0 && y2 < M)
            result = Math.max(map[x][y1] + map[x][y] + map[x][y2] + map[x2][y], result);

        if (x1 >= 0 && x2 < N && y2 < M)
            result = Math.max(map[x1][y] + map[x][y] + map[x2][y] + map[x][y2], result);

        if (x1 >= 0 && y1 >= 0 && x2 < N)
            result = Math.max(map[x1][y] + map[x][y] + map[x2][y] + map[x][y1], result);


        max = Math.max(result, max);
    }

    private static void dfs(int x, int y, int count, int depth) {
        if (depth == 0) {
            max = Math.max(max, count);
            return;
        }

        count += map[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, count, depth-1);
            visited[nx][ny] = false;
        }
    }
}