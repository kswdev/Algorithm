package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352 {

    //도시 개수, 도로 개수, 도달해야 하는 거리, 출발 도시 번호
    private static int N, M, K, X;
    private static boolean[] visited;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static Queue<Integer> dist = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
        }

        searchCityAtDistance(X);

        if (dist.isEmpty()) System.out.println(-1);
        else
            while(!dist.isEmpty()) System.out.println(dist.poll());
    }

    private static void searchCityAtDistance(int start) {

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.add(new int[] {start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int currentCity = cur[0];
            int currentDistance = cur[1];

            if (currentDistance == K) {
                dist.add(currentCity);
                continue;
            }

            for (int targetCity : graph.get(currentCity)) {

                if (visited[targetCity]) continue;

                visited[targetCity] = true;

                queue.add(new int[] {targetCity, currentDistance + 1});
            }
        }
    }
}
