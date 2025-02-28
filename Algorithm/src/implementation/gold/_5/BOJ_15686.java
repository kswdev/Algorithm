package implementation.gold._5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 크기 : N*N
 * 치킨집의 갯수 : M
 * 요소(r, c) : 빈 칸(0), 치킨집(2), 집(1)

 * 치킨 거리 : 집 ~ 가장 가까운 치킨집 거리
 * 도시의 치킨 거리 : 모든 집의 치킨 거리 합
 * 거리 구하는 공식 |r1 - r2| + |c1 - c2|

 * 문제 : M개의 치킨집을 제외하고 모두 폐업시킬 때 도시의 치킨 거리가 가장 작게되는 프로그램 구현
 */


public class BOJ_15686 {

    private static int N, M;
    private static int MIN = Integer.MAX_VALUE;

    private static int[][] city;
    private static boolean[] open;

    private static List<int []> homes = new ArrayList<>();
    private static List<int []> chickens = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) homes.add(new int[] {i, j});
                if (city[i][j] == 2) chickens.add(new int[] {i, j});
            }
        }

        open = new boolean[chickens.size() + 1];

        comb(0, 0);

        System.out.println(MIN);
    }


    private static void comb(int start, int depth) {
        if (M == depth) {
            MIN = Math.min(MIN, chickenStreetLengthInCity());
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            open[i] = true;
            comb(i + 1, depth + 1);
            open[i] = false;
        }
    }

    private static int chickenStreetLengthInCity() {
        int sum = 0;

        for (int[] home: homes) {
            if (sum >= MIN) return Integer.MAX_VALUE;
            sum += chickenStreetClosestLength(home[0], home[1]);
        }

        return sum;
    }

    private static int chickenStreetClosestLength(int i, int j) {
        int length = Integer.MAX_VALUE;

        for (int k = 0; k < chickens.size(); k++) {
            if (open[k]) {
                int[] chicken = chickens.get(k);
                length = Math.min(length, chickenStreetLength(i, j, chicken[0], chicken[1]));
            }
        }

        return length;
    }

    private static int chickenStreetLength(int i, int j, int k, int l) {
        return Math.abs(i - k) + Math.abs(j - l);
    }
}