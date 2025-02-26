package bfs.gold._3.gerrymandering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17471 {

    private static int N;
    private static int min = Integer.MAX_VALUE;
    private static int[] popularity;
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        popularity = new int[N+1];

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

        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 1; i <= N / 2; i++) {
            combination(1, N, i, A); // 조합을 통한 지역 분리.
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    public static void combination(int start, int areaNumber, int depth, List<Integer> A) {
        if (depth == 0) {
            gerrymandering(A);
            return;
        }

        for (int i = start; i <= areaNumber; i++) {
            A.add(i);
            combination(i + 1, areaNumber, depth - 1, A);
            A.remove(A.size() - 1);
        }
    }

    public static void gerrymandering(List<Integer> A) {
        if(!isConnect(A.get(0), A, A.size())) {
            return;
        }

        ArrayList<Integer> B = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (A.contains(i)) {
                continue;
            }
            B.add(i);
        }

        if(!isConnect(B.get(0), B, B.size())) {
            return;
        }

        int resultA = 0;
        int resultB = 0;

        for (Integer value : A) {
            resultA += popularity[value];
        }

        // B 지역구 인구 계산
        for (Integer integer : B) {
            resultB += popularity[integer];
        }

        int result = Math.abs(resultA - resultB);
        min = Math.min(min, result);
    }

    public static boolean isConnect(int num, List<Integer> arr, int size) {
        boolean[] visited = new boolean[N + 1];
        visited[num] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);

        int count = 1;
        while (!q.isEmpty()) {
            int start = q.poll();

            for (int i : graph.get(start)) {
                if (!visited[i] && arr.contains(i)) {
                    visited[i] = true;
                    count++;
                    q.offer(i);
                }
            }
        }

        return count == size;
    }
}
