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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeNum = Integer.parseInt(st.nextToken());
        int edgeNum = Integer.parseInt(st.nextToken());

        int[][] graph = new int[nodeNum+1][nodeNum+1];


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

        int temp = Integer.MAX_VALUE;

        for (int i = 1; i <= nodeNum; i++) {
            if(graph[i][startNode] == Integer.MAX_VALUE) continue;

            for (int j = 1; j <= nodeNum; j++) {
                if (i == j || graph[endNode][j] == Integer.MAX_VALUE) continue;

                visited = new boolean[nodeNum+1];
                visited[i] = true;
                visited[j] = true;

                int essentialDistance = shortestDistance(graph, startNode, endNode);

                if (essentialDistance == Integer.MAX_VALUE) continue;

                temp = Math.min(temp, graph[i][startNode] +  essentialDistance + graph[endNode][j]);
            }
        }

        if (temp == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(temp);
    }

    private static int shortestDistance(int[][] graph, int startNode, int endNode) {
        int[] dist = new int[graph.length+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt(a -> a[1]));

        pq.offer(new int[] {startNode, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currentEndNode = curr[0];
            int currentWeight = curr[1];

            if (visited[currentEndNode]) continue;

            visited[currentEndNode] = true;

            for (int i = 1; i < graph.length; i++) {
                // 간선으로 연결 되어 있을 때
                if (graph[currentEndNode][i] != Integer.MAX_VALUE) {
                    int startToINodeWeight = graph[currentEndNode][i] + currentWeight;
                    // 방문 전 && 거리가 더 짧을 때
                    if (!visited[i] && dist[i] > startToINodeWeight) {
                        dist[i] = startToINodeWeight;
                        pq.offer(new int[]{i, startToINodeWeight});
                    }
                }
            }
        }
        return dist[endNode];
    }
}
