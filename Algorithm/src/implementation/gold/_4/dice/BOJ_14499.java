package implementation.gold._4.dice;

/*
 *
 * 지도 크기 : NxM
 *
 * 주사위 전개도 :
 *      2
 *  4   1   3
 *      5
 *      6
 *
 * 초기 주사위 상태 : 윗면이 1 동쪽을 바라보는 방향이 3
 *
 * 동, 서, 북, 남 : 1, 2, 3, 4
 */

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_14499 {

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int commandNum = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        Dice dice = new Dice(x, y, map);

        for (int i = 0; i < commandNum; i++) {
            int command = Integer.parseInt(st.nextToken());
            int result = dice.move(command);

            if (result != -1) bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static class Dice {
        private int[] num = {0, 0, 0, 0, 0, 0, 0};
        private int[] dx = {0, 0, 0, -1, 1};
        private int[] dy = {0, 1, -1, 0, 0};

        private int x, y;
        private int[][] map;

        public Dice(int x, int y, int[][] map) {
            this.x = x;
            this.y = y;
            this.map = map;
        }

        private int move(int dir) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) return -1;

            x = nx;
            y = ny;

            roll(dir);

            if (map[nx][ny] == 0)
                map[nx][ny] = num[6];
            else {
                num[6] = map[nx][ny];
                map[nx][ny] = 0;
            }

            return num[1];
        }

        private void roll(int dir) {
            num[0] = num[1];

            switch (dir) {
                case 1: {
                    num[1] = num[4];
                    num[4] = num[6];
                    num[6] = num[3];
                    num[3] = num[0];
                    break;
                }
                case 2: {
                    num[1] = num[3];
                    num[3] = num[6];
                    num[6] = num[4];
                    num[4] = num[0];
                    break;
                }
                case 3: {
                    num[1] = num[5];
                    num[5] = num[6];
                    num[6] = num[2];
                    num[2] = num[0];
                    break;
                }
                case 4: {
                    num[1] = num[2];
                    num[2] = num[6];
                    num[6] = num[5];
                    num[5] = num[0];
                    break;
                }
                default: throw new IllegalArgumentException();
            }
        }
    }
}