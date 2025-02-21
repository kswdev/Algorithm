package bfs.gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17471 {

    private static int N;
    private static int min = Integer.MAX_VALUE;
    private static int[] popularity;
    private static boolean[] visited;
    private static List<Integer> blue, red = new ArrayList<>();
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        popularity = new int[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());

        for (int k = 1; k <= N; k++) {
            popularity[k] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            for (int j = 0; j < n; j++) {
                int closest = Integer.parseInt(st.nextToken());
                graph.get(i).add(closest);
            }
        }

        int num = minGerrymandering(1, 1);

        if (num == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(num);
        }
    }

    private static int minGerrymandering(int area, int depth) {

        //모든 요소를 확인했으면 재귀 호출을 그만둔다.
        if (depth == N) return min;

        //blue에 요소 하나를 넣고 나머지 요소를 red에 넣는다.
        blue.add(area);

        //조건 blue, red 각 선거구가 연결 되있는가?

        //두 선거구의 합 차이의 절댓값을 구한다.

        return 0;
    }
}
