package dijkstra.gold4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import static java.util.Comparator.comparingInt;

public class BOJ_1504 {

    private static boolean[] visited;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeNum = Integer.parseInt(st.nextToken());
        int edgeNum = Integer.parseInt(st.nextToken());

        int[][] graph = new int[nodeNum+1][nodeNum+1];

        visited = new boolean[nodeNum+1];
        dist = new int[nodeNum+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 1; i <= nodeNum; i++) {
            for (int j = 1; j <= nodeNum; j++) {
                if (i == j) graph[i][j] = 0;
                else graph[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start][end] = weight;
            graph[end][start] = weight;
        }

        st = new StringTokenizer(br.readLine());

        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        int essentialDistance = minDistance(graph, startNode, endNode);
        //System.out.println(essentialDistance);
    }

    private static int minDistance(int[][] graph, int startNode, int endNode) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt(a -> a[1]));

        pq.offer(new int[] {startNode, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentEndNode = curr[0];
            int currentWeight = curr[1];

            if (visited[currentEndNode]) continue;

            visited[currentEndNode] = true;

            for (int i = 1; i <= graph.length-1; i++) {
                // 간선으로 연결 되어 있을 때
                if (graph[currentEndNode][i] != Integer.MAX_VALUE) {
                    // 방문 전
                    int startToINodeWeight = graph[currentEndNode][i] + currentWeight;

                    if (!visited[i] && dist[i] > startToINodeWeight) {

                        System.out.println(currentEndNode);

                        dist[i] = startToINodeWeight;
                        pq.offer(new int[]{i, startToINodeWeight});
                    }
                }
            }
        }

        return dist[endNode];
    }
}
