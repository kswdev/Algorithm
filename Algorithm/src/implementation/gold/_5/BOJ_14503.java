package implementation.gold._5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {

    private static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int[][] map;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());

        int robotX = Integer.parseInt(st.nextToken());
        int robotY = Integer.parseInt(st.nextToken());
        int robotDir = Integer.parseInt(st.nextToken());

        Robot robot = new Robot(robotX, robotY, robotDir);

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        robot.execute();
        System.out.println(robot.count);
    }

    private static class Robot {
        int x, y;
        int dir;
        boolean[][] visited;
        int count = 1;

        private Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            visited = new boolean[N][M];
            visited[x][y] = true;
        }

        private void execute() {
            if (clean()) {
                execute();
            } else {
                if (isPossibleBackward()) {
                    backward();
                    execute();
                }
            }
        }

        private boolean clean() {
            for (int i = 1; i <= 4; i++) {
                int TempDir = (4 + (dir - i)) % 4;
                int[] direction = directions[TempDir];
                int nx = x + direction[0];
                int ny = y + direction[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if (!visited[nx][ny] && map[nx][ny] != 1) {
                    dir = TempDir;
                    visited[nx][ny] = true;
                    count++;
                    x = nx;
                    y = ny;

                    return true;
                }
            }

            return false;
        }

        private void backward() {
            x += directions[dir][0] * -1;
            y += directions[dir][1] * -1;
        }

        private boolean isPossibleBackward() {
            int bx = x + directions[dir][0] * -1;
            int by = y + directions[dir][1] * -1;

            return bx >= 0 && by >= 0 && bx < N && by < M && map[bx][by] != 1;
        }
    }
}


/*
 *
 * 로봇 청소기가 방의 상태가 주어졌을 대, 청소하는 영역의 개수
 *
 * 방 NxM 벽 or 빈칸
 * 청소기 : 동 서 남 북 방향
 * 처음 빈 칸 : 청소되지 않은 상태
 *
 * 청소기
 *  1. 청소되지 않은 칸 청소
 *  2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
 *      가. 바라보는 방향을 유지한 채 한 칸 후진할 수 있다면 후진 후 1번으로 돌아감
 *      나. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동x
 *  3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
 *      가. 반시계 방향으로 90 회전
 *      나. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
 *      다. 1번으로 돌아감
 *
 */

/*
4 6
1 2 3
1 1 1 1 1 1
1 0 0 0 1 1
1 0 1 0 0 1
1 1 1 1 1 1

=> 4

5 4
1 2 1
1 1 1 1
1 0 0 1
1 0 0 1
1 0 0 1
1 1 1 1

=> 6
 */