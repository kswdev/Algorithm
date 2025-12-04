package implementation.gold._3.slope;


/*
 * 지도 크기 : N x N
 * 길 갯수 : 2xN
 *
 * 길을 지나갈 수 있는 조건
 *  1. 길에 속한 모든 칸의 높이가 모두 같아야 한다.
 *  2. 경사로를 놓아서 지나갈 수 있는 길을 만들 수 있다. *경사로는 높이가 항상 1이며 길이는 L이다.
 *  3. 개수는 많아 부족할 일이 없다.
 *  4. 경사로는 낮은 칸과 높은 칸을 연결하며 아래와 같은 조건을 만족해야 한다.
 *   a. 경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
 *   b. 낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
 *   c. 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
 *
 *  5. 아래와 같은 경우에는 경사로를 놓을 수 없다.
 *   a. 경사로를 놓은 곳에 또 경사로를 놓는 경우
 *   b. 낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
 *   c. 낮은 지점의 칸의 높이가 모두 같지 않거나 L개가 연속되지 않은 경우
 *   d. 경사로를 놓다가 범위를 벗어나는 경우

 *
 *  방에 정보가 주어졌을 때, T초가 지난 후 구사과의 방에 남아있는 미세먼지의 양을 구해보자
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {
    private static int N, L;
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new  StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new  StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private boolean isRowWalkable(int row) {
        int[] newMap = map[row].clone();

        for (int i = 1; i < N; i++) {
            if (newMap[i] == newMap[i-1]) continue;
            else if (Math.abs(newMap[i] - newMap[i-1]) > 1) return false;
        }
        return true;
    }
}
